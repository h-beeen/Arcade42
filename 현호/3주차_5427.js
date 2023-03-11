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
const size = input.shift();
const dx = [1, -1, 0, 0]; // 상, 하, 좌, 우 이동을 위한 x배열
const dy = [0, 0, 1, -1]; // 상, 하, 좌, 우 이동을 위한 y배열
let map = null; // 입력받은 지도 저장을 위한 변수 (입력이 여러개이기에 재할당이 가능한 let 사용)
let fire = null; // 불의 위치를 담고 있는 변수 (입력이 여러개이기에 재할당이 가능한 let 사용)

function app() {
  for (let i = 0; i < size; i++) {
    initialize(); // 시작 전 값 초기화
    start(); // 기능 시작
  }
}

function start() {
  // 비구조화 할당을 통한 width, heigth 구하기
  const [width, height] = input.shift().split(" ").map(Number);
  let player = null; // 플레이어 위치 저장을 위한 변수
  let answer = null; // bfs 결과 저장을 위한 변수

  // input을 바탕으로 map에 지도 데이터 입력
  for (let i = 0; i < height; i++) {
    const str = input.shift();
    map.push(new Array());
    for (let j = 0; j < width; j++) {
      map[i].push(str[j]);
      // 플레이어 위치를 확인한다면 player 변수에 객체 형태로 저장
      if (str[j] === "@") player = { x: j, y: i, depth: 0 };
      // 불의 위치를 확인한다면 fire (큐) 에 저장
      if (str[j] === "*") fire.enQueue({ x: j, y: i });
    }
  }
  answer = bfs(width, height, player);
  if (answer !== -1) console.log(answer + 1);
  else console.log("IMPOSSIBLE");
}

function initialize() {
  // map과 fire 초기화
  map = [];
  fire = new Queue();
}

function bfs(width, height, player) {
  const queue = new Queue(); // 방문목록을 위한 큐 생성
  const visited = []; // 방문처리를 위한 배열 생성
  let now = -1; // 불의 움직임을 제어하기 위한 변수

  // start 값을 넣어줌
  queue.enQueue(player);
  // 방문 배열을 2차원 배열로 생성
  for (let i = 0; i < height; i++) visited.push(new Array(width).fill(0));
  visited[player.y][player.x] = 1; // 시작 위치 방문처리

  while (queue.getSize()) {
    const node = queue.deQueue();

    // depth의 값이 바뀔때만 불이 옮겨붙어야한다.
    if (node.depth > now) {
      let len = fire.getSize();
      for (let i = 0; i < len; i++) {
        let fire_node = fire.deQueue();

        // 불은 상, 하, 좌, 우로 움직인다
        for (let j = 0; j < 4; j++) {
          let pos_x = fire_node.x + dx[j];
          let pos_y = fire_node.y + dy[j];

          // 움직일 장소가 배열 밖을 벗어나지는 않는지 체크
          if (pos_x >= 0 && pos_x < width && pos_y >= 0 && pos_y < height) {
            if (
              (map[pos_y][pos_x] === "." || map[pos_y][pos_x] === "@") &&
              !visited[pos_y][pos_x]
            ) {
              map[pos_y][pos_x] = "*"; // map의 값을 불로 바꿔주고
              visited[pos_y][pos_x] = 1; // 방문처리를 한다
              fire.enQueue({ x: pos_x, y: pos_y }); // 불이 옮겨붙을 예정인 위치를 넣어준다
            }
          }
        }
      }
      now = node.depth; // 같은 depth에서 여러번 불이 옮겨붙는것을 방지
    }

    // 플레이어의 움직임 (상, 하, 좌, 우)
    for (let i = 0; i < 4; i++) {
      let pos_x = node.x + dx[i];
      let pos_y = node.y + dy[i];

      // 플레이어가 탈출에 성공했을 경우 depth를 반환
      if (pos_x === -1 || pos_x === width || pos_y === -1 || pos_y === height)
        return node.depth;
      // 움직일 예정인 위치가 비어있고 방문처리가 되지 않았다면
      if (map[pos_y][pos_x] === "." && !visited[pos_y][pos_x]) {
        // 방문 예정 목록에 추가
        queue.enQueue({ x: pos_x, y: pos_y, depth: node.depth + 1 });
        visited[pos_y][pos_x] = 1; // 방문처리
      }
    }
  }
  // 탈출에 실패한 경우이므로 -1 반환
  return -1;
}

app();
