const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const size = Number(input.shift());
let numbers = input.filter((val, idx) => idx >= 0).map((val) => val.split(" "));
let answer = "";

numbers = numbers.sort((a, b) => {
  // 0번 값이 같으면 1번 값 비교하여 return
  if (a[1] === b[1])
    return a[0] - b[0];
  return a[1] - b[1];
});

// 빈 string을 선언하여 값을 더해준다
numbers.forEach((value) => {
  answer += `${value[0]} ${value[1]}\n`;
});

console.log(answer);
