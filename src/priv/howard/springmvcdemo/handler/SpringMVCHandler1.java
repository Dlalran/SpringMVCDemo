package priv.howard.springmvcdemo.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import priv.howard.springmvcdemo.entity.Address;
import priv.howard.springmvcdemo.entity.Student;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//添加@Controller注解，属于控制器层部分
@Controller
//添加@RestController，则该Controller内所有方法返回值都不经过视图解析器(即等于所有方法都被添加@ResponseBody)，但以JSON格式传值时仍需要添加@ResponseBody
//@RestController

//如果存在类映射（类上标注@RequestMapping)，则跳转的URL应该为 类映射url/方法映射url
@RequestMapping("SpringMVCHandler")
//类上标注@SessionAttributes，并通过value指定要放入Session域中的已放入Request域中的所有对象参数名，types指定类型名
@SessionAttributes(value = "sessionStudent", types = {Student.class})
public class SpringMVCHandler1 {
    /**
     * @Description SpringMVC的处理类，相当于Java Web的Servlet（Controller层）
     */

//    方法映射,通过注解@RequestMapping对指定URL请求进行拦截,
//    method参数指定拦截的请求方式：POST、GET，非指定方式跳转时，会报405 Method Not Allowed
//    params参数指定请求中必须包含的参数(!参数名 则为不包含）、参数条件(注意:等号两端不能有空格，不等于条件时无该参数值也符合条件）
    @RequestMapping(value = "test1", method = RequestMethod.GET, params = {"name=howard", "age!=20"})
    public String test1() {
        return "success";
    }

//    headers参数指定请求头部(header)中必须包含的参数值
    @RequestMapping(value = "test2", headers = {"Accept-Encoding=gzip, deflate, br"})
    public String test2() {
        return "success";
    }

//    Ant风格路径，?为任意单个字符，*为任意多个字符，**为任意路径
    @RequestMapping("/**/test3")
    public String test3() {
        return "success";
    }

//    通过{参数名}获取该位置的值并赋给参数
    @RequestMapping("test4/{name}")
//    通过在参数前添加注解@PathVariable("参数名")获得参数
    public String test4(@PathVariable("name") String name) {
        System.out.println(name);
        return "success";
    }

//  通过SrpingMVC拦截器实现RESTful风格，处理DELETE类型的请求
//    注意：Tomcat8.0不支持该两种类型请求，需要降级为7.0，或者(?)转发给另一个Controller再跳转到jsp页面,也可以在对应页面jsp头部添加isErrorPage="true"
    @RequestMapping(value = "test5/{id}", method = RequestMethod.DELETE)
//    上面注解相当于以下注解，@RequestMapping(method=RequestMethod.请求类型) = @请求类型Mapping()
    @DeleteMapping("test5/{id}")
    public String test5Delete(@PathVariable("id") int id) {
        System.out.println("Delete Request" + id);
        return "success";
    }
//    处理DELETE类型的请求
    @RequestMapping(value = "test5/{id}", method = RequestMethod.PUT)
    public String test5Put(@PathVariable("id") int id) {
        System.out.println("Put Request" + id);
        return "success";
    }

