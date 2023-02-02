#include <stdio.h>

char str[102][102];
int	dy[8] = {1, 1, 1, 0, 0, -1, -1, -1};
int dx[8] = {0, -1, 1, 1, -1, 0, 1, -1};
int n;
int result;

typedef struct
{
	int y;
	int x;
}point;


int		check_valid(int y, int x)
{
	if (y == -1 || x == -1 || y == n || x == n)
		return 0;
	return 1;
}

int	is_num(char c)
{
	if (c > 47 && c < 58)
		return 1;
	return 0;
}

void solve(int y, int x)
{
	for (int j = 0; j < 8; j++)
	{
		int ny = y + dy[j];
		int nx = x + dx[j];
		if(!check_valid(ny, nx))
			continue;
		if (str[y][x] > 48 && str[ny][nx] == '#')
		{
			str[ny][nx] = 'x';
			result++;
			for (int k = 0; k < 8; k++)
			{
				int ny2 = ny + dy[k];
				int nx2 = nx + dx[k];
				if (is_num(str[ny2][nx2]))
					str[ny2][nx2]--;
			}
		}
		else if (str[y][x] == 48 && str[ny][nx] == '#')
			str[ny][nx] = 'o';
	}
}

void	check(int start, int end)
{
	if (start >= end)
		return ;
	point p[4] = {{start, start}, {start, end}, {end, start}, {end, end}};

	for (int i = 0; i < 4; i++)
	{
		int y = p[i].y;
		int x = p[i].x;
		solve(y, x);
	}
	for (int i = start; i <= end; i++)
	{
		if (is_num(str[start][i])) {
			solve(start, i);
		}
		if (is_num(str[i][start]))
			solve(i, start);
		if (is_num(str[i][end]))
			solve(i, end);
		if (is_num(str[end][i]))
			solve(end, i);
	}
}

int main() {

	scanf("%d", &n);

	for (int i = 0; i < n; i++)
		scanf("%s", str[i]);
	check(0, n - 1);
	if (n - 4 > 0)
		result += ((n - 4) * (n - 4));
	printf("%d\n", result);
}
