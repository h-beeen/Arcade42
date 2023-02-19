const fs = require("fs");
const input = fs
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map(Number);
const command_amount = input.shift();
let arr = [];
let sum = 0;

for (let i = 0; i < command_amount; i++) {
  if (input[i]) arr.push(input[i]);
  else arr.pop();
}
arr.forEach((el) => (sum += el));
console.log(sum);
