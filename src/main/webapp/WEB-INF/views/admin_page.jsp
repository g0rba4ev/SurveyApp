<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
  <script src="${pageContext.request.contextPath}/js/libs/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/js/admin/admin-main.js"></script>
</head>
<body>
<div id="tabs">
  <ul>
    <li><a href="#user-management-tab">Users Management</a></li>
    <li><a href="#statistics-view-tab">Statistics View</a></li>
    <li class="tab-link" style="float: right"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
  </ul>
  <div id="user-management-tab">
    Добавить пользователя
  </div>
  <div id="statistics-view-tab">
    Посмотреть статистичку
  </div>
</div>
</body>
</html>
