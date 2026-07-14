package top.annieholo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import top.annieholo.pojo.Result;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Result> handleException(Exception e){
        log.error("程序出错了", e);
        // return Result.error("出错了，请联系工作人员。");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error("出错了，请联系工作人员。"));
    }

    @ExceptionHandler
    public ResponseEntity<Result> handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错啦，字段值重复");
        // java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '13271111111' for key 'emp.phone'
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] strings = errMsg.split(" ");
        // return Result.error(strings[2] + "已存在");
        return ResponseEntity.status(HttpStatus.OK).body(Result.error(strings[2] + "已存在"));
    }

    @ExceptionHandler
    public ResponseEntity<Result> handleNoResourceFoundException(NoResourceFoundException e){
        log.error("程序出错啦，找不到接口");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Result.error("接口不存在"));
    }

}
