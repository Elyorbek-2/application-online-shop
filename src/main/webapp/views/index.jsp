<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <jsp:include page="../fragments/css.jsp"/>
<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>




<h1><%= "Hello World!" %></h1>
<br/>

<a href="hello-servlet">Hello Servlet</a>

<p>${nom1}</p>
<p>${nom2}</p>
<p>${nom3}</p>



<jsp:include page="../fragments/js.jsp"/>
</body>
</html>