const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

document.addEventListener('DOMContentLoaded', function () {
    var isRegisterSuccessValue = document.getElementById('isRegisterSuccess').textContent.trim();
    console.log(isRegisterSuccessValue);

    if (isRegisterSuccessValue === '0') {
        // Retrieve values and error message from the hidden div
        var fullName = document.getElementById('fullName').textContent;
        var phone = document.getElementById('phone').textContent;
        var email = document.getElementById('email').textContent;
        var deliveryAddress = document.getElementById('deliveryAddress').textContent;
        var username = document.getElementById('username').textContent;
        var password = document.getElementById('password').textContent;
        var errorMessage = document.getElementById('errorMessage')

        errorMessage.style.display = 'block'
        document.getElementById('registrationForm').fullName.value = fullName;
        document.getElementById('registrationForm').phone.value = phone;
        document.getElementById('registrationForm').email.value = email;
        document.getElementById('registrationForm').deliveryAddress.value = deliveryAddress;
        document.getElementById('registrationForm').username.value = username;
        document.getElementById('registrationForm').password.value = password;


        setTimeout(function () {
            container.classList.add("right-panel-active");
        }, 150);
    }
    var isLoginSuccess = document.getElementById('isLoginSuccess').textContent.trim();
    if (isLoginSuccess === '0') {
        var errorMessage1 = document.getElementById('errorMessage1')
        errorMessage1.style.display = 'block'
    }
});
