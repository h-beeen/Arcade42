# 11399 ATM
from sys import stdin

# n, time 입력
n = int(stdin.readline())
time = list(map(int,stdin.readline().split()))

# time 정렬
time.sort()

# 결과 값 선언
result = 0

for i in range(n):
    # 각 시간이 나온 횟수를 곱하여 결과 값에 저장
    result += time[i] * n
    n -= 1

# 결과 값 출력
print(result)
