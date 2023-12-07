<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />
    <title>Thanh toán</title>
    <link rel="stylesheet" href="../CSS/Manager.css" >
    <link rel="stylesheet" href="../CSS/ProductManager.css" >
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap" type="text/css">
</head>
<body>
<article>
    <nav>
        <div class="logo">
        </div>
        <div class="object">
            <div class="button" onclick="selected(this,'Product')" name="Product">
                <label>Product</label>
            </div>
            <div class="button" onclick="selected(this,'Order')" value="Order">
                <label>Order</label>
            </div>
            <div class="button" onclick="selected(this,'Account')" value="Account">
                <label>Account</label>
            </div>
        </div>
    </nav>
    <section class="manager">
        <section class="Product">
            <div class="function">
                <button style="height: 50%; margin-right: 10rem" onclick="addNew()">
                    Add new
                </button>
            </div>
            <section class="view">
                <div class="list">
                    <table>
                        <thead>
                        <tr>
                            <th style="width:3rem">ID</th>
                            <th style="width:6rem">Brand</th>
                            <th style="width:18rem">Product Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr id="${product.productId}" onclick="productClicked(this)">
                                <td>${product.productId}</td>
                                <td>${product.brand}</td>
                                <td>${product.productName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="form">
                    <label id="prid" value="">Product ID: </label><br>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Brand: </label><input type="text" id="brand"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Line: </label><input type="text" id="line"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Product Name: </label><input type="text" id="name"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Price: </label><input type="text" id="price"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Variation: </label><input type="text" id="vari"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Image Link: </label><input type="text" id="link"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Chip: </label><input type="text" id="chip"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>RAM/ROM: </label><input type="text" id="rom"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Screen: </label><input type="text" id="scr"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Camera: </label><input type="text" id="cam"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Pin: </label><input type="text" id="pin"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Connection: </label><input type="text" id="connect"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Available: </label><input type="checkbox" id="avai"><br></div>
                    <div style="display: flex; margin-bottom: 0.5rem"><label>Tag: </label>       <label for="flagshipCheckbox">
                        <input type="checkbox" id="flagshipCheckbox" class="tag" value="Flagship">
                        Flagship
                    </label>

                        <label for="pinTrauCheckbox">
                            <input type="checkbox" id="pinTrauCheckbox" class="tag"  value="Pin trâu">
                            Pin trâu
                        </label>

                        <label for="gamingCheckbox">
                            <input type="checkbox" id="gamingCheckbox" class="tag"  value="Gaming">
                            Gaming
                        </label>

                        <label for="baoSongAoCheckbox">
                            <input type="checkbox" id="baoSongAoCheckbox" class="tag"  value="Bao sống ảo">
                            Bao sống ảo
                        </label><br></div>
                    <button id="bt" value="update" onclick="buttonCLick(this)">
                        Update
                    </button>
                    <label id="noti"></label>
                </div>
                <div class="pic">
                    <img id="pic" src="" style="max-width: 15rem">
                </div>
            </section>
        </section>
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
                    <th>Action</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="customer" items="${customerList}">
                    <tr>
                        <td contenteditable="true" onBlur="updateAccountField('${customer.userID}', 'userId', this)">${customer.userID}</td>
                        <td contenteditable="true" onBlur="updateAccountField('${customer.userID}', 'password', this)">${customer.password}</td>
                        <td>${customer.registerDate}</td>
                        <td contenteditable="true" onBlur="updateAccountField('${customer.userID}', 'customerName', this)">${customer.customerName}</td>
                        <td contenteditable="true" onBlur="updateAccountField('${customer.userID}', 'phoneNumber', this)">${customer.phoneNumber}</td>
                        <td contenteditable="true" onBlur="updateAccountField('${customer.userID}', 'emailAddress', this)">${customer.emailAddress}</td>
                        <!-- Apply inline style to the Delivery Address column -->
                        <td contenteditable="true" onBlur="updateAccountField('${customer.userID}', 'deliveryAddressDefault', this)" style="max-width: 200px; word-wrap: break-word;">${customer.deliveryAddressDefault}</td>
                        <td><button onclick="deleteAccount('${customer.userID}')">Delete</button></td>
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

    function updateAccountField(userId, field, element) {
        var value = element.innerText;

        // Gọi Ajax để cập nhật giá trị trong cơ sở dữ liệu
        updateAccountFieldAjax(userId, field, value);
    }

    function updateAccountFieldAjax(userId, field, value) {
        // Tạo một đối tượng XMLHttpRequest
        var xhr = new XMLHttpRequest();

        // Xác định phương thức và URL của request
        var url = '/updateAccountField';
        var params = 'userId=' + encodeURIComponent(userId) + '&field=' + encodeURIComponent(field) + '&value=' + encodeURIComponent(value);

        // Mở kết nối
        xhr.open('POST', url, true);

        // Thiết lập tiêu đề của request
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        // Định nghĩa hàm xử lý khi request hoàn thành
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Xử lý phản hồi từ server (nếu cần)
                console.log(xhr.responseText);
            }
        };

        // Gửi request với tham số
        xhr.send(params);
    }
    function deleteAccount(userId) {
        var confirmation = confirm('Are you sure you want to delete this account?');

        if (confirmation) {
            // Gọi Ajax để xóa tài khoản
            deleteAccountAjax(userId);
        }
    }

    function deleteAccountAjax(userId) {
        var xhr = new XMLHttpRequest();
        var url = '/deleteAccount';
        var params = 'userId=' + encodeURIComponent(userId);

        xhr.open('POST', url, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // Xóa dòng từ bảng
                    var row = document.getElementById(userId);
                    row.parentNode.removeChild(row);

                    console.log(xhr.responseText);
                } else {
                    console.error('Error deleting account');
                }
            }
        };

        xhr.send(params);
    }

</script>
</body>
</html>
