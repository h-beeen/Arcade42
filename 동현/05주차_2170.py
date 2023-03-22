from sys import stdin

n = int(stdin.readline())

xy = [list(map(int, stdin.readline().split())) for _ in range(n)]

xy.sort(key = lambda x: x[0])

line = [xy[0]]

for i in range(1,n):
    if xy[i-1][-1] >= xy[i][0]:
        y_max = max(xy[i-1][-1],xy[i][-1])
        xy[i].append(y_max)
        line[-1].append(y_max)
    else:
        line.append(xy[i])

result = 0

for i in range(len(line)):
    result += line[i][-1]-line[i][0]

print(result)
