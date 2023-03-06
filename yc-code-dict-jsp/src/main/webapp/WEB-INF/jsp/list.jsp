<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>jsp展示</title>
</head>
<style>
    table {
        width: 100%;
        border: 1px solid #999;
    }

    table td {
        word-break: keep-all;
        white-space: nowrap;
    }
</style>
<body>
<hr>
<table border="1">
    <tr>
        <th>用户名</th>
        <th>年龄</th>
    </tr>
    <c:forEach items="${userList}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.age}</td>
        </tr>
    </c:forEach>
</table>

<hr>
<h2>提交转发写法</h2>
<form action="/view/saveA" method="post">
    姓名:<input type="text" name="name"/><br/>
    年龄:<input type="text" name="age"/><br/>
    <input type="submit" value="保存"/>
</form>
<br>
<h2>提交重定向写法</h2>
<form action="/view/saveB" method="post">
    姓名:<input type="text" name="name"/><br/>
    年龄:<input type="text" name="age"/><br/>
    <input type="submit" value="保存"/>
</form>
</body>
<script>

</script>
</html>