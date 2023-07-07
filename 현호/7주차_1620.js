const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const [size, problems] = input.shift().split(" ").map(Number);
const pokemons = new Map();
const pokemonArray = [];

function solve() {
  for (let i = 0; i < size; i++) {
    // 맵에 포켓몬 이름, 순서 쌍으로 저장
    pokemons.set(input[i], i + 1);
    pokemonArray.push(input[i]);
  }

  for (let i = size; i < input.length; i++)
    if (Number.isNaN(+input[i])) // + 연산자를 달아 Number shift, 문자열이라면 NaN이 나오기때문에 문자열 판별 가능
      console.log(pokemons.get(input[i])); // map 객체에서 index 추출
    else // number일 경우
      console.log(pokemonArray[+input[i] - 1]); // 배열 인덱싱하여 name 추출
}

solve();