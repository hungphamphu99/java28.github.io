const todoList = document.getElementById("todo-list");
const API_URL = "http://localhost:8000/todos";
let todos = [];

// Lấy danh sách công việc từ API
const getAllTodos = async () => {
    try {
        const res = await axios.get(API_URL);
        if (res.status === 200) {
            todos = res.data;
            renderTodos();
        } else {
            console.error("Lỗi: Không thể lấy danh sách công việc");
        }
    } catch (error) {
        console.error("Lỗi khi gọi API:", error.message);
        alert("Không thể kết nối đến server. Vui lòng kiểm tra API.");
    }
};

// Hiển thị danh sách công việc
function renderTodos() {
    todoList.innerHTML = "";
    if (todos.length === 0) {
        todoList.innerHTML = '<p>Danh sách công việc trống</p>';
        return;
    }

    todos.forEach((todo) => {
        const todoItem = document.createElement("div");
        todoItem.className = "todo-item";
        todoItem.innerHTML = `
            <li>
                <input type="checkbox" onchange="toggleStatus(${todo.id})" ${todo.completed ? 'checked' : ''}>
                <span class="${todo.completed ? 'completed' : ''}" style="flex: 1; margin-left: 10px; color: ${todo.completed ? 'red' : 'black'};">${todo.title}</span>
                <button onclick="editTodo(${todo.id})">Edit</button>
                <button onclick="deleteTodo(${todo.id})">Delete</button>
            </li>
        `;
        todoList.appendChild(todoItem);
    });
}

// Thêm công việc mới
async function addTodo() {
    const todoInput = document.getElementById("todo-input");
    const title = todoInput.value.trim();

    if (!title) {
        alert("Tên công việc không được để trống");
        return;
    }

    const isDuplicate = todos.some(todo => todo.title.toLowerCase() === title.toLowerCase());
    if (isDuplicate) {
        alert("Công việc này đã có trong danh sách");
        return;
    }

    try {
        const res = await axios.post(API_URL, { title, completed: false });
        todos.push(res.data);
        todoInput.value = "";
        renderTodos();
    } catch (error) {
        console.error("Lỗi khi thêm công việc:", error);
        alert("Không thể thêm công việc. Vui lòng thử lại.");
    }
}

// Chuyển đổi trạng thái hoàn thành
async function toggleStatus(id) {
    const todo = todos.find(item => item.id === id);
    if (!todo) return;

    try {
        const res = await axios.patch(`${API_URL}/${id}`, { completed: !todo.completed });
        todo.completed = res.data.completed;
        renderTodos();
    } catch (error) {
        console.error("Lỗi khi thay đổi trạng thái:", error);
        alert("Không thể thay đổi trạng thái công việc.");
    }
}

// Chỉnh sửa công việc
async function editTodo(id) {
    const todo = todos.find(item => item.id === id);
    if (!todo) return;

    const newTitle = prompt("Chỉnh sửa tiêu đề công việc", todo.title);
    if (newTitle !== null && newTitle.trim() !== "") {
        const isDuplicate = todos.some(item => item.id !== id && item.title.toLowerCase() === newTitle.toLowerCase());
        if (isDuplicate) {
            alert("Công việc này đã có trong danh sách");
            return;
        }

        try {
            const res = await axios.patch(`${API_URL}/${id}`, { title: newTitle.trim() });
            todo.title = res.data.title;
            renderTodos();
        } catch (error) {
            console.error("Lỗi khi chỉnh sửa công việc:", error);
            alert("Không thể chỉnh sửa công việc.");
        }
    }
}

// Xóa công việc
async function deleteTodo(id) {
    if (confirm("Bạn có chắc muốn xóa công việc này?")) {
        try {
            await axios.delete(`${API_URL}/${id}`);
            todos = todos.filter(item => item.id !== id);
            renderTodos();
        } catch (error) {
            console.error("Lỗi khi xóa công việc:", error);
            alert("Không thể xóa công việc.");
        }
    }
}

// Khởi chạy ứng dụng
getAllTodos();
