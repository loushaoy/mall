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
    <div>
        <table>
            <c:forEach items="${list}" var="file">
                <tr>
                    <td><a href="${src}/login/showFile?id=${file.id}">${file.name}</a></td>
                </tr>
            </c:forEach>
        </table>

    </div>
</body>
</html>
<script src="${ctx}/static/js/jquery1.3.2.js"></script>
