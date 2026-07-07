package top.annieholo.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.annieholo.pojo.Dept;
import top.annieholo.pojo.Result;
import top.annieholo.service.DeptService;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping()
    public Result list(){
        // System.out.println("查询全部的部门数据");
        // deptService.findAll();
        log.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();

        return Result.success(deptList);
    }

    /**
     * 第一种接收参数方式（不推荐）
     * @param request
     * @return
     */
    // @DeleteMapping("/depts")
    // public Result delete(HttpServletRequest request){
    //     String idStr = request.getParameter("id");
    //     int id = Integer.parseInt(idStr);
    //     System.out.println("根据ID删除部门：" + id);
    //     return Result.success();
    // }
    /**
     * 第二种接收参数方式@requestParams
     * 注意@RequstParams注解required属性默认为true
     * @param deptId
     * @return
     */
    // @DeleteMapping("/depts")
    // public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
    //     System.out.println("根据ID删除部门：" + deptId);
    //     return Result.success();
    // }

    /**
     * 第三种接收参数方式 前端请求字段和形参一样，省略@RequestParams，必填默认为false（推荐）
     * @param id
     * @return
     */
    @DeleteMapping()
    public Result delete(Integer id){
        // System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门：{}", id);

        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping()
    public Result add(@RequestBody Dept dept){
        // System.out.println("传入的参数:" + dept);
        log.info("新增部门传入的参数：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    // @GetMapping("/depts/{id}")
    // public Result get(@PathVariable("id") Integer deptId){
    //     Dept dept = deptService.getById(deptId);
    //     return Result.success(dept);
    // }


    /**
     * 如果请求字段名和形参一致，可以省略@PathVariable后面的("id")
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping()
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.success();
    }

}
