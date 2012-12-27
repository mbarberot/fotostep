<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title></title>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.userId}">
        <c:redirect url="main.jsf"/>
    </c:when>

    <c:otherwise>
        <c:redirect url="user-pages/news.jsf"/>
    </c:otherwise>
</c:choose>

</body>
</html>