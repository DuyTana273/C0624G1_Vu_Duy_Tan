// ********** CÀI ĐẶT BẢNG **********
let board;
let boardWidth = 360;
let boardHeight = 640;
let context;

// ********** THUỘC TÍNH CỦA BIRD **********
let birdWidth = 34;
let birdHeight = 24;
let birdX = boardWidth / 8;
let birdY = boardHeight / 2;
let birdImg;

let bird = {
    x: birdX,
    y: birdY,
    width: birdWidth,
    height: birdHeight,
    velocityY: 0, // Tốc độ rơi của BIRD
    flapStrength: -7, // Độ mạnh của Jump
    maxFallSpeed: 10, // Tốc độ rơi tối đa để tránh rơi quá nhanh
    angle: 0, // Góc xoay của BIRD
    angleChange: 0.4 // Tốc độ thay đổi góc xoay của BIRD
}

// ********** THUỘC TÍNH CỦA PIPES **********
let pipeArray = [];
let pipeWidth = 64;
let pipeHeight = 640;
let pipeX = boardWidth;
let pipeY = 0;

let topPipeImg;
let bottomPipeImg;

// ********** ÂM THANH **********
let jumpSound = new Audio('./audio/flap.mp3');
let hitSound = new Audio('./audio/die.mp3');
let pointSound = new Audio('./audio/point.mp3');
let backgroundMusic = new Audio('./audio/background.mp3');
backgroundMusic.loop = true;
backgroundMusic.volume = 0.5;

// ********** LOGIC CỦA TRÒ CHƠI **********
let velocityX = -2; // Tốc độ di chuyển ngang của PIPES
let gravity = 0.3; // Trọng lực tác động lên BIRD

let gameOver = false;
let score = 0;
let gameStarted = false;

// ********** KHỞI TẠO **********
window.onload = function() {
    board = document.getElementById("board");
    board.height = boardHeight;
    board.width = boardWidth;
    context = board.getContext("2d");

    // Tải hình ảnh
    birdImg = new Image();
    birdImg.src = "./img/flappybird.png";
    birdImg.onload = function() {
        context.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height);
    }

    topPipeImg = new Image();
    topPipeImg.src = "./img/toppipe.png";

    bottomPipeImg = new Image();
    bottomPipeImg.src = "./img/bottompipe.png";

    requestAnimationFrame(update);
    setInterval(placePipes, 1500); // Tạo PIPES mỗi 1.5 giây
    document.addEventListener("keydown", e => moveBird(e));
    board.addEventListener("click", e => moveBird(e));
}

// ********** HÀM UPDATE **********
function update() {
    requestAnimationFrame(update);

    if (!gameStarted) {
        drawStartScreen();
        return;
    }

    if (gameOver) {
        backgroundMusic.pause();
        drawGameOverScreen();
        return;
    }

    context.clearRect(0, 0, board.width, board.height);

    // Cập nhật BIRD
    bird.velocityY += gravity;
    bird.velocityY = Math.min(bird.velocityY, bird.maxFallSpeed);
    bird.y = Math.max(bird.y + bird.velocityY, 0);
    bird.angle = Math.min(bird.angle + bird.velocityY * bird.angleChange, 90);
    if (bird.velocityY < 0) {
        bird.angle = Math.max(bird.angle + bird.velocityY * bird.angleChange, -30);
    }

    context.save();
    context.translate(bird.x + bird.width / 2, bird.y + bird.height / 2);
    context.rotate(bird.angle * Math.PI / 180);
    context.drawImage(birdImg, -bird.width / 2, -bird.height / 2, bird.width, bird.height);
    context.restore();

    // Kiểm tra nếu BIRD ra khỏi màn hình
    if (bird.y > board.height - bird.height) {
        bird.y = board.height - bird.height;
        gameOver = true;
        hitSound.play();
    }

    // Cập nhật vị trí của PIPES
    for (let i = 0; i < pipeArray.length; i++) {
        let pipe = pipeArray[i];
        pipe.x += velocityX;
        context.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height);

        if (!pipe.passed && bird.x > pipe.x + pipe.width) {
            score += 0.5;
            pointSound.play();
            pipe.passed = true;
        }

        if (detectCollision(bird, pipe)) {
            gameOver = true;
            hitSound.play();
        }
    }

    while (pipeArray.length > 0 && pipeArray[0].x < -pipeWidth) {
        pipeArray.shift();
    }

    drawScore();
    level();
}

