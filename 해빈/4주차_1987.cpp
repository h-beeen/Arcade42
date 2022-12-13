#include <iostream>
using namespace std;

int n, m, mx;
bool vis[26];
char board[20][20];
int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};

void dfs(int x, int y, int cnt){
	mx = max(cnt, mx);
	for(int i = 0; i < 4; i++)
	{
		int nx = dx[i] + x;
		int ny = dy[i] + y;
		if(nx < 0 || nx >= n || ny < 0 || ny >= m)
			continue;

		int temp = (int) board[nx][ny] - 'A';
		if(vis[temp])
			continue;

		vis[temp] = true;
		dfs(nx, ny, cnt + 1);
		vis[temp] = false;
	}
}

int	main(void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> n >> m;

	for(int i = 0; i < n; i++)
		for(int j = 0; j < m; j++)
			cin >> board[i][j];

	vis[(int)board[0][0] - 'A'] = true;
	dfs(0, 0, 1);
	cout << mx;
	return (0);
}