package top.annieholo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.annieholo.pojo.Emp;
import top.annieholo.pojo.LoginInfo;
import top.annieholo.pojo.Result;
import top.annieholo.service.EmpService;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登陆参数：{}", emp);
        LoginInfo info = empService.login(emp);
        log.info("查找用户：{}", emp);
        if(info != null){
            return Result.success(info);
        }
        return Result.error("用户或密码错误～");
    }
}
