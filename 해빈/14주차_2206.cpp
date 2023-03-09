#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int n, m, board[1005][1005];
bool vis[1005][1005];
int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};

typedef struct {
    int x;
    int y;
    int distance;
    bool breaking;
} pos;

int main(void)
{
    ios::sync_with_stdio(0); cin.tie(0);

    cin >> n >> m;
    for(int x = 0; x < n; x++)
        for(int y = 0; y < m; y++)
            cin >> board[x][y];

    queue <pos> Q;
    pos temp = {0, 0, 1, 0};
    Q.push(temp);

    while(!Q.empty())
    {
        temp = Q.front(); Q.pop();
        if(temp.x == n && temp.y == m) {
            cout << temp.distance;
            return 0;
        }

        for(int dir = 0; dir < 4; dir++){
            int nx = temp.x + dx[dir];
            int ny = temp.y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m && vis[nx][ny]) continue;

            // 벽인데 부순 적이 없고, 방문한 적 없는 경우
            if(board[nx][ny] == 1 && !temp.breaking == 0){
                pos temp2 = {nx, ny, temp.distance + 1, 1};
                Q.push(temp2);
                continue;
            }

            // 일반 길인데 방문한 적 없는 경우
            if(!board[nx][ny]){
                pos temp2 = {nx, ny, temp.distance + 1, temp.breaking};
                Q.push(temp2);
                continue;
            }
        }
    }

    cout << -1;
    return 0;
}
