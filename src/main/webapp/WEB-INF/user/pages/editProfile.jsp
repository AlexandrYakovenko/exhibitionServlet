<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
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
        <c:if test="${sessionScope.access eq \"ADMIN\"}">
            <%@ include file="/WEB-INF/admin/parts/navbarAdmin.jsp" %>
        </c:if>
        <c:if test="${sessionScope.access eq \"USER\"}">
            <%@ include file="../parts/navbarUser.jsp" %>
        </c:if>
    </div>

    <div class="container mt-2">
        <c:if test="${requestScope.message ne null}">
            <div class="alert alert-success" align="center">
                <strong>${message}</strong>
            </div>
        </c:if>

        <div>
            <h5>Username : ${user.username}</h5>
        </div>

        <form method="post" class="col-sm-4"
              action="${pageContext.request.contextPath}/exhibition/user/editProfile">
            <!-- Username -->
            <b>Edit Username.</b><br/>
            <label for="username">Username :</label>
            <input type="text" name="username" value="${user.username}" autofocus
                    id="username" class="form-control"/>

            <button type="submit" class="btn btn-primary mt-2">
                Save
            </button>
        </form>
        <form method="post" class="col-sm-4"
              action="${pageContext.request.contextPath}/exhibition/user/editProfile">
            <!-- Password -->
            <b>Edit Password.</b><br/>
            <label for="password">Password :</label>
            <input type="password" name="password" id="password" class="form-control"
                    pattern="^[\w-]{1,20}$"/>

            <!-- Password Confirm -->
            <label for="passwordConfirm">
                Confirm password, if you want to change it :
            </label>
            <input type="password" name="passwordConfirm" id="passwordConfirm"
                   class="form-control"  pattern="^[\w-]{1,20}$"/>

            <button type="submit" class="btn btn-primary mt-2">
                Save
            </button>
        </form>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>