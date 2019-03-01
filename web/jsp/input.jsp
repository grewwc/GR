<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%--<link rel="stylesheet" type="text/css" href="css/g_input.css"/>--%>

    <style>
        .g_input {
            margin: 0.1em 2em 0 3em;
        }

        .g_input input[type="text"] {
            width: 10em;
            height: 0.8em;
            margin: 0;
            padding: 1em;
            border-radius: 1px;
            border-width: 1px;
            border-style: solid;
        }

        input[type="checkbox"] {
            margin-left:2em;
            margin-top:1px;
            -webkit-appearance: none;
            width: 20px;
            height: 20px;
            border-radius: 3px;
            background: white;
            border: 2px solid #555
        }

        input[type='checkbox']:checked {
            background: #abd;
        }


        #empty{
            margin-bottom: 500px;
        }

    </style>
</head>

<body>

<form action="/GR/result">
    <small style="margin-left: 40%;"> (default: identity matrix)</small>
    <div class="g_input">
        <c:if test="${dim == 1}">
            <input type="text" name="b11"/> <br/>
        </c:if>
        <c:if test="${dim==2}">
            <input type="text" name="b11"/><input type="text" name="b12"/> <br/>
            <input type="text" name="b21"/><input type="text" name="b22"/> <br/>
        </c:if>

        <c:if test="${dim==3}">
            <input type="text" name="b11"/><input type="text" name="b12"/><input type="text" name="b13"/><br/>
            <input type="text" name="b21"/><input type="text" name="b22"/><input type="text" name="b23"/><br/>
            <input type="text" name="b31"/><input type="text" name="b32"/><input type="text" name="b33"/><br/>
        </c:if>

        <c:if test="${dim==4}">
            <input type="text" name="b11"/><input type="text" name="b12"/><input type="text" name="b13"/><input
                type="text" name="b14"/><br/>
            <input type="text" name="b21"/><input type="text" name="b22"/><input type="text" name="b23"/><input
                type="text" name="b24"/><br/>
            <input type="text" name="b31"/><input type="text" name="b32"/><input type="text" name="b33"/><input
                type="text" name="b34"/><br/>
            <input type="text" name="b41"/><input type="text" name="b42"/><input type="text" name="b43"/><input
                type="text" name="b44"/><br/>
        </c:if>

    </div>
    <br/>
    <input type="checkbox" name="ChristoffelSymbol" checked="1"> Christoffel Symbol <br/>
    <input type="checkbox" name="RiemannTensor" checked="1"> RiemannTensor <br/>
    <input type="checkbox" name="RicciTensor"> RicciTensor<br/>
    <input type="checkbox" name="RicciScalar"> RicciScalar<br/>
    <input type="checkbox" name="EinsteinTensor"> EinsteinTensor <br/>

    <input type="submit" name="submit" value="OK"/>
    <%
        String[] coords = (String[]) request.getAttribute("coord");
        out.println("<small><em>&nbsp;&nbsp;&nbsp; (independent parameters: [");
        for (int i = 0; i < coords.length; i++) {
            out.println("\" " + coords[i] + " \"");
            if (i < coords.length - 1) {
                out.println(" , ");
            }
        }
        out.println("] </em></small>)");
    %>
    <div id="empty"></div>
</form>

</body>
</html>