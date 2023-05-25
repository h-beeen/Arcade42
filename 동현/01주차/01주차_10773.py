# 10773 제로

import sys
k = int(sys.stdin.readline())
money = []

for i in range(k):
    money.append(int(sys.stdin.readline().split()[0]))

    if money[-1] == 0:
        del money[-2:]
    else:
        continue

print(sum(money))
