<%--<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Page -->
<%@ include file="parts/head.jsp" %>

<c:if test="${requestScope.error eq true}">
    <div class="alert alert-danger" align="center">
        <strong>Invalid username or password</strong>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/exhibition/login" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="usernameInput">Username</label>
        <div class="col-sm-4">
            <input type="text" name="username" class="form-control" placeholder="username" required
                   autofocus id="usernameInput"/>
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="passwordInput">Password</label>
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

<%@ include file="parts/tail.jsp" %>
<!-- Page -->