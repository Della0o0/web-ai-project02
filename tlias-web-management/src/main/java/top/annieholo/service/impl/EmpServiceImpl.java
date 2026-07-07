package top.annieholo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import top.annieholo.mapper.EmpMapper;
import top.annieholo.pojo.Emp;
import top.annieholo.pojo.EmpQueryParam;
import top.annieholo.pojo.PageResult;
import top.annieholo.service.EmpService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
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
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> list = empMapper.list(empQueryParam);
        PageInfo<Emp> empPageInfo = new PageInfo<Emp>(list);
        return new PageResult<Emp>(empPageInfo.getTotal(), empPageInfo.getList());
    }
}
