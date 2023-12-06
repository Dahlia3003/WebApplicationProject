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
            <label id="totalcost">Tổng giá trị đơn hàng: <strong><fmt:formatNumber type="number" value="${cost}" pattern="#,###"/>đ</strong></label>
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
                        function confirmOrders(){
                            $.ajax({
                                url: 'checkout?paid=true&method='+method,
                                type: 'POST',
                                success: function() {
                                },
                                error: function(jqXHR, textStatus, errorThrown) {
                                    console.error('Error:', textStatus, errorThrown);
                                }
                            });
                            var message = document.getElementById('message');
                            message.innerHTML = "Xác nhận thanh toán thành công! <a href='/homeservlet'>Về trang chủ</a>";
                        }
                        function hideAll()
                        {
                            var codDiv = document.getElementById("cod_detail");
                            var paypalDiv = document.getElementById("pp_detail");
                            codDiv.style.display = "none";
                            paypalDiv.style.display = "none";
                            var buttonDiv = document.querySelectorAll(".method");
                            buttonDiv.forEach(function(div) {
                                div.style.display = "none";
                            });
                            var label = document.getElementById("totalcost");
                            label.innerHTML='Tổng giá trị đơn hàng: <strong>0đ</strong>';
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
                                    confirmOrders();
                                    alert('Transaction completed by ' + details.payer.name.given_name + '!');
                                    hideAll()
                                });
                            }


                        }).render('#paypal-button-container');
                    </script>
                </section>
            </section>
        </section>
<%--        <a href="${request.contextPath}/views/checkout?paid=true&method=Thanh toán khi nhận hàng" id="pay" style="text-decoration: none; color:black; width: 100%">--%>
            <button class="button" id="checkout" onclick="confirmOrder(this)">
                <label>Xác nhận thanh toán</label>
            </button>
<%--        </a>--%>
    </section>
</article>
<script src="../JS/checkout.js"></script>
</body>
</html>