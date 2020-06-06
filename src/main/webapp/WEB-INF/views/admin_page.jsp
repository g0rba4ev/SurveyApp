<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-page.css">
  <script src="${pageContext.request.contextPath}/js/libs/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/crypto-js/crypto-js.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/crypto-js/sha256.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/mustache.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/admin/admin-main.js"></script>
  <script src="${pageContext.request.contextPath}/js/admin/admin-render.js"></script>
</head>
<body>
<div id="tabs">
  <ul>
    <li><a href="#user-management-tab">Users Management</a></li>
    <li><a href="#statistics-view-tab">Statistics View</a></li>
    <li class="tab-link" style="float: right"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
  </ul>
  <div id="user-management-tab">
    <fieldset>
      <legend>Add new user</legend>
      <div>
        <input class="ui-button-like" id="new-login" placeholder="Enter new login">
        <input class="ui-button-like" id="new-pwd" placeholder="Enter new password">
        <select class="ui-button-like" id="new-role">
          <option>user</option>
          <option>admin</option>
        </select>
        <input id="add-new-user" class="button" type="button" value="Add user">
      </div>
    </fieldset>
    <fieldset>
      <legend>Change user password</legend>
      <div>
        <input class="ui-button-like" id="login" placeholder="Enter user login">
        <input class="ui-button-like" id="pwd" placeholder="Enter new password">
        <input id="change-user-pwd" class="button" type="button" value="Change password">
      </div>
    </fieldset>

    <br/>
    <table id="users-table"></table>

  </div>
  <div id="statistics-view-tab">
    Посмотреть статистичку
  </div>
</div>
</body>
</html>
