//Bài 1

function GPA() {
    var math = parseFloat(document.getElementById('math').value);
    var literature = parseFloat(document.getElementById('literature').value);
    var english = parseFloat(document.getElementById('english').value);
    if ((isNaN(math) || isNaN(literature)) || isNaN(english)) {
        alert('Vui lòng nhập đủ điểm của 3 môn!!!');
        return;
    }
    if (math < 0 || math > 10) {
        alert('Nhập điểm toán từ 1 đến 10. Vui lòng nhập lại!!!');
        document.getElementById('math').focus();
        return;
    }
    if (literature < 0 || literature > 10) {
        alert('Nhập điểm văn từ 1 đến 10. Vui lòng nhập lại!!!');
        document.getElementById('literature').focus();
        return;
    }
    if (english < 0 || english > 10) {
        alert('Nhập điểm anh từ 1 đến 10. Vui lòng nhập lại!!!');
        document.getElementById('english').focus();
        return;
    }
    var GPA = (math + literature + english) / 3;
    var tGPA = math + literature + english;
    if (GPA >= 8) {
        alert('Tổng điểm của bạn là: ' + tGPA + ' Điểm trung bình của bạn là: ' + GPA);
    }
    if (GPA >= 6 && GPA < 8) {
        alert('Tổng điểm của bạn là: ' + tGPA + ' Điểm trung bình của bạn là: ' + GPA);
    }
    if (GPA >= 3.5 && GPA < 6) {
        alert('Tổng điểm của bạn là: ' + tGPA + ' Điểm trung bình của bạn là: ' + GPA);
    }
    if (GPA < 3.5) {
        alert('Tổng điểm của bạn là: ' + tGPA + ' Điểm trung bình của bạn là: ' + GPA);
    }

}

//Bài 2
function f() {
    let c = document.getElementById('c').value;
    let f = (c * 1.8) + 32;
    document.getElementById("result").innerHTML = "Result: " + f;
}

//Bài 3 + 4

function area() {
    let r = document.getElementById('r').value;
    let s = 3.14 * r * r;
    let p = 3.14 * r * 2;
    document.getElementById("s_area").innerHTML = "Diện tích hình tròn: " + s;
    document.getElementById("p_area").innerHTML = "Chu vi hình tròn: " + p;
}