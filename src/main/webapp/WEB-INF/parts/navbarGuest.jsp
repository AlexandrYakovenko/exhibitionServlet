<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

        </ul>

        <div class="navbar-text mr-2"> ${sessionScope.username}</div>

        <c:if test="${sessionScope.username eq null}">
            <div class="mr-3">
                <form action="${pageContext.request.contextPath}/exhibition/login" method="get">
                    <button type="submit" class="btn btn-primary">
                        Login
                    </button>
                </form>
            </div>
        </c:if>
        <c:if test="${sessionScope.username ne null}">
        <div class="mr-3">
            <form action="${pageContext.request.contextPath}/exhibition/logout" method="post">
                <button type="submit" class="btn btn-primary">
                    Log Out
                </button>
            </form>
        </div>
        </c:if>

    </div>
</nav>
