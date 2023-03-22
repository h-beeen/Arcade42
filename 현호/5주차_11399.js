const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const size = Number(input.shift());

function app() {
  // 입력 파싱
  const line = input[0].split(" ").map(Number);
  let sum = 0;

  line.sort((a, b) => a - b);
  for (let i = 0; i < size; i++) {
    for (let j = 0; j <= i; j++) sum += line[j];
  }
  console.log(sum);
}

app();
