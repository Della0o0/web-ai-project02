package top.annieholo.service;

import top.annieholo.pojo.Emp;
import top.annieholo.pojo.EmpQueryParam;
import top.annieholo.pojo.JobOption;
import top.annieholo.pojo.PageResult;

import java.util.List;

public interface EmpService {
    List<Emp> getList(EmpQueryParam empQueryParam);

    // PageResult<Emp> page(Integer page, Integer pageSize,
    //                      String name, // 姓名
    //                      Integer gender, // 性别
    //                      LocalDate begin, // 入职日期起
    //                      LocalDate end // 入职日期止
    // );

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    void realDelete(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

}
