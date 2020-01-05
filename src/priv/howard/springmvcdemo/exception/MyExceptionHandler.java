package priv.howard.springmvcdemo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    /**
     * @Description 自定义Controller异常处理类，添加@ControllerAdvice，自动捕获其他Controller出现的异常并处理
     */

    @ExceptionHandler({ArithmeticException.class})
    public void handleException(Exception e) {
        e.printStackTrace();
    }
}
