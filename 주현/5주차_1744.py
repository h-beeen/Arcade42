import sys

input = sys.stdin.readline

n = int(input())
plus = []
minus = []
ans = 0



for i in range(n):
    a = int(input())
    if a > 1:
        plus.append(a)
    if a < 1:
        minus.append(a)
    if a == 1:
        ans += 1

plus.sort()
minus.sort()

if len(plus)%2 != 0:
    ans += plus.pop(0)
if len(minus)%2 != 0:
    ans += minus.pop()

for i in range(1,len(plus),2):
    ans += (plus[i]*plus[i-1])

for i in range(1,len(minus),2):
    ans += (minus[i]*minus[i-1])


print(ans)