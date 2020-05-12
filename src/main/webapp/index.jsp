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
  <h1>Welcome to survey!</h1>
  <div id="surveyDiv">
    <div id="surveyHead" data-survey-date=""></div>
    <form id="surveyForm">

    </form>
    <input id="submitAnswerBtn" type="button" value="Send answer">
  </div>

</body>
</html>
