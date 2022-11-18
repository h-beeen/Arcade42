#include <stdio.h>

typedef struct {
    int data;
    int index;
}Stack;

Stack s[1000010];
int ans[1000010];
int size;

void push(int n, int index) {
    s[size].data = n;
    s[size++].index = index;
}

void pop(int n) {
    ans[s[size-1].index] = n;
    s[--size].data = 0;
}

int top() {
    return s[size-1].data;
}

int main() {
    int n, num;
    int i = 0;
    int len = 0;

    scanf("%d", &n);
    len = n;
    
    while (n--) {
        scanf("%d", &num);
        while (1) {
            if (size == 0 || top() >= num) break;
            pop(num);
        }
        push(num, i);
        i++;
    }
    
    while (size > 0) {
        pop(-1);
    }

    for (int j = 0; j < len; ++j) {
        printf("%d ", ans[j]);
    }
    return 0;
}
