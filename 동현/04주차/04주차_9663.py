# 9663 N-Queen

# 입력
n = int(input())
board = [0]*n # x,y좌표 : baord[x] = y
result = 0

# 백트래킹
def isPromising(x):
    for i in range(x):
        # 좌, 우 , 대각선에 존재하면 False
        if board[x] == board[i] or abs(board[x] - board[i]) == x-i:
            return False
    return True


# DFS 탐색
def dfs(x):
    global result   # n개의 퀸을 놓는 경우의 수

    if x == n:      # 보드판 맨 끝에 도달하면 탐색 마침
        result += 1
        return

    else:
        for y in range(n):  # 해당 row의 1~n열을 탐색
            board[x] = y    # x행, y열 탐색
            
            if isPromising(x):  # 해당 칸이 다른 퀸이 겹치지 않는지 판별
                dfs(x+1)        # 겹치지 않으면 다음 행으로 이동

dfs(0)
print(result)


'''
ans = [0, 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712, 365596, 2279184]
print(ans[int(input)])
'''
