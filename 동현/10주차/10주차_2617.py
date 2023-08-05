# 구슬 찾기 2617

n, m = map(int,input().split())

mid = (n + 1) / 2                           # 중간 index

heavier_list = [[] for _ in range(n+1)]     # 자신보다 무거운 구슬 list 초기화
lighter_list = [[] for _ in range(n+1)]     # 자신보다 가벼운 구슬 list 초기화

for i in range(m):                          # 각 번호의 구슬보다 무거운 구슬과 가벼운 구슬을 따로 저장
    heavy, light = map(int,input().split())
    heavier_list[light].append(heavy)
    lighter_list[heavy].append(light)

def dfs(graph, n):                            
    global count
    for i in graph[n]:
        if visited[i] == 0:
            visited[i] = 1
            count += 1
            dfs(graph, i)   # 재귀

answer = 0                  # 정답을 저장할 answer 변수 선언

for i in range(1, n+1):
    visited = [0] * (n+1)   # 각각의 index에서 방문 list 초기화
    
    count = 0               # 해당 i보다 큰 숫자를 세기 위한 count 변수 초기화
    dfs(heavier_list, i)    # dfs
    if count >= mid:        # 큰 숫자의 갯수가 mid 보다 크면 answer에 1 추가
        answer += 1
    
    count = 0               # 해당 i보다 작은 숫자를 세기 위한 count 변수 초기화
    dfs(lighter_list, i)    # dfs
    if count >= mid:        # 작은 숫자의 갯수가 mid보다 크면 answer에 1추가
        answer += 1

print(answer)
