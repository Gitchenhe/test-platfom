<%--
  Created by IntelliJ IDEA.
  User: chenhe
  Date: 2018/4/28
  Time: 15:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <!-- 引入 css js -->
    <sitemesh:write property='head' />
</head>

<body>
<h1>header</h1>
<!-- 编写统一风格的header 或者 include -->
<sitemesh:write property='body' />
<!-- 编写统一风格的footer 或者 include -->
<h1>footer</h1>
</body>
</html>