// ********** HÀM ĐẶT PIPES **********
function placePipes() {
    if (gameOver) {
        return;
    }

    let randomPipeY = pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2);
    let openingSpace = board.height / 4;

    let topPipe = {
        img: topPipeImg,
        x: pipeX,
        y: randomPipeY,
        width: pipeWidth,
        height: pipeHeight,
        passed: false
    }
    pipeArray.push(topPipe);

    let bottomPipe = {
        img: bottomPipeImg,
        x: pipeX,
        y: randomPipeY + pipeHeight + openingSpace,
        width: pipeWidth,
        height: pipeHeight,
        passed: false
    }
    pipeArray.push(bottomPipe);
}

// ********** HÀM DI CHUYỂN BIRD **********
function moveBird(e) {
    if (e.code == "Space" || e.code == "ArrowUp" || e.code == "KeyX" || e.type === "click") {
        // Jump
        bird.velocityY = bird.flapStrength;
        jumpSound.currentTime = 0;
        jumpSound.play();

        // Bắt đầu trò chơi
        if (!gameStarted) {
            backgroundMusic.play();
            gameStarted = true;
            bird.velocityY = bird.flapStrength;
            jumpSound.currentTime = 0;
            jumpSound.play();
        }

        // Reset trò chơi
        if (gameOver) {
            bird.y = birdY;
            bird.velocityY = 0;
            pipeArray = [];
            score = 0;
            gameOver = false;
            backgroundMusic.play();
            velocityX = -2
            gravity = 0.3;
            bird.velocityY = bird.flapStrength;
            jumpSound.currentTime = 0;
            jumpSound.play();
        }
    }
}

// ********** HÀM KIỂM TRA VA CHẠM **********
function detectCollision(a, b) {
    return a.x < b.x + b.width &&
        a.x + a.width > b.x &&
        a.y < b.y + b.height &&
        a.y + a.height > b.y;
}

// ********** HÀM KIỂM TRA LEVEL **********

function level() {
    if (score >= 40) {
        velocityX = -15;
        gravity = 0.9;
    } else if (score >= 40) {
        velocityX = -10;
        gravity = 0.1;
    } else if (score >= 30) {
        velocityX = -4;
        gravity = 0.15
    } else if (score >= 20) {
        velocityX = -3.5;
        gravity = 0.2;
    } else if (score >= 10) {
        velocityX = -3;
        gravity = 0.23;
    } else if (score >= 5) {
        velocityX = -2.5;
        gravity = 0.25;
    } else if (score >= 2) {
        velocityX = -2;
        flapStrength = -11
        gravity = 0.28;
    } else if (score = 0) {
        velocityX
        flapStrength
        gravity
    }
}



// ********** HÀM VẼ MÀN HÌNH BẮT ĐẦU **********
function drawStartScreen() {
    context.fillStyle = "rgba(0, 0, 0, 0.75)";
    context.fillRect(0, 0, board.width, board.height);

    context.fillStyle = "rgba(255, 215, 0, 1)";
    context.font = "20px 'Press Start 2P', cursive";
    context.textAlign = "center";
    context.textBaseline = "middle";
    context.fillText("Press SPACE or CLICK to start", board.width / 2, board.height / 2);
}

// ********** HÀM VẼ ĐIỂM SỐ **********
function drawScore() {
    context.fillStyle = "rgba(255, 215, 0, 0.9)";
    context.font = "45px 'Press Start 2P', cursive";
    context.textAlign = "center";
    context.textBaseline = "top";
    context.fillText("Score: " + Math.floor(score), 180, 10);
}

// ********** HÀM VẼ MÀN HÌNH KẾT THÚC TRÒ CHƠI **********
function drawGameOverScreen() {
    context.fillStyle = "rgba(0, 0, 0, 0.75)";
    context.fillRect(0, 0, board.width, board.height);

    context.fillStyle = "yellow";
    context.font = "60px 'Press Start 2P', cursive";
    context.textAlign = "center";
    context.textBaseline = "middle";
    context.fillText("GAME OVER", board.width / 2, board.height / 2 - 40);

    context.font = "50px 'Press Start 2P', cursive";
    context.fillText("Score: " + Math.floor(score), board.width / 2, board.height / 2 + 40);

    context.font = "20px 'Press Start 2P', cursive";
    context.fillText("Press SPACE or CLICK reset game", board.width / 2, board.height / 2 + 110);
}

jumpSound.addEventListener('canplaythrough', () => {});
hitSound.addEventListener('canplaythrough', () => {});
pointSound.addEventListener('canplaythrough', () => {});
backgroundMusic.addEventListener('canplaythrough', () => {});