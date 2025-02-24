const todoList = document.getElementById("todo-list");
let todos = [
    { title: "Di choi", completed: false },
    { title: "Hoc bai", completed: true },
    { title: "Da bong", completed: false }
];
function renderTodos() {
    todoList.innerHTML = "";
    if (todos.length === 0) {
        todoList.innerHTML = '<p>Danh sách công việc trống</p>';
        return;
    }

    todos.forEach((todo, index) => {
        const todoItem = document.createElement("div");
        todoItem.className = "todo-item";
        todoItem.innerHTML = `
            <li>
                <input type="checkbox" onchange="toggleStatus(${index})" ${todo.completed ? 'checked' : ''}>
                <span class="${todo.completed ? 'completed' : ''}" style="flex: 1; margin-left: 10px; color: ${todo.completed ? 'red' : 'black'};">${todo.title}</span>
                <button onclick="editTodo(${index})">Edit</button>
                <button onclick="deleteTodo(${index})">Delete</button>
            </li>
        `;
        todoList.appendChild(todoItem);
    });
}

function addTodo() {
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

    todos.push({ title, completed: false });
    todoInput.value = "";
    renderTodos();
}

function toggleStatus(index) {
    todos[index].completed = !todos[index].completed;
    renderTodos();
}

function editTodo(index) {
    const newTitle = prompt("Chỉnh sửa tiêu đề công việc", todos[index].title);
    if (newTitle !== null && newTitle.trim() !== "") {
        const isDuplicate = todos.some((todo, i) => i !== index && todo.title.toLowerCase() === newTitle.toLowerCase());
        if (isDuplicate) {
            alert("Công việc này đã có trong danh sách");
            return;
        }

        todos[index].title = newTitle.trim();
        renderTodos();
    }
}

function deleteTodo(index) {
    if (confirm("Bạn có chắc muốn xóa công việc này?")) {
        todos.splice(index, 1);
        renderTodos();
    }
}

renderTodos();
