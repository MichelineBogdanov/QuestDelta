<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="${pageContext.request.contextPath}/static/main.css" rel="stylesheet" type="text/css">
<html>
<head>
    <title>Квесты онлайн</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/" class="headerPanel">Главная</a></li>
            <li><a href="${pageContext.request.contextPath}/quests" class="headerPanel">Квесты</a></li>
            <li><a href="${pageContext.request.contextPath}/notimplemented" class="headerPanel">Создать</a></li>
            <li><a href="${pageContext.request.contextPath}/game" class="headerPanel">Играть</a></li>
            <li><a href="${pageContext.request.contextPath}/stats" class="headerPanel">Статистика</a></li>
            <li><a href="${pageContext.request.contextPath}/users" class="headerPanel">Пользователи</a></li>
        </ul>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/profile?id=${sessionScope.user.id}"
                           class="headerPanel">Профиль</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout"
                           class="headerPanel">Выйти</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/login"
                           class="headerPanel">Войти</a></li>
                    <li><a href="${pageContext.request.contextPath}/signup"
                           class="headerPanel">Регистрация</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </header>
</div>

