# 11725 트리의 부모찾기

import sys
sys.setrecursionlimit(10**6)        # 최대 깊이 제한
n = int(sys.stdin.readline()) 

graph = [[] for _ in range(n+1)]    # graph 선언
for i in range(n-1):
    point1, point2 = map(int, sys.stdin.readline().split()) # 입력값 저장
    graph[point1].append(point2)    # 이차원 배열 저장
    graph[point2].append(point1)

visited = [0] * (n+1)               # 방문처리 기록할 visited list 초기화

def dfs(s):                         # dfs
    for i in graph[s]:
        if visited[i] == 0:
            visited[i] = s
            dfs(i)

dfs(1)

for i in range(2, n+1):
    print(visited[i])               # 출력
