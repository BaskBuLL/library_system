package com.baskbull.library_system.common.lang.exception;

import com.baskbull.library_system.common.lang.Result;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

import javax.validation.UnexpectedTypeException;
import javax.validation.ValidationException;
import java.util.List;

/**
 * 全局异常处理
 * @author baskbull
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e){
        log.error("Assert异常:----------------{}",e);
        return Result.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public Result handler(ValidationException e){
        log.error("实体检验异常:----------------{}",e);
        return Result.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public Result handler(MethodArgumentNotValidException e){
        log.error("实体检验异常:----------------{}",e);
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder errorMessage = new StringBuilder();

        /*
        for(int i = 0;i < errors.size();i++){
            ObjectError error = errors.get(i);
            errorMessage.append(error.getDefaultMessage());
            if(i != errors.size()-1){
                errorMessage.append(',');
            }
        }
        return Result.error(errorMessage.toString());
         */

        ObjectError error = errors.stream().findFirst().get();
        return Result.error(error.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e){
        log.error("运行时异常:----------------{}",e);
        return Result.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e){
        log.error("没有权限，请联系管理员:-----------{}",e);
        return Result.error(401,e.getMessage(),null);
    }
}
