# 용액 2467

n = int(input())
liquids = list(map(int, input().split()))

# result 무한대로 설정
result = float("inf")
# 선택한 두 용액의 초기값 
liquid_1, liquid_2 = 0, n - 1

for i in range(n - 1):
# i번째 용액과 섞을 최소값 이분 탐색(i + 1 ~ n - 1)
    left = i + 1
    right = n - 1

    while left <= right:
        mid = (left + right) // 2
        mix = liquids[mid] + liquids[i]

        # 용액을 혼합한 값이 이 전의 값보다 작으면 liquid_1,2, result 값 초기화
        if abs(mix) < result:
            liquid_1 = i
            liquid_2 = mid
            result = abs(mix)
        
        if mix == 0:
            break
        elif mix < 0:
            left = mid + 1
        else:
            right = mid - 1

print(liquids[liquid_1], liquids[liquid_2])
