<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .mainDiv {
            display: flex;
            min-height: 100vh;
            align-items: center;
            justify-content: center;
            font-family: Montserrat, 'Open Sans', sans-serif;
            background-color: #fda4e9;
        }
        .cardDiv {
            display: flex;
            padding: 0 20px 20px 20px;
            align-items: center;
            flex-direction: column;
            justify-content: center;
        }
        .cardStyle {
            width: 430px;
            border-color: white;
            background: #fff;
            padding: 36px 0;
            border-radius: 4px;
            margin: 30px 0;
            box-shadow: 0px 0 2px 0 rgba(0,0,0,0.25);
            padding: 40px 23px;
            text-align: center;
        }
        .cardText{
            font-size: 16px;
            color: #414141;
        }
        .warningImg{
            height: 50px;
            width: 50px;
        }
    </style>
</head>
<body>

<div class="mainDiv">
    <div class="cardStyle">
        <img class="warningImg" src="https://s3-us-west-2.amazonaws.com/shipsy-public-assets/shipsy/SHIPSY_LOGO_BIRD_BLUE.png"/>
        <p class="cardText"><b>Link Expired</b></p>
        <p class="cardText">The requested signup link has expired.
            Please contact your admin for further assistance.</p>
    </div>
</div>

</body>
</html>
