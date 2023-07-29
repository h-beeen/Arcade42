# 2164 카드

import sys
from collections import deque

n = int(sys.stdin.readline())

card = deque([i for i in range(1, n+1)])

while len(card)>1:
    card.popleft()
    move_card = card.popleft()
    card.append(move_card)

print(card[0])
