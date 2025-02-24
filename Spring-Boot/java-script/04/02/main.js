const text = document.getElementById("text");
const btn1 = document.getElementById("btn-1");
const btn2 = document.getElementById("btn-2");
const btn3 = document.getElementById("btn-3");
function getRandom(){
    const number =[
    "One",
    "Two",
    "Three",
    ];
    return number[Math.floor(Math.random() * number.length)];
}

function changeContent(){
    text.innerText = getRandom();

}

function getRandomHexColor() {
    const letters = "0123456789ABCDEF";
    let color = "#";
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
// function changeColor(){
//     text.style.color = getRandomHexColor();

// }

function getRandomRGBColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    return `rgb(${r}, ${g}, ${b})`;
}


function changeColorBackground(){
    text.style.backgroundColor = getRandomRGBColor();
}


// btn1.addEventListener("click",changeContent)
// btn2.addEventListener("click", changeColor)

btn2.onclick = function changeColor(){
    text.style.color = getRandomHexColor();

}
btn3.addEventListener("click", changeColorBackground)