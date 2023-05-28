<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="parts/header.jsp" %>
<div class="container">
    <form class="form-horizontal" action="signup?id=${requestScope.user.id}" method="post">
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

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="roleSelect">Role</label>
                <div class="col-md-4">
                    <select id="roleSelect" name="select" class="form-control">
                        <c:forEach var="role" items="${applicationScope.roles}">
                            <option value="${role}" ${role==requestScope.user.role?"selected":""}>${role}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <br>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="${requestScope.user.id>0?"update":"create"}" class="btn btn-success">
                        ${requestScope.user.id>0?"Update":"Create"}
                    </button>
                </div>
            </div>
            <br>


            <!-- Button -->
            <c:if test="${requestScope.user.id>0 && requestScope.user.id!=sessionScope.user.id}">
                <div class="form-group">
                    <label class="col-md-4 control-label" for="delete"></label>
                    <div class="col-md-4">
                        <button id="delete" name="delete" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.error}">
                <script type="text/javascript">
                    alert("${requestScope.error}");
                </script>
            </c:if>
        </fieldset>
    </form>
</div>
<%@include file="parts/footer.jsp" %>
