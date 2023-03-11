# 1926 그림
from collections import deque

n, m = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]
  
que = deque()
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

size_list = []  

def bfs(x, y):
    que.append((x, y))
    matrix[i][j] = 0
    size = 1  
    while que:
        x, y = que.popleft()
        for k in range(4):  
            nx = x + dx[k]
            ny = y + dy[k]
            if 0 <= nx < n and 0 <= ny < m:  
                if matrix[nx][ny]:  
                    que.append((nx, ny))
                    size += 1
                    matrix[nx][ny] = 0
    else:
        size_list.append(size)
        return

for i in range(n):
    for j in range(m):
        if matrix[i][j]:
            bfs(i, j)

print(len(size_list))
print(max(size_list) if size_list else 0)
