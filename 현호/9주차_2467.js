const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const numbers = input[1].split(" ").map(Number);
const answer = [0, 0];
let min = Math.abs(numbers[numbers.length - 1] + numbers[0]);

function solve() {
  let start = 0;
  let end = 1;

  while (end <= numbers.length - 1) {
    if (Math.abs(numbers[start] + numbers[end]) > min) {

    } else {
      min = Math.abs(numbers[start] + numbers[end]);
    }
  }
}

solve();