<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />
    <title>Thanh toán</title>
    <link rel="stylesheet" href="../CSS/checkout.css" >
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap" type="text/css">
</head>
<body>
<article>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <section class="head">
        <div class="text">
            <h1>Thanh toán</h1>
        </div>
        <div class="cost">
            <label>Tổng giá trị đơn hàng: <strong><fmt:formatNumber type="number" value="${cost}" pattern="#,###"/>đ</strong></label>
        </div>
    </section>
    <section class="payment">
        <h1 id="message">${message}</h1>
        <section class="payment_info">
            <section class="select_payment">
                <div class="method" id="cod" onclick="displayPaymentInfo('cod')">
                    <label>Thanh toán khi nhận hàng</label>
                </div>
                <div class="method" id="paypal_bt" onclick="displayPaymentInfo('paypal_bt')">
                    <label>Ví điện tử Paypal</label>
                </div>
            </section>
            <section class="payment_detail">
                <section class="cod" id="cod_detail">
                    <div class="left">
                        <label>Khách hàng:</label>
                        <label>Số điện thoại:</label>
                        <label>Email:</label>
                        <label>Địa chỉ:</label>
                    </div>
                    <div class="right">
                        <label>${customer.customerName}</label>
                        <label>${customer.phoneNumber}</label>
                        <label>${customer.emailAddress}</label>
                        <label>${customer.deliveryAddressDefault}</label>
                    </div>
                </section>
                <section class="ppal" id="pp_detail">
                    <div id="paypal-button-container"></div>
                    <script
                            src="https://www.paypal.com/sdk/js?client-id=test&currency=USD"></script>
                    <script>
                        function getCookie(name) {
                            var cookieValue = null;
                            if (document.cookie && document.cookie !== '') {
                                var cookies = document.cookie.split(';');
                                for (var i = 0; i < cookies.length; i++) {
                                    var cookie = cookies[i].trim();
                                    // Does this cookie string begin with the name we want?
                                    if (cookie.substring(0, name.length + 1) === (name + '=')) {
                                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                                        break;
                                    }
                                }
                            }
                            return cookieValue;
                        }
                        var csrftoken = getCookie('csrftoken');

                        var total = 0.01
                        var productId = '{{object.id}}'

                        function completeOrder() {
                            var url = "{% url 'complete' %}"

                            fetch(url, {
                                method: 'POST',
                                headers: {
                                    'content-type': 'application/json',
                                    'X-CSRFToken': csrftoken,
                                },
                                body: JSON.stringify({ 'productId': productId })
                            })
                        }

                        // Render the PayPal button into #paypal-button-container
                        paypal.Buttons({
                            // Set up the transaction
                            createOrder: function (data, actions) {
                                return actions.order.create({
                                    purchase_units: [{
                                        amount: {
                                            value: total
                                        }
                                    }]
                                });
                            },

                            // Finalize the transaction
                            onApprove: function (data, actions) {
                                return actions.order.capture().then(function (details) {
                                    // Show a success message to the buyer
                                    completeOrder()
                                    alert('Transaction completed by' + details.payer.name.given_name + '!');
                                });
                            }


                        }).render('#paypal-button-container');
                    </script>
                </section>
            </section>
        </section>
<%--        <a href="${request.contextPath}/views/checkout?paid=true&method=Thanh toán khi nhận hàng" id="pay" style="text-decoration: none; color:black; width: 100%">--%>
            <button class="button" onclick="confirmOrder(this)">
                <label>Xác nhận thanh toán</label>
            </button>
<%--        </a>--%>
    </section>
</article>
<script src="../JS/checkout.js"></script>
</body>
</html>