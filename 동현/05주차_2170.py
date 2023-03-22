from sys import stdin

n = int(stdin.readline())

xy = [list(map(int, stdin.readline().split())) for _ in range(n)]

xy.sort(key = lambda x: x[0])

line = [xy[0]]

for i in range(1,n):
    if xy[i-1][1] >= xy[i][0]:
        line[-1].append(max(xy[i-1][1],xy[i][1]))
        line[-1].pop(1)
    else:
        line.append(xy[i])
 
result = 0

print(line)

for i in range(len(line)):
    result += line[i][1]-line[i][0]

print(result)