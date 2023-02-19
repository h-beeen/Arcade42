const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const tower = input[1].split(" ").map(Number);
const stack = [];
const result = [];

tower.forEach((value, idx) => {
  const currTower = {
    idx: idx + 1,
    value,
  };

  if (!stack.length) {
    stack.push(currTower);
    result.push(0);
    return;
  }

  if (stack[stack.length - 1].value < currTower.value) {
    while (stack.length) {
      if (stack[stack.length - 1].value >= currTower.value) break;
      stack.pop();
    }
    result.push(!stack.length ? 0 : stack[stack.length - 1].idx);
    stack.push(currTower);
  } else {
    result.push(!stack.length ? 0 : stack[stack.length - 1].idx);
    stack.push(currTower);
  }
});
console.log(result.join(" "));
