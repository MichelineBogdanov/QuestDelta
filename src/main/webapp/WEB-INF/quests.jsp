<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <c:forEach var="quest" items="${requestScope.all}">
        <div class="quests">
            <h2>${quest.getName()}</h2>
            <b>Описание: ${quest.getDescription()}</b>
            <br>
            <b>Автор: ${quest.getAuthor().getLogin()}</b>
            <br>
            <div class="buttons">
                <button onclick="window.location.href='/game?questid=${quest.id}'">Играть!</button>
            </div>
        </div>
        <br>
    </c:forEach>
</div>
<%@include file="parts/footer.jsp" %>
