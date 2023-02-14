const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().split("\n");
const command_amount = input.shift();
const stack = [];
const result = [];

for (let i = 0; i < command_amount; i++) {
  switch (input[i]) {
    case "pop":
      result.push(stack.length ? stack.pop() : -1);
      break;
    case "top":
      result.push(stack.length ? stack[stack.length - 1] : -1);
      break;
    case "size":
      result.push(stack.length);
      break;
    case "empty":
      result.push(stack.length ? 0 : 1);
      break;
    default:
      stack.push(input[i].split(" ")[1]);
      break;
  }
}
console.log(result.join("\n"));
