let currentSlide = 0;

function nextSlide(trayItem) {
    const slideTray = document.getElementById(trayItem);
    const fslide = slideTray.getElementsByClassName("first_items");
    const slides = slideTray.getElementsByClassName("items");
    const itemsWidth = slides[0].getBoundingClientRect().width + 18;
    const totalSlides = slides.length;
    if (currentSlide < totalSlides - 5) {
        for (let i = 0; i < slides.length; i++) {
            fslide[0].style.transform = `translateX(${currentSlide * -itemsWidth}px)`;
            slides[i].style.transform = `translateX(${currentSlide * -itemsWidth}px)`;
        }
        currentSlide += 1;
    }


    if (currentSlide === totalSlides - 5) {
        const lastItem = slides[currentSlide];
        lastItem.style.pointerEvents = "none";
    }
}

function prevSlide(trayItem) {
    const slideTray = document.getElementById(trayItem);
    const fslide = slideTray.getElementsByClassName("first_items");
    const slides = slideTray.getElementsByClassName("items");
    const itemsWidth = slides[0].getBoundingClientRect().width + 18;
    const totalSlides = slides.length;

    if (currentSlide > 0) {
        currentSlide -= 1;

        for (let i = 0; i < slides.length; i++) {
            fslide[0].style.transform = `translateX(${currentSlide * -itemsWidth}px)`;
            slides[i].style.transform = `translateX(${currentSlide * -itemsWidth}px)`;
        }

        const lastItem = slides[totalSlides - 5];
        lastItem.style.pointerEvents = "auto";
    }
}
