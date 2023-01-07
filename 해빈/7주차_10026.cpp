#include <iostream>
#include <queue>
using namespace std;

int dx[4] = {0, 1, -1, 0};
int dy[4] = {1, 0, 0, -1};
int res_1, res_2, n, num = 0;
char board[105][105] = {};
int vis[105][105] = {};
int vis2[105][105] = {};
string s[105];

int	main(void)
{
	cin >> n;
	for(int i = 0; i < n; i++)			// 보드에 input값 입력
	{
		cin >> s[i];
		for(int j = 0; j < n; j++)
			board[i][j] = s[i][j];
	}

	for(int i = 0; i < n; i++)			// BFS[1] 정상인 식별 구역의 수
	{
		for(int j = 0; j < n; j++)
		{
			if(!board[i][j] || vis[i][j]) //
				continue;

			num++;
			queue<pair<int,int> > q;
			q.push(make_pair(i, j));

			while(!q.empty())
			{
				pair <int,int> temp = q.front();
				q.pop();

				for(int dir = 0; dir < 4; dir++)
				{
					int nx = temp.first + dx[dir];
					int ny = temp.second + dy[dir];
	
					if(nx < 0 || ny < 0 || nx > n || ny > n)
						continue;
					if(board[nx][ny] != board[temp.first][temp.second] || vis[nx][ny])
						continue;
				
					vis[nx][ny] = 1;
					q.push(make_pair(nx,ny));
				}
			}
		}
	}
	res_1 = num;

	for(int i = 0; i < n; i++)
		for(int j = 0; j < n; j++)
			if(board[i][j] == 'G')
				board[i][j] = 'R';
	num = 0;
	
	for(int i = 0; i < n; i++)			// BFS[2] 적록색약 식별 구역의 수
	{
		for(int j = 0; j < n; j++)
		{
			if(!board[i][j] || vis2[i][j])
				continue;

			num++;
			queue<pair<int,int> > q;
			q.push(make_pair(i, j));

			while(!q.empty())
			{
				pair <int,int> temp = q.front();
				q.pop();

				for(int dir = 0; dir < 4; dir++)
				{
					int nx = temp.first + dx[dir];
					int ny = temp.second + dy[dir];

					if(nx < 0 || ny < 0 || nx > n || ny > n)
						continue;
					if(board[nx][ny] != board[temp.first][temp.second] || vis2[nx][ny])
						continue;

					vis2[nx][ny] = 1;
					q.push(make_pair(nx,ny));
				}
			}
		}
	}

	res_2 = num;

	cout << res_1 << ' ' << res_2;
}
