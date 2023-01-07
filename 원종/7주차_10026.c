#include <stdio.h>

int n;
int dy[4] = {1, 0, 0, -1};
int dx[4] = {0, 1, -1, 0};
char str[101][102];
int v[101][102];
void rec(int y, int x, char c) {

	if (y >= n || x >= n || y < 0 || x < 0)
		return ;
	if (str[y][x] != c || v[y][x])
		return ;
	for (int i = 0; i < 4; i++) {
		v[y][x] = 1;
		rec(y + dy[i], x + dx[i], c);
	}
}

int main() {

	scanf("%d", &n);
	int j = 0;
	int a, b;
	a = 0;
	b = 0;
	while (j < n) {
		scanf("%s", str[j]);
		j++;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!v[i][j]) {
				rec(i, j, str[i][j]);
				a++;
			}
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (str[i][j] == 'G')
				str[i][j] = 'R';
			v[i][j] = 0;
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!v[i][j]) {
				rec(i, j, str[i][j]);
				b++;
			}
		}
	}
	printf("%d %d\n", a, b);
	return (0);
}
