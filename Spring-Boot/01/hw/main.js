function calculateFactorial(n) {
    if (n < 0) return "Invalid";
    let factorial = 1;
    for (let i = 1; i <= n; i++) {
        factorial *= i;
    }
    return factorial;
}

console.log(calculateFactorial(5));


function reverseString(str){
    return str.split('').reverse().join('');
}
console.log(reverseString('hello'));


function translate(countryCode) {
    switch (countryCode) {
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


function subString(str) {
    if (str.length > 15) {
        return str.slice(0, 10) ;
    }
    return str;
}

console.log(subString("xinchaocacbandenvoiTechmaster"));
console.log(subString("hello world"));

