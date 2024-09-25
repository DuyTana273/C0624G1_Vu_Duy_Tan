    function editProduct(productId, name, description, brand, price, stock, image) {

    document.getElementById('editProductId').value = productId;
    document.getElementById('editProductName').value = name;
    document.getElementById('editProductDescription').value = description;
    document.getElementById('editProductBrand').value = brand;
    document.getElementById('editProductPrice').value = price;
    document.getElementById('editProductStock').value = stock;
    document.getElementById('editProductImage').value = image;
    $('#editProductModal').modal('show');
}

    function editUser(account, email, name, phone, address, role) {
        document.getElementById('editUserAccount').value = account;
        document.getElementById('editUserEmail').value = email;
        document.getElementById('editUserName').value = name;
        document.getElementById('editUserPhone').value = phone;
        document.getElementById('editUserAddress').value = address;
        document.getElementById('editUserRole').value = role;
        $('#editUserModal').modal('show');
    }