<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    // Lấy ngày hiện tại
    Calendar calendar = Calendar.getInstance();

    // Cộng thêm 5 ngày
    calendar.add(Calendar.DAY_OF_MONTH, 5);

    // Format ngày thành chuỗi
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String deliveryDate = sdf.format(calendar.getTime());
%>
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
                <b>Tổng giá trị giỏ hàng của bạn là <fmt:formatNumber type="number" value="${cart.calcTotal()}" pattern="#, ###"/>VND</b>
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
                <!-- Hiển thị thông tin cho mỗi CartLine -->
                <div class="item">
                    <div class="productimg">
                        <img class="mpu73-icon" alt="" src="${cartLine.product.productImage}" />
                    </div>
                    <div class="productlineinfo">
                        <div class="productname">
                            <b class="iphone-14-pro-container">
                                <p class="iphone-14-pro">${cartLine.product.productName}</p>
                            </b>
                        </div>
                        <form action="cart" method="post">
                            <div class="quantity">
                                <b class="iphone-14-pro-container">
                                    Số lượng:
                                    <label>
                                        <input type="number" name="quantity" value="${cartLine.quantity}" min="1" style="background: none ; width: 4rem; height: 1.75rem; border: none; outline: none; font-family: 'Arial', sans-serif; font-weight: bold; font-size: 24px;">
                                    </label>
                                    <input type="hidden" name="cartlineID" value="${cartLine.id}" />
                                    <input class="update-button" name="action" value="update" type="submit" />
                                </b>
                            </div>
                        </form>
                        <div class="unitcost">
                            <div class="price">
                                <b class="iphone-14-pro-container"><fmt:formatNumber type="number" value="${cartLine.uniCost}" pattern="#, ###"/></b>
                            </div>
                            <div class="removeproduct">
                                <a href="${request.contextPath}/views/cart?action=remove&cartlineID=${cartLine.id}" class="xa">Xóa</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- Kết thúc vòng lặp -->
            <div class="summary">
                <div class="summarymain">
                    <div class="unitcosttotal">
                        <div class="summary-label">
                            <div class="tng-ph">Tổng phụ</div>
                        </div>
                        <div class="summary-value">
                            <div class="div"><fmt:formatNumber type="number" value="${cart.calcTotal()}" pattern="#, ###"/></div>
                        </div>
                    </div>
                    <div class="deliverycost">
                        <div class="summary-label1">
                            <div class="tng-ph">Vận chuyển</div>
                        </div>
                        <div class="summary-value1">
                            <div class="min-ph">MIỄN PHÍ</div>
                        </div>
                    </div>
                    <div class="item-fulfillment">
                        <div class="detail">
                            <div class="deliverylocate">
                                <div class="t-hng-hm">Đặt hàng hôm nay.</div>
                                <div class="giao-hng-n">Giao hàng đến:</div>
                                <div class="button-thnh">Thành Phố Hồ Chí Minh</div>
                            </div>
                            <div class="deliverydate">
                                <b class="th-6-<c:out value="${ngaySau5Ngay}" />"><%= deliveryDate %> — Miễn Phí</b>
                            </div>
                        </div>
                        <img class="img-icon" alt="" src="data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='15' height='15' viewBox='0 0 18 19' fill='none'%3E%3Cpath d='M16.672 4.15117L10.6744 0.907467C10.3091 0.709745 9.90026 0.606201 9.48488 0.606201C9.0695 0.606201 8.66067 0.709745 8.29536 0.907467L2.29776 4.15117C1.90152 4.36563 1.57063 4.68324 1.34011 5.07036C1.1096 5.45749 0.988032 5.89973 0.988282 6.35029V12.6168C0.988129 13.0674 1.10985 13.5098 1.34054 13.8969C1.57124 14.284 1.90233 14.6016 2.29876 14.8159L8.29636 18.0596C8.66154 18.2572 9.07019 18.3606 9.48538 18.3606C9.90057 18.3606 10.3092 18.2572 10.6744 18.0596L16.672 14.8159C17.0682 14.6014 17.3991 14.2838 17.6296 13.8967C17.8602 13.5096 17.9817 13.0673 17.9815 12.6168V6.35029C17.9817 5.89973 17.8602 5.45749 17.6296 5.07036C17.3991 4.68324 17.0682 4.36563 16.672 4.15117ZM8.77517 1.78712C8.994 1.66796 9.2392 1.60552 9.48838 1.60552C9.73755 1.60552 9.98276 1.66796 10.2016 1.78712L16.1992 5.02982C16.2518 5.0632 16.3022 5.09992 16.3501 5.13977L13.4203 6.80411L6.68 2.91966L8.77517 1.78712ZM5.64742 3.47744L12.4127 7.37588L9.48488 9.04121L2.62363 5.14277C2.67153 5.10293 2.72195 5.0662 2.77457 5.03282L5.64742 3.47744ZM2.77457 13.9343C2.53679 13.8058 2.33815 13.6154 2.19967 13.3833C2.06118 13.1513 1.98801 12.886 1.98788 12.6158V6.35029C1.98834 6.21858 2.00615 6.0875 2.04086 5.96045L8.98508 9.90687V17.2619C8.91205 17.2399 8.84087 17.2121 8.77217 17.179L2.77457 13.9343ZM16.9819 12.6158C16.9818 12.8859 16.9088 13.1511 16.7705 13.3832C16.6322 13.6153 16.4338 13.8057 16.1962 13.9343L10.1986 17.178C10.1295 17.2114 10.058 17.2394 9.98468 17.2619V9.90687L16.9289 5.95945C16.9636 6.08684 16.9814 6.21825 16.9819 6.35029V12.6158Z' fill='%231D1D1F'/%3E%3C/svg%3E" />
                    </div>
                    <div class="total">
                        <div class="summary-label2">
                            <b class="iphone-14-pro-container"
                            >Tổng Số Tiền Bạn Cần Thanh Toán</b
                            >
                        </div>
                        <div class="summary-value2">
                            <b class="iphone-14-pro-container"><fmt:formatNumber type="number" value="${cart.calcTotal()}" pattern="#, ###"/>VND</b>
                        </div>
                    </div>
                </div>
            </div>
            <a href="/views/checkout" style="text-decoration: none">
                <div class="checkout">
                    <div class="checkoutbutton">
                        <div class="thanh-ton1">Thanh Toán</div>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>
