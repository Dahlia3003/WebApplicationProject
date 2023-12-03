<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />

    <link rel="stylesheet" href="../CSS/global.css" />
    <link rel="stylesheet" href="../CSS/style.css" />
    <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap"
    />
</head>
<body>
<div class="cartpageproduct">
    <div class="maincontent1">
        <div class="textmain1">
            <div class="h1-text1">
                <b class="tng-gi-tr"
                >Tổng giá trị giỏ hàng của bạn là 44.429.000đ.</b
                >
            </div>
            <div class="b1-text1">
                <div class="vn-chuyn-min">
                    Vận chuyển miễn phí đối với mọi đơn hàng.
                </div>
            </div>
            <div class="bluebutton1">
                <div class="thanh-ton">Thanh Toán</div>
            </div>
        </div>
        <div class="productlist">
            <!-- Bắt đầu lặp qua danh sách CartLine trong Cart -->
            <c:forEach var="cartLine" items="${cart.getCartList()}">
                <div class="productline">
                    <!-- Hiển thị thông tin cho mỗi CartLine -->
                    <div class="item">
                        <div class="productimg">
                            <img class="mpu73-icon" alt="" src="${cartLine.product.imageUrl}" />
                        </div>
                        <div class="productlineinfo">
                            <div class="productname">
                                <b class="iphone-14-pro-container">
                                    <p class="iphone-14-pro">${cartLine.product.name}</p>
                                    <p class="iphone-14-pro">${cartLine.product.color}</p>
                                </b>
                            </div>
                            <div class="quantity">
                                <b class="iphone-14-pro-container">${cartLine.quantity}</b>
                            </div>
                            <div class="unitcost">
                                <div class="price">
                                    <b class="iphone-14-pro-container">${cartLine.calcPrice()}đ</b>
                                </div>
                                <div class="removeproduct">
                                    <div class="xa">Xóa</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- Kết thúc vòng lặp -->
        </div>
    </div>
</div>
</body>
</html>

