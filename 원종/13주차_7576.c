#include <stdio.h>

typedef struct {
	int day;
	int y;
	int x;
} t_point;

int n, m;
int count;
int arr[1001][1001];
int v[1001][1001];
int dy[4] = {0, 1, -1, 0};
int dx[4] = {1, 0, 0, -1};
t_point q[3000001];
int head;
int tail;
int min;
int is_valid;
void enqueue(t_point p) {q[tail++] = p;}
void dequeue() {head++;}

int check_valid(int y, int x) {
	if (y < 0 || x < 0 || y > n - 1 || x > m - 1)
		return 0;
	if (v[y][x] || arr[y][x] == 1 || arr[y][x] == -1)
		return 0;
	return 1;
}

void bfs() {
	while (head != tail) {
		t_point temp = q[head];
		for (int i = 0; i < 4; i++) {
			int ny = temp.y + dy[i];
			int nx = temp.x + dx[i];
			if (check_valid(ny, nx)) {
				v[ny][nx] = 1;
				count--;
				if (count == 0) {
					min = temp.day + 1;
					is_valid = 1;
					return ;
				}
				t_point nt = {temp.day + 1, ny, nx};
				enqueue(nt);
			}
		}
		dequeue();
	}
}

int main() {
	scanf("%d %d", &m, &n);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++) {
			scanf("%d", &arr[i][j]);
			if (arr[i][j] == 0)
				count++;
			if (arr[i][j] == 1) {
				t_point t = {0, i, j};
				enqueue(t);
				v[i][j] = 1;
			}
		}
	bfs();
	if (is_valid)
		printf("%d\n", min);
	else if (!is_valid && count == 0)
		printf("0\n");
	else
		printf("-1\n");
	return 0;
}
