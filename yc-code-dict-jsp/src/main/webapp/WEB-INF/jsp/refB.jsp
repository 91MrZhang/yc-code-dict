<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>解析base64</title>
</head>
<body>
<form action="/data/parse" method="post">
    <c:if test="${true == sessionScope.showDefault}">
        显示默认值:<input type="checkbox" name="showDefault" value="true" checked/>&nbsp;&nbsp;
    </c:if>
    <c:if test="${empty  sessionScope.showDefault || false == sessionScope.showDefault}">
        显示默认值:<input type="checkbox" name="showDefault" value="true"/>&nbsp;&nbsp;
    </c:if>
    &nbsp;&nbsp;
    <c:if test="${true == sessionScope.showEnum}">
        枚举数值形式展示:<input type="checkbox" name="showEnum" value="true" checked/>&nbsp;&nbsp;
    </c:if>
    <c:if test="${empty  sessionScope.showEnum || false == sessionScope.showEnum}">
        枚举数值形式展示:<input type="checkbox" name="showEnum" value="true"/>&nbsp;&nbsp;
    </c:if>
    base64:<input type="text" name="base64Str" value="${base64Str}"/>&nbsp;&nbsp;<input type="submit" value="解析"/>
</form>
<hr>
<textarea id="content" rows="40" cols="100 " name="content">${json}</textarea><br/>
</body>
</html>