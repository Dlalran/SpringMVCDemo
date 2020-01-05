package priv.howard.springmvcdemo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import priv.howard.springmvcdemo.exception.MyExceptionHandler;
import priv.howard.springmvcdemo.exception.MyException;

@Controller
@RequestMapping("SpringMVCHandler2")
public class SpringMVCHandler2 {

    @RequestMapping("testArithmeticException")
    public String testArithmeticException() {
//        下行造成ArithmeticException，
//        如果存在可以处理该异常的方法(标注@ExceptionHandler，且参数包含该类异常)，则自动跳转至那个方法继续处理
        System.out.println(1/0);
        return "success";
    }

    @RequestMapping("testArrayException")
    public String testArrayException() {
        int[] nums = new int[2];
        /**
         * 下行造成ArrayIndexOutOfBoundsException，被异常处理类{@link MyExceptionHandler}捕获
         */
        System.out.println(nums[3]);
        return "success";
    }

    /**
     * 添加@ExceptionHandler使得方法可以捕获该Controller中其他方法的异常，参数指定需要捕获的异常类
     * 如果有多个异常处理方法，则最短路径优先：同一Controller中优先，处理同类异常优先
     * 还可以单独给出一个异常处理类进行异常处理 {@link MyExceptionHandler}
     */
    @ExceptionHandler({ArithmeticException.class})
    public String handleException(Exception e, Model model) {
        e.printStackTrace();
//        可以将异常放入Model并跳转到错误页
        model.addAttribute("exception", e);
        return "error";
    }

    /**
     * 测试自定义异常类{@link MyException}
     */
    @RequestMapping("testMyException")
    public void testMyException() throws MyException {
        throw new MyException();
    }

//    2 @ResponseStatus加在Controller方法前可以指定跳转后的状态码和描述信息
    @ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT, reason = "自定义状态值")
    @RequestMapping("testStatus")
    public String testStatus() {
        return "success";
    }

//    测试配置方式处理异常
    @RequestMapping("test1")
    public String test1() {
//        下行造成ArithmeticException，
//        如果存在可以处理该异常的方法(标注@ExceptionHandler，且参数包含该类异常)，则自动跳转至那个方法继续处理
        System.out.println(1/0);
        return "success";
    }
}
