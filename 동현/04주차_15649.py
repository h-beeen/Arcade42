# N과 M (1)

n, m = map(int, input().split())

k = []

def func():
  if len(k) == m:
    print(' '.join(map(str, k)))
    return

  for i in range(1, n + 1):
    if i in k:
      continue
    k.append(i)
    func()
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
