package top.annieholo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.annieholo.pojo.Result;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错了", e);
        return Result.error("出错了，请联系工作人员。");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错啦，字段值重复");
        // java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '13271111111' for key 'emp.phone'
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] strings = errMsg.split(" ");
        return Result.error(strings[2] + "已存在");
    }
}
