<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <style>
        body {
            background-color: white;
            padding: 0;
            width: 100vw;
            height: 100vh;
            font-family: "Open Sans", serif;
            font-weight: 400;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .card {
            font-family: "Open Sans", serif;
            text-align: center;
            width: 500px;
            color: white;
            height: auto;
            background: #f12711;
            background: -webkit-linear-gradient(45deg, #f5af19, #f12711);
            background: linear-gradient(45deg, #f5af19, #f12711);
            border-radius: 0.3rem;
            box-shadow: 2px 2px 12px 6px rgba(0, 0, 0, 0.11);
            padding: 50px 50px 50px 50px;
        }

        h1 {
            font-size: 30px;
        }

        .signup {
            width: 444px;
            height: 50px;
            background-color: white;
            border: 4px solid white;
            box-shadow: 2px 4px 8px 0 rgba(0, 0, 0, 0.11);
            border-radius: 100px;
            margin: auto;
        }

        #email-signup {
            height: 50px;
            background-color: black;
            padding: 0 5px 0 10px;
            width: 300px;
            caret-color: #fc4e03;
            background-color: white;
            font-size: 20px;
            color: #383838;
            outline-style: none;
            border: none;
            border-radius: 100px;
        }

        #btn {
            height: 50px;
            width: 124px;
            background-color: #fc4e03;
            border: none;
            font-size: 18px;
            border-radius: 100px;
            float: right;
            color: white;
        }

        #btn:hover {
            background-color: #e34602;
        }

        #email-signup:hover {
            background-color: #f2f2f2;
        }

        #email-signup:focus {
            background-color: #f2f2f2;
        }

        #spam {
            color: #f2f2f2;
            font-size: 16px;
            padding: 3px;
        }

        @media (max-width: 500px) {
            body {
                margin-top: 50px;
            }

            .card {
                width: 99vw;
                height: auto;
                padding: 0 10px 0 10px;
                overlap: hidden;
            }

            .signup {
                display: inline-flex;
                width: 95vw;
                height: auto;
                margin: 0;
                flex-wrap: wrap;
                border-radius: 0.2rem;
            }

            #btn {
                width: 100%;
                border-radius: 0.2rem;
            }

            #email-signup {
                width: 90vw;
                border-radius: 0.2rem;
            }

            h1 {
                font-size: 24px;
                width: 100%;
            }

            #spam {
                font-size: 14px;
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="card">
    <h1>We will send you a password change link to your email</h1>
    <div class="signup">
        <form action="${pageContext.request.contextPath}/loginregister" method="post">
            <input type="hidden" name="action" value="forgetpassword" />
            <input type="email" name="email" id="email-signup" placeholder="Enter your email here...." required>
            <input type="submit" value="Send to email" id="btn">
        </form>
        <c:if test="${isForgetSuccess eq '0'}">
            <p id="spam">This email doesnt exist!</p>
        </c:if>
        <c:if test="${isForgetSuccess eq '1'}">
            <p id="spam">The email has been sent!</p>
        </c:if>
    </div>

</div>
</body>
</html>
