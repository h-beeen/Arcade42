const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [amount, pair] = input.shift().split(' ').map(Number);
const comparisons = input.map(str => str.split(" "));
const HEAVY = 1;
const LIGHT = -1;

function solve() {
  const marbles = [];
  // 중간값이 될 수 있는 자기보다 무거운 구슬의 최대값
  const heavyMax = (amount - 1) / 2;
  // 중간값이 될 수 있는 자기보다 가벼운 구슬의 최대값
  const lightMax = amount - (amount + 1) / 2;
  let answer = 0;

  for (let i = 0; i < amount; i++) {
    marbles.push(new Array(amount).fill('0'));
  }

  for (let i = 0; i < comparisons.length; i++) {
    const heavier = Number(comparisons[i][0]) - 1;
    const lighter = Number(comparisons[i][1]) - 1;
    // 자기보다 무겨우면 1, 가벼우면 -1
    marbles[heavier][lighter] = 1;
    marbles[lighter][heavier] = -1;
  }
  // 플로이드-워셜 알고리즘 사용
  floyd(marbles);
  // marbles 배열을 순환하며 탐색
  for (let i = 0; i < amount; i++) {
    // i번째 구슬이 다른 구슬에 비해서 무거운지 체크
    let heavy = 0;
    // i번쨰 구슬이 다른 구슬에 비해서 가벼운지 체크
    let light = 0;

    for (let j = 0; j < amount; j++) {
      if (marbles[i][j] === LIGHT) {
        // marbles[i][j]가 -1이라면 i가 j보다 가벼운것이므로
        // [i][j]번째 구슬에비해 무겁다고 할 수 있다
        heavy += 1;
      } else if (marbles[i][j] === HEAVY) {
        // marbles[i][j]가 1이라면 i가 j보다 무거운것이므로
        // [i][j]번째 구슬에비해 가볍다고 할 수 있다
        light += 1;
      }
    }
    // 최대치를 넘어가면 중간값이 될 수 없는 구슬이므로
    if (light > lightMax || heavy > heavyMax) {
      answer += 1;
    }
  }
  console.log(answer);
}

function floyd(marbles) {
  for (let i = 0; i < amount; i++) {
    for (let j = 0; j < amount; j++) {
      for (let k = 0; k < amount; k++) {
        if (marbles[j][i] === LIGHT && marbles[i][k] === LIGHT) {
          marbles[j][k] = LIGHT;
          marbles[k][j] = HEAVY;
        }
      }
    }
  }
}

solve();
