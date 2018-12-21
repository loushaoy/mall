<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\10 0010
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件管理</title>
</head>
<body>
    <c:set var="src" value='<%="http://" + request.getServerName()  + ":"+ request.getServerPort()%>'></c:set>
    <form id="form1" action="${ctx}/login/addFile" enctype="multipart/form-data" method="post">
        <div class="div1">
            <p>${src}</p>
            <input id="file" type="file" name="file">
            <input class="upFile" type="button" value="上传" onclick="addFile();">
        </div>
    </form>
</body>
</html>
<script src="${ctx}/static/js/jquery1.3.2.js"></script>
<script>
    
    function addFile() {
        $("#form1").submit();
    }
</script>
