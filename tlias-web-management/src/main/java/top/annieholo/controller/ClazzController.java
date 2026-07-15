package top.annieholo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.annieholo.pojo.Clazz;
import top.annieholo.pojo.ClazzQueryDTO;
import top.annieholo.pojo.PageResult;
import top.annieholo.pojo.Result;
import top.annieholo.service.ClazzService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    // Spring 容器会查找一个 ClazzService 类型的 Bean，并赋值给 clazzService，不需要手动创建 ClazzService clazzService = new ClazzServiceImpl()
    @Autowired
    ClazzService clazzService;

    /**
     * 查询列表
     * @param clazzQueryDTO
     * @return
     */
    @GetMapping
    public Result getList(ClazzQueryDTO clazzQueryDTO){
        PageResult<Clazz> pageResult = clazzService.getList(clazzQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 获取所有班级列表
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }

    /**
     * 新增班级
     * @param clazz
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        clazzService.add(clazz);
        return Result.success();
    }
    // 查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询班级详情: {}",id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 更新班级
     * @param clazz
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        clazzService.deleteById(id);
        return Result.success();
    }
}
