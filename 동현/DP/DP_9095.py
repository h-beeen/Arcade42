# 9095 1, 2, 3 더하기

from sys import stdin

# t, n 입력
t = int(input())
n = [int(stdin.readline()) for _ in range(t)]

# 초기 수열 선언
# 1 -> 1
# 1+1, 2 -> 2
# 1+1+1, 1+2, 2+1, 3 -> 4
num_list =[1,2,4]

# 배열 상의 이전 세 값을 더한 후 배열에 추가

for i in range(3,max(n)):
    last_num = num_list[i-1] + num_list[i-2] + num_list[i-3]
    num_list.append(last_num)

# 결과 출력
for i in n:
    print(num_list[i-1])
