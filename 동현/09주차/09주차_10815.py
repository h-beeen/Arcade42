# 숫자 카드 10815

n = int(input())
card = list(map(int, input().split()))
m = int(input())
check = list(map(int, input().split()))
card.sort()

for i in range(m):
    start, end = 0, n-1
    exist = False
    while start <= end:
        mid = (start + end) // 2
        if check[i] > card[mid]:
            start = mid +1
        elif check[i] < card[mid]:
            end = mid - 1
        elif check[i] == card[mid]:
            exist = True
            break
    print(1 if exist else 0, end=' ')
