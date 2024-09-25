document.addEventListener('DOMContentLoaded', function () {
    var accountErrorSpan = document.getElementById('accountError');
    var pass1ErrorSpan = document.getElementById('pass1Error');
    var passErrorSpan = document.getElementById('passError');
    var emailErrorSpan = document.getElementById('emailError');
    var nameErrorSpan = document.getElementById('nameError');
    var phoneErrorSpan = document.getElementById('phoneError');
    var addressErrorSpan = document.getElementById('addressError');

    // Lấy thông điệp lỗi từ thuộc tính data-error
    var accountError = accountErrorSpan ? accountErrorSpan.getAttribute('data-error') || '' : '';
    var pass1Error = pass1ErrorSpan ? pass1ErrorSpan.getAttribute('data-error') || '' : '';
    var passError = passErrorSpan ? passErrorSpan.getAttribute('data-error') || '' : '';
    var emailError = emailErrorSpan ? emailErrorSpan.getAttribute('data-error') || '' : '';
    var nameError = nameErrorSpan ? nameErrorSpan.getAttribute('data-error') || '' : '';
    var phoneError = phoneErrorSpan ? phoneErrorSpan.getAttribute('data-error') || '' : '';
    var addressError = addressErrorSpan ? addressErrorSpan.getAttribute('data-error') || '' : '';
    if (accountError) {
        accountErrorSpan.textContent = accountError;
        accountErrorSpan.style.display = 'block';
        var registerModal = new bootstrap.Modal(document.getElementById('register'));
        registerModal.show();
    } else if (pass1Error) {
        pass1ErrorSpan.textContent = pass1Error;
        pass1ErrorSpan.style.display = 'block';
        var registerModal = new bootstrap.Modal(document.getElementById('register'));
        registerModal.show();
    } else if (passError) {
        passErrorSpan.textContent = passError;
        passErrorSpan.style.display = 'block';
        var registerModal = new bootstrap.Modal(document.getElementById('register'));
        registerModal.show();
    } else if (emailError) {
        emailErrorSpan.textContent = emailError;
        emailErrorSpan.style.display = 'block';
        var registerModal = new bootstrap.Modal(document.getElementById('register'));
        registerModal.show();
    } else if (nameError) {
        nameErrorSpan.textContent = nameError;
        nameErrorSpan.style.display = 'block';
        var registerModal = new bootstrap.Modal(document.getElementById('register'));
        registerModal.show();
    } else if (phoneError) {
        phoneErrorSpan.textContent = phoneError;
        phoneErrorSpan.style.display = 'block';
        var registerModal = new bootstrap.Modal(document.getElementById('register'));
        registerModal.show();
    } else if (addressError) {
        addressErrorSpan.textContent = addressError;
        addressErrorSpan.style.display = 'block';
        var registerModal = new bootstrap.Modal(document.getElementById('register'));
        registerModal.show();
    } else {
        if (accountErrorSpan) accountErrorSpan.style.display = 'none';
        if (pass1ErrorSpan) pass1ErrorSpan.style.display = 'none';
        if (nameErrorSpan) nameErrorSpan.style.display = 'none';
        if (phoneErrorSpan) phoneErrorSpan.style.display = 'none';
        if (addressErrorSpan) addressErrorSpan.style.display = 'none';
        if (passErrorSpan) passErrorSpan.style.display = 'none';
        if (emailErrorSpan) emailErrorSpan.style.display = 'none';
    }

    var registerModalElement = document.getElementById('register');
    if (registerModalElement) {
        registerModalElement.addEventListener('hidden.bs.modal', function () {
            if (accountErrorSpan) accountErrorSpan.style.display = 'none';
            if (emailErrorSpan) emailErrorSpan.style.display = 'none';
            if (passErrorSpan) passErrorSpan.style.display = 'none';
        });
    }

    var loginErrorSpan = document.getElementById('loginError');
    var loginError = loginErrorSpan ? loginErrorSpan.getAttribute('data-error') || '' : '';

    if (loginError) {
        loginErrorSpan.textContent = loginError;
        loginErrorSpan.style.display = 'block';
        var loginModal = new bootstrap.Modal(document.getElementById('signup'));
        loginModal.show();
    } else {
        if (loginErrorSpan) loginErrorSpan.style.display = 'none';
    }

    var loginModalElement = document.getElementById('signup');
    if (loginModalElement) {
        loginModalElement.addEventListener('hidden.bs.modal', function () {
            if (loginErrorSpan) loginErrorSpan.style.display = 'none';
        });
    }
});

