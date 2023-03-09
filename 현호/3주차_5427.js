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
const size = input.shift();
const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];
let map = null;
let fire = null;

function app() {
  for (let i = 0; i < size; i++) {
    initialize();
    start();
  }
}

function start() {
  const [width, height] = input.shift().split(" ").map(Number);
  let player = null;
  let answer = null;

  for (let i = 0; i < height; i++) {
    const str = input.shift();
    map.push(new Array());
    for (let j = 0; j < width; j++) {
      map[i].push(str[j]);
      if (str[j] === "@") player = { x: j, y: i, depth: 0 };
      if (str[j] === "*") fire.enQueue({ x: j, y: i });
    }
  }
  answer = bfs(width, height, player);
  if (answer !== -1) console.log(answer + 1);
  else console.log("IMPOSSIBLE");
}

function initialize() {
  map = [];
  fire = new Queue();
}

function bfs(width, height, player) {
  const queue = new Queue();
  const visited = [];
  let now = -1;

  queue.enQueue({ x: player.x, y: player.y, depth: player.depth });
  for (let i = 0; i < height; i++) visited.push(new Array(width).fill(0));
  visited[player.y][player.x] = 1;

  while (queue.getSize()) {
    const node = queue.deQueue();

    if (node.depth > now) {
      let len = fire.getSize();
      for (let i = 0; i < len; i++) {
        let fire_node = fire.deQueue();

        for (let j = 0; j < 4; j++) {
          let pos_x = fire_node.x + dx[j];
          let pos_y = fire_node.y + dy[j];

          if (pos_x >= 0 && pos_x < width && pos_y >= 0 && pos_y < height) {
            if (
              (map[pos_y][pos_x] === "." || map[pos_y][pos_x] === "@") &&
              !visited[pos_y][pos_x]
            ) {
              map[pos_y][pos_x] = "*";
              visited[pos_y][pos_x] = 1;
              fire.enQueue({ x: pos_x, y: pos_y });
            }
          }
        }
      }
      now = node.depth;
    }

    for (let i = 0; i < 4; i++) {
      let pos_x = node.x + dx[i];
      let pos_y = node.y + dy[i];

      if (pos_x === -1 || pos_x === width || pos_y === -1 || pos_y === height)
        return node.depth;
      if (map[pos_y][pos_x] === "." && !visited[pos_y][pos_x]) {
        queue.enQueue({ x: pos_x, y: pos_y, depth: node.depth + 1 });
        visited[pos_y][pos_x] = 1;
      }
    }
  }
  return -1;
}

app();
