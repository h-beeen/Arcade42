const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const size = Number(input.shift());
let numbers = input.filter((val, idx) => idx >= 0).map((val) => val.split(" "));
let answer = "";

numbers = numbers.sort((a, b) => {
  if (a[1] === b[1])
    return a[0] - b[0];
  return a[1] - b[1];
});
numbers.forEach((value) => {
  answer += `${value[0]} ${value[1]}\n`;
});

console.log(answer);
