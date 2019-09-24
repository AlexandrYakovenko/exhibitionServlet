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
        <%@ include file="navbarSuperAdmin.jsp" %>
    </div>
    <div class="container mt-2">
        <div class="row">
                <h4>Username : <c:out value="${editUsername}"/></h4>
        </div>
        <div class="row">
            <h4>Role : <c:out value="${editRole}"/></h4>
        </div>

        <form action="${pageContext.request.contextPath}/exhibition/super_admin/edit" method="post">
            <div class="form-group row">
                <label class="col-sm-1 col-form-label" for="usernameInput">Username</label>
                <div class="col-sm-4">
                    <input type="text" name="newUsername" class="form-control" placeholder="username"
                           autofocus id="usernameInput"/>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-1 col-form-label" for="roleInput">Role</label>
                <div class="col-sm-4 ">
                    <input type="password" name="newRole" class="form-control" placeholder="role"
                           pattern="USER | ADMIN" id="roleInput"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">
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