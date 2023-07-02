# 1620 나는야 포켓몬 마스터 이다솜

from sys import stdin

# n,m 입력
n, m = map(int,stdin.readline().split(' '))
# dictionary 선언
pocketmon_dict = {}

# 입력받은 pocketmon과 i를 dictionary에 저장
for i in range(1, n + 1):
    pocketmon = stdin.readline().rstrip()
    pocketmon_dict[pocketmon] = i
    pocketmon_dict[str(i)] = pocketmon

# 입력값이 숫자일때와 문자열일때 각각의 출력
for i in range(m):
    question = stdin.readline().rstrip()
    print(pocketmon_dict[question])
