const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [size, target] = input.shift().split(" ").map(Number);
const numbers = input[0].split(" ").map(Number);
let cnt = 0; // 정답 개수 카운팅

function solve() {
  let start = 0; // 시작 포인터
  let end = 0; // 종료 포인터
  let curr = 0; // 부분합

  while (end <= size) { // 종료 포인터가 size보다 작을때까지
    if (curr + numbers[end] >= target) { // 부분합 + 종료포인터가 가르키는 값이 target보다 크다면
      if (curr + numbers[end] === target)
        cnt += 1;
      curr -= numbers[start++]; // start 포인터를 1 증가시켜주면서 부분합에서 그만큼 감산
    }
    else // 부분합 + 종료포인터가 가르키는 값이 target보다 크다면
      curr += numbers[end++]; // end 포인터를 1 증가시켜주면서 부분합에서 그만큼 증산
  }
  console.log(cnt);
}

solve();