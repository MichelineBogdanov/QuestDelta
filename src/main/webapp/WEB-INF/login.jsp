<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <c:if test="${not empty requestScope.error}">
        <script type="text/javascript">
            alert("${requestScope.error}");
        </script>
    </c:if>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/login" method="post">
        <fieldset>

            <!-- Form Name -->
            <legend>Form Name</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">Login</label>
                <div class="col-md-4">
                    <input id="userLogin" name="login" type="text" placeholder="placeholder"
                           class="form-control input-md"
                           value="${requestScope.user.login}">
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userPassword">Password</label>
                <div class="col-md-4">
                    <input id="userPassword" name="password" type="password" placeholder="placeholder"
                           class="form-control input-md"
                           value="${requestScope.user.password}">

                </div>
            </div>
            <br>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="SignIn" class="btn btn-success">
                        Sign-in
                    </button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<%@include file="parts/footer.jsp" %>
