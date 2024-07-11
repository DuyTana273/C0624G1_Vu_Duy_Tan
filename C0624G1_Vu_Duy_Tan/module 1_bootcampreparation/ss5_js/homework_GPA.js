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
    if (GPA >= 8) {
        alert('Điểm trung bình của bạn là: ' + GPA + ' và bạn được xếp loại Giỏi');
    }
    if (GPA >= 6 && GPA < 8) {
        alert('Điểm trung bình của bạn là: ' + GPA + ' và bạn được xếp loại Khá');
    }
    if (GPA >= 3.5 && GPA < 6) {
        alert('Điểm trung bình của bạn là: ' + GPA + ' và bạn được xếp loại Trung Bình');
    }
    if (GPA < 3.5) {
        alert('Điểm trung bình của bạn là: ' + GPA + ' và bạn được xếp loại Yếu');
    }
    // alert('Điểm trung bình của bạn là: ' + GPA);
}