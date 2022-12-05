#include <stdio.h>

<<<<<<< HEAD
=======

>>>>>>> e269ad981f041362ea6e515b08d3f299a1569853
int u[11];

int check(int n) {
    if (n == 0) {
        if (u[0]) return 0;
        else return 1;
    }
    else {
        while (n) {
            int temp = n % 10;
            if (u[temp]) return 0;
            n /= 10;
        }
        return 1;
    }
}

int count_n(int n) {
    int count = 0;
    if (n == 0) return 1;
    else {
        while (1) {
            if (n == 0) break;
            n /= 10;
            count++;
        }
        return count;
    }
}
int main() {
    int n;
    int t;
    int used;
    int ans = 0;
    scanf("%d", &n);
    scanf("%d", &t);

    while (t--) {
        scanf("%d", &used);
        u[used] = 1;
    }
    ans = 100 - n;
    if (ans < 0) ans = -ans;

    for (int i = 0; i < 1000000; ++i) {
        if (!check(i)) continue;

        int count = count_n(i);
        int temp = i - n;
        if (temp < 0) temp = -temp;
        if (ans > temp + count) ans = temp + count;
    }
    printf("%d", ans);

    return 0;
}
