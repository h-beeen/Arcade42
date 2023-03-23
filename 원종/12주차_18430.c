#include <stdio.h>

typedef struct {

	int y;
	int x;
} t_point;

int arr[100][100];
int v[100][100];
int max;
int n, m;

t_point d[4][3] = {

	{
		{0, 0},
		{0, -1},
		{1, 0}
	},

	{
		{0, 0},
		{0, -1},
		{-1, 0}
	},

	{
		{0, 0},
		{-1, 0},
		{0, 1}
	},

	{
		{0, 0},
		{0, 1},
		{1, 0}
	}
};

int	check(int y, int x) {

	if (y < 0 || x < 0 || y >= n || x >= m)
		return 0;
	if (v[y][x])
		return 0;
	return 1;
}

void rec(int y, int x, int sum) {

	if (x == m) {
		x = 0;
		y++;
	}
	if (sum > max)
		max = sum;
	if (y == n)
		return ;

	for (int i = 0; i < 4; i++) {
		int temp = 0;
		int try = 0;
		t_point arr2[3];
		t_point t;
		if (!v[y][x]){
			for (int j = 0; j < 3; j++) { //방향보기
				t.y = y + d[i][j].y;
				t.x = x + d[i][j].x;
				if (!check(t.y, t.x))
					break ;
				arr2[j] = t;
				try++;
			}

			if (try == 3) {
				for (int j = 0; j < 3; j++) {
					int ny = arr2[j].y;
					int nx = arr2[j].x;
					v[ny][nx] = 1;
					temp += arr[ny][nx];
				}
				rec(y, x + 1, sum + temp + arr[y][x]);
				for (int k = 0; k < 3; k++) { //방문처리 해제
					int ny = y + d[i][k].y;
					int nx = x + d[i][k].x;
					v[ny][nx] = 0;
				}
			}
		}
	}

		rec(y, x + 1, sum);
}

int main() {

	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			scanf("%d", &arr[i][j]);
	rec(0, 0, 0);
	printf("%d\n", max);
}
