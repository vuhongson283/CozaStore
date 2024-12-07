/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const btns = document.querySelectorAll("[data-target]");
const close_modals = document.querySelectorAll(".close-modalm");
const overlaym = document.getElementById("overlaym");

btns.forEach((btnm) => {
  btnm.addEventListener("click", () => {
    document.querySelector(btnm.dataset.target).classList.add("active");
    overlaym.classList.add("active");
  });
});

close_modals.forEach((btnm) => {
  btnm.addEventListener("click", () => {
    const modal = btnm.closest(".modalm");
    modal.classList.remove("active");
    overlaym.classList.remove("active");
  });
});

window.onclick = (event) => {
  if (event.target == overlaym) {
    const modals = document.querySelectorAll(".modalm");
    modals.forEach((modalm) => modalm.classList.remove("active"));
    overlaym.classList.remove("active");
  }
};




