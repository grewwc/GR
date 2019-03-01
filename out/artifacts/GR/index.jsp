<%@ page import="java.util.ResourceBundle" %><%--
  Created by IntelliJ IDEA.
  User: grewwc
  Date: 2/26/2019
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>

<head>
    <title>Application</title>
    <link rel="stylesheet" type="text/css" href="css/g_input.css"/>
    <style>
        form{
            padding-left: 15%;
            padding-top:10%;
        }

        form input[type="text"]{
            margin-top: 1em;
            border-radius: 2px;
            height: 2em;
            width: 20em;
            padding:1em;
        }
    </style>
</head>
<body>

<div>
    <form action="/GR/process_coord_input">
        <label>Input independent parameters (空白符隔开): </label> <br/>
        <input type="text" name="coord"/> <br/> <br/> <br/>
        <input type="submit" name="coord_submit" value="Finish"/>
    </form>
</div>


</body>
</html>











