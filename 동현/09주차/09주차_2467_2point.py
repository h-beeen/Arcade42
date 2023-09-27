# 용액 2467

n = int(input())
liquids = list(map(int, input().split()))

left, right = 0, n-1
min_value = float('inf')
min_li = []
while left <= right:
    if left == right:
        break
    mix = liquids[left] + liquids[right]
    if abs(mix) <= abs(min_value):
        min_li = [liquids[left], liquids[right]]
        min_value = abs(mix)
    if mix < 0:
        left = left + 1
    else:
        right = right - 1
print(*min_li)
