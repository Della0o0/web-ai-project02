package top.annieholo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.annieholo.pojo.PageResult;
import top.annieholo.pojo.Result;
import top.annieholo.pojo.Student;
import top.annieholo.pojo.StudentQueryParam;
import top.annieholo.service.StudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生列表
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询学生参数：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增学生
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学生参数：{}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 批量删除学生，单个删除也传一个 id
     */
    @DeleteMapping("/{ids}")
    // public Result delete(@RequestParam List<Integer> ids) {
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学生ids：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 根据ID查询学生
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询学生详情：{}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 修改学生
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生参数：{}", student);
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable("id") Integer id, @PathVariable("score") Integer score){
        log.info("违纪扣分：{}, {}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
