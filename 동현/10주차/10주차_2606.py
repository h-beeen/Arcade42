# 바이러스 2606 

com = int(input())                  # 컴퓨터 개수 입력
pair = int(input())                 # 컴퓨터 쌍의 개수 입력

graph = [[] for _ in range(com+1)]  # graph 선언 
for _ in range(pair):               # graph에 입력값 저장
    x, y = map(int, input().split())		
    graph[x].append(y)              # 각 번호와 연결된 번호를 이차원 list로 저장 
    graph[y].append(x)

visited = [0] * (com+1)	            # 방문처리 기록할 visited list 초기화

def dfs(graph, n, visited):
    visited[n] = 1                  # 방문처리
    for i in graph[n]:              # 그래프 확인 후 방문 처리 x 일 때 dfs 재귀 호춣
        if visited[i] == 0:
            dfs(graph, i, visited)

dfs(graph, 1, visited)
print(sum(visited)-1)	            # 방문한 컴퓨터 개수 출력
