package com.sharfine.common.exception;

import com.sharfine.common.enums.BizCodeEnum;
import com.sharfine.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Sharfine
 * @createTime: 2021/2/1 14:19
 */
@Slf4j
public abstract class AbstractFmallExceptionAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException exception) {
        Map<String, String> map = new HashMap<>();
        BindingResult bindingResult = exception.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            map.put(field, message);
        });

        log.error("数据校验出现问题{},异常类型{}", exception.getMessage(), exception.getClass());
        return R.error(400, "数据校验出现问题").put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("未知异常{},异常类型{}", throwable.getMessage(), throwable.getClass());
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), throwable.getMessage());
    }

}
