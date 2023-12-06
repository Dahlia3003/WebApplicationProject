<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Password Change</title>
    <style>
        html,body{
            min-height:100%
        }
        body {
            background: #f6f6f6 url(https://goo.gl/1yhE3P) top center no-repeat;
            background-size:cover;
            font-family: "Open Sans", sans-serif;
            font-size: 14px;
            margin:0
        }
        #box {
            width: 400px;
            margin: 10% auto;
            text-align: center;
            background:rgba(255,255,255,0.6);
            padding:20px 50px;
            box-sizing:border-box;
            box-shadow:0 3px 12px rgba(0,0,0,0.2);
            border-radius:2%
        }
        h1 {
            margin-bottom: 1.5em;
            font-size: 30px;
            color: #484548;
            font-weight: 100;
        }
        h1 span, small {
            display:block;
            font-size: 14px;
            color: #989598;
        }
        small{ font-style: italic;
            font-size: 11px;}
        form p { position: relative; }

        .password {
            width: 90%;
            padding: 15px 12px;
            margin-bottom: 5px;
            border: 1px solid #e5e5e5;
            border-bottom: 2px solid #ddd;
            background: rgba(255,255,255,0.2) !important;
            color: #555;
        }
        .password + .unmask {
            position: absolute;
            right: 5%;
            top: 10px;
            width: 25px;
            height: 25px;
            background: transparent;
            border-radius: 50%;
            cursor:pointer;
            border: none;
            font-family:FontAwesome;
            font-size:14px;
            line-height:24px;
            -webkit-appearance:none;
            outline:none
        }
        .password + .unmask:before {
            content: "\f06e";
            position:absolute;
            top:0; left:0;
            width: 25px;
            height: 25px;
            background: rgba(205,205,205,0.2);
            z-index:1;
            color:#aaa;
            border:2px solid;
            border-radius: 50%;
        }
        .password[type="text"] + .unmask:before {
            content:"\f070";
            background: rgba(105,205,255,0.2);
            color:#06a
        }
        #valid{
            font-size:12px;
            color:#daa;
            height:15px
        }
        #strong{
            height:20px;
            font-size:12px;
            color:#daa;
            text-transform:capitalize;
            background:rgba(205,205,205,0.1);
            border-radius:5px;
            overflow:hidden
        }

        #strong span{
            display:block;
            box-shadow:0 0 0 #fff inset;
            height:100%;
            transition:all 0.8s
        }
        #strong .weak{
            box-shadow:5em 0 0 #daa inset;
        }
        #strong .medium{
            color:#da6;
            box-shadow:10em 0 0 #da6 inset
        }
        #strong .strong{
            color:#595;
            box-shadow:50em 0 0 #ada inset
        }
        #submitBtn {
            width: 90%; /* Adjust the width as needed */
            margin: 10px auto; /* Center the button horizontally */
            background: none; /* Remove background */
            border: none; /* Remove border */
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2); /* Add initial box shadow */
            transition: box-shadow 0.3s ease; /* Add transition to box shadow */
        }

        #submitBtn:hover {
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.4); /* Change box shadow on hover */
        }
    </style>
</head>
<body>
<div id="box">
    <form id="myform-search" action="passwordservlet" method="post" autocomplete="off">
        <h1>Change Password <span>choose a good one!</span></h1>
        <p>
            <input type="password" name="password" value="" placeholder="Enter Password" id="p" class="password">
            <button class="unmask" type="button"></button>
        </p>
        <p>
            <input type="password" value="" placeholder="Confirm Password" id="p-c" class="password">
            <button class="unmask" type="button"></button>
        </p>
        <input type="hidden" name="email" value="${email}">
        <button id="submitBtn" type="submit" style="display:none">Submit</button>
        <div id="strong"><span></span></div>
        <div id="valid"></div>
        <small>Must be 6+ characters long and contain at least 1 upper case letter, 1 number, 1 special character</small>
        <!-- Add the submit button with display:none initially -->
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $('.unmask').on('click', function(){
        if($(this).prev('input').attr('type') == 'password')
            $(this).prev('input').prop('type', 'text');
        else
            $(this).prev('input').prop('type', 'password');
        return false;
    });

    $('.password').on('input', function() {
        var p_c = $('#p-c');
        var p = $('#p');
        if (p.val().length > 0) {
            if (p.val() != p_c.val()) {
                $('#valid').html("Passwords Don't Match");
                $('#submitBtn').css('display', 'none');
            } else {
                $('#valid').html('');
                $('#submitBtn').css('display', 'block');
            }
            var s = 'weak';
            if (p.val().length > 5 && p.val().match(/\d+/g))
                s = 'medium';
            if (p.val().length > 6 && p.val().match(/[^\w\s]/gi))
                s = 'strong';
            $('#strong span').attr('class', s).html(s);
        } else {
            // Reset the strength indicator and hide the submit button if the password is empty
            $('#valid').html('');
            $('#submitBtn').css('display', 'none');
            $('#strong span').attr('class', '').html('');
        }
    }
    );
    const urlParams = new URLSearchParams(window.location.search);
    const email = urlParams.get('email');
    const code = urlParams.get('code');

    document.querySelector('input[name="email"]').value = email;
</script>

</body>
</html>