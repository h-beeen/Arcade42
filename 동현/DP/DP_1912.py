# 1912 연속합

# n, 수열 입력
n = int(input())
sequence = list(map(int,input().split()))

# 수열에서 sequence[i], sequence[i-1]의 합과 sequence[i]를 비교 후 큰 수를 sequence[i]에 저장    
for i in range(1, n):
    sequence[i] = max(sequence[i], sequence[i-1] + sequence[i])

print(max(sequence))
