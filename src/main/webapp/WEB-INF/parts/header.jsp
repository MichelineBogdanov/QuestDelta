<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                <use xlink:href="#bootstrap"></use>
            </svg>
        </a>
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/" class="nav-link px-2 link-secondary">Главная</a></li>
            <li><a href="notImplemented" class="nav-link px-2 link-dark">Квесты</a></li>
            <li><a href="notImplemented" class="nav-link px-2 link-dark">Создать</a></li>
            <li><a href="notImplemented" class="nav-link px-2 link-dark">Играть</a></li>
            <li><a href="notImplemented" class="nav-link px-2 link-dark">Статистика</a></li>
            <li><a href="users" class="nav-link px-2 link-dark">Пользователи</a></li>
        </ul>

        <ul class="nav col-md-3 text-end">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/profile?id=${sessionScope.user.id}"
                           class="nav-link px-2 link-dark">Профиль</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout"
                           class="nav-link px-2 link-dark">Выйти</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/login"
                           class="nav-link px-2 link-dark">Sign-in</a></li>
                    <li><a href="${pageContext.request.contextPath}/signup"
                           class="nav-link px-2 link-dark">Sign-up</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </header>
</div>
