const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
// JS의 비구조화 할당으로 height에는 split으로 생성된 배열의 첫번째, width에는 두번째 값이 들어감
const [height, width] = input.shift().split(" ").map(Number);
const map = []; // 입력받은 지도를 저장하는 배열
const areas = []; // 넓이를 저장하는 배열
const dx = [1, -1, 0, 0]; // 상, 하, 좌, 우 방향을 움직이기 위한 x배열
const dy = [0, 0, 1, -1]; // 상, 하, 좌, 우 방향을 움직이기 위한 y배열
let amount = 0;

function start() {
  // visited 배열을 2차원 배열로 생성함. (지도가 2차원 배열이므로)
  const visited = Array.from(Array(height), () => new Array(width).fill(0));
  for (let i = 0; i < height; i++) map.push(input[i].split(" ").map(Number));
  for (let i = 0; i < height; i++) {
    for (let j = 0; j < width; j++) {
      // 시작지점을 변경시켜주기 위해서 2중 포문을 사용
      picture(visited, { y: i, x: j });
    }
  }
  console.log(amount);
  // JS의 spread 연산자를 사용하여 값을 열거 후 최대값을 구함
  if (areas.length !== 0) console.log(Math.max(...areas));
  else console.log(0);
}

function picture(visited, start) {
  // 방문 예정인 목록들임 (queue를 JS에서 지원하지 않아 array.shift() 사용)
  const willVisit = [];
  let area = 0;

  // visited[y][x]가 0일 경우에만 탐색함
  if (!visited[start.y][start.x]) {
    // map[y][x]의 값이 1이라면 방문 예정목록에 추가
    if (map[start.y][start.x]) {
      willVisit.push(start);
      amount += 1; // 섬의 개수 추가
      area += 1; // 섬의 면적 계산
      visited[start.y][start.x] = 1; // 방문 처리
    } else {
      visited[start.y][start.x] = 1; // 0이라면 (이동불가능한 위치) 방문처리
      return;
    }

    while (willVisit.length) {
      const node = willVisit.shift(); // queue가 없어서 shift

      // node기준 상 하 좌 우 탐색
      for (let i = 0; i < 4; i++) {
        if (
          node.y + dy[i] >= 0 &&
          node.y + dy[i] < height &&
          node.x + dx[i] >= 0 &&
          node.x + dx[i] < width
        ) {
          // map에서 이동 가능하고 방문처리가 되어있지 않다면
          if (
            map[node.y + dy[i]][node.x + dx[i]] &&
            !visited[node.y + dy[i]][node.x + dx[i]]
          ) {
            // 방문 예정목록에 추가
            willVisit.push({ x: node.x + dx[i], y: node.y + dy[i] });
            area += 1; // 면적 계산
          }
          // 방문 처리
          visited[node.y + dy[i]][node.x + dx[i]] = 1;
        }
      }
    }
  }
  // 면적이 1 이상이라면 넓이를 저장하는 배열에 추가
  if (area) areas.push(area);
}

start();
