function passWord() {
    const btn = document.querySelector("button");
    const pass = document.querySelector("input");
    if (pass.type == "password") {
        pass.type = "text";
        btn.innerText = "Hide";
    } else {
        btn.innerText = "Show";
        pass.type = "password";
    }
}