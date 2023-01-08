import sys
from collections import deque
sys.stdin = open('input.txt')

input = sys.stdin.readline

N = int(input())

m = []
visited = [[0]*N for _ in range(N)]
visited1 = [[0]*N for _ in range(N)]

for i in range(N):
    a = list(map(str,input().strip()))
    m.append(a)

r = [-1,1,0,0]
c = [0,0,-1,1]
queue = deque()
def bfs(j,k):
    queue.append((j,k))
    while queue:
        x,y = queue.popleft()
        visited[x][y] = 1

        for i in range(4):
            dx = x + r[i]
            dy = y + c[i]

            if 0 <= dx < N and 0 <= dy < N:
                if visited[dx][dy] == 0 and m[dx][dy] == m[x][y]:
                    queue.append((dx,dy))
                    visited[dx][dy] = 1

ans = 0
ans1 = 0
for i in range(N):
    for j in range(N):
        if visited[i][j] == 0:
            bfs(i,j)
            ans += 1

for i in range(N):
    for j in range(N):
        visited[i][j] = 0
        if m[i][j] == 'G':
            m[i][j] = 'R'

for i in range(N):
    for j in range(N):
        if visited[i][j] == 0:
            bfs(i,j)
            ans1 += 1

print(ans,ans1)