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
        <%@ include file="/WEB-INF/parts/navbarGuest.jsp" %>
    </div>
    <div class="container mt-2">

        <c:if test="${requestScope.error eq true}">
            <div class="alert alert-danger" align="center">
                <strong>Invalid email or password</strong>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/exhibition/registration" method="post">
            <!-- Username -->
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-4">
                    <input type="text" name="username" class="form-control" placeholder="username" required
                           autofocus/>
                </div>
            </div>

            <!-- Password -->
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Password </label>
                <div class="col-sm-4 ">
                    <input type="password" name="password" class="form-control" placeholder="password"
                           required/>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">
                Registration
            </button>
        </form>

        <div class="mt-2">
            <a href="${pageContext.request.contextPath}/exhibition/login"
               class="btn btn-light">
                Login
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