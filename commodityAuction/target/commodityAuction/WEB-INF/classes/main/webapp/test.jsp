<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/31
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" id="num">
<input type="submit" onclick="f()">
</body>

<script>
    function f() {
        var a=document.getElementById("num").value;
        console.log(a);
    }
</script>
</html>
