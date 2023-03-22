from sys import stdin

n = int(stdin.readline())
time = list(map(int,stdin.readline().split()))

time.sort()

result = 0

for i in range(n):
    result += time[i] * n
    n -= 1

print(result)