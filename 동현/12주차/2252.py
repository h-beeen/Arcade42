import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]    # 인접노드를 넣을 2차원 배열 선언
inDegree = [0]*(N+1)                # 차수 를 넣을 배열 선언

for _ in range(M):
    A, B = map(int, input().split())
    graph[A].append(B)              # A에 대한 인접노드 B 추가
    inDegree[B] += 1                # B 차수 증가

q = deque()

for i in range(1, N+1):             # que에 차수가 0인 노드들 추가
    if inDegree[i] == 0:
        q.append(i)

ans = []

while q:                            # que가 빌 때 까지
    tmp = q.popleft() 
    ans.append(tmp)
    
    for i in graph[tmp]:            # 차수가 0 인 노드와 인접 노드들의
        inDegree[i] -= 1            # 차수 1 제거
        if inDegree[i] == 0:        # 제거 되었을 때 차수가 0이면 q에 추가
            q.append(i)

print(*ans, sep=" ")