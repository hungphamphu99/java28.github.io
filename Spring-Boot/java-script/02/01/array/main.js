


// /*Hàm tìm vị trí số lớn nhất trong mảng JavaScript*/
// function maxElement(array){

//     //Giả định vị trí số lớn nhất là số đầu tiên của mảng.
//     let max =  array[0];
//     let max_index= 0;   
//     /*So sánh từng số trong mảng với giá trị đầu tiên để tìm ra giá trị lớn nhất*/
//     for (let i = 0; i < array.length; ++i) {
//         if (max < array[i]) { //Thay đổi giá trị lớn nhất nếu tìm ra số lớn hơn
//             max = array[i];
//             max_index =i;
//         }
//     }
//     console.log("max= ",max);
//     console.log("max_index= ",max_index);
// }

// let num = [5, 4, 7, 2, 8, 7, 3];

// maxElement(num);



let array = [5, 4, 7, 2, 8, 12 ,7, 3];

array.sort(function(a, b) {
  return a - b;
});
console.log("max= ",array[array.length-1]);

n = 2
console.log("max thứ "+n +"= ",array[array.length-n]);



function mod2(array){
    let newarray =[];
    for (let index = 0; index < array.length; index++) {
        let x = array[index] % 2 ;
        newarray.push(x)
    }
    return newarray
}
let result = mod2([1, 2, 3, 4, 5]);
console.log(result); 



function repeatString(str) {
    return Array(10).fill(str).join('-');
}

console.log(repeatString('a')); 
console.log(repeatString('hello')); 


function isSymmetricString(string) {
    const cleanedString = string.replace(/\s+/g, '').toLowerCase();
    const reversedString = cleanedString.split('').reverse().join('');
    return cleanedString === reversedString;
}

console.log(isSymmetricString("Race car")); 
console.log(isSymmetricString("hello world")); 


function sortDigitsToMin(number) {
    let digits = number.toString().split('');
    
    digits.sort((a, b) => a - b);
    
    if (digits[0] === '0') {
        for (let i = 1; i < digits.length; i++) {
            if (digits[i] !== '0') {
                [digits[0], digits[i]] = [digits[i], digits[0]];
                break;
            }
        }
    }
    
    return parseInt(digits.join(''), 10);
}

console.log(sortDigitsToMin(53751));  
console.log(sortDigitsToMin(14350));  
console.log(sortDigitsToMin(203950)); 
