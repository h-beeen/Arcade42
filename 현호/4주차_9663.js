const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().split(" ");
const size = Number(input);
const position = new Array(size); // 퀸의 위치를 담을 배열
let count = 0; // 경우의 수를 체크하는 변수

/*
    코드를 읽기전에, 
    position[index]의 의미는 다음과같다
    index = 현재 y좌표
    position[index] = 현재 x좌표
*/

function app() {
  // 백트래킹 시작
  queen(0);
  console.log(count);
}

function queen(curr) {
  // 종료 조건
  if (curr === size) {
    count += 1;
    return;
  }

  for (let i = 0; i < size; i++) {
    // 퀸 위치 설정
    position[curr] = i;
    // 프루닝
    if (promise(position, curr)) queen(curr + 1);
  }
}

function promise(position, curr) {
  for (let i = 0; i < curr; i++) {
    // 같은 가로줄에 있는지는 체크 할 필요가 없음, 한줄에 하나만 놓을거니까
    // 같은 세로줄을 검사할 때는 x좌표가 동일한지 검사하는것이므로 position[index]를 검사
    // 좌, 우 대각선상에 있음은 x좌표의 차, y좌표의 차가 서로 같은지 확인함으로써 검사할 수 있다.
    // 단, 부호가 양수 음수 모두 나오므로 둘 모두 검사하기 위해 Math.abs를 사용하여 절댓값 계산을 진행
    if (
      position[i] === position[curr] ||
      Math.abs(curr - i) === Math.abs(position[curr] - position[i])
    )
      return false;
  }
  return true;
}

app();
