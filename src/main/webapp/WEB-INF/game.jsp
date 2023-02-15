<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="parts/header.jsp" %>
<%@page import="ru.javarush.quest.bogdanov.questdelta.entities.GameState" %>
<div class="container">
    <div class="questText">
        <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
        <h2>${sessionScope.question.getText()}</h2>
        <div class="buttons">
            <br>
            <c:forEach var="answer" items="${sessionScope.question.getAnswerList()}">
                <button onclick="sendAnswer(this)" id="${answer.getId()}">${answer.getText()}</button>
            </c:forEach>
        </div>
        <c:if test="${sessionScope.game.getGameState() != GameState.STARTED}">
            <div class="buttons">
                <button onclick="window.location.href='/finish'">Завершить!</button>
            </div>
        </c:if>
    </div>
</div>
<script>
    function sendAnswer(button) {
        $.ajax({
            type: 'POST',
            url: '/game?answerid=' + button.id + '&currentquestionid=' + ${sessionScope.game.getCurrentQuestion().getId()},
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.reload();
            }
        });
    }
</script>
<%@include file="parts/footer.jsp" %>
