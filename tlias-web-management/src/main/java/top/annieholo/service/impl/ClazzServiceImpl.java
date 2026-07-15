package top.annieholo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.annieholo.mapper.ClazzMapper;
import top.annieholo.pojo.Clazz;
import top.annieholo.pojo.ClazzQueryDTO;
import top.annieholo.pojo.PageResult;
import top.annieholo.service.ClazzService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    ClazzMapper clazzMapper;

    /**
     * 获取班级列表
     * PageHelper.startPage() 是 PageHelper 分页插件的核心方法，用于设置接下来一次查询的页码和每页条数。
     *
     * @param clazzQueryDTO
     * @return
     */
    @Override
    public PageResult<Clazz> getList(ClazzQueryDTO clazzQueryDTO) {
        // clazzMapper.
        PageHelper.startPage(clazzQueryDTO.getPage(), clazzQueryDTO.getPageSize());
        List<Clazz> list = clazzMapper.list(clazzQueryDTO);
        // 设置结课状态
        LocalDate today = LocalDate.now();
        for(Clazz clazz : list){
            if(today.isBefore(clazz.getBeginDate())){
                clazz.setStatus("未开班");
            } else if(today.isAfter(clazz.getEndDate())){
                clazz.setStatus("已结课");
            } else {
                clazz.setStatus("在读中");
            }
        }
        PageInfo<Clazz> clazzPageInfo = new PageInfo<>(list);
        return new PageResult<Clazz>(clazzPageInfo.getTotal(), clazzPageInfo.getList());
    }

    @Override
    public List<Clazz> list() {
        return clazzMapper.listAll();
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        return clazz;
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);

    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }
}
