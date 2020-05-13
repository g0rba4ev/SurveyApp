<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Main</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/jquery-ui.css">
  <script src="js/libs/jquery.js"></script>
  <script src="js/libs/jquery-ui.js"></script>
  <script src="js/libs/mustache.min.js"></script>
  <script src="js/main.js"></script>
  <script src="js/render.js"></script>
</head>

<body>
  <div id="surveyDiv">
    <div id="surveyHead">
      <input id="userName" type="text" placeholder="Enter your name">
      <input id="surveyDate" type="text" readonly>
    </div>
    <form id="surveyForm">
      <%-- place for survey questions --%>
    </form>
    <input id="submitAnswerBtn" type="button" value="Submit answer" hidden>
    <input id="updateAnswerBtn" type="button" value="Update answer" hidden>
  </div>

</body>
</html>
