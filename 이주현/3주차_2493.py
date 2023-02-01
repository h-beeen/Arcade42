import sys
input = sys.stdin.readline
n = int(input())
lst = []
stack = [0]
idx = [0]*(n)
lst = list(map(int,input().split()))
lst.reverse()
for i in range(1,n):
    while stack and lst[stack[-1]] < lst[i]:
        idx[stack.pop()] = n - i
    stack.append(i)
idx.reverse()
print(*idx)