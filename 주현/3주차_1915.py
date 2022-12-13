import sys


input = sys.stdin.readline

N,M = map(int,input().split())
lst = []
temp = 0

for _ in range(N):
    lst.append(list(map(int,input().strip())))

for i in range(N):
    for j in range(M):
        if lst[i][j] == 1:
            if lst[i-1][j] != 0 and 0 <= i -1 < N and 0 <= j < M:
                if lst[i-1][j-1] != 0 and 0 <= i -1 < N and 0 <= j-1 < M:
                    if lst[i-1][j-1] != 0 and 0 <= i - 1 < N and 0 <= j -1 < M:
                        lst[i][j] = (min(lst[i-1][j-1], lst[i][j-1], lst[i-1][j]) + 1)
                        if lst[i][j] >= temp:
                            temp = lst[i][j]
        if lst[i][j] >= temp:
                temp = lst[i][j]

print(temp*temp)