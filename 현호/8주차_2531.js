const input = require('fs')
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n');

const [N, d, k, c] = input[0].split(' ').map(Number);
const sushi = input.slice(1).map(Number);
let maxCount = 0;

function solve() {
  let count = 0;
  let start = 0;
  let end = k - 1;
  // sushiList에는 현재 선택된 초밥들의 종류별 개수 저장됨
  const sushiList = sushi.slice(0, k).reduce((acc, val) => {
    // 리스트에 있으면 + 1
    if (acc[val])
      acc[val]++;
    // 리스트에 없으면 1로 만들고 count, maxCount 증가
    else {
      acc[val] = 1;
      count++;
      maxCount++;
    }
    return acc;
  }, {});

  // 투포인터 알고리즘 시작
  // 가장 최대의 경우의수는 k개의 초밥의 개수가 전부 다를때임
  while (start < N) {
    // 초밥이 하나밖에 없으면 i번째 초밥을 버렸을 때 한종류의 초밥을 선택하지 않게됨
    if (sushiList[sushi[start]] === 1)
      count--;
    sushiList[sushi[start]]--;

    start++;
    end++;

    // 회전초밥이므로 끝에 도달하면 처음으로 가야함
    if (end === N)
      end = 0;

    // 리스트에 해당 초밥이 존재한다면 해당 값에 + 1
    if (sushiList[sushi[end]])
      sushiList[sushi[end]]++;
    // 리스트에 해당 초밥이 존재하지 않는다면 count를 1 올리고 리스트에서의 값을 1로만들어줌
    else {
      sushiList[sushi[end]] = 1;
      count++;
    }

    // 해당회차 count와 maxCount를 비교하여 최댓값 갱신
    maxCount = Math.max(maxCount, count + !sushiList[c]);
  }
}

solve();
console.log(maxCount);
