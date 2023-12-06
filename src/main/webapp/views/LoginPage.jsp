<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/LoginCss.css">
</head>
<body>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form id="registrationForm" action="${pageContext.request.contextPath}/loginregister" method="post">
            <h1>Create Account</h1>
            <p id="errorMessage" style="display: none; font-size: 13px; font-weight: 100;letter-spacing: 0.5px; margin: 0 0 0 0">${errorMessage}</p>
            <!-- <div class="social-container">
                <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
            </div> -->
            <input type="hidden" name="action" value="register" />
            <input type="text" name="fullName" placeholder="Full Name" required />
            <input type="text" name="phone" placeholder="Phone Number" required />
            <input type="email" name="email" placeholder="Email" required />
            <input type="text" name="deliveryAddress" placeholder="Delivery Address" required />
            <input type="text" name="username" placeholder="Username" required />
            <input type="password" name="password" placeholder="Password" required />
            <button type="submit">Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="${pageContext.request.contextPath}/loginregister" method="post">
            <h1>Log in</h1>
            <!-- <div class="social-container">
                <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
            </div> -->
            <p id="errorMessage1" style="display: none; font-size: 13px; font-weight: 100;letter-spacing: 0.5px; margin: 0 0 0 0">Username or password is wrong</p>
            <input type="text" name="logintext" placeholder="Email or Username" required />
            <input type="hidden" name="action" value="login" />
            <input type="password" name="password" placeholder="Password" required />
            <a href="${pageContext.request.contextPath}/views/ForgotPasswordSendMail.jsp">Forgot your password?</a>
            <button>Log In</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>To keep connected with us please login with your personal info</p>
                <button class="ghost" id="signIn">Log In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
                <p>Enter your personal details and start journey with us</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
    <div id="isRegisterSuccess" style="display: none;">${isRegisterSuccess}</div>
    <div id="isLoginSuccess" style="display: none;">${isLoginSuccess}</div>
    <div id="serverValues" style="display:none;">
        <span id="fullName">${fullName}</span>
        <span id="phone">${ phone }</span>
        <span id="email">${ email }</span>
        <span id="deliveryAddress">${ deliveryAddress }</span>
        <span id="username">${ username }</span>
        <span id="password">${ password }</span>
    </div>
</div>
<script src="${pageContext.request.contextPath}/JS/login.js"></script>
</body>
</html>
