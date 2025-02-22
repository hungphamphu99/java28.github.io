const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('breed-list');

// Load danh sách breed khi trang vừa load
async function getBreedList() {
    try {
        const res = await axios.get("https://dog.ceo/api/breeds/list/all");
        renderBreed(res.data.message);
    } catch (error) {
        console.error("Lỗi khi lấy danh sách giống chó:", error);
    }
}

function renderBreed(breeds) {
    // Xóa các option cũ 
    select.innerHTML = "";

    // Duyệt qua object breeds và tạo thẻ option
    Object.keys(breeds).forEach(breed => {
        const option = document.createElement("option");
        option.value = breed;
        option.textContent = breed.charAt(0).toUpperCase() + breed.slice(1); // Viết hoa chữ cái đầu
        select.appendChild(option);
    });
}

// Sự kiện khi nhấn nút 
btn.addEventListener('click', async () => {
    const selectedBreed = select.value;
    if (!selectedBreed) {
        alert("Vui lòng chọn một giống chó.");
        return;
    }

    try {
        const res = await axios.get(`https://dog.ceo/api/breed/${selectedBreed}/images/random`);
        image.src = res.data.message;
        image.alt = `Hình ảnh của giống chó ${selectedBreed}`;
    } catch (error) {
        console.error("Lỗi khi tải hình ảnh:", error);
        alert("Không thể tải hình ảnh cho giống chó này.");
    }
});

getBreedList();
