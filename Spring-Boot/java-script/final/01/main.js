const myStrings = ['aba', 'aa', 'ad', 'c', 'vcd'];

function getStringHasMaxLength(arr) {
    let maxLength = 0;
    let result = [];
  
    for (let str of arr) {
      if (str.length > maxLength) {
        maxLength = str.length;
        result = [str]; 
      }
      else if (str.length === maxLength) {
        result.push(str);
      }
    }
  
    return result;
  }
  
  const longestStrings = getStringHasMaxLength(myStrings);
  console.log(longestStrings);
  