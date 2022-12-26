#include <stdio.h>

int n;
int tmp[5];
int arr[21][21];
int arr2[21][21];
int max;

void init() {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            arr2[i][j] = arr[i][j];
        }
    }
    return;
}

void check(int d) {
    //왼쪽으로 이동
    if (d == 1) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (arr2[i][j] == 0) {
                    //현재 위치가 0이면 오른쪽 걸 땡겨옴
                    for (int k = j + 1; k < n; ++k) {
                        if (arr2[i][k]) {
                            arr2[i][j] = arr2[i][k];
                            arr2[i][k] = 0;
                            break;
                        }
                    }
                }
                //다 땡겨왔으면 같은 수 찾기
                for (int k = j + 1; k < n; ++k) {
                    if (arr2[i][j] == arr2[i][k]) {
                        arr2[i][j] *= 2;
                        arr2[i][k] = 0;
                        break;
                    }
                    else if (arr2[i][k] != 0 && (arr2[i][k] != arr2[i][j])) {
                        break;
                    }
                }
            }
        }
    }
    //오른쪽으로 이동
    if (d == 2) {
        for (int i = n - 1; i > -1; --i) {
            for (int j = n - 1; j > 0; --j) {
                if (arr2[i][j] == 0) {
                    //현재 위치가 0이면 왼쪽 걸 땡겨옴
                    for (int k = j - 1; k > -1; --k) {
                        if (arr2[i][k]) {
                            arr2[i][j] = arr2[i][k];
                            arr2[i][k] = 0;
                            break;
                        }
                    }
                }
                //다 땡겨왔으면 같은 수 찾기
                for (int k = j - 1; k > -1; --k) {
                    if (arr2[i][j] == arr2[i][k]) {
                        arr2[i][j] *= 2;
                        arr2[i][k] = 0;
                        break;
                    }
                    else if (arr2[i][k] != 0 && (arr2[i][k] != arr2[i][j]))
                        break;
                }
            }
        }
    }
    //위로 끌어올림
    if (d == 3) {
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < n - 1; ++i) {
                if (arr2[i][j] == 0) {
                    //현재 위치가 0이면 아래 걸 땡겨옴
                    for (int k = i + 1; k < n; ++k) {
                        if (arr2[k][j]) {
                            arr2[i][j] = arr2[k][j];
                            arr2[k][j] = 0;
                            break;
                        }
                    }
                }
                //다 땡겨왔으면 같은 수 찾기
                for (int k = i + 1; k < n; ++k) {
                    if (arr2[i][j] == arr2[k][j]) {
                        arr2[i][j] *= 2;
                        arr2[k][j] = 0;
                        break;
                    }
                    else if (arr2[k][j] != 0 && (arr2[k][j] != arr2[i][j]))
                        break;
                }
            }
        }
    }
    //아래로 내림
    if (d == 4) {
        for (int j = n - 1; j > -1; --j) {
            for (int i = n - 1; i > 0; --i) {
                if (arr2[i][j] == 0) {
                    //현재 위치가 0이면 위에 걸 땡겨옴
                    for (int k = i - 1; k > -1; --k) {
                        if (arr2[k][j]) {
                            arr2[i][j] = arr2[k][j];
                            arr2[k][j] = 0;
                            break;
                        }

                    }
                }
                //다 땡겨왔으면 같은 수 찾기
                for (int k = i - 1; k > -1; --k) {
                    if (arr2[i][j] == arr2[k][j]) {
                        arr2[i][j] *= 2;
                        arr2[k][j] = 0;
                        break;
                    }
                    else if (arr2[k][j] != 0 && (arr2[k][j] != arr2[i][j]))
                        break;
                }
            }
        }
    }
}

void rec(int depth) {
    if (depth == 5) {
        for (int k = 0; k < 5; ++k) {
            check(tmp[k]);
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr2[i][j] > max) {
                    max = arr2[i][j];
                }
            }
        }
        init();
        return;
    }
    for (int i = 1; i < 5; ++i) {
        tmp[depth] = i;
        rec(depth + 1);
    }
}

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            scanf("%d", &arr[i][j]);
        }
    }
    init();
    rec(0);
    printf("%d", max);
    return 0;
}
