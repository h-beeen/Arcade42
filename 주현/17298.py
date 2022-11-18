import sys

sys.stdin = open('input.txt')

input = sys.stdin.readline


N = int(input())

input = list(map(int,input().split()))
stack = [0]
output = [-1]*N


stack.append(0)
for i in range(1,N):
    while stack and input[stack[-1]] < input[i]:
        output[stack.pop()] = input[i]
    stack.append(i)

print(*output)