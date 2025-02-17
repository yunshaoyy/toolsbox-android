let touchStartY = 0;
let touchEndY = 0;
document.addEventListener('touchstart', (e) => {
    touchStartY = e.changedTouches[0].screenY;  // 获取起始触摸位置 Y
}, false);
document.addEventListener('touchend', (e) => {
    touchEndY = e.changedTouches[0].screenY;  // 获取结束触摸位置 Y
    // 向上滑动超过50px
    if (touchStartY > touchEndY + 50) {
        launchActivity()
    }
}, false);
function launchActivity() {
    Android.launchActivity();
}


function fhuijia() {
    let huijia = new Date();
    huijia.setHours(22, 20, 0, 0);
    let now = new Date();
    let lhuijia = huijia - now;
    let hhours = Math.floor(lhuijia / (1000 * 60 * 60));
    let hminutes = Math.floor((lhuijia % (1000 * 60 * 60)) / (1000 * 60));
    let hseconds = Math.floor((lhuijia % (1000 * 60)) / 1000);
    document.getElementById("huijia").innerHTML = `还有${hhours}时${hminutes}分${hseconds}秒回家`;
}

function fxianzai() {
    let xianzai = new Date();
    document.getElementById("xianzai1").innerHTML = xianzai.getFullYear() + "年" + (xianzai.getMonth() + 1) + "月" + xianzai.getDate() + "日";
    document.getElementById("xianzai2").innerHTML = xianzai.getHours() + "时" + xianzai.getMinutes() + "分" + xianzai.getSeconds() + "秒";
}

let gaokao = new Date(2025, 6 - 1, 7, 9, 0);
function fgaokao() {
    let now = new Date();
    let lgaokao = gaokao - now;
    let gdays = Math.floor(lgaokao / (1000 * 60 * 60 * 24));
    let ghours = Math.floor((lgaokao % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    let gminutes = Math.floor((lgaokao % (1000 * 60 * 60)) / (1000 * 60));
    let gseconds = Math.floor((lgaokao % (1000 * 60)) / 1000);
    document.getElementById("gaokao").innerHTML = `还有${gdays}天${ghours}时${gminutes}分${gseconds}秒高考`;
}
setInterval(fhuijia, 1000);
setInterval(fxianzai, 1000);
setInterval(fgaokao, 1000);