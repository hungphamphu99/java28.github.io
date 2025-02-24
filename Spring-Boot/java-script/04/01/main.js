const sayHello = ()=>{
    alert("hello");
}

const button2 = document.getElementById("2");
button2.onclick =() =>{
    alert("hello");
}

const button3 = document.getElementById("3");
button3.addEventListener("click", sayHello)