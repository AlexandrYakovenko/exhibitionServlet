<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
    <meta charset="UTF-8">
    <title>Exhibition</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <div class="container mt-2">
        <%@ include file="/WEB-INF/parts/navbarGuest.jsp" %>
    </div>
    <div class="container mt-2">

        <c:if test="${requestScope.error eq true}">
            <div class="alert alert-danger" align="center">
                <strong>Invalid username or password</strong>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/exhibition/login" method="post">
            <div class="form-group row">
                <label class="col-sm-1 col-form-label" for="usernameInput">Username</label>
                <div class="col-sm-4">
                    <input type="text" name="username" class="form-control" placeholder="username" required
                           autofocus id="usernameInput"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label" for="passwordInput">Password</label>
                <div class="col-sm-4 ">
                    <input type="password" name="password" class="form-control" placeholder="password" required
                           id="passwordInput"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">
                Login
            </button>
        </form>

        <div class="mt-2">
            <a href="${pageContext.request.contextPath}/exhibition/registration"
               class="btn btn-light">
                Registration
            </a>
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