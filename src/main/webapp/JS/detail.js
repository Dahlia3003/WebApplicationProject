function displayColor(self) {
    var hiddenDiv = document.getElementById("color");
    hiddenDiv.style.display ="flex";
    hiddenDiv.style.transform = "translateY(0)"
    hiddenDiv.style.opacity = "1"

    var buttonDiv = document.querySelectorAll(".vers");
    buttonDiv.forEach(function(div) {
        div.style.border = "1px solid #86868B";
    });
    self.style.border = "1px solid #FF0000"

    var hiddenDiv = document.getElementById("button");
    hiddenDiv.style.display ="none";
}
function displayBuy(self) {
    var hiddenDiv = document.getElementById("button");
    hiddenDiv.style.display ="flex";

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
