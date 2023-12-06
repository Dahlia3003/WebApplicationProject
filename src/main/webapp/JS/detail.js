function displayColor(self, line, rom) {
    console.log("ok")
    $.ajax({
        url: 'color?line='+line+'&variRom='+rom,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            updateColorSection(data);
            console.log(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Xử lý lỗi trong quá trình gửi yêu cầu hoặc xử lý phản hồi
            console.error('Error:', textStatus, errorThrown);
        }
    });
    var hiddenDiv = document.getElementById("color");
    hiddenDiv.style.display ="flex";
    hiddenDiv.style.opacity ="0";
    hiddenDiv.style.transition = "none"
    hiddenDiv.style.transform = "translateY(-2rem)";
    setTimeout(function() {
        hiddenDiv.style.transition = "opacity 0.7s ease-in-out, transform 0.7s ease-in-out"
    }, 10);
    setTimeout(function() {
        hiddenDiv.style.opacity ="1";
        hiddenDiv.style.transform = "translateY(0rem)";
    }, 10);
    var buttonDiv = document.querySelectorAll(".vers");
    buttonDiv.forEach(function(div) {
        div.style.border = "1px solid #86868B";
    });
    self.style.border = "1px solid #FF0000"

    var hiddenBtn = document.getElementById("button");
    hiddenBtn.style.display ="none";
}

function updateColorSection(colors) {
    var colorSection = document.getElementById('colorSection');
    colorSection.innerHTML = '';
    colors.forEach(color => {
        var colorDiv = document.createElement('div');
        colorDiv.className = 'vers';
        colorDiv.onclick = function () {
            displayBuy(this);
        };

        var leftDiv = document.createElement('div');
        leftDiv.className = 'left';
        var h1 = document.createElement('h1');
        h1.innerText = color.productName;
        leftDiv.appendChild(h1);

        var rightDiv = document.createElement('div');
        rightDiv.className = 'right';
        var p = document.createElement('p');
        var formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(color.price);
        formattedPrice = formattedPrice.replace(/[^\d.]/g, '');
        p.innerHTML = 'Giá chỉ '+formattedPrice+'đ';
        rightDiv.appendChild(p);

        colorDiv.appendChild(leftDiv);
        colorDiv.appendChild(rightDiv);

        colorSection.appendChild(colorDiv);
    });
}

function displayBuy(self) {
    var hiddenDiv = document.getElementById("button");
    hiddenDiv.style.display ="flex";
    hiddenDiv.style.opacity ="0"
    hiddenDiv.style.transform = "translateY(-2rem)";
    setTimeout(function() {
        hiddenDiv.style.opacity ="1";
        hiddenDiv.style.transform = "translateY(0rem)";
    }, 10);

    var colorDiv = self.closest('.color');
    var buttonDiv = colorDiv.querySelectorAll(".vers");
    buttonDiv.forEach(function(div) {
        div.style.border = "1px solid #86868B";
    });
    self.style.border = "1px solid #FF0000"
}
function changeContentBuy() {
    var button = document.getElementById('add_cart');
    gsap.to(button, { duration: 0.5, opacity: 0, onComplete: function() {
            button.innerHTML = 'Đã thêm vào giỏ hàng';
            gsap.to(button, { duration: 0.5, opacity: 1 });
        }});
    setTimeout(function() {
        gsap.to(button, { duration: 0.5, opacity: 0, onComplete: function() {
                button.innerHTML = 'Thêm vào giỏ hàng';
                gsap.to(button, { duration: 0.5, opacity: 1 });
            }});
    }, 3500);
}

//slideShowPreview
var point = document.querySelectorAll(".point");
point.forEach(function(div) {
    div.style.opacity = "0.5";
});
point[0].style.opacity = "1";

const slideshowImages = document.getElementById('slideShow');
const prev = document.getElementById('previous');
const next = document.getElementById('next');
let currentIndex = 0;
const totalImages = document.querySelectorAll('#slideShow img').length;
function changeImage(index) {
    const translateValue = -index * (100 / totalImages);
    slideshowImages.style.transform = `translateX(${translateValue}%)`;
    var point = document.querySelectorAll(".point");
    point.forEach(function(div) {
        div.style.opacity = "0.5";
    });
    point[index].style.opacity = "1";
}
function startSlideshow() {
    intervalId = setInterval(() => {
        currentIndex = (currentIndex + 1) % totalImages;
        changeImage(currentIndex);
    }, 5000);
}
function resetSlideshow() {
    clearInterval(intervalId);
    startSlideshow();
}
prev.addEventListener('click', () => {
    currentIndex = (currentIndex - 1 + totalImages) % totalImages;
    changeImage(currentIndex);
    resetSlideshow();
});
next.addEventListener('click', () => {
    currentIndex = (currentIndex + 1) % totalImages;
    changeImage(currentIndex);
    resetSlideshow();
});
startSlideshow();

var screenWidth = window.screen.width;
var scaleRatio = screenWidth / 1366;
document.body.style.zoom = scaleRatio;
