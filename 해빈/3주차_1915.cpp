#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

string s;
int board[1005][1005] = {};
int n, m;
int sc[1005][1005];
int ans = -2147483648;

int	main(void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> m;
	for(int i = 1; i <= n; i++)
	{
		cin >> s;
		for(int j = 1; j <= m; j++)
			board[i][j] = s[j-1] - '0';
	}

	for(int i = 1; i <= n; i++)
	{
		for(int j = 1; j <= m; j++)
		{
			if(board[i][j])
			{
				sc[i][j] = min({sc[i-1][j-1], sc[i][j-1], sc[i-1][j]}) + 1;
				ans = max(ans, sc[i][j]);
			}
		}
	}
	cout << ans * ans;

	// // 보드 출력 테스트 구문
	// for(int i = 1; i <= n; i++)
	// {
	// 	cout << '\n';
	// 	for(int j = 1; j <= m; j++)
	// 		cout << sc[i][j];
	// }
}