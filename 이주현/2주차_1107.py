import sys


input = sys.stdin.readline

N = int(input())
M = int(input())
ls = (list(map(str,input().split())))
target = []
cnt = 0
jaritsu = len(str(N))
temp = 100
min = abs(temp-N)# 최소차이
near = 100 #가장 가까운 수

for i in range(1000000):
    if any(ls in str(i) for ls in ls) == False:
        target.append(i)
for j in target:
    sub = abs(N-j)
    if sub < min:
        min = sub
        near = j

if abs(N-100)>(len(str(near)) + min):
    print((len(str(near)) + min))
else:
    print(abs(N-100))
