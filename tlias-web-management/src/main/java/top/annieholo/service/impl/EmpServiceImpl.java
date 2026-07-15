package top.annieholo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.annieholo.mapper.EmpExprMapper;
import top.annieholo.mapper.EmpMapper;
import top.annieholo.pojo.*;
import top.annieholo.service.EmpService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;
    // @Override
    // public List<Emp> findAll() {
    //     return empMapper.findAll();
    // }

    // @Override
    // public PageResult<Emp> page(Integer page, Integer pageSize,
    //                             String name, // 姓名
    //                             Integer gender, // 性别
    //                             LocalDate begin, // 入职日期起
    //                             LocalDate end // 入职日期止
    //                             ) {
    //                             /**
    //      * 使用传递参数方法 原始获取
    //      */
    //                             // 获取总数
    //                             // Long count = empMapper.count();
    //                             // Integer start = (page - 1) * pageSize;
    //                             // List<Emp> list = empMapper.list(start, pageSize);
    //                             // log.info("page, start, pageSize: {},{},{}", page,start,pageSize);
    //                             // // PageResult<Emp> pageResult = new PageResult<>();
    //                             // // pageResult.setRows(list);
    //                             // // pageResult.setTotal(count);
    //                             // // log.info("pageResult", pageResult);
    //                             // // return pageResult;
    //                             // return new PageResult<Emp>(count, list);
    //
    //                             /**
    //                              * 使用PageHelper分页查询
    //                              */
    //     PageHelper.startPage(page, pageSize);
    //     List<Emp> list = empMapper.list(name, gender, begin, end);
    //     PageInfo<Emp> empPageInfo = new PageInfo<Emp>(list);
    //     return new PageResult<Emp>(empPageInfo.getTotal(), empPageInfo.getList());
    // }

    @Override
    public List<Emp> getList(EmpQueryParam empQueryParam) {
        List<Emp> list = empMapper.list(empQueryParam);
        return list;
    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> list = empMapper.list(empQueryParam);
        // 表示把查询得到的员工列表 list 包装成 PageHelper 提供的分页信息对象。
        // pageInfo.getList();      // 当前页的数据
        // pageInfo.getTotal();     // 总记录数
        // pageInfo.getPageNum();   // 当前页码
        // pageInfo.getPageSize();  // 每页数量
        // pageInfo.getPages();     // 总页数
        // pageInfo.isHasNextPage();// 是否有下一页
        // pageInfo.isHasPreviousPage(); // 是否有上一页
        PageInfo<Emp> empPageInfo = new PageInfo<Emp>(list);
        return new PageResult<Emp>(empPageInfo.getTotal(), empPageInfo.getList());
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED) // 事务管理
    @Override
    public void save(Emp emp) {
        // 1、新增员工信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        log.info("新增员工返回的主键id：{}", emp.getId());

        // int i = 1/0;
        // 2、新增员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    /**
     * 删除员工（逻辑删除）
     * @param ids
     */
    @Override
    public void delete(List<Integer> ids) {
        // 删除员工（逻辑update删除）
        empMapper.delete(ids);
    }

    /**
     * 删除员工（真实删除）
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void realDelete(List<Integer> ids) {
        // 删除员工（delete删除）
        empMapper.realDelete(ids);
        // 删除员工经历
        empMapper.deleteExpByEmpId(ids);
    }

    @Override
    public Emp getById(Integer id) {

        // 获取员工经历list
        Emp emp = empMapper.getById(id);

        List<EmpExpr> exprList = empMapper.getExpById(id);
        emp.setExprList(exprList);
        return emp;
    }

    /**
     * 更新员工信息
     * @param emp
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        // 1、更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        // 2、更新员工经历信息
        updateEmpExprList(emp.getId(), emp.getExprList());
    }

    /**
     * 更新员工经历信息
     * @param empId
     * @param exprList
     */
    private void updateEmpExprList(Integer empId, List<EmpExpr> exprList) {
        // 1. 查询数据库中已有的经历 id
        List<Integer> oldEmpExprIds = empExprMapper.listIdsByEmpId(empId);
        log.info("------查询数据库中已有的经历oldEmpExprIds：{}", oldEmpExprIds);
        // 2. 获取前端传来的已有经历 id
        // exprList.stream(): 把 exprList 转成流，方便链式处理。
        List<Integer> newIds = exprList == null ? new ArrayList<>() :
                exprList.stream()
                        .map(EmpExpr::getId)
                        .filter(Objects::nonNull)
                        .toList();
        log.info("------获取前端传来的已有经历newIds：{}", newIds);
        // 3. 删除数据库有、前端没传的经历
        List<Integer> deleteIds = oldEmpExprIds.stream().filter(id -> !newIds.contains(id)).toList();
        log.info("------删除数据库有、前端没传的经历deleteIds：{}", deleteIds);
        if(!deleteIds.isEmpty()){
            empExprMapper.deleteByIds(deleteIds);
        }
        // 4. 新增或更新前端传来的经历
        if(!CollectionUtils.isEmpty(exprList)){
            for (EmpExpr empExpr : exprList){
                empExpr.setEmpId(empId);
                if(empExpr.getId() == null){
                    empExprMapper.insert(empExpr);
                }else{
                    empExprMapper.update(empExpr);
                }
            }
        }
    }
}
