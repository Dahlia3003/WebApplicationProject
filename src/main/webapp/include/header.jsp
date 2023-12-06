<!-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nav Bar</title>
    <link rel="stylesheet" href="CSS/NavCss.css">
    <link   rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap">
</head>

<body>
<div class="nav_cover">

</div>
<div class = nav_container>
    <div class="width_wrapper_div">
        <div class="icon_div underlinestroke">
            <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="25" height="25" viewBox="0 0 30 30">
                <path d="M25.565,9.785c-0.123,0.077-3.051,1.702-3.051,5.305c0.138,4.109,3.695,5.55,3.756,5.55 c-0.061,0.077-0.537,1.963-1.947,3.94C23.204,26.283,21.962,28,20.076,28c-1.794,0-2.438-1.135-4.508-1.135 c-2.223,0-2.852,1.135-4.554,1.135c-1.886,0-3.22-1.809-4.4-3.496c-1.533-2.208-2.836-5.673-2.882-9 c-0.031-1.763,0.307-3.496,1.165-4.968c1.211-2.055,3.373-3.45,5.734-3.496c1.809-0.061,3.419,1.242,4.523,1.242 c1.058,0,3.036-1.242,5.274-1.242C21.394,7.041,23.97,7.332,25.565,9.785z M15.001,6.688c-0.322-1.61,0.567-3.22,1.395-4.247 c1.058-1.242,2.729-2.085,4.17-2.085c0.092,1.61-0.491,3.189-1.533,4.339C18.098,5.937,16.488,6.872,15.001,6.688z"></path>
            </svg>
        </div>
        <div class="text_div underlinestroke">Apple</div>
        <div class="text_div underlinestroke">Samsung</div>
        <div class="text_div underlinestroke">Xiaomi</div>
        <div class="text_div underlinestroke">Oppo</div>
        <div class="text_div underlinestroke">Realmi</div>
        <div class="text_div underlinestroke">Vivo</div>
        <div class="text_div underlinestroke">Nokia</div>
        <div class="text_div underlinestroke">Asus</div>
        <div class="text_div underlinestroke">Google</div>
        <div class="text_div underlinestroke">Nubia</div>
        <c:choose>
            <c:when test="${not empty Username}">
                <div class="text_div underlinestroke">${Username}</div>
            </c:when>
            <c:otherwise>
                <div class="text_div">Đăng Nhập</div>
            </c:otherwise>
        </c:choose>
        <div class="icon_div underlinestroke" id="iconSearch" onclick="toggleIconSearch()" >
            <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15" fill="none">
                <path d="M13.9857 13.202L10.1157 9.33195C10.8167 8.40295 11.2377 7.25095 11.2377 5.99995C11.2377 2.93995 8.7487 0.449951 5.6877 0.449951C2.6277 0.449951 0.137695 2.93995 0.137695 5.99995C0.137695 9.06095 2.6277 11.55 5.6877 11.55C6.9387 11.55 8.0907 11.129 9.0197 10.428L12.8897 14.298C13.0407 14.449 13.2397 14.526 13.4377 14.526C13.6357 14.526 13.8337 14.45 13.9857 14.298C14.2887 13.995 14.2887 13.505 13.9857 13.202ZM1.2377 5.99995C1.2377 3.54595 3.2347 1.54995 5.6877 1.54995C8.1417 1.54995 10.1377 3.54695 10.1377 5.99995C10.1377 8.45295 8.1417 10.45 5.6877 10.45C3.2337 10.45 1.2377 8.45395 1.2377 5.99995Z" fill="black" fill-opacity="0.8"/>
            </svg>
        </div>
        <div class="icon_div underlinestroke">
            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="15" viewBox="0 0 14 15" fill="none">
                <path d="M11.0408 3.0283H10.0203C9.91088 2.21728 9.51453 1.4723 8.90308 0.928393C8.29162 0.384485 7.50553 0.0776475 6.68729 0.0634995C5.86905 0.0776475 5.08295 0.384485 4.4715 0.928393C3.86004 1.4723 3.46369 2.21728 3.35429 3.0283H2.33429C1.77307 3.02867 1.23494 3.25172 0.838032 3.64848C0.441121 4.04524 0.217868 4.58329 0.217285 5.1445V12.86C0.217788 13.4211 0.44089 13.9592 0.837631 14.356C1.23437 14.7528 1.77234 14.9761 2.33349 14.9767H11.0405C11.6017 14.9762 12.1399 14.7531 12.5367 14.3562C12.9336 13.9594 13.1568 13.4212 13.1573 12.86V5.1445C13.1567 4.58338 12.9335 4.0454 12.5367 3.64866C12.1399 3.25191 11.6019 3.0288 11.0408 3.0283ZM6.68729 1.1631C7.2147 1.17456 7.7224 1.36567 8.12647 1.70482C8.53053 2.04397 8.80676 2.51086 8.90949 3.0283H4.46509C4.56781 2.51086 4.84404 2.04397 5.2481 1.70482C5.65217 1.36567 6.15987 1.17456 6.68729 1.1631ZM12.0573 12.86C12.057 13.1296 11.9498 13.388 11.7592 13.5787C11.5687 13.7694 11.3103 13.8767 11.0407 13.8771H2.33379C2.06423 13.8766 1.80585 13.7693 1.6153 13.5787C1.42475 13.388 1.31758 13.1296 1.31729 12.86V5.1445C1.3176 4.87498 1.42481 4.61659 1.61539 4.42601C1.80597 4.23542 2.06436 4.12822 2.33389 4.1279H11.0409C11.3104 4.12827 11.5687 4.2355 11.7593 4.42608C11.9498 4.61665 12.057 4.87501 12.0573 5.1445V12.86Z" fill="black" fill-opacity="0.8"/>
            </svg>
        </div>
    </div>
</div>

<div class="search_container">
    <div class="search_wrapper">
        <input type="text" class="search-input" placeholder="Find Something">
        <div>
            <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15" fill="none">
                <path d="M13.9857 13.202L10.1157 9.33195C10.8167 8.40295 11.2377 7.25095 11.2377 5.99995C11.2377 2.93995 8.7487 0.449951 5.6877 0.449951C2.6277 0.449951 0.137695 2.93995 0.137695 5.99995C0.137695 9.06095 2.6277 11.55 5.6877 11.55C6.9387 11.55 8.0907 11.129 9.0197 10.428L12.8897 14.298C13.0407 14.449 13.2397 14.526 13.4377 14.526C13.6357 14.526 13.8337 14.45 13.9857 14.298C14.2887 13.995 14.2887 13.505 13.9857 13.202ZM1.2377 5.99995C1.2377 3.54595 3.2347 1.54995 5.6877 1.54995C8.1417 1.54995 10.1377 3.54695 10.1377 5.99995C10.1377 8.45295 8.1417 10.45 5.6877 10.45C3.2337 10.45 1.2377 8.45395 1.2377 5.99995Z" fill="black" fill-opacity="0.8"/>
            </svg>
        </div>
    </div>
</div>

<script src="JS/nav.js"></script>
</body>

</html>