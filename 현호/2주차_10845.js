const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().split("\n");
const command_amount = input.shift();
const queue = [];
const result = [];

for (let i = 0; i < command_amount; i++) {
  switch (input[i]) {
    case "pop":
      result.push(queue.length ? queue.shift() : -1);
      break;
    case "front":
      result.push(queue.length ? queue[0] : -1);
      break;
    case "back":
      result.push(queue.length ? queue[queue.length - 1] : -1);
      break;
    case "size":
      result.push(queue.length);
      break;
    case "empty":
      result.push(queue.length ? 0 : 1);
      break;
    default:
      queue.push(input[i].split(" ")[1]);
      break;
  }
}
console.log(result.join("\n"));
