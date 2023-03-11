# 7576 토마토
from collections import deque

m, n = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]

que = deque([])
dx = [-1, 1, 0, 0] 
dy = [0, 0, -1, 1]

result = 0

for i in range(n):
    for j in range(m):
        if matrix[i][j] == 1:
            que.append([i, j])

def bfs():
    while que:
        x, y = que.popleft()
        for k in range(4):
            nx, ny = dx[k] + x, dy[k] + y
            if 0 <= nx < n and 0 <= ny < m and matrix[nx][ny] == 0:
                matrix[nx][ny] = matrix[x][y] + 1
                que.append([nx, ny])

bfs()

for i in matrix:
    for j in i:
        if j == 0:
            print(-1)
            exit(0)
    result = max(result, max(i))

print(result - 1)
