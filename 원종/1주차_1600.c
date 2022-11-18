#include <stdio.h>

int board[222][222];
int v[222][222][33];
int dy[4] = {1, 0, 0, -1};
int dx[4] = {0, 1, -1, 0};
int hx[8] = { -1, -2, -2,-1, 1, 2, 2, 1 };
int hy[8] = { -2, -1, 1, 2, -2, -1, 1, 2 };
int w, h, k;

typedef struct Point {

	int y;
	int x;
	int c;
	int res;
} Point;

typedef struct Queue {
	Point p[10000001];
	int head;
	int tail;
} Queue;

void bfs(int y, int x) {
	Queue q;
	int nx, ny, nc;
	q.head = 0;
	q.tail = 0;

	v[y][x][k] = 1;
	Point p = {y, x, k, 0};
	q.p[q.tail++] = p;
	if (h == 1 && w == 1) {
		printf("0\n");
		return ;
	}
	while (q.tail > q.head)
	{
		Point tmp = q.p[q.head];
		if (tmp.y == h - 1 && tmp.x == w - 1) {
				printf("%d\n", tmp.res);
				return ;
		}
		for(int i = 0; i < 4; i++) {
			int ny = dy[i] + tmp.y;
			int nx = dx[i] + tmp.x;
			int nc = tmp.c;
			int nr = tmp.res;
			if (ny > -1 && nx > -1 && !v[ny][nx][nc] && !board[ny][nx] && ny < h && nx < w) {
				v[ny][nx][nc] = 1;
				Point pp = {ny, nx, nc, ++nr};
				q.p[q.tail++] = pp;
			}
		}
		if (tmp.c > 0) {
			for (int i = 0; i < 8; i++) {
				int ny = hy[i] + tmp.y;
				int nx = hx[i] + tmp.x;
				int nc = tmp.c - 1;
				int nr = tmp.res;
				if (ny > -1 && nx > -1 && !v[ny][nx][nc] && !board[ny][nx] && ny < h && nx < w) {
					v[ny][nx][nc] = 1;
					Point pp = {ny, nx, nc , ++nr};
					q.p[q.tail++] = pp;
				}
			}
		}
		q.head++;
	}
	printf("-1\n");

}

int main() {
	scanf("%d", &k);
	scanf("%d %d", &w, &h);
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			scanf("%d", &board[i][j]);
		}
	}
	bfs(0, 0);
	return (0);
}
