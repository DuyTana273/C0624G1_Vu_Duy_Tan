 function searchByDate() {
    // var searchDate = document.getElementById('searchDate').value;
    // if (searchDate) {
    //     window.location.href = '/orders?action=searchDate&date=' + encodeURIComponent(searchDate);
    // } else {
    //     alert('Please select a day');
    // }
     var searchDate = document.getElementById("searchDate").value;

    if (!searchDate) {
        alert("Vui long chon ngay!");
        return;
    }

    $.ajax({
        url: '/orders?action=searchDate',
        method: 'GET',
        data: { date: searchDate },
        success: function(response) {
            const orders = JSON.parse(response);
            console.log(orders);
            let content =``
            for (let i = 0; i <orders.length ; i++) {
                let formattedSum = orders[i].sum.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                content+=`<tr>
                            <td>${orders[i].orderId}</td>
                            <td>${orders[i].userId}</td>
                            <td>${orders[i].customerName}</td>
                            <td>${orders[i].date}</td>
                            <td>${formattedSum}</td>
                            <td>${orders[i].status}</td>
                            <td>
                                <a href="/orderdetails?orderId=${orders[i].orderId}" class="btn btn-sm btn-primary">Xem</a>
                                <form action="orders?action=updateStatus" method="post" class="d-inline" onsubmit="return confirmShipping();">
                                    <input type="hidden" name="orderId" value="${orders[i].orderId}">
                                    <input type="hidden" name="userId" value="${orders[i].userId}">
                                    <input type="hidden" name="status" value="Shipped">
                                    <button type="submit" class="btn btn-sm btn-success">Giao hang</button>
                                </form>
                                <button class="btn btn-sm btn-danger">Huy</button>
                            </td>
                        </tr>`;
            }
            document.getElementById("content").innerHTML=content;
            let total = 0;
            for (let i = 0; i <orders.length ; i++){
                total = total + orders[i].sum;
            }
            document.getElementById("total-revenue").innerHTML=total.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });
        },
        error: function(error) {
            console.error('Lỗi:', error);
        }
    });

}
