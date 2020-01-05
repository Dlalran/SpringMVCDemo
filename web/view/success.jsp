<%--
  Created by IntelliJ IDEA.
  User: Howard Zhong
  Date: 2019/12/5
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%-- 引入JSTL标签库中fmt标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Hello SpringMVC!<br/>
    ${requestScope.student.id} - ${requestScope.student.name} - ${requestScope.student.address.homeAddress} - ${requestScope.student.address.schoolAddress}
    ${sessionScope.sessionStudent.id} - ${sessionScope.sessionStudent.name} - ${sessionScope.sessionStudent.address.homeAddress} - ${sessionScope.sessionStudent.address.schoolAddress}
<%--    下面的国际化标签字符需要通过SpringMVC的跳转才能生效，SpringMVC会配置国际化文件资源，更换浏览器语言查看效果--%>
    <fmt:message key="resource.welcome"></fmt:message>
    <fmt:message key="resource.exit"></fmt:message><br/>
<%--    通过jstl打印校验的错误信息--%>
    <c:forEach items="${requestScope.errors}" var="error">
        ${error.getDefaultMessage()}<br/>
    </c:forEach>
</body>
</html>
