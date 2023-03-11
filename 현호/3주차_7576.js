// Queue Code
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
// 비구조화 할당을 통한 width와 height 값 구하기
const [width, height] = input.shift().split(" ").map(Number);
const map = [];
const dx = [1, -1, 0, 0]; // 상, 하, 좌, 우 움직임을 위한 x배열
const dy = [0, 0, 1, -1]; // 상, 하, 좌, 우 움직임을 위한 y배열

function start() {
  const queue = new Queue(); // 방문 예정 목록 관리를 위한 큐 선언
  const visited = []; // 방문 처리를 위한 배열 선언
  let needGrow = 0; // 익어야 하는 토마토의 개수를 카운팅하는 변수

  // 방문 처리를 위한 이차원 배열 생성
  for (let i = 0; i < height; i++) visited.push(new Array(width).fill(0));
  // input을 map에 저장
  for (let i = 0; i < height; i++) map.push(input[i].split(" ").map(Number));
  // map을 순회하면서 이미 익어있는 토마토와 익어야 하는 토마토의 수 카운팅
  for (let i = 0; i < height; i++) {
    for (let j = 0; j < width; j++) {
      if (map[i][j] === 1) {
        // 토마토가 익었다면 큐에 추가
        queue.enQueue({ x: j, y: i, depth: 0 });
        visited[i][j] = 1; // 방문 처리
      } else if (map[i][j] === -1) {
        visited[i][j] = 1; // 토마토가 없으니 방문처리만
      } else needGrow += 1; // 익어야 하는 토마토므로 카운트 +
    }
  }

  console.log(bfs(queue, visited, needGrow));
}

function bfs(queue, visited, needGrow) {
  let cnt = 0; // 익은 토마토의 개수를 카운팅하는 변수
  let now = -1; // 현재 일수 카운팅

  while (queue.getSize()) {
    const node = queue.deQueue();

    // 익은 토마토는 상, 하, 좌, 우에 위치하는 토마토를 익게 한다
    for (let i = 0; i < 4; i++) {
      let pos_x = node.x + dx[i];
      let pos_y = node.y + dy[i];

      // 현재 몇일째인지 파악하기 위해 depth보다 now가 작으면 갱신
      if (node.depth > now) now = node.depth;

      // 참조하려는 위치가 유효한 범위인지 확인
      if (pos_x >= 0 && pos_x < width && pos_y >= 0 && pos_y < height) {
        // 해당 원소가 안익은 토마토이고, 방문이 되어있지 않다면
        if (map[pos_y][pos_x] === 0 && !visited[pos_y][pos_x]) {
          visited[pos_y][pos_x] = 1; // 방문 처리
          map[pos_y][pos_x] = 1; // 맵에 해당 내용 반영
          cnt += 1; // 익은 토마토 카운트 + 1
          // 방문 예정 목록에 추가
          queue.enQueue({ x: pos_x, y: pos_y, depth: node.depth + 1 });
        }
      }
    }
  }

  // 익어야 하는 토마토의 수와 익은 토마토의 수가 같은지 체크
  if (needGrow !== cnt) return -1;
  return now;
}

start();
