<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.06.2023
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/fragments/css.jsp"/>
</head>
<body>
<jsp:include page="/fragments/navbar.jsp"/>
<h1>Login and Register page</h1>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h1>Login</h1>
            <%--<c:if test="${error_message != null}">
                <div class="alert alert-danger">
                        ${error_message }
                </div>
            </c:if>--%>
<%--            <form action="/auth/login?next=${next}" method="post">--%>
            <form action="/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" name="email">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
                    <label class="form-check-label" for="rememberMe">Remmeber me ?</label>
                </div>
                <button type="submit" class="btn btn-success">Login</button>
            </form>
        </div>
        <div class="col-md-6">
            <h1>Register</h1>
<%--            <form action="/auth/register" method="post">--%>
            <form action="/register" method="post">
                <div class="mb-3">
                    <label for="p_email" class="form-label">Email address</label>
                    <input type="text" class="form-control" id="p_email" name="email">
                    <c:if test="${email_error != null}">
                        <snap class="text-danger">${email_error}</snap>
                    </c:if>

                </div>
                <div class="mb-3">
                    <label for="p_password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="p_password" name="password">
                    <c:if test="${password_error != null}">
                        <snap class="text-danger">${password_error}</snap>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="p2_password" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="p2_password" name="confirm_password">
                    <c:if test="${password_error != null}">
                        <snap class="text-danger">${password_error}</snap>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
            </form>
        </div>
    </div>
</div>



<jsp:include page="/fragments/js.jsp"/>
</body>
</html>
