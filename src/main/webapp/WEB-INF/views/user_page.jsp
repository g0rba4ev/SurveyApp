<%@ page import="apps.g0rba4ev.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Main</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
  <script src="${pageContext.request.contextPath}/js/libs/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/mustache.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/main.js"></script>
  <script src="${pageContext.request.contextPath}/js/render.js"></script>
</head>

<body>

<div id="surveyDiv">
  <div id="surveyHead">
    <input id="surveyDate" type="text" readonly>
    <% User user = (User) request.getSession().getAttribute("loggedUser"); %>
    <input id="userName" type="text" value="<%= user.getLogin() %>" readonly>
    <a id="logoutBtn" href="${pageContext.request.contextPath}/logout">Logout</a>
  </div>

  <form id="surveyForm">
    <%-- place for survey questions --%>
  </form>
  <input id="submitAnswerBtn" type="button" value="Submit answer" hidden>
  <input id="updateAnswerBtn" type="button" value="Update answer" hidden>
</div>

</body>
</html>
