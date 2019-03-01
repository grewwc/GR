<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: grewwc
  Date: 2/27/2019
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <c:forEach var="g" items="${gs}">

        <c:forEach var="g1" items="${g}">
            here ${g1}
        </c:forEach>
        <br/>
    </c:forEach>
</body>
</html>
