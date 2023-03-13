class Node {
  constructor(item) {
    this.item = item;
    this.next = null;
  }
}

class Queue {
  constructor() {
    this.front = null;
    this.rear = null;
    this.size = 0;
  }

  enQueue(item) {
    const node = new Node(item);
    if (this.front === null) {
      this.front = node;
      this.front.next = this.rear;
    } else this.rear.next = node;
    this.rear = node;
    this.size += 1;
  }

  deQueue() {
    if (this.size === 1) {
      const temp = this.front.item;
      this.front = null;
      this.rear = null;
      this.size -= 1;
      return temp;
    } else if (this.size === 2) {
      const temp = this.front.item;
      const front = this.front.next;
      this.front = front;
      this.rear = front;
      this.size -= 1;
      return temp;
    } else if (this.size > 2) {
      const temp = this.front.item;
      this.front = this.front.next;
      this.size -= 1;
      return temp;
    }
  }

  getSize() {
    return this.size;
  }

  printFront() {
    return this.front.item;
  }

  printBack() {
    return this.rear.item;
  }
}



const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const command_amount = input.shift();
const queue = new Queue();
const result = [];

for (let i = 0; i < command_amount; i++) {
  switch (input[i]) {
    case "pop":
      result.push(queue.getSize() ? queue.deQueue() : -1);
      break;
    case "front":
      result.push(queue.getSize() ? queue.printFront() : -1);
      break;
    case "back":
      result.push(queue.getSize() ? queue.printBack() : -1);
      break;
    case "size":
      result.push(queue.getSize());
      break;
    case "empty":
      result.push(queue.getSize() ? 0 : 1);
      break;
    default:
      queue.enQueue(input[i].split(" ")[1]);
      break;
  }
}
console.log(result.join("\n"));
