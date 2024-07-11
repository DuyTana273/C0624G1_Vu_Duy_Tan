function currency() {
    let amount = parseFloat(document.getElementById('amount').value);
    let fromCurrency = document.getElementById('fromCurrency').value;
    let toCurrency = document.getElementById('toCurrency').value;
    var resultElement = document.getElementById('result');

    if (amount <= 0) {
        alert('Vui lòng nhập số tiền hợp lệ!!!')
        document.getElementById('amount').focus();
        return;
    }

    let exchangeRate;

    if ((fromCurrency === 'VND' && toCurrency === 'USD')) {
        exchangeRate = 1 / 23000;
    } else if (fromCurrency === 'USD' && toCurrency === 'VND') {
        exchangeRate = 23000;
    } else {
        exchangeRate = 1;
    }

    var result = amount * exchangeRate;
    resultElement.innerHTML = 'Result: ' + result;
}