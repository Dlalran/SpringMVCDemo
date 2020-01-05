<%--
  Created by IntelliJ IDEA.
  User: Howard Zhong
  Date: 2019/12/5
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
      <script type="text/javascript" src="js/jquery-3.4.1.js"></script>

      <script type="text/javascript">
<%--          页面初始化函数--%>
        $(document).ready(function() {
            $("#testjson").click(function() {
                $.post(
                    //URL
                    "SpringMVCHandler/test23",
                    //JSON
                    {"name":"howard"},
                    function(result) {
                    //  回调函数，处理Controller返回的JSON对象（这里是JSON数组）
                    //    注意：Controller中方法的注解@ResponseBody已经使得返回的数组转为JSON格式，不需要通过eval()转成JSON对象
                        $.each(result, function(i, element) {
                            alert("第" + i + "个对象:" + element.id + " -- " + element.name);
                        })
                    }
                )
            });
        })
      </script>
  </head>
  <body>

    <a href="SpringMVCHandler/test1">Get方式请求</a>

    <form action="SpringMVCHandler/test1" method="post">
      <input name="name" placeholder="name=howard"/>
      <input name="age" placeholder="age!=20" />
      <input type="submit" value="Post方式请求"/>
    </form>
    <a href="SpringMVCHandler/test2">请求头参数判断</a><br/>
    <a href="SpringMVCHandler/a/b/c/test3">Ant风格测试</a><br/>
    <a href="SpringMVCHandler/test4/howard">获取参数测试</a><br/>
    <form action="SpringMVCHandler/test5/1" method="post">
<%--      指定隐藏域属性_method=delete实现拦截器转换为delete类型请求--%>
      <input type="hidden" name="_method" value="delete"/>
      <input type="submit" value="Delete方式请求"/>
    </form>
    <form action="SpringMVCHandler/test5/2" method="post">
<%--      指定隐藏域属性_method=put实现拦截器转换为put类型请求--%>
      <input type="hidden" name="_method" value="put"/>
      <input type="submit" value="Put方式请求"/>
    </form>
    <form action="SpringMVCHandler/test6" method="post">
        <input name="uname" placeholder="uname"/>
        <input type="submit" value="获取参数"/>
    </form>
    <a href="SpringMVCHandler/test7">获取头信息</a>
    <a href="SpringMVCHandler/test8">获取Cookie值</a>
    <form action="SpringMVCHandler/test9" method="post">
        <input name="id" placeholder="id"/>
        <input name="name" placeholder="name"/>
<%--        级联属性的name标为属性值.级联属性值--%>
        <input name="address.homeAddress" placeholder="address.homeAddress"/>
        <input name="address.schoolAddress" placeholder="address.schoolAddress"/>
        <input type="submit" value="对象类型获取参数"/>
    </form>
    <form action="SpringMVCHandler/test10">
        <input name="name" placeholder="name"/>
        <input type="submit" value="原生Serlvet获取参数"/>
    </form>
    <a href="SpringMVCHandler/test11">ModelAndView跳转</a>
    <a href="SpringMVCHandler/test12">Model跳转</a>
    <a href="SpringMVCHandler/test13">ModelMap跳转</a>
    <a href="SpringMVCHandler/test14">Map跳转</a><br/>
    <a href="SpringMVCHandler/test15">Session域</a>
    <form action="SpringMVCHandler/test16" method="post">
        <input name="id" placeholder="id"/>
        <input name="name" placeholder="name"/>
        <input type="submit" value="ModelAttribute提前处理"/>
    </form>
    <a href="SpringMVCHandler/test17">国际化测试</a>
<%--    Controller中无以下url的方法，通过SpringMVC配置直接转发--%>
    <a href="SpringMVCHandler/test18">不经过Controller直接跳转</a>
    <a href="SpringMVCHandler/test19">重定向转发</a>
    <a href="test.png">访问静态资源</a>
    <form action="SpringMVCHandler/test20" method="post">
        <input name="studentInfo" placeholder="studentInfo"/>
        <input type="submit" value="自定义转换器处理参数"/>
    </form>
    <form action="SpringMVCHandler/test21" method="post">
        <input name="id" placeholder="id"/>
        <input name="name" placeholder="name"/>
        <input name="birthday" placeholder="birthday"/>
        <input type="submit" value="格式化数据（日期）"/>
    </form>
    <form action="SpringMVCHandler/test22" method="post">
        <input name="id" placeholder="id"/>
        <input name="name" placeholder="name"/>
        <input name="birthday" placeholder="birthday"/>
        <input type="submit" value="Hibernate Validator校验"/>
    </form>
    <input id="testjson" type="button" value="JSON传参"/>
<%--    SpringMVC处理文件上传--%>
    <form action="SpringMVCHandler/test24" method="post" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <input name="desc" placeholder="文件描述"/>
        <input type="submit" value="上传文件"/>
    </form>
<%--    测试文件下载，在SpringMVC中使用Servlet进行处理--%>
    <a href="SpringMVCHandler/test25">下载文件</a><br/>
    <a href="SpringMVCHandler/test26">测试拦截器</a><br/>
    <a href="SpringMVCHandler2/testArithmeticException">算术异常处理</a>
    <a href="SpringMVCHandler2/testArrayException">数组越界异常处理</a>
    <a href="SpringMVCHandler2/testMyException">自定义异常</a>
    <a href="SpringMVCHandler2/testStatus">设置状态码</a>
    <a href="SpringMVCHandler2/test1">配置方式处理异常</a>
  </body>
</html>
