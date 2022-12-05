#include <stdio.h>
#define min(a, b) a < b ? a : b;
int arr[1001][1002];
int d[1001][1001];
char str[1001][1002];

int main() {

    int n, m;
    int max = -1;
    scanf("%d %d", &n, &m);

    for (int i = 0; i < n; ++i) {
        scanf("%s", str[i]);
    }
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (str[i][j] == '0')
                arr[i][j] = 0;
            else if (str[i][j] == '1')
                arr[i][j] = 1;
        }
    }

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            if (arr[i][j] == 1) {
                d[i][j] = 1;
                if (max < d[i][j])
                    max = d[i][j];
            }
            else if (arr[i][j] == 0) {
                d[i][j] = 0;
                if (max < d[i][j])
                    max = d[i][j];
            }
            if (!arr[i][j] || !arr[i-1][j-1] || !arr[i-1][j] || !arr[i][j-1]) continue;
            else {
                d[i][j] = min(d[i - 1][j - 1], d[i - 1][j]);
                d[i][j] = min(d[i][j], d[i][j - 1]);
                d[i][j] += 1;
                if (max < d[i][j]) max = d[i][j];
            }
        }
    }

    printf("%d", max * max);
    return 0;

}
