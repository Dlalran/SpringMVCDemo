package priv.howard.springmvcdemo.converter;

import org.springframework.core.convert.converter.Converter;
import priv.howard.springmvcdemo.entity.Address;
import priv.howard.springmvcdemo.entity.Student;

public class MyConverter implements Converter<String, Student> {
    /**
     * @Description 自定义转换器，实现Converter接口并指定泛型<SpringMVC接受类型，页面请求类型>
     */
    @Override
    public Student convert(String source) {
//        处理页面传递的参数(以-分割为字符串数组）
        String[] studentMsg = source.split("-");
//        依次放入对象中
        Student student = new Student();
        student.setId(Integer.parseInt(studentMsg[0]));
        student.setName(studentMsg[1]);
        student.setAddress(new Address(studentMsg[2], studentMsg[3]));
        return student;
    }
}
