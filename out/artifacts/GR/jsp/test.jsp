<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: grewwc
  Date: 2/28/2019
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <%!
        boolean isDigit(char ch) {
            return Character.isDigit(ch);
        }
    %>
</head>

<style>
    #result{
        padding:1em 3em 1em 3emt;
    }
</style>
<body>

<div id="result">
    <%
        @SuppressWarnings("unchecked")
        List<List<String>> data = (List<List<String>>) request.getAttribute("data");

        for (List<String> tensor : data) {
            for (int i = 0; i < tensor.size(); i++) {
                if (i == 0) {
                    out.println("<strong> " + tensor.get(i) + "</strong>");
                } else {
                    out.println(tensor.get(i));
                }
                out.println("<br/>");
            }
            out.println("<br/><hr/>");
        }
    %>
</div>
</body>

</html>
