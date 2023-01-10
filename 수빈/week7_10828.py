def execute_instruction(input, stack) :
	try :
		instruction, num = input.split()
		if instruction == "push":
			stack.append(num)
	except ValueError:
		instruction = input
		if instruction == "pop":
			if not stack:
				print(-1)
			else:
				print(stack.pop())
		elif instruction == "size":
			print(len(stack))
		elif instruction == "empty":
			if not stack:
				print(1)
			else:
				print(0)
		elif instruction == "top":
			if not stack:
				print(-1)
			else:
				print(stack[-1])
	return stack

if __name__ == "__main__" :
	import sys
	input = sys.stdin.readline
	n = int(input())
	stack = []
	for i in range (n) :
		execute_instruction(input().strip(), stack)