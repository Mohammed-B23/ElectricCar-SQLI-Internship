let fixedNav = document.querySelector('.header');
window.addEventListener("scroll",()=>{
    window.scrollY > 100 ? fixedNav.classList.add('active') : fixedNav.classList.remove('active');
})

const menuLinks = document.querySelectorAll('.header ul li');
menuLinks.forEach(link => {
    link.addEventListener('click', () => {
        menuLinks.forEach(otherLink => otherLink.classList.remove('active'));
        link.classList.add('active');
    });
});


