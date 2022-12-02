#include <stdio.h>

typedef struct {
    int data;
    int index;
}St;

St s[1000010];
int ans[1000010];
int size;

void push(St st) {
	s[size++] = st;
}

void pop() {
	size--;
}

St top() {
    return s[size-1];
}

int main() {
    int n, num;
    int i = 1;
	int len;

    scanf("%d", &n);
    len = n;
    
    while (n--) {
        scanf("%d", &num);
		while (1)
		{
			if (size == 0) break;
			if (num < top().data)
			{
				ans[i] = top().index;
				break;
			}
			pop();
		}
		St st = {num, i};
		push(st);
		i++;
    }
	for (int i = 1; i <= len; i++)
	{
		printf("%d ", ans[i]);
	}
    return 0;
}
