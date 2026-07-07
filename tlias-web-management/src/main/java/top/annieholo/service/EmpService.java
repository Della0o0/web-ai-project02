package top.annieholo.service;

import org.springframework.format.annotation.DateTimeFormat;
import top.annieholo.pojo.Emp;
import top.annieholo.pojo.EmpQueryParam;
import top.annieholo.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // List<Emp> findAll();

    // PageResult<Emp> page(Integer page, Integer pageSize,
    //                      String name, // 姓名
    //                      Integer gender, // 性别
    //                      LocalDate begin, // 入职日期起
    //                      LocalDate end // 入职日期止
    // );

    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
