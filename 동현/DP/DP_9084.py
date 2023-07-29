# 9084 연속합

import sys

# T 입력
t = int(sys.stdin.readline())

for _ in range(t):
    # N, 동전, M 입력
    n = int(sys.stdin.readline())
    coins = list(map(int, sys.stdin.readline().split()))
    m = int(sys.stdin.readline())

    # 0원 부터 M원까지 각각을 만들 수 있는 가지 수를 저장할 list 선언
    dp = [0] * (m+1)

    # 0원을 만드는 경우의 수 : 1
    dp[0] = 1

    for coin in coins:
        # 크기가 가장 작은 동전 부터 1원에서 M원까지 만들 수 있는 경우의 수:
        # 해당 가격에서 동전을 뺀 횟수를 그대로 가져오기
        # dp[i] = dp[i] + dp[i-coin]
        for i in range(1, m+1):
            if i >= coin:
                dp[i] += dp[i-coin]
    print(dp[m])

# reference : https://d-cron.tistory.com/23
