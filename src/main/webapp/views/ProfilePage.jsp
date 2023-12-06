
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />

    <link rel="stylesheet" href="../CSS/global.css" />
    <link rel="stylesheet" href="../CSS/profile-page.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap" />

    <script>
        function showChangeEmail() {
            document.getElementById("profileload1").style.display = "none";
            document.getElementById("profileload").style.display = "block";
            document.getElementById("buyedload").style.display = "none";
        }

        function showEditProfile() {
            document.getElementById("profileload1").style.display = "block";
            document.getElementById("profileload").style.display = "none";
            document.getElementById("buyedload").style.display = "none";
            console.log(`Original value: "${customer.getDeliveryAddressDefault()}"`);

        }

        function showBuyedLoad() {
            document.getElementById("profileload1").style.display = "none";
            document.getElementById("profileload").style.display = "none";
            document.getElementById("buyedload").style.display = "block";
        }
    </script>
</head>
<body>
<div class="profilepage">
    <div class="component-2infoload">
        <div class="customername">
            <b class="xin-cho-hunh" style="text-transform: uppercase;">XIN CHÀO ${customer.getCustomerName()} !</b>
        </div>
        <div class="editprofile" onclick="showEditProfile()">
            <div class="editprofilecontext">Chỉnh sửa thông tin</div>
        </div>
        <div class="buyed" onclick="showBuyedLoad()">
            <div class="editprofilecontext1">Đơn đã mua</div>
        </div>
        <div id="profileload1" class="profileload1">
            <form action="profile" method="post" id="profileForm">
            <div class="info1">
                <div class="cusinfolabel1">
                    <div class="cusnamelabel">
                        <b class="h-tn">Họ tên</b>
                    </div>
                    <div class="cusnamelabel">
                        <b class="h-tn">Mail </b>
                    </div>
                    <div class="cusnamelabel">
                        <b class="h-tn">Số điện thoại</b>
                    </div>
                    <div class="deliaddresslabel">
                        <b class="a-ch-giao">Địa chỉ giao hàng</b>
                    </div>
                </div>
                <div class="cusinfovalue1">
                    <div class="cusname">
                        <input name="CustomerName" class="h-tn" value="${customer.getCustomerName()}" style=" border-radius: 2px; font-size: 20px; font-family: var(--font-arial);"></input>
                    </div>
                    <div class="mailaddressandchange">
                        <div class="mailaddress1">
                            <div class="div">${customer.getEmailAddress()}</div>
                        </div>
                        <div class="mailaddresschange" onclick="showChangeEmail()">
                            <div class="thay-i">Thay đổi</div>
                        </div>
                    </div>
                    <div class="phoneandchange">
                        <div class="phone">
                            <input name="PhoneNum" class="div" value="${customer.getPhoneNumber()}" style="border-radius: 2px; font-size: 20px; font-family: var(--font-arial);"></input>
                        </div>
                    </div>
                    <div class="deliaddress"  >
                        <textarea name="Deli" class="a-ch-giao"  style="font-size: 20px; border-radius: 2px; font-family: var(--font-arial);">${customer.getDeliveryAddressDefault().trim()}</textarea>
                    </div>
                </div>
            </div>
            <div class="title1">
                <b class="thng-tin-c1">Thông tin cá nhân</b>
            </div>
            <input  class="savebutton1" name="action" value="Save" type="submit" />
            </form>
        </div>
        <div id="profileload" class="profileload1" style="display: none;">
            <form action="sendConfirmationMail" method="post">
            <div class="info">
                <div class="cusinfolabel">
                    <div class="newmaillabel">
                        <b class="nhp-mail-mi">Email cũ của bạn</b>
                    </div>
                    <div class="newmaillabel">
                        <b class="nhp-mail-mi">Nhập mail mới</b>
                    </div>
                </div>
                <div class="    cusinfovalue">
                    <div class="mailaddress">
                        <div class="h-tn" style=" border-radius: 2px; font-size: 20px; font-family: var(--font-arial);">${customer.getEmailAddress()}</div>
                    </div>
                    <div class="mailaddress">
                        <input name="MailChange"
                               class="h-tn"
                               value=""
                               style="border-radius: 2px; font-size: 20px; font-family: var(--font-arial);"
                               pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"
                               title="Vui lòng nhập địa chỉ email hợp lệ"
                               required>
                    </div>
                </div>
            </div>
            <div class="title">
                <b class="thng-tin-c">Thông tin cá nhân</b>
            </div>
            <div>
                <input class="savebutton" class="gi-mail-xc" name="action" value="Send mail" type="submit" ></input>
            </div>
            </form>
        </div>

        <div id="buyedload" class="profileload1" style="display: none;"></div>
    </div>
</div>
</body>
</html>
