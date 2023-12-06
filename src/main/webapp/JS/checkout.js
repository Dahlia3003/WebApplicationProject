var method='Thanh toán khi nhận hàng';
function displayPaymentInfo(id) {
    var codDiv = document.getElementById("cod_detail");
    var paypalDiv = document.getElementById("pp_detail");
    //var linkElement = document.getElementById('pay');
    console.log("in");
    if (id=="cod") {
        codDiv.style.display = "flex";
        paypalDiv.style.display = "none";
        method='Thanh toán khi nhận hàng';
        //linkElement.href = 'checkout?paid=true&method=Thanh toán khi nhận hàng';
    }
    else
    if (id=="paypal_bt"){
        codDiv.style.display = "none";
        paypalDiv.style.display = "block";
        method='Ví điện tử Paypal';
        //linkElement.href = 'checkout?paid=true&method=Ví điện tử Paypal';
    }
    selected(id)
}
selected("cod");
function selected(id) {
    var select = document.getElementById(id);
    console.log("ok");
    var buttonDiv = document.querySelectorAll(".method");
    buttonDiv.forEach(function(div) {
        div.style.border = "1px solid #86868B";
    });
    select.style.border = "1px solid #FF0000"
}
function confirmOrder(self){
    var mg='';
    $.ajax({
        url: 'checkout?paid=true&method='+method,
        type: 'POST',
        dataType: 'json',
        success: function(data) {
            mg=data;
            console.log(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', textStatus, errorThrown);
        }
    });
    console.log(mg);
    var message = document.getElementById('message');
        message.innerHTML = "Xác nhận thanh toán thành công! <a href='/homeservlet'>Về trang chủ</a>";

    self.style.display="none";

}

function confirmOrders(){
    var mg='';
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