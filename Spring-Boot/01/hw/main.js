// Bài 1: Viết function truyền vào 1 số nguyên dường. Tính giai thừa của số đó
// Ví dụ: calculateFactorial(5) = 5 * 4 * 3 * 2 * 1 = 120
function calculateFactorial(n) {
    if (n < 0) return "Invalid";
    let factorial = 1;
    for (let i = 1; i <= n; i++) {
        factorial *= i;
    }
    return factorial;
}
console.log(calculateFactorial(5));


// Bài 2: Viết function truyền vào 1 chuỗi. In ra chuỗi đảo ngược của chuỗi đó
// Ví dụ: reverseString(‘hello’) => olleh
function reverseString(str){
    return str.split('').reverse().join('');
}
console.log(reverseString('hello'));


// Bài 3: Viết function truyền vào mã quốc gia. Trả về message có ý nghĩa là “Xin chào”, tương ứng với mã quốc gia được truyền vào
// Ví dụ: translate(‘VN’) => “Xin chào”
// translate(‘EN’) => “Hello”

function translate(zipCode) {
    switch (zipCode) {
        case 'VN':
            return "Xin chào";
        case 'EN':
            return "Hello";
        case 'FR':
            return "Bonjour";
        default:
            return "Chào bạn!";
    }
}

console.log(translate('VN'));
console.log(translate('EN'));
console.log(translate('FR'));
console.log(translate('DE'));


// Bài 4: Cho function truyền vào 1 chuỗi dài hơn 15 ký tự. Viết 1 function cắt chuỗi, lấy ra 10 ký tự đầu tiên và thêm vào dấu “…” ở cuối chuỗi.
// Ví dụ : subString(“xinchaocacbandenvoiTechmaster”) => “xinchaocac…”

function subString(str) {
    if (str.length > 15) {
        return str.slice(0, 10) +'...' ;
    }
    return str;
}

console.log(subString("xinchaocacbandenvoiTechmaster"));
console.log(subString("hello world"));

