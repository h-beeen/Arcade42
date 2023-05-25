# 7576 토마토

from collections import deque

# m,n  입력
m, n = map(int, input().split())        
# matrix 입력
matrix = [list(map(int, input().split())) for _ in range(n)]    

# que 선언
que = deque([])

# 방향 리스트 생성         
dx = [-1, 1, 0, 0]      
dy = [0, 0, -1, 1]

result = 0

# 익은 토마토가 담긴 위치 que에 추가
for i in range(n):              
    for j in range(m):
        if matrix[i][j] == 1:
            que.append([i, j])

def bfs():
    while que:
        # 익은 토마토 좌표를 pop
        x, y = que.popleft()    
        # 익힐 토마토 찾기
        for k in range(4):
            # 확인할 토마토 위치 설정      
            nx, ny = dx[k] + x, dy[k] + y
            # 범위 내의 익지않은 토마토라면 que에 추가 및 행렬에 횟수 추가
            if 0 <= nx < n and 0 <= ny < m and matrix[nx][ny] == 0:
                matrix[nx][ny] = matrix[x][y] + 1
                que.append([nx, ny])

bfs()

for i in matrix:
    for j in i:
        # 익지 않은 토마토가 존재하면 -1 출력
        if j == 0:
            print(-1)
            exit(0)
    # 행렬에서 가장 큰 값 출력
    result = max(result, max(i))

# 첫날은 카운트에서 제외
print(result - 1)
