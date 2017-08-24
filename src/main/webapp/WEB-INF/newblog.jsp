<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/8/23
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>新建博客</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/add" method="post">

    &nbsp;标题:<input id="title" name="title" style="width:600px; height:30px"></br>
    &nbsp;描述:<input id="des" name="des" style="width:600px; height:30px"></br>
    &nbsp;内容:<textarea id="content" name="content" style="width:600px; height:200px"></textarea></br>
      <input type="submit" value="提交">
      <a href="${pageContext.request.contextPath}/return">返回</a>

</form>



</body>
</html>
