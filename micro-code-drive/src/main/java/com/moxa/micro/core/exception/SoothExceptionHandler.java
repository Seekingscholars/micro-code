package com.moxa.micro.core.exception;

import com.moxa.dream.template.validate.ValidateDreamRunTimeException;
import com.moxa.micro.core.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SoothExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(SoothException.class)
    public Result<?> handleMicroException(SoothException e) {
        Throwable throwable = e.getCause();
        if (throwable != null) {
            handleThrowable(throwable);
        }
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(ValidateDreamRunTimeException.class)
    public Result<?> handleMicroException(ValidateDreamRunTimeException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result<?> handleThrowable(Throwable e) {
        Throwable curThrowable = e;
        while (curThrowable.getCause() != null) {
            curThrowable = curThrowable.getCause();
        }
        log.error(e.getMessage(), e);
        return Result.error("操作失败，" + e.getMessage());
    }
}
