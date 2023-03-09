const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [height, width] = input.shift().split(" ").map(Number);
const map = [];
const areas = [];
const dx = [1, -1, 0, 0];
const dy = [0, 0, 1, -1];
let amount = 0;

function start() {
  const visited = Array.from(Array(height), () => new Array(width).fill(0));
  for (let i = 0; i < height; i++) map.push(input[i].split(" ").map(Number));
  for (let i = 0; i < height; i++) {
    for (let j = 0; j < width; j++) {
      picture(visited, { y: i, x: j });
    }
  }
  console.log(amount);
  if (areas.length !== 0) console.log(Math.max(...areas));
  else console.log(0);
}

function picture(visited, start) {
  const willVisit = [];
  let area = 0;

  if (!visited[start.y][start.x]) {
    if (map[start.y][start.x]) {
      willVisit.push(start);
      amount += 1;
      area += 1;
      visited[start.y][start.x] = 1;
    } else {
      visited[start.y][start.x] = 1;
      return;
    }

    while (willVisit.length) {
      const node = willVisit.shift();

      for (let i = 0; i < 4; i++) {
        if (
          node.y + dy[i] >= 0 &&
          node.y + dy[i] < height &&
          node.x + dx[i] >= 0 &&
          node.x + dx[i] < width
        ) {
          if (
            map[node.y + dy[i]][node.x + dx[i]] &&
            !visited[node.y + dy[i]][node.x + dx[i]]
          ) {
            willVisit.push({ x: node.x + dx[i], y: node.y + dy[i] });
            area += 1;
          }
          visited[node.y + dy[i]][node.x + dx[i]] = 1;
        }
      }
    }
  }
  if (area) areas.push(area);
}

start();
