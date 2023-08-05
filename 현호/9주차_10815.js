const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const myCards = input[1].split(" ").map(Number).sort((a, b) => a - b);
const targetCards = input[3].split(" ").map(Number);

function binarySearch(array, val) {
  let low = 0;
  let high = array.length - 1;

  while (low <= high) {
    let mid = Math.floor((low + high) / 2);

    if (val > array[mid])
      low = mid + 1;
    else if (val < array[mid])
      high = mid - 1;
    else if (val === array[mid])
      return true;
  }

  return false;
}

function solve() {
  const answer = new Array(targetCards.length).fill(0);

  targetCards.forEach((card, idx) => {
    if (binarySearch(myCards, card))
      answer[idx] = 1;
  });

  return answer;
}

console.log(solve().join(' '));