<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login page</title>
  <script src="${pageContext.request.contextPath}/js/libs/jquery.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/crypto-js/crypto-js.js"></script>
  <script src="${pageContext.request.contextPath}/js/libs/crypto-js/sha256.js"></script>
  <script src="${pageContext.request.contextPath}/js/user/login.js"></script>
</head>

<body>
<h1>Login Page</h1><br>
<form id="login_form" onsubmit="">
  <input id="login" type="text" required placeholder="login" name="login"><br>
  <input id="pwd" type="password" required placeholder="password" name="password"><br><br>
  <input class="button" type="submit" value="Войти">
</form>
</body>
</html>
