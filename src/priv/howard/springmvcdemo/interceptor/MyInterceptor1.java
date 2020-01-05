package priv.howard.springmvcdemo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {
    /**
     * @Description 自定义拦截器，实现HandlerInterceptor接口
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        拦截请求
        System.out.println("1请求时被拦截");
//        返回值为true允许请求通行，false阻止请求通行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        拦截响应
        System.out.println("1响应后被拦截");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        页面渲染完毕后执行
        System.out.println("1页面渲染完毕");
    }
}
