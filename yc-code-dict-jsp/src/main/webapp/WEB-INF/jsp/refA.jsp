<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>场景模拟工具</title>
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
<form action="/data/filter" method="post">
    过滤条件:<input type="text" name="key" value="${sessionScope.filterKey}"/>&nbsp;&nbsp;<input type="submit" value="过滤"/>
</form>
<a href="/data/stopAll">全部暂停</a>&nbsp;&nbsp;&nbsp;<a href="/data/runAll">全部启动</a>&nbsp;&nbsp;&nbsp;<a href="/data/persist">持久化当前数据</a>&nbsp;&nbsp;&nbsp;<a href="/data/toParse" target="_blank">base64解析</a><br><br>
<hr>
<table border="1">

    <tr>
        <th>名称</th>
        <th>组</th>
        <th>组角色</th>
        <th>状态</th>
        <th>操作</th>
        <th>类型</th>
        <th>头部延迟</th>
        <th>末尾延迟</th>
        <th>自身周期</th>
        <th>净周期(前延+自身)</th>
        <th>总周期(有误差)</th>
        <th>修改时间</th>
    </tr>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.groupKey}</td>
            <td>${item.groupMode}</td>
            <c:if test="${1 eq item.status}">
                <td bgcolor="red"><a href="/data/run?id=${item.id}">启动</a></td>
            </c:if>
            <c:if test="${0 eq item.status}">
                <td bgcolor="#adff2f"><a href="/data/stop?id=${item.id}">暂停</a></td>
            </c:if>
            <td><a href="/data/remove?id=${item.id}" onclick="return confirm('确认删除吗');">删除</a> | <a href="/data/edit?id=${item.id}">编辑</a> | <a href="/data/copy?id=${item.id}">复制</a></td>
            <td>${item.type}</td>
            <th>${item.startDelay}</th>
            <th>${item.endDelay}</th>
            <th>${item.period - item.startDelay - item.endDelay}</th>
            <th>${item.period - item.endDelay}</th>
            <th>${item.period}</th>
            <td><fmt:formatDate value="${item.upTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
    </c:forEach>
</table>

<hr>
<form action="/data/save" method="post">
    <input type="hidden" name="upTime" value="${model.upTime}"/>
    <input type="hidden" name="id" value="${model.id}"/>
    <c:if test="${not empty model.id}">
        <span style="color: red">ID:${model.id}</span><br>
    </c:if>
    画面元素属性：<br>
    类型:<input type="text" name="type" value="${model.type}"/>&nbsp;&nbsp;<a href="/data/guide" target="_blank">类型模板</a><br/>
    名称:<input type="text" name="name" value="${model.name}" size="54"/><br/>
    组名:<input type="text" name="groupKey" value="${model.groupKey}"/>&nbsp;&nbsp;组角色:<input type="text" name="groupMode" value="${model.groupMode}"/><br/><br>
    周期设置:<br>
    头部延迟(ms):<input type="text" name="startDelay" value="${model.startDelay}"/>&nbsp;&nbsp; 末尾延迟(ms):<input type="text" name="endDelay" value="${model.endDelay}"/><br/><br>
    JSON:<br/>
    <textarea id="content" rows="30" cols="100 " name="content">${model.content}</textarea><br/>
    <input type="submit" value="保存"/>
</form>
<br>
</body>


<script>

</script>
</html>