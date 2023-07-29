# 1926 그림

from collections import deque

# n,m 입력
n, m = map(int, input().split())        
# matrix 입력
matrix = [list(map(int, input().split())) for _ in range(n)]    

# que 선언
que = deque()

# 방향 리스트 생성
dx = [-1, 1, 0, 0]      
dy = [0, 0, -1, 1]

# 그림 크기들에 대한 list
size_list = []          


def bfs(x, y):          
    # que에 좌표 저장
    que.append([x, y])

    # 최초 발견한 1을 0으로 초기화  
    matrix[i][j] = 0
    
    # 최초 그림 크기 1
    size = 1
    while que:
        x, y = que.popleft()

        # 그림 상하좌우 좌표 찾기
        for k in range(4):  
            nx = x + dx[k]
            ny = y + dy[k]

            # 해당 범위 내의 1이 있으면 que에 추가 후 size +1
            if 0 <= nx < n and 0 <= ny < m and matrix[nx][ny] == 1:  
                    que.append([nx, ny])
                    size += 1
                    # 1을 0으로 초기화
                    matrix[nx][ny] = 0
    else:
        size_list.append(size)
        return

# matrix 상에서 1이면 bfs 발동
for i in range(n):
    for j in range(m):
        if matrix[i][j]:
            bfs(i, j)

# 그림의 개수
print(len(size_list))
# 최대 그림 개수 그림 없으면 0 출력
print(max(size_list) if size_list else 0)
