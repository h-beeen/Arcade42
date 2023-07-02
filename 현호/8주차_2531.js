const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [plates_amount, sushi_kind, plates_continual, coupon_number] = input[0].split(" ").map(Number);
const sushi = input.map(Number);

function solve() {
  let start = 0;
  let end = plates_continual - 1;
  let max_cnt= 0;
  let cnt = 0;
  const continual = sushi.slice(0, plates_continual).reduce((sum , val) => {
    if (sum[val])
      sum[val] += 1;
    else {
      sum[val] = 1;
      cnt += 1;
      max_cnt += 1;
    }
    return sum;
  }, {});

  while (start < plates_amount) {
    if (continual[sushi[start]] === 1)
      cnt -= 1;
    continual[sushi[start]] -= 1;

    start += 1;
    end += 1;

    if (end === plates_amount)
      continual[sushi[end]] += 1;
    else {
      continual[sushi[end]] = 1;
      cnt += 1;
    }
  }

  console.log(Math.max(max_cnt, cnt + !continual[coupon_number]));
}

solve();