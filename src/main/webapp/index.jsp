<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Page -->
<%@ include file="parts/head.jsp" %>

<c:if test="${requestScope.error eq true}">
    <div class="alert alert-danger" align="center">
        <strong>${requestScope.message}</strong>
    </div>
</c:if>

<h5>
    Welcome
    <c:if test="${sessionScope.username ne null}">${sessionScope.username}</c:if>
    <c:if test="${sessionScope.username eq null}">Guest</c:if>
</h5>

<%@ include file="parts/tail.jsp" %>
<!-- Page -->
