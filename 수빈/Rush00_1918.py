import sys
from collections import deque
input = sys.stdin.readline
exp = list(input().strip())

stack = []

result = ''

for i in exp:
	if i.isalpha():
		result += i;
	else:
		if i == '(':
			stack.append(i)
		elif i in "*/":
			while stack and (stack[-1] in "*/"):
				result += stack.pop()
			stack.append(i)
		elif i in "+-":
			while stack and stack[-1] != '(':
				result += stack.pop()
			stack.append(i)
		elif i == ')':
			while stack and stack[-1] != '(':
				result += stack.pop()
			stack.pop()
while stack :
	result += stack.pop()
print(result)