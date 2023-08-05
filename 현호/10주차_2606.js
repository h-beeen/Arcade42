const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const computerNumber = input.shift();
const connectedComputer = input.shift();

function solve() {
  const networks = input.map((arr) => arr.split(" ")).sort((a, b) => a[0] + a[1] - (b[0] + b[1]));
  const res = bfs('1', networks);

  console.log(res.size - 1);
}

function bfs(start, networks) {
  const queue = [];
  const visited = new Set();

  // 시작지점을 넣어준다
  queue.push({ computer: start, depth: 0 });
  // 방문처리를 해준다
  visited.add(start);
  while (queue.length) {
    const node = queue.shift();

    // networks배열을 순회하며 방문목록에 추가해준다
    networks.forEach((network) => {
      if (node.computer === network[0] && !visited.has(network[1])) {
        // network값의 첫 번째 값과 node.computer가 같고, network의 두 번째 값을 방문하지 않았을 때
        queue.push({ computer: network[1], depth: node.depth + 1 });
      } else if (node.computer === network[1] && !visited.has(network[0])) {
        // network값의 두 번째 값과 node.computer가 같고, network의 첫 번째 값을 방문하지 않았을 때
        queue.push({ computer: network[0], depth: node.depth + 1 });
      }
      // node.computer를 visit처리해준다.
      visited.add(node.computer);
    });
  }

  return visited;
}

solve();
