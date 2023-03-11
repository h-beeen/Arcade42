#include <stdio.h>

int n, m;
int arr[101][101];
int v[101][101];
int c[101][101];
int dy[4] = {0, 1, 0, -1};
int dx[4] = {1, 0, -1, 0};
int flag;

void init_all() {
	flag = 0;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) {
			v[i][j] = 0;
			c[i][j] = 0;
		}
}

void dfs(int y, int x) {

	if (arr[y][x] == 1) {
		return ;
	}
	v[y][x] = 1;
	for (int i = 0; i < 4; i++) {
		int ny = y + dy[i];
		int nx = x + dx[i];
		if (ny < 0 || nx < 0 || ny >= n || nx >= m || v[ny][nx])
			continue ;
		if (arr[ny][nx] == 1)
		{
			c[ny][nx]++;
			flag = 1;
		}
		dfs(ny, nx);
	}
}

int main() {

	int result = 0;

	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			scanf("%d", &arr[i][j]);

	while (1) {
		dfs(0, 0);
		if (flag == 0)
			break ;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (c[i][j] >= 2)
					arr[i][j] = 0;
			}
		}
		init_all();
		result++;
	}
	printf("%d\n", result);
	return 0;
}
