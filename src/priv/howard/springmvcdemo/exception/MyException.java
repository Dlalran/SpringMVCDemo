package priv.howard.springmvcdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 1 @ResponseStatus通过value(或code)定义返回Response的状态码，reason定义描述信息（tomcat服务器将显示在message行中）
@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "自定义异常")
public class MyException extends Exception{
    /**
     * @Description 自定义错误类，Controller抛出该错误时，页面将显示@ResponseStatus定义的错误码与信息
     */
}
