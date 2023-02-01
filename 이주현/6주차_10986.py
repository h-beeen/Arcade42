import sys

sys.stdin = open('input.txt')

input = sys.stdin.readline

N,M = map(int,input().split())


a = list(map(int,input().split()))
m = [0]*M
cnt = 0
s=0

for i in range(0,N):
    s += a[i]
    res = s%M
    if res == 0:
        cnt += 1
    m[res] +=1

for j in m:
    if j > 0:
        cnt += (j*(j-1))//2
print(m)
print(cnt)