let account_modal = document.querySelector('.account-menu');

document.querySelector('#account-btn').onclick = () => {
    account_modal.classList.toggle('active');
};

function buttonAdmin() {
    location.href = "admin";
}
function buttonUser() {
    location.href = "admin-user";
}
function buttonProduct() {
    location.href = "admin-product";
}
function buttonCategory() {
    location.href = "admin-category";
}

