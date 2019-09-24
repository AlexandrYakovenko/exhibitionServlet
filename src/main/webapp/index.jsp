<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html>

<head>
    <meta charset="UTF-8">
    <title>Exhibition</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <div class="container mt-2">
       <%-- <c:if test="${sessionScope.role eq \"SUPER_ADMIN\"}">
            <%@ include file="/WEB-INF/super_admin/parts/navbarSuperAdmin.jsp" %>
        </c:if>
        <c:if test="${sessionScope.role eq \"ADMIN\"}">
            <%@ include file="/WEB-INF/admin/parts/navbarAdmin.jsp" %>
        </c:if>
        <c:if test="${sessionScope.role eq \"USER\"}">
            <%@ include file="/WEB-INF/user/parts/navbarUser.jsp" %>
        </c:if>--%>
       <%-- <c:if test="${sessionScope.role ne \"USER\" && ne \"ADMIN\" and ne \"SUPER_ADMIN\"}">--%>
        <%@ include file="/WEB-INF/parts/navbarGuest.jsp" %>
        <%--</c:if>--%>
    </div>
    <div class="container mt-2">

        <c:if test="${requestScope.error eq true}">
        <div class="alert alert-danger" align="center">
            <strong>${requestScope.message}</strong>
        </div>
        </c:if>
        <div align="center">
            <h5>
                Welcome
                <c:if test="${sessionScope.username ne null}">${sessionScope.username}</c:if>
                <c:if test="${sessionScope.username eq null}">Guest</c:if>
            </br>
                <c:if test="${sessionScope.access eq \"SUPER_ADMIN\"}">
                    <a href="${pageContext.request.contextPath}/exhibition/superAdmin">Your Page</a>
                </c:if>
                <c:if test="${sessionScope.access eq \"ADMIN\"}">
                    <a href="${pageContext.request.contextPath}/exhibition/admin">Your Page</a>
                </c:if>
                <c:if test="${sessionScope.access eq \"USER\"}">
                    <a href="${pageContext.request.contextPath}/exhibition/user">Your Page</a>
                </c:if>
            </h5>
        </div>

    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>