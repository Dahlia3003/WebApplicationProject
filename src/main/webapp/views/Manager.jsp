<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />
    <title>Thanh toán</title>
    <link rel="stylesheet" href="../CSS/Manager.css" >
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap" type="text/css">
</head>
<body>
<article>
    <nav>
        <div class="logo">
        </div>
        <div class="object">
            <div class="button" onclick="selected(this)">
                <label>Product</label>
            </div>
            <div class="button" onclick="selected(this)">
                <label>Order</label>
            </div>
            <div class="button" onclick="selected(this)">
                <label>Account</label>
            </div>
        </div>
    </nav>
    <section class="manager">
        <section class="Account">
            <h2>Customer List</h2>
            <table border="1">
                <thead>
                <tr>
                    <th>User ID</th>
                    <th>Password</th>
                    <th>Register Date</th>
                    <!-- Additional Customer Fields -->
                    <th>Customer Name</th>
                    <th>Phone Number</th>
                    <th>Email Address</th>
                    <th>Delivery Address</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="customer" items="${customerList}">
                    <tr>
                        <td>${customer.userID}</td>
                        <td>${customer.password}</td>
                        <td>${customer.registerDate}</td>
                        <td>${customer.customerName}</td>
                        <td>${customer.phoneNumber}</td>
                        <td>${customer.emailAddress}</td>
                        <!-- Apply inline style to the Delivery Address column -->
                        <td style="max-width: 200px; word-wrap: break-word;">${customer.deliveryAddressDefault}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <section class="Order">
            <h2>Order List</h2>
            <table border="1">
                <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Product List</th>
                    <th>Date Ordered</th>
                    <th>Customer Name</th>
                    <th>Status</th>
                    <th>Payment Method</th>
                    <th>Total Cost</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td>${order.id}</td>
                        <td>
                            <ul>
                                <c:forEach var="cartLine" items="${order.productList}">
                                    <li>${cartLine.product.productName} x ${cartLine.quantity}</li></c:forEach>
                            </ul>
                        </td>
                        <td>${order.dateOrder}</td>
                        <td>${order.customer.customerName}</td>
                        <td>
  <span id="status-${order.id}" class="${order.status == 'Đã giao' ? 'Đã giao' : 'Đang giao'}" onclick="toggleStatus(${order.id})">
          ${order.status}
  </span>
                        </td>
                        <td>${order.paymentMethod}</td>
                        <td>${order.totalCost}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
    </section>
</article>
<script src="../JS/Manager.js"></script>
<style>
    .delivered {
        color: green;
    }

    .pending {
        color: orange;
    }
</style>
<script>
    function toggleStatus(orderId) {
        var statusElement = document.getElementById('status-' + orderId);
        var currentStatus = statusElement.innerText;

        // Chuyển đổi trạng thái
        var newStatus = currentStatus === 'Delivered' ? 'Pending' : 'Delivered';

        // Cập nhật màu sắc và văn bản
        statusElement.innerText = newStatus;
        statusElement.className = newStatus === 'Delivered' ? 'delivered' : 'pending';

        // Gọi Ajax để cập nhật trạng thái trong cơ sở dữ liệu
        updateOrderStatus(orderId, newStatus);
    }
    function updateOrderStatus(orderId, newStatus) {
        // Tạo một đối tượng XMLHttpRequest
        var xhr = new XMLHttpRequest();

        // Xác định phương thức và URL của request
        var url = '/updateOrderStatus';
        var params = 'orderId=' + encodeURIComponent(orderId) + '&newStatus=' + encodeURIComponent(newStatus);

        // Mở kết nối
        xhr.open('POST', url, true);

        // Thiết lập tiêu đề của request
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        // Định nghĩa hàm xử lý khi request hoàn thành
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Xử lý phản hồi từ server (nếu cần)
                console.log(xhr.responseText);
            }
        };

        // Gửi request với tham số
        xhr.send(params);
    }
</script>
</body>
</html>
