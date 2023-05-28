<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="parts/header.jsp" %>
<%@page import="ru.javarush.quest.bogdanov.questdelta.entities.Role" %>
<div class="container">
    <c:if test="${not empty sessionScope.user}">
        <c:if test="${sessionScope.user.getRole() == Role.ADMIN}">
            <div class="buttons">
                <button onclick="window.location.href='user?id=0'">Create new user!</button>
            </div>
        </c:if>
    </c:if>
    <br>
    <c:forEach var="user" items="${requestScope.all}">
        <div class="users">
            <a href="user?id=${user.getId()}">${user.getLogin()}</a>
            <br>
            <b>Роль: ${user.getRole()}</b>
            <br>
            <b>Всего игр сыграно: ${user.getGames().size()}</b>
            <br>
        </div>
        <br>
    </c:forEach>


</div>
<%@include file="parts/footer.jsp" %>
