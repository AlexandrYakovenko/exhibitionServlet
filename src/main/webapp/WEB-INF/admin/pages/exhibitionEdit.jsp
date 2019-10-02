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
        <%@ include file="../parts/navbarAdmin.jsp" %>
    </div>

    <div class="container mt-2">
        <c:if test="${requestScope.error eq true}">
            <div class="alert alert-danger" align="center">
                <strong><c:out value="${error}"/></strong>
            </div>
        </c:if>

        <div align="center">
            <h5>Edit your exhibition</h5>
        </div>

            <div class="form-group mt-3">
                <div class="col-sm-6">
                    <form action="${pageContext.request.contextPath}/exhibition/admin/exhibitions/edit" method="post">

                        <!-- Name -->
                        <div class="form-group">
                            <label for="exhibition">Exhibition</label>
                            <input type="text" name="name" class="form-control"
                                   id="exhibition" placeholder="name of exhibition" required
                                   value="<c:if test="${requestScope.exhibition ne null}">${exhibition.name}</c:if>"/>
                        </div>

                        <!-- Showroom -->
                        <div class="form-group">
                            <label for="showroom">Showroom</label>
                            <input type="text" name="showroom" class="form-control"
                                   id="showroom" placeholder="name of showroom" required
                                   value="<c:if test="${requestScope.exhibition ne null}">${exhibition.showroom}</c:if>"/>
                        </div>

                        <!-- Description -->
                        <div class="form-group">
                            <label for="description">Description</label>
                            <input type="text" name="description" class="form-control"
                                   id="description" placeholder="description of event" required
                                   value="<c:if test="${requestScope.exhibition ne null}">${exhibition.description}</c:if>"/>
                        </div>

                        <!-- Price -->
                        <div class="form-group">
                            <label for="price">Price</label>
                            <input type="text" name="price" class="form-control"
                                   id="price" placeholder="0" required
                                   value="<c:if test="${requestScope.exhibition ne null}">${exhibition.price}</c:if>"/>
                        </div>

                        <!-- Date -->
                        <div class="form-group">
                            <label for="date">Date</label>
                            <input type="date" name="date" class="form-control"
                                   id="date" placeholder="Date of event" required
                                   value="<c:if test="${requestScope.exhibition ne null}">${exhibition.date}</c:if>"/>
                        </div>

                        <!-- security_token only to post-methods -->
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />

                        <input type="hidden" name="exhibitionId"
                               value="<c:if test="${requestScope.exhibition ne null}">${exhibition.id}</c:if>" />

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">
                                Save
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        <div class="card-columns mt-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${exhibition.name}"/></h5>
                        <h6 class="card-subtitle mb-2 text-muted">Showroom : <c:out value="${exhibition.showroom}"/></h6>
                        <h6 class="card-subtitle mb-2 ">Price : <c:out value="${exhibition.price}"/></h6>
                        <p class="card-text"><c:out value="${exhibition.description}"/></p><br/>
                        <h6 class="card-subtitle mb-2 ">Date : <c:out value="${exhibition.date}"/></h6>
                        <a href="${pageContext.request.contextPath}/exhibition/sales"
                           class="card-link">
                            Buy Ticket
                        </a>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <p class="col align-self-center"><c:out value="${exhibition.author.username}"/></p>
                            <p class="col align-self-center"></p>
                            <form class="col align-self-center" method="post"
                                  action="${pageContext.request.contextPath}/exhibition/user/exhibitions">
                                  <input type="hidden" name="exhibitionIdDelete" value="${exhibition.id}"/>
                                  <button class="btn btn-secondary" type="submit">
                                      Delete
                                  </button>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        rossorigin="anonymous"></script>
</body>
</html>