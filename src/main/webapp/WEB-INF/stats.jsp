<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <b>${requestScope.gamesStats}</b>
    <br>
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Название квеcта</th>
            <th>Время</th>
            <th>Игрок</th>
            <th>Исход</th>
        </tr>

        <c:forEach var="playedGame" items="${requestScope.all}">
            <tr>
                <td>${playedGame.getId()}</td>
                <td>${playedGame.getQuest().getName()}</td>
                <td>${playedGame.getCreateDate()}</td>
                <td>${playedGame.getUser().getLogin()}</td>
                <td>${playedGame.getGameState()}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="parts/footer.jsp" %>
