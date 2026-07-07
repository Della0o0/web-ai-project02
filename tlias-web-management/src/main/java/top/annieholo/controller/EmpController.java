package top.annieholo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.annieholo.pojo.Emp;
import top.annieholo.pojo.EmpQueryParam;
import top.annieholo.pojo.PageResult;
import top.annieholo.pojo.Result;
import top.annieholo.service.EmpService;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理的Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    // @GetMapping
    // public Result list(){
    //     List<Emp> empList = empService.findAll();
    //     return Result.success(empList);
    // }

    @GetMapping
    // public Result page(
    //         @RequestParam(defaultValue = "1") Integer page,
    //         @RequestParam(defaultValue = "10") Integer pageSize,
    //         String name, // 姓名
    //         Integer gender, // 性别
    //         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, // 入职日期起
    //         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end // 入职日期止
    // ){
    //     log.info("分页查询参数：{}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
    //     PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
    //     return Result.success(pageResult);
    // }

    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询参数：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

}
