import sys

sys.stdin = open('input.txt')

input = sys.stdin.readline

n,c = map(int,input().split())
lst = []
for _ in range(n):
   lst.append(int(input()))
lst.sort()

start = 1
end = lst[-1] - lst[0]

print(lst)

while (start <= end):
    mid = (start + end)//2
    cnt = 1
    temp = lst[0]


    for i in range(n):
        
        if lst[i] - temp >= mid:
            cnt += 1
            temp = lst[i]
    
    if cnt >= c:
        start = mid + 1
    if cnt < c:
        end = mid - 1

    print(cnt,start,mid,end)

print(end)
        