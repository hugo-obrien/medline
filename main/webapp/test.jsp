<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Test</title>
</head>
<body>

<form action="" method="post">
    <p>PN <input type="text" name="partNumber"></p>
    <p>Part name <input type="text" name="partName"></p>
    <p>Vendor <input type="text" name="vendor"></p>
    <p>Qty <input type="text" name="qty"></p>
    <input type="submit" value="Отправить"/>
</form>

<c:choose>
    <c:when test="${show_table}">
        <table border="1">
            <thead>
            <tr>
                <th>PN</th>
                <th>Part number</th>
                <th>Vendor</th>
                <th>Qty</th>
                <th>Shipped</th>
                <th>Received</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${parts}" var="part">
                <tr>
                    <td><c:out value="${part.name}"/></td>
                    <td><c:out value="${part.number}"/></td>
                    <td><c:out value="${part.vendor}"/></td>
                    <td><c:out value="${part.qty}"/></td>
                    <td><c:out value="${part.shipped}"/></td>
                    <td><c:out value="${part.received}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
</c:choose>

</body>
</html>