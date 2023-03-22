const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const size = Number(input.shift());
const lines = [];

function app() {
  // 입력 파싱
  for (let i = 0; i < size; i++) lines.push(input[i].split(" ").map(Number));
  // 입력 정렬
  lines.sort((a, b) => a[0] - b[0]);
  // 기능 시작
  console.log(draw());
}

function draw() {
  // 좌표 초기화
  const pos = { start: null, end: null };
  // 길이의 합
  let sum = 0;

  // 좌표 초기 지정
  [pos.start, pos.end] = lines[0];
  for (let i = 1; i < size; i++) {
    // 비구조화 할당으로 x, y에 각각 lines[i][0], lines[i][1]
    const [x, y] = lines[i];

    // 현재 조회중인 지점의 끝점보다 pos의 시작점이 더 크다면,
    // 현재 조회중인 지점의 시작점보다 pos의 끝점이 더 작다면,
    // 다른 영역에 있다
    if (x > pos.end || y < pos.start) {
      sum += pos.end - pos.start;
      pos.start = x;
      pos.end = y;
      continue;
    }
    // pos의 시작점보다 현재 조회중인 지점의 시작점이 더 작다면 좌표를 갈아준다
    if (x < pos.start) pos.start = x;
    // pos의 끝점보다 현재 조회중인 지점의 끝점이 더 크다면 좌표를 갈아준다
    if (y > pos.end) pos.end = y;
  }
  // 총합에 현재 계산중인 끝점 - 시작점을 더해준다
  sum += pos.end - pos.start;

  return sum;
}

app();
