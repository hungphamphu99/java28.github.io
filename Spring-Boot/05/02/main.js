let counter = 0;
const h1 = document.getElementById("counter"); // .btn.counterBtn.prevBtn

function plus() {
    counter++;
    changeColor();
}

function minus() {
    counter--;
    changeColor();
}

function changeColor() {
    h1.innerText = counter;
    if (counter < 0) {
        h1.style.color = "red";
    } else if (counter == 0) {
        h1.style.color = "#333";
    } else {
        h1.style.color = "green";
    }
}