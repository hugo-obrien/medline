<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="scripts/sorttable.js"></script>
<style>
    <%@include file="styles/test.css"%>
</style>

<html>
<head>
    <title>Test</title>
</head>
<body>

<%
    String partNumber = request.getParameter("partnumber");
    String partName = request.getParameter("partname");
    String vendor = request.getParameter("vendor");
    String qty = request.getParameter("qty");
    String shippedAfter = request.getParameter("shippedafter");
    String shippedBefore = request.getParameter("shippedbefore");
    String receivedAfter = request.getParameter("receivedafter");
    String receivedBefore = request.getParameter("receivedbefore");
%>

<form action="" method="get">
    <div class="form-row">
        <div class="form-label">
            PN
        </div>
        <div>
            <input type="text" name="partnumber" value="<%=partNumber != null ? partNumber : ""%>">
        </div>
    </div>
    <div class="form-row">
        <div class="form-label">
            Part name
        </div>
        <div>
            <input type="text" name="partname" value="<%=partName != null ? partName : ""%>">
        </div>
    </div>
    <div class="form-row">
        <div class="form-label">
            Vendor
        </div>
        <div>
            <input type="text" name="vendor" value="<%=vendor != null ? vendor : ""%>">
        </div>
    </div>
    <div class="form-row">
        <div class="form-label">
            Qty
        </div>
        <div>
            <input type="text" name="qty" value="<%=qty != null ? qty : ""%>">
        </div>
    </div>
    <div class="form-row">
        <div class="form-label">
            Shipped after
        </div>
        <input type="date" name="shippedafter" value="<%=shippedAfter != null ? shippedAfter : ""%>">
        before
        <input type="date" name="shippedbefore" value="<%=shippedBefore != null ? shippedBefore : ""%>">
    </div>
    <div class="form-row">
        <div class="form-label">
            Received after
        </div>
        <input type="date" name="receivedafter" value="<%=receivedAfter != null ? receivedAfter : ""%>">
        before
        <input type="date" name="receivedbefore" value="<%=receivedBefore != null ? receivedBefore : ""%>">
    </div>
    <input type="hidden" name="submitted" id="submitted" value="true">
    <input type="submit" value="Отправить"/>
</form>

<c:choose>
    <c:when test="${show_table}">
        <table border="1" class="sortable">
            <thead>
            <tr>
                <th class="col-pn">PN</th>
                <th class="col-string">Part name</th>
                <th class="col-string">Vendor</th>
                <th class="col-int">Qty</th>
                <th class="col-date">Shipped</th>
                <th class="col-date">Received</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${parts}" var="part">
                <tr>
                    <td><c:out value="${part.number}"/></td>
                    <td><c:out value="${part.name}"/></td>
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