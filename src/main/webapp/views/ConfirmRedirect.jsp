<html>
<head>
  <style>
    body {
      color: #414141;
    }

    .container {
      font-family: 'Helvetica Neue', Helvetica;
      text-align: center;
      padding: 5px;
    }

    .text-container {
      width: 90%;
      max-width: 800px;
      font-weight: 300;
      margin: 0 auto;
      padding: 15px;
      padding-bottom: 15px;
    }

    h1 {
      font-weight: 100;
    }

    a {
      text-decoration: none;
    }

    .button {
      padding: 15px;
      font-family: 'Helvetica Neue', Helvetica;
      font-size: 18px;
      color: white;
      background-color: #E6A1C3;
      border: 0;
      border-radius: 5px;
      margin: 10px;
      display: block;
      max-width: 200px;
      margin: auto;
      text-decoration: none;
    }

    p {
      line-height: 1.5;
    }
  </style>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
<div class="container" id="mobile">
  <div class="text-container">
    <h1><i class="fa fa-check-circle" aria-hidden="true" style="color: #E6A1C3;"></i> Email verified</h1>
    <p>Your email is verified for Lomotif username <a href="#" style="font-weight: bold; color: #9DA4DB;">@someusername</a>.</p>
    <p>Redirecting you to Lomotif app in <span id="count">5</span>.</p>
    <a href="http://localhost:8080/WebAppProject_war_exploded/views/LoginPage.jsp" class="button" id="goToApp">Go to app now</a>
  </div>
</div>

<script>
  // Countdown timer
  var count = 5;
  var redirectTimer = setInterval(function () {
    count--;
    document.getElementById("count").textContent = count;

    if (count === 0) {
      clearInterval(redirectTimer);
      window.location.href = "http://localhost:8080/WebAppProject_war_exploded/views/LoginPage.jsp"; // Replace with your app URL
    }
  }, 1000);

  // Redirect when the "Go to app now" button is clicked
  document.getElementById("goToApp").addEventListener("click", function () {
    clearInterval(redirectTimer);
    window.location.href = "http://localhost:8080/WebAppProject_war_exploded/views/LoginPage.jsp"; // Replace with your app URL
  });
</script>
</body>
</html>
