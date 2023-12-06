function displayColor(self, line, rom) {
    $.ajax({
        url: 'color?line='+line+'&variRom='+rom,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            updateColorSection(data);
            console.log(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
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

    var specifiSection = document.querySelector('.specifi');
    specifiSection.style.display="none";
}
function updateColorSection(colors) {
    var colorSection = document.getElementById('colorSection');
    colorSection.innerHTML = '';
    var imageLinks = [];
    colors.forEach(color => {
        var colorDiv = document.createElement('div');
        colorDiv.className = 'vers';
        colorDiv.onclick = function () {
            displayBuy(this);
        };
        colorDiv.id=color.productId;
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
        imageLinks.push(color.productImage);
    });
    updateImage(imageLinks);
    console.log(imageLinks);
}
function updateImage(imageLinks) {
    var slideShow = document.getElementById("slideShow");
    var tablist = document.getElementById("tablist");
    slideShow.innerHTML = "";
    tablist.innerHTML = "";
    totalImages=imageLinks.length;
    for (var i = 0; i < imageLinks.length; i++) {
        // Tạo div.picbox và thêm vào slideShow
        var picbox = document.createElement("div");
        picbox.className = "picbox";
        var img = document.createElement("img");
        img.src = imageLinks[i];
        picbox.appendChild(img);
        slideShow.appendChild(picbox);

        // Tạo div.point và thêm vào tablist
        var point = document.createElement("div");
        point.className = "point";
        var svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        svg.setAttribute("width", "9");
        svg.setAttribute("height", "8");
        svg.setAttribute("viewBox", "0 0 9 8");
        var rect = document.createElementNS("http://www.w3.org/2000/svg", "rect");
        rect.setAttribute("x", "0.5");
        rect.setAttribute("width", "8");
        rect.setAttribute("height", "8");
        rect.setAttribute("rx", "4");
        rect.setAttribute("fill", "black");
        rect.setAttribute("fill-opacity", "0.8");
        svg.appendChild(rect);
        point.appendChild(svg);
        tablist.appendChild(point);
    }
    var point = document.querySelectorAll(".point");
    point.forEach(function(div) {
        div.style.opacity = "0.5";
    });
    if (currentIndex<totalImages)
        point[currentIndex].style.opacity = "1";
    else
        point[0].style.opacity = "1";
}
function showSpeci(productid){
    $.ajax({
        url: 'info?id='+productid,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            updateSpecifi(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', textStatus, errorThrown);
        }
    });
}
function updateSpecifi(speci) {
    var titles = ["Vi xử lý", "RAM/ROM", "Màn hình", "Camera", "Pin", "Kết nối"];
    var detailSection = document.querySelector('.detail');
    detailSection.innerHTML = '';
    for (var i = 0; i < 6; i++) {
        var detailValueDiv = document.createElement('div');
        detailValueDiv.className = 'detail_value';
        var h1 = document.createElement('h1');
        h1.innerText = titles[i];
        var p = document.createElement('p');
        p.innerText = speci[i];
        detailValueDiv.appendChild(h1);
        detailValueDiv.appendChild(p);
        detailSection.appendChild(detailValueDiv);
    }
    updateImage(speci.slice(6, 6 + 1));
}
var selectedProduct;
function displayBuy(self) {
    var specifiSection = document.querySelector('.specifi');
    specifiSection.style.display="flex";
    showSpeci(self.id);
    selectedProduct = self.id;
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

    var linkElement = document.getElementById('forward');
    linkElement.href = 'detail?action=buy&id='+selectedProduct;
}
function changeContentBuy() {
    $.ajax({
        url: 'addcart?id='+selectedProduct,
        type: 'GET',
        success: function() {
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', textStatus, errorThrown);
        }
    });
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
var currentIndex = 0;
var totalImages = document.querySelectorAll('#slideShow img').length;
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
