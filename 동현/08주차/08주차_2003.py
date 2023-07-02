# 2003 수들의 합 2

n, m = map(int, input().split())
sequence = list(map(int, input().split()))

start, end = 0, 1

result = 0

while (start <= end and end <= n):

    # 구간의 합 계산
    total = sum(sequence[start:end])

    # 합이 m보다 작으면 end 포인터 우측 한칸 이동
    if (total < m):
        end += 1

    # 합이 m보다 크면 start 포인터 우측 한칸 이동
    elif (total > m):
        start += 1

    # 합이 m 이면 결과값에 추가 및 end 포인터 우측 한칸 이동
    elif (total == m):
        result += 1
        end += 1

print(result)
