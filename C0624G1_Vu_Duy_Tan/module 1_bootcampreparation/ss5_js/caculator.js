function appendToDisplay(value) {
    const display = document.getElementById('displayInput');
    if (display.value === '0' && value !== '.') {
        display.value = value;
    } else {
        display.value = display.value + value;
    }
}

function clearDisplay() {
    document.getElementById('displayInput').value = '0';
}

function calculateResult() {
    const display = document.getElementById('displayInput');
    display.value = eval(display.value);
}