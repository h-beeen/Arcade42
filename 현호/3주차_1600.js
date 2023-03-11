// 말이 되고싶은 원숭이 (미완성)

class Node {
  constructor(item) {
    this.x = item.x;
    this.y = item.y;
    this.horse = item.horse;
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
        horse: this.front.horse,
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
        horse: this.front.horse,
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
        horse: this.front.horse,
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
    return {
      x: this.front.x,
      y: this.front.y,
      horse: this.front.horse,
      depth: this.front.depth,
    };
  }

  printBack() {
    return {
      x: this.rear.x,
      y: this.rear.y,
      horse: this.rear.horse,
      depth: this.rear.depth,
    };
  }
}

const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const move = Number(input.shift());
const [width, height] = input.shift().split(" ").map(Number);
const dx_horse = [-2, -1, 1, 2, -2, -1, 1, 2];
const dy_horse = [-1, -2, -2, -1, 1, 2, 2, 1];
const dx_normal = [-1, 1, 0, 0];
const dy_normal = [0, 0, -1, 1];
const map = [];
const visited = new Array(move + 1);
const answer = [];
let queue;

function start() {
  for (let i = 0; i < height; i++) map.push(input[i].split(" ").map(Number));
  bfs({ x: 0, y: 0, horse: 0, depth: 0 });
  if (answer.length) console.log(Math.min(...answer));
  else console.log(-1);
}

function bfs(start) {
  for (let i = 0; i <= move; i++) {
    queue = new Queue();
    visited[i] = new Array();

    for (let j = 0; j < height; j++) {
      visited[i].push(new Array());
      for (let k = 0; k < width; k++) visited[i][j].push(0);
    }
    queue.enQueue(start);
    visited[i][0][0] = 1;

    while (queue.getSize()) {
      const node = queue.deQueue();

      if (node.x === width - 1 && node.y === height - 1)
        answer.push(node.depth);
      if (node.horse < i) {
        for (let j = 0; j < 8; j++) {
          let pos_x = node.x + dx_horse[j];
          let pos_y = node.y + dy_horse[j];

          movement(pos_x, pos_y, node, i, 1);
        }
      }
      for (let j = 0; j < 4; j++) {
        let pos_x = node.x + dx_normal[j];
        let pos_y = node.y + dy_normal[j];

        movement(pos_x, pos_y, node, i, 0);
      }
    }
  }
}

function movement(pos_x, pos_y, node, idx, check) {
  if (pos_x < 0 || pos_x >= width || pos_y < 0 || pos_y >= height) return;
  if (visited[idx][pos_y][pos_x] || map[pos_y][pos_x] === 1) return;

  queue.enQueue({
    x: pos_x,
    y: pos_y,
    horse: node.horse + check,
    depth: node.depth + 1,
  });
  visited[idx][pos_y][pos_x] = 1;
}

start();
