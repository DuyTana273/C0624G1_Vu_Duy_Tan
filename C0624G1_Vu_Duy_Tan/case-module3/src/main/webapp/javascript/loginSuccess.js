$(document).ready(function() {
    if ($('#loginToast .toast-body').text().trim() !== "") {
        $('#loginToast').show();
        var toastElement = new bootstrap.Toast($('#loginToast')[0], { delay: 2000 });
        toastElement.show();
    }
});
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    let regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    let results = regex.exec(window.location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}
window.onload = function() {
    const scrollTo = getUrlParameter('scrollTo');
    if (scrollTo) {
        const element = document.getElementById(scrollTo);
        if (element) {
            element.scrollIntoView({ behavior: 'smooth' });
        }
    }
};