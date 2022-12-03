import sys


input = sys.stdin.readline

n = int(input())
lst = []

lst = list(map(int,input().split()))
lst.sort()

temp = 3000000001
ptr1 = 0
ptr2 = 0
ptr3 = 0
for i in range(n):
    start = i + 1
    end = n - 1
    while (start < end):
        ans = lst[i] + lst[start] + lst[end]
        if abs(ans) < abs(temp):
            temp = abs(ans)
            ptr1,ptr2,ptr3 = lst[i],lst[start],lst[end]
        if ans < 0:
            start += 1
        elif ans > 0:
            end -= 1
        else:
            break