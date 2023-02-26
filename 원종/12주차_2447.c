#include <stdio.h>

int arr[3000][3000];

void rec(int y, int x,int n) {
    if (n == 1) {
        arr[y][x] = 1;
    }
    else {
        int range = n / 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i == 1 && j == 1) continue;
                rec(y + range * i, x + range * j, n / 3);
            }
        }
    }
}

int main() {
    int n;
    scanf("%d", &n);
    rec(0, 0, n);
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            if (arr[i][j] == 1)
                printf("*");
            else
                printf(" ");
        }
        printf("\n");
    }
    return 0;

}
