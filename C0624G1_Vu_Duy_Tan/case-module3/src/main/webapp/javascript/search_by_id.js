function searchByOrderId() {
    var orderId = document.getElementById('searchOrderId').value;
    if (orderId) {
        window.location.href = '/orders?action=searchOrderId&orderId=' + encodeURIComponent(orderId);
    }
}