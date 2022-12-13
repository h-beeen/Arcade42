#include <stdio.h>

char str[21][21];
char arr[21];
int v[255];
int dy[4] = {1, 0, -1, 0};
int dx[4] = {0, 1, 0, -1};
int max;
void rec(int y, int x, int r, int c, int try) {
    if (y < 0 || x < 0 || y >= r || x >= c) return;
    int temp = str[y][x];
    if (v[temp]) return;
    if (try > max) max = try;
    v[temp] = 1;
    for (int i = 0; i < 4; ++i) {
        rec(y + dy[i], x + dx[i], r, c, try + 1);
    }
    v[temp] = 0;

}

int main() {
    int r, c;
    scanf("%d %d", &r, &c);

    for (int i = 0; i < r; ++i) {
        scanf("%s", str[i]);
    }
    rec(0, 0, r, c, 1);
    printf("%d", max);
    return 0;
}
