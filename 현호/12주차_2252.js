const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on('line', (line) => {
  input.push(line);
}).on('close', () => {
  solution(input);
  process.exit();
});

const solution = function (input) {
  const [n, m] = input.shift().split(' ').map(Number);
  const entry = Array.from({ length: n + 1 }, () => 0);
  const relations = Array.from({ length: n + 1 }, () => []);
  const answer = [];
  const queue = [];

  for (row of input) {
    const [a, b] = row.split(' ').map(Number);
    entry[b]++; // entry 카운트를 세어준다
    relations[a].push(b); // 관계 생성
  }

  // 위상정렬 알고리즘에 따라, entry가 0인 노드를 찾는다
  for (let i = 1; i <= n; i++) {
    if (entry[i] === 0) {
      queue.push(i);
      entry[i] = -1;
    }
  }

  while (queue.length > 0) {
    const node = queue.shift();
    answer.push(node);

    // queue에 들어가있는 값을 꺼내어서 relations를 순회한다
    for (student of relations[node]) {
      entry[student]--; // 해당 student의 값 차감

      if (entry[student] === 0) {
        queue.push(student); // 방문 예정 목록에 올려놓는다
        entry[student] = -1; // 방문 예정 목록에 올려놓으면서 해당 student의 값 차감
      }
    }
  }
  console.log(answer.join(' '));
};
