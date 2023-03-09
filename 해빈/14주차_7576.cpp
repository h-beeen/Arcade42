#include <iostream>
#include <queue>
using namespace std;

int n, m;
int board[1005][1005] = {};

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
int result = 0;

int	main (void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> m >> n;
	queue<pair<int,int> > q;

	for(int i = 0; i < n; i++)
	{
		for(int j = 0; j < m; j++)
		{
			cin >> board[i][j];
			if(board[i][j] == 1)
				q.push(make_pair(i,j));
		}
	}

	while(!q.empty())
	{
		pair<int,int> temp = q.front();
		q.pop();

		for(int dir = 0; dir < 4; dir++)
		{
			int nx = temp.first + dx[dir];
			int ny = temp.second + dy[dir];

			if(nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 0)
			{
				q.push(make_pair(nx, ny));
				board[nx][ny] = board[temp.first][temp.second] + 1;
			}
		}
	}

	for(int i = 0; i < n; i++)
	{
		for(int j = 0; j < m; j++)
		{
			if(board[i][j] == 0)
			{
				cout << -1;
				return 0;
			}
			if(result < board[i][j])
				result = board[i][j];
		}
	}
		cout << result -1 << '\n';

}