    @RequestMapping("test6")
//    通过@RequestParam获取请求内指定参数值，参数required=false(默认true)指定该参数非必需（没有该参数的请求也可以接受）,defaultValue指定无参数值时的默认值
    public String test6(@RequestParam("uname") String name, @RequestParam(value = "uage", required = false, defaultValue = "21") Integer age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    @RequestMapping("test7")
//    通过@RequestHeader获得请求头信息，value指定要获取头信息的参数名
    public String test7(@RequestHeader("Accept-Language") String msg) {
        System.out.println(msg);
        return "success";
    }

    @RequestMapping("test8")
//    通过@CookieValue获得Cookie中指定值
    public String test8(@CookieValue("JSESSIONID") String jsessionId) {
        System.out.println(jsessionId);
        return "success";
    }

    @RequestMapping("test9")
//    SpringMVC支持将form表单中的属性封装到对象中（且支持级联属性），前提是对象属性名和表单name值相同
    public String test9(Student student) {
        System.out.println(student);
        return "success";
    }

    @RequestMapping("test10")
//    在参数添加request和response使用原生的Servlet（注意添加依赖servlet-api.jar）
    public String test10(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        System.out.println(name);
        return "success";
    }

    @RequestMapping("test11")
//    将数据（Model）封装ModelAndView中并跳转到另一页面（View）
    public ModelAndView test11() {
//        1将需要跳转到的页面(View)作为参数传给构造方法
        ModelAndView mv = new ModelAndView("success");
        Student student = new Student(1, "howard", new Address("sz", "scau"));
//        2将对象(Model)添加到Request域中，相当于request.setAttribute()
        mv.addObject(student);
//        将ModelAndView对象返回完成跳转
        return mv;
    }

//    以下三种方法SpringMVC都会自动将数据放入Request域中
    @RequestMapping("test12")
//    将数据放入Model中并跳转
    public String test12(Model model) {
        Student student = new Student(2, "john", new Address("gz", "scau"));
//        只需要将数据对象添加到Model中
        model.addAttribute("student", student);
        return "success";
    }
    @RequestMapping("test13")
//    将数据放入ModelMap中并跳转
    public String test13(ModelMap mm) {
        Student student = new Student(3, "king", new Address("bj", "pku"));
        mm.put("student", student);
        return "success";
    }
    @RequestMapping("test14")
//    将数据放入Map中并跳转
//    注意：不推荐使用
    public String test14(Map<String, Object> map) {
        Student student = new Student(4, "kate", new Address("sh", "pku"));
        map.put("student", student);
        return "success";
    }

    @RequestMapping("test15")
//    该方法返回的对象将由于类上的注解@SessionAttributes而放入Session域中
    public String test15(Model model) {
        Student sessionStudent = new Student(5, "bill", new Address("sz", "su"));
        model.addAttribute("sessionStudent", sessionStudent);
        return "success";
    }

//    @ModelAttribute标注的方法在所有其他方法前执行，用于在请求处理前将数据加入到Model中
//    1标注在返回值为void方法，则在参数中标注Model，并在Model中addAttribute(objectName,object)添加值
//    2标注在返回值为对象方法，则在注解value中指定返回的对象参数名(相当于1中objectName)，并返回对象(1中object)
    @ModelAttribute
    public void queryStudent(Model model) {
        Student student = new Student(0, "defaultName", new Address("defaultHome", "defaultSchool"));
//        <1>一般这里的参数名都设置为类型名首字母小写，@RequestMapping的方法同类型参数将自动获得该对象，否则需要在参数前加@ModelAttribute("参数名")才能获得
        model.addAttribute("student", student);
//        System.out.println("查询的Student：" + student);
    }

    @RequestMapping("test16")
//    <1>获取@ModelAttribute中加入Model的对象，参数名等于参数类型首字母小写可以省略
    public String test16(@ModelAttribute("student") Student student) {
//        此时Student已被修改，未修改的值(这里是Address的值)则为@ModelAttribute中原Student的值
        System.out.println("修改后的Student:" + student);
        return "success";
    }

//    国际化字符测试，SpringMVC转发请求时会加载国际化资源文件i18n_xx_XX.properties，JSP页面中通过JSTL进行国际化
    @RequestMapping("test17")
    public String test17() {
        return "success";
    }

//    test18的转发由配置完成，不需要Controller

    @RequestMapping("test19")
    public String test19() {
//        前缀“forward:”为请求转发（默认），前缀"redirect:"为重定向
//        注意配置该前缀后，视图解析器ViewResolver将不会添加前后缀
        return "redirect:/view/success.jsp";
    }

    /**
     * 使用已经配置的自定义转换器MyConverter{@link priv.howard.springmvcdemo.converter.MyConverter}转换为Student对象
     */
    @RequestMapping("test20")
    public String test20(@RequestParam("studentInfo") Student student) {
        System.out.println(student);
        return "success";
    }

    @RequestMapping("test21")
//    如果格式化错误（如页面输入值不符合格式），会将错误信息放入BindingResult中
//    注意：BindingResult必须在第二参数位置，紧跟需要被校验格式的实体类参数
    public String test21(Student student, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
//            result.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
            for (FieldError error : result.getFieldErrors()) {
                try {
                    throw new Exception(error.getDefaultMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            将错误信息放入ModelMap并转发
            modelMap.put("errors", result.getFieldErrors());
        }
//        通过在实体类的属性（如日期）上标注注解进行格式化传参
        System.out.println(student.getBirthday());
        return "success";
    }

//    在要检验的参数前添加@Valid，会对指定条件（实体类上的JSP303、HV注解）进行校验，否则将对应错误信息放入BindingResult中
    @RequestMapping("test22")
    public String test22(@Valid Student student, BindingResult result, ModelMap modelMap) {
        if(result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
            modelMap.put("errors", result.getFieldErrors());
        }
        System.out.println(student.getBirthday());
        return "success";
    }

//    使用@ResponseBody时，返回值将不经过视图解析器处理（即不解析为跳转路径），而是直接将返回值写入页面输入流，以JSON格式返回对象给页面
    @ResponseBody
//    jQuery中JSON格式传参，并返回结果
    @RequestMapping("test23")
    public List<Student> test23() {
//        模拟查询结果
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "howard", null));
        students.add(new Student(2, "john", null));
        students.add(new Student(3, "bill", null));
        return students;
    }

//    处理文件上传
    @RequestMapping("test24")
    public String test24(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("文件描述信息:" + desc);
//
        System.out.println("项目所在服务器地址:" + System.getProperty("rootPath"));
//        获取输入流通过缓冲区并通过输出流输出到硬盘文件中

//        获取上传文件名
        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream("C:\\Users\\Howard Zhong\\IdeaProjects\\SpringMVCDemo\\" + fileName);
        byte[] buf = new byte[1024];
        int len = -1;
        while ( (len = in.read(buf)) != -1){
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        return "success";
    }

//    处理文件下载，通过原生Servlet处理
    @RequestMapping("test25")
    public void test25(HttpServletResponse response) throws IOException {
        String path = "C:\\Users\\Howard Zhong\\IdeaProjects\\SpringMVCDemo\\";
        String fileName = "IDEA快捷键.txt";

        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.addHeader("content-Type", "application/octet-stream");
        response.addHeader("content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8") );

        File file = new File(path + fileName);
        System.out.println(file.length());
        InputStream in = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ( (len = in.read(buf)) != -1) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    /**
     * 观察拦截器的作用，自定义拦截器:{@link priv.howard.springmvcdemo.interceptor.MyInterceptor1}{@link priv.howard.springmvcdemo.interceptor.MyInterceptor2}
     */
    @RequestMapping("test26")
    public String test26() {
        System.out.println("Controller处理请求...");
        return "success";
    }
}
