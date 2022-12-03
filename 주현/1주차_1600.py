import sys
from collections import deque

sys.stdin = open('input.txt')

input = sys.stdin.readline

K = int(input())
W,H = map(int,input().split())
graph=[]
for i in range(H):
    graph.append(list(map(int,input().split())))
visit = [[[0]*W for _ in range(H)] for _ in range(K+1)]
len = 0

dx = [-1,1,0,0,2,-2,1,-1,-2,-1,2,1]
dy = [0,0,-1,1,1,1,2,2,-1,-2,-1,-2]

queue = deque()
queue.append((0,0,K,0))
flag = 0

while queue:
    x, y, k, c = queue.popleft()

    if x == H-1 and y == W-1:
        print(c)
        flag = 1
        break
    if k == 0:
        ft_len = 4
    if k > 0:
        ft_len = 12
    
    print(x, y, k, c)

    for i in range(ft_len):
        nx = x + dx[i]
        ny = y + dy[i]
        nk = k
        if abs(dx[i]) > 1 or abs(dy[i]) > 1:
            nk = k - 1
        if 0 <= nx < H and 0 <= ny < W:
            if  graph[nx][ny] != 1 and visit[nk][nx][ny] == 0:
                visit[nk][nx][ny] = 1
                queue.append((nx,ny,nk,c + 1))

if flag == 0:
    print(-1)

