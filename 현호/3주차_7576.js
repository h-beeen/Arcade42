class Node {
  constructor(item) {
    this.x = item.x;
    this.y = item.y;
    this.depth = item.depth;
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
      const temp = {
        x: this.front.x,
        y: this.front.y,
        depth: this.front.depth,
      };
      this.front = null;
      this.rear = null;
      this.size -= 1;
      return temp;
    } else if (this.size === 2) {
      const temp = {
        x: this.front.x,
        y: this.front.y,
        depth: this.front.depth,
      };
      const front = this.front.next;
      this.front = front;
      this.rear = front;
      this.size -= 1;
      return temp;
    } else if (this.size > 2) {
      const temp = {
        x: this.front.x,
        y: this.front.y,
        depth: this.front.depth,
      };
      this.front = this.front.next;
      this.size -= 1;
      return temp;
    }
  }

  getSize() {
    return this.size;
  }

  printFront() {
    return { x: this.front.x, y: this.front.y, depth: this.front.depth };
  }

  printBack() {
    return { x: this.rear.x, y: this.rear.y, depth: this.rear.depth };
  }
}

const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [width, height] = input.shift().split(" ").map(Number);
const map = [];
const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];

function start() {
  const queue = new Queue();
  const visited = [];
  let needGrow = 0;

  for (let i = 0; i < height; i++) visited.push(new Array(width).fill(0));
  for (let i = 0; i < height; i++) map.push(input[i].split(" ").map(Number));
  for (let i = 0; i < height; i++) {
    for (let j = 0; j < width; j++) {
      if (map[i][j] === 1) {
        queue.enQueue({ x: j, y: i, depth: 0 });
        visited[i][j] = 1;
      } else if (map[i][j] === -1) {
        visited[i][j] = 1;
      } else needGrow += 1;
    }
  }

  console.log(bfs(queue, visited, needGrow));
}

function bfs(queue, visited, needGrow) {
  let cnt = 0;
  let now = -1;

  while (queue.getSize()) {
    const node = queue.deQueue();
    for (let i = 0; i < 4; i++) {
      let pos_x = node.x + dx[i];
      let pos_y = node.y + dy[i];

      if (node.depth > now) now = node.depth;

      if (pos_x >= 0 && pos_x < width && pos_y >= 0 && pos_y < height) {
        if (map[pos_y][pos_x] === 0 && !visited[pos_y][pos_x]) {
          visited[pos_y][pos_x] = 1;
          map[pos_y][pos_x] = 1;
          cnt += 1;
          queue.enQueue({ x: pos_x, y: pos_y, depth: node.depth + 1 });
        }
      }
    }
  }

  if (needGrow !== cnt) return -1;
  return now;
}

start();
