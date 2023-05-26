# 19583 싸이버개강총회

from sys import stdin

s, e, q = stdin.readline().split(' ')
attend = {}
answer = set()

# 반복문으로 시간과 닉네임 입력 받기
for i in stdin:
    time, name = i.rstrip().split(' ')

    # 시작시간보다 일찍 들어온 사람 attend에 저장
    if time <= s:
        attend[name] = time

    # 종료시간과 스트리밍 종료시간 사이에 들어온 사람 확인
    elif e <= time <= q:
        # 시작시간보다 일찍 들어온 사람이라면 answer에 저장
        if name in attend:
            answer.add(name)

print(len(answer))
