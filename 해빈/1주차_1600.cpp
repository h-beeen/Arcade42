#include <iostream>
#include <algorithm>
#include <queue>
#include <tuple>
using namespace std;

int board[202][202];
int vis[32][202][202];
int q_k, q_x, q_y, temp_x, temp_y;
int monkey_x[4] = { 0, 0, 1, -1 };
int monkey_y[4] = { 1, -1, 0, 0 };
int knight_x[8] = { -2, -2, -1, -1, 1, 1, 2, 2 };
int knight_y[8] = { 1, -1, 2, -2, 2, -2, 1, -1 };

queue <tuple<int, int, int> > Q;	
int x, y, k;

int main(void) 
{
	ios::sync_with_stdio(0);
  	cin.tie(0);

  	cin >> k >> y >> x;
  	for (int i = 0; i < x; i++)
		for (int j = 0; j < y; j++)
      		cin >> board[i][j];

	Q.push(make_tuple(0, 0, 0));
	vis[0][0][0] = 1;
  
	while (!Q.empty())
	{
 
    	q_k = get<0>(Q.front());					// Q의 현 동작 수 get 후 q_k에 저장 q_k == 지금 현재 큐의 나이트 동작 횟수
		q_x = get<1>(Q.front());					// Q의 현 x좌표 q_x에 저장
		q_y = get<2>(Q.front());					// Q의 현 y좌표 q_y에 저장

    	Q.pop();
    	if (q_k < k)
		{
      		for (int i = 0; i < 8; i++)
			{
        		{
					temp_x = q_x + knight_x[i];
					temp_y = q_y + knight_y[i];
				}
        		if(temp_x < 0 || temp_y < 0 || x <= temp_x || y <= temp_y)
					continue;
	
				if(board[temp_x][temp_y])
					continue;
       		 	
				if(vis[q_k + 1][temp_x][temp_y])
					continue;
       		 	
				vis[q_k + 1][temp_x][temp_y] = vis[q_k][q_x][q_y] + 1;
       			Q.push(make_tuple(q_k+1, temp_x, temp_y));
      		}
    	}

    	for (int i = 0; i < 4; i++)
		{
    		temp_x = q_x + monkey_x[i];
			temp_y = q_y + monkey_y[i];

    		if(temp_x < 0 || temp_y < 0 || x <= temp_x || y <= temp_y)
				continue;

    		if(board[temp_x][temp_y])
				continue;

    		if(vis[q_k][temp_x][temp_y])
				continue;

    		vis[q_k][temp_x][temp_y] = vis[q_k][q_x][q_y] + 1;
    		Q.push(make_tuple(q_k, temp_x, temp_y));
    	}
  	}
	int ans = 2147483647;

	for (int i = 0; i < k + 1; i++)
		if(vis[i][x - 1][y - 1])
			ans = min(ans, vis[i][x - 1][y - 1]);
	
		if(ans == 2147483647)
			cout << -1;
		else
			cout << ans - 1;
}