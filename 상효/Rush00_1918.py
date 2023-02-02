index = 0
def recursion(formula: str, operators: dict, stack: list)->None:
	global index
	while index < len(formula):
		if not formula or formula[index] == ')':
			while stack:
				print(stack.pop(), end="")
			while index < len(formula) and formula[index] == ')':
				index += 1
			return
		if formula[index].isalpha():
			print(formula[index], end="")
		elif formula[index] in set(operators.keys()):
			if not stack:
				stack.append(formula[index])
			else:
				while stack and operators[stack[-1]] >= operators[formula[index]]:
					print(stack.pop(), end="")
				stack.append(formula[index])
		else:
			if formula[index] != ')':
				index += 1
				recursion(formula, operators, list())
			if index == len(formula):
				while stack:
					print(stack.pop(), end="")
				return
			else:
				while stack and operators[stack[-1]] >= operators[formula[index]]:
					print(stack.pop(), end="")
			stack.append(formula[index])
		index += 1
	while stack:
		print(stack.pop(), end="")

def operatorsInit()->dict:
	operators:dict = dict()
	operators['+'] = 1
	operators['-'] = 1
	operators['*'] = 2
	operators['/'] = 2
	return operators

def main():
	formula: str = str(input())
	operators: dict = operatorsInit()
	stack: list = list()
	recursion(formula, operators, stack)


if __name__ == '__main__':
	main()