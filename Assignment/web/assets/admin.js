let account_modal = document.querySelector('.account-menu');

document.querySelector('#account-btn').onclick = () => {
    account_modal.classList.toggle('active');
};

function toggleAdministrator() {
    location.href = "admin";
}
function toggleUser() {
    location.href = "admin-user";
}
function toggleProduct() {
    location.href = "admin-listProduct";
}


