const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const attempt = Number(input.shift()); // 시도 횟수
let max = 0; // 최댓값을 구하기 위해 사용하는 변수

function app() {
  for (let i = 0; i < attempt; i++) {
    const stat = new Array(11); // 플레이어들의 스탯을 담는 배열
    const info = []; // 입력 값 받아오는 배열

    for (let i = 0; i < 11; i++) stat[i] = { value: 0, index: -1 }; // 스탯 배열 초기화
    for (let i = 0; i < 11; i++)
      info.push(input.shift().split(" ").map(Number)); // 입력 파싱
    select(info, stat, 0); // 백트래킹 진행
    console.log(max); // 최댓값 출력
    max = 0; // 변수 값 초기화
  }
}

function select(info, stat, player) {
  // 종료 조건
  if (player === 11) {
    // 배열의 합을 구하는 로직
    const currSum = stat.reduce((sum, curr) => {
      return (sum += curr.value);
    }, 0);

    // 현재 구한 최댓값보다 크면 값을 변경
    if (currSum > max) max = currSum;
    return;
  }

  for (let i = 0; i < 11; i++) {
    // 프루닝 과정
    if (promise(info[player][i], stat, player, i)) {
      stat[player].value = info[player][i];
      stat[player].index = i;
      // 재귀
      select(info, stat, player + 1);
    }
  }
}

function promise(info, stat, player, index) {
  // 값이 0이면 담을 수 없기에
  if (!info) return false;
  // 겹치는 index가 있는지
  for (let i = 0; i < player; i++) if (stat[i].index === index) return false;
  return true;
}

app();
