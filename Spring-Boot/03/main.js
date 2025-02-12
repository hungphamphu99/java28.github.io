// Yêu cầu
// Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
const heading = document.getElementById("heading");
console.log(heading)

const heading2 = document.querySelector("#heading");
console.log(heading2)

const heading3 = document.querySelector("h1");
console.log(heading3)

// const heading4 = document.querySelector("h1:nth-chid(1)");
// console.log(heading4)


heading.style.color = "red";
heading.style.textTransform = "Uppercase" ;

// Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
const paraList = document. querySelectorAll(" para");
console.log(paraList)

// paraList.forEach((para) => {
//     para.style.color = "blue";
//     para.style.fontSize = "20px";
// });

Array. from(paraList) .map ((para) => {
    para.style.color = "blue";
    para.style.fontSize = "20px" ;
    }) ;
// Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
const link = document.createElement("a") ;
link. href = "https://google.com"; 
// link.innerText ="<b>Google</b>"
link.innerHTML ="<b>Google</b>"
console.log(link);
const content = document.querySelector(".content")
document.body.insertBefore(link, content)
// Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
const title = document. getElementById("title");
title.innerHTML = "Đây là thẻ tiếu đề";

// Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
// const description = document. querySelector(" description");
// description.classList.add("text-bold", "abc", "xyz");
// description.classList.remove("abc", "xyz");
// console.log(description.classList.contains("abc"));
// console.log(description.classList.contains("text-bold"));


// setInterval(() => {
//     description.classList.toggle("text-bold")
// }, 200);


// Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
const button =document.createElement("button");
button.innerHTML = "click me";

const p3 = document.querySelector(".para-3");
document.body.replaceChild(button, p3);

// Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó
const p2 = document.querySelector(".para-2");
const cpp2 = p2.cloneNode(cpp2, button);
document.body.insertBefore(cpp2, button);
// Xóa thẻ có class=“para-1”
const p1 = document.querySelector(".para-1");

document.body.removeChild(p1);

// Thực hành: Cho 1 array có câu trúc như sau

const socials = [
    {id: 1, name: 'Facebook' , url: 'https://facebook.com'},
    {id: 2, name: 'Instagram', url: 'https://instagram.com'}, 
    {id: 3, name: 'Twitter', url: 'https://twitter.com'},
]

// Tạo ra cấu trúc thẻ như sau
// <UL>
// ‹li>‹a href="https://facebook.com">Facebook</a></li>
// ‹li›‹a href="https://instagram.com">Instagram</a>/li›
// ‹li><a href="https://twitter.com">Twitter</a></li>
// </ul>
// Insert vào thẻ div có class="socials"


const ul = document.createElement("ul")
socials.forEach(social => {
    const li = document.createElement('li');
    const a = document.createElement('a');
    a.href = social.url;
    a.textContent = social.name;
    li.appendChild(a);
    ul.appendChild(li);
});

console.log(ul)
const div = document.createElement("div");
div.classList.add("socials");
div.appendChild(ul);
document.body.appendChild(div);