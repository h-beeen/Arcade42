# 11651 좌표 정렬하기 2

# n 입력
n = int(input())

# x, y 입력 후, 입력받은 값을 이차원 list로 저장
x_y_lists = [list (map(int, input().split(" "))) for _ in range(n)]

# 두번째 인자를 기준으로 오름차순 정렬 후, 첫번째 인자를 기준으로 오름차순 정렬
sorted_x_y = sorted(x_y_lists, key = lambda x:(x[1], x[0]))

# 출력
for i in range(n):
    print(sorted_x_y[i][0], sorted_x_y[i][1])
