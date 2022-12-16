def solution(land):
    answer = 0
    temp = 0
    
    for i in range(1,len(land)):
        for j in range(len(land[0])):
            temp = land[i-1][j]
            land[i-1][j] = 0
            land[i][j] = land[i][j] + max(land[i-1])
            land[i-1][j] = temp
        
    answer = max(land[-1])
    
    return answer