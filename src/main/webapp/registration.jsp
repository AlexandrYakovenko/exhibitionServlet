<!-- Page -->
<%@ include file="parts/head.jsp" %>

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

<%@ include file="parts/tail.jsp" %>
<!-- Page -->