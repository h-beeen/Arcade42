  const fs = require("fs");
  const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
  // 시간을 ex) 2400 꼴의 정수형으로 나타내기 위한 작업
  const [eventStartTime, eventEndTime, streamEndTime] = input.shift().split(" ").map((el) => el.split(":").map(Number).reduce((a, b) => a * 100 + b));
  // 중복을 제거하기위한 set 자료구조 사용
  const attendance = new Set();
  let count = 0;

  function solve() {
    const log = [];

    input.forEach((str) => {
      const [time, id] = str.split(" ");
      const curr = time.split(":").map(Number).reduce((a, b) => a * 100 + b);

      // startTime보다 이르면 출석자 명부에 넣는다
      if (curr <= eventStartTime)
        attendance.add(id);
      // endTime보다 크고 streamEnd보다 이르면 출석자 명부에서 빼면서 count 1 증가, 이 때 출석자 명부에 있었어야함
      else if (curr >= eventEndTime && curr <= streamEndTime && attendance.has(id)) {
        count += 1;
        attendance.delete(id);
      }
    });
  }

  solve();
  console.log(count);