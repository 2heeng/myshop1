document.addEventListener('DOMContentLoaded', (event) => {
let currentIndex = 0;
const slides = document.querySelectorAll('.main_slide');
console.log("javascript동작함")
function showSlide(index) {
    slides.forEach((slide, i) => {
        slide.style.display = i === index ? 'block' : 'none';
    });
}

function nextSlide() {
    currentIndex = (currentIndex + 1) % slides.length;
    showSlide(currentIndex);
}

showSlide(currentIndex);
setInterval(nextSlide, 2000); // 2000ms = 2s
});