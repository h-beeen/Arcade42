const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().split(" ").map(Number);
const array = new Array(input[1]);

function promise(curr) {
  // n개까지의 중복을 검증
  const subArray = array.slice(0, curr + 1);
  // set을 사용하면 간단하게 중복의 여부를 체크할 수 있음
  const set = new Set(subArray);
  return set.size === subArray.length;
}

function combination(curr) {
  // 종료 조건
  if (curr === input[1]) {
    console.log(array.join(" "));
    return;
  }
  // 백트래킹
  for (let i = 0; i < input[0]; i++) {
    array[curr] = i + 1;
    if (promise(curr)) combination(curr + 1);
  }
}

combination(0);
