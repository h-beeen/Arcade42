from sys import stdin

n, k = map(int,stdin.readline().split())
coin = [int(stdin.readline()) for _ in range(n)]

result = 0

for i in range(n-1,-1,-1):
    result += k//coin[i]
    k %= coin[i]

print(result)