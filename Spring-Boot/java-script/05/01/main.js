const defaultColors = ['#3498db', '#9b59b6', '#e74c3c', '#2c3e50', '#d35400'];
let colors = defaultColors.slice();

const btn = document.querySelector("#btn");
const points = document.querySelector(".points");
const boxes = document.querySelector(".boxes");

const renderBoxes = (colors) => {
    let html = "";
    colors.forEach((color, index) => {
        html += `<div 
                    class="box" 
                    style="background-color: ${color}"
                    onclick="removeBox(${index})"
                ></div>`;
    });
    boxes.innerHTML = html;

    // Update total boxes
    points.innerHTML = colors.length;
}

// Xóa box
const removeBox = (index) => {
    colors.splice(index, 1);
    renderBoxes(colors);
}

// Thêm box
btn.addEventListener("click", () => {
    colors = colors.concat(defaultColors);
    renderBoxes(colors);
})

// Vừa vào trang thì hiển thị danh sách box ban đầu
renderBoxes(colors);