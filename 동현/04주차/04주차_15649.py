# 15649 N과 M (1)

n, m = map(int, input().split())

k = []  # 수열을 저장하기 위한 list

def func():
    if len(k) == m: # 수열의 숫자가 m 개가 되면 출력
        print(' '.join(map(str, k)))
        return

    for i in range(1, n + 1):
        if i not in k:    # 숫자 i가 중복이 아니면
            k.append(i)   # 숫자 i 추가
            func()        # 다음 숫자를 넣기 위해 가지치기 
            k.pop()

func()


'''
# 백트래킹 사용하지 않을 때
from itertools import permutations

n, m = map(int, input().split())
nums = [i for i in range(1, n+1)]

p = list(permutations(nums, m))

for i in p:
    print(' '.join(map(str,i)))
'''
