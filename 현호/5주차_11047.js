const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [kind, sum] = input.shift().split(" ").map(Number);

function coin() {
  // 계산을 몇번 하는지에 대한 변수
  let count = 0;
  // 파싱받은 sum을 직접 변경하기보단 값을 복사하고 변경한다
  let curr = sum;

  // 큰 값부터 시작하여 값을 나눠준다
  for (let i = input.length - 1; i >= 0; i--) {
    if (Number(input[i]) <= curr) {
      // 나눈 몫을 횟수에 더해준다
      count += Math.floor(curr / input[i]);
      curr %= input[i];
    }
  }
  console.log(count);
}

coin();
