<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
  <script src="${pageContext.request.contextPath}/js/libs/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/jquery-ui.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/crypto-js/crypto-js.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/crypto-js/sha256.js"></script>
  <script src="${pageContext.request.contextPath}/js/login.js"></script>
</head>

<body>
<h1>Welcome to Survey Application!</h1>
<form id="login_form" onsubmit="">
  <br>
  <input id="login" type="text" required placeholder="login" name="login"><br>
  <br>
  <input id="pwd" type="password" required placeholder="password" name="password"><br>
  <br>
  <input class="button" type="submit" value="login">
</form>
</body>
</html>
