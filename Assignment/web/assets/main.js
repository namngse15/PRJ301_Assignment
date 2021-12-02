
/* global google */


//header navbar,search
let account_modal = document.querySelector('.account-menu');
let searchForm = document.querySelector('.search-form');

document.querySelector('#search-btn').onclick = () => {
    searchForm.classList.toggle('active');
    account_modal.classList.remove('active');
}
document.querySelector('#account-btn').onclick = () => {
    account_modal.classList.toggle('active');
    searchForm.classList.remove('active');
}
document.querySelector('#menu-btn').onclick = () => {
    account_modal.classList.remove('active');
    searchForm.classList.remove('active');
}

//product image
function chooseImg(image) {
    var src = image.src;
    document.getElementById("frame").src = src;
}
//remove item
window.onscroll = () => {
    searchForm.classList.remove('active');
    account_modal.classList.remove('active');
}
//product card animation
$(document).ready(function () {
    // Lift card and show stats on Mouseover
    $('.product-card').hover(function () {
        $(this).addClass('animate');
        $('div.carouselNext, div.carouselPrev').addClass('visible');
    }, function () {
        $(this).removeClass('animate');
        $('div.carouselNext, div.carouselPrev').removeClass('visible');
    });
});
// sub menu sidebar
$('.sub-menu ul').hide();
$(".sub-menu button").click(function () {
    $(this).parent(".sub-menu").children("ul").slideToggle("100");
});
//product detail btn
$(document).ready(function () {
    var quantity = 1;
    $('.qtyminus').click(function (e) {
        // dung hanh dong cua button
        e.preventDefault();
        var quantity = parseInt($('.qty').val());
        if (quantity > 1) {
            $('.qty').val(quantity - 1);
        }
    });
});
$(document).ready(function () {
    var quantity = 1;
    $('.qtyplus').click(function (e) {
        // dung hanh dong cua button
        e.preventDefault();
        var quantity = parseInt($('.qty').val());

        $('.qty').val(quantity + 1);
    });
});
//cart btn
$('.cateBtn').on("click", function () {
    $('button-active').not(this).removeClass('button-active');
    $(this).toggleClass('button-active');
})
//google-map api