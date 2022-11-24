import sys
import string

sys.stdin = open('input.txt')

input = sys.stdin.readline

N = int(input())
n = int(input())
lst = []
map = []
intab = 'ijabcdefghklmnprstuvwxyoqz'
outtab = '11222333445566777888999000'
for _ in range(n):
	lst.append(input().strip())

for i in range(n):
	transtab = lst[i].maketrans(intab, outtab)
	map.append(lst[i].translate(transtab))

print(lst)
print(map)

# 7325189087
# 18
# 9087
# 7325189
# 7325
# 087
