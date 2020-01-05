package priv.howard.springmvcdemo.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Past;
import java.util.Date;

public class Student {

//    通过@NumberFormat指定数字类型的格式化（此处指定格式为xxx.x）
//    @NumberFormat(pattern = "###.#")
    private int id;
    private String name;
    private Address address;

    /**
     * 对属性值进行校验，错误时会在Controller方法的BindingResult参数中附加错误信息，错误信息可以通过在注解参数message指定
     *
     * JSR303部分注解：
     * @Max、@DecimalMax、@Min、@DecimalMin 小于、大于整数（或浮点数）
     * @Future、@Past 未来、过去的日期
     * @Null、@NotNull 为空、不为空
     * @Pattern 匹配正则表达式
     * @Size(min,max) 长度在min、max之内
     *
     * Hibernate Validator注解（包含以上注解）：
     * @NotEmpty、@NotBlank 字符串不为空、不为空或不全为空格
     * @Email 为e-mail地址
     * @Length(min,max) 字符串长度必须在min、max以内
     * @Range(min,max) 元素值在min、max以内
     *
     */

    @Past(message = "Need a past date")
//    通过@DateTimeFormat进行日期类型变量格式化，pattern指定页面数据的格式（需要配置格式化Bean）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Student() {
    }

    public Student(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address.toString() +
                '}';
    }
}
