<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/19 0019
  Time: 下午 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>家庭圈后台</title>
</head>
<body>
<h1 style="align-content: center">欢迎来到家庭圈</h1>

<h1>上传用户图像测试</h1>

<form method="post" action="${pageContext.request.contextPath}/userRestful/uploadUserPortrait"
      enctype="multipart/form-data">
    <input type="text" name="sessionId" value="20170219220621E85F83FE726D4C558CB23D90E7FA7661">
    <input type="file" name="file" value="file">
    <input type="submit">
</form>

<h1>发表朋友圈测试</h1>
<form method="post" action="${pageContext.request.contextPath}/friends/addMoment"
      enctype="multipart/form-data">
    <input type="text" name="sessionId" value="20170219220621E85F83FE726D4C558CB23D90E7FA7661">
    <input type="text" name="textContext" value="测试而已">
    <input type="file" name="file" value="file">
    <input type="submit">
</form>
</body>
</html>
