# 11047 동전

from sys import stdin

# n, k, coin 입력
n, k = map(int,stdin.readline().split())
coin = [int(stdin.readline()) for _ in range(n)]

# 결과 값 선언
result = 0

for i in range(n-1,-1,-1):
    # 값이 큰 coin부터 차례대로 나눈 후 몫을 결과값에 추가
    result += k//coin[i]
    # k에 나머지 초기화
    k %= coin[i]

# 결과 출력
print(result)
