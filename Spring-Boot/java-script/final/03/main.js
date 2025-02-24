const btnPosts = document.getElementById("btn-posts");
const btnPhotos = document.getElementById("btn-photos");
const btnAlbums = document.getElementById("btn-albums");
const currentTypeEl = document.getElementById("current-type");
const resultListEl = document.getElementById("result-list");
let currentResource = "posts";

// lấy dữ liệu và chèn vào danh sách resultListEl
function fetchAndDisplay(resource) {
    fetch(`https://jsonplaceholder.typicode.com/${resource}`)
      .then(res => res.json())
      .then(data => {
        resultListEl.innerHTML = "";
        data.forEach(item => {
          const li = document.createElement("li");
          li.textContent = item.title;
          resultListEl.appendChild(li);
        });
      })
      .catch(err => console.log("Error:", err));
  }
  
// "active" cho nút mà người dùng vừa chọn
function highlightActiveButton(resource) {
  btnPosts.classList.remove("active");
  btnPhotos.classList.remove("active");
  btnAlbums.classList.remove("active");
  if (resource === "posts") btnPosts.classList.add("active");
  if (resource === "photos") btnPhotos.classList.add("active");
  if (resource === "albums") btnAlbums.classList.add("active");
}
// Đánh dấu nút được chọn và gọi hàm lấy dữ liệu
function handleClick(resource) {
  currentResource = resource;
  currentTypeEl.textContent = resource;
  highlightActiveButton(resource);
  fetchAndDisplay(resource);
}
// Sự kiện 'click' cho các nút, khi nhấn sẽ gọi hàm handleClick 
btnPosts.addEventListener("click", () => handleClick("posts"));
btnPhotos.addEventListener("click", () => handleClick("photos"));
btnAlbums.addEventListener("click", () => handleClick("albums"));

document.addEventListener("DOMContentLoaded", function() {
  handleClick(currentResource);
});
