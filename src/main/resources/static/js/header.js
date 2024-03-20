// header.js

let menuToggle = document.querySelector('#hamburger');
let menuUl = document.querySelector('#menu-hamburger');

console.log(menuToggle);
console.log(menuUl);

menuToggle.addEventListener('click', function(){
    if (menuUl.style.display === 'block') {
        menuUl.style.display = 'none';
    } else {
        menuUl.style.display = 'block';
    }
})