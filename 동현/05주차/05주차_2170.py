# 2170 선긋기

from sys import stdin

# n, xy 입력
n = int(stdin.readline())
xy = [list(map(int, stdin.readline().split())) for _ in range(n)]

# x, y를 x를 기준으로 정렬
xy.sort(key = lambda x: x[0])

# 가장 작은 x,y list에 저장
line = [xy[0]]

for i in range(1,n):
    # 현재 y값이 다음 x 값보다 크거나 같으면
    if xy[i-1][1] >= xy[i][0]:
        # 현재 y 값과 다음 y값 중 더 큰 값을 다음 y 값에 추가
        y_max = max(xy[i-1][1],xy[i][1])
        xy[i][1]= y_max
        # line list에 있는 y 값 역시 변경
        line[-1][1]= y_max
    # 현재 y 값이 다음 x 값보다 작으면 line에 다음 x,y 추가
    else:
        line.append(xy[i])

# 결과 값 선언
result = 0

# 각각의 line 길이 계산 후 결과 값에 저장
for i in range(len(line)):
    result += line[i][-1]-line[i][0]

print(result)
