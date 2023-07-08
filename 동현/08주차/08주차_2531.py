# 2531 회전초밥

from collections import defaultdict

n, d, k, c = map(int, input().split())

arr = [int(input()) for _ in range(n)]

# 원형이기 때문에 list 두개를 이어주기
arr = arr * 2   
left = 0
right = 0
dict_ = defaultdict(int)
result = 0

# 보너스 무조건 먹기 
dict_[c] += 1          

# k 구간만큼 먹기
while right < k:
    dict_[arr[right]] += 1
    right += 1

# 슬라이딩 윈도우 
while right < len(arr):
    result = max(result, len(dict_))
    # 맨 왼쪽 초밥 제거
    dict_[arr[left]] -= 1
    # 삭제된 왼쪽 초밥이 0 이면 dict 삭제
    if dict_[arr[left]] == 0:   
        del dict_[arr[left]]
    # 오른쪽 초밥 추가 
    dict_[arr[right]] += 1
    # 왼쪽 위치 + 1
    left += 1
    # 오른쪽 위치 + 1
    right += 1

print(result)
