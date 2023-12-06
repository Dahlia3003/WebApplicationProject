function displayPaymentInfo(id) {
    var codDiv = document.getElementById("cod_detail");
    var paypalDiv = document.getElementById("pp_detail");
    var linkElement = document.getElementById('pay');
    console.log("in");
    if (id=="cod") {
        codDiv.style.display = "flex";
        paypalDiv.style.display = "none";
        linkElement.href = 'checkout?paid=true&method=Thanh toán khi nhận hàng';
    }
    else
    if (id=="paypal_bt"){
        codDiv.style.display = "none";
        paypalDiv.style.display = "block";
        linkElement.href = 'checkout?paid=true&method=Ví điện tử Paypal';
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