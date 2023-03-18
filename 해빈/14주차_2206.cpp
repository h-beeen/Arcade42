#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

string s;
int n, m, board[1005][1005];
bool vis[1005][1005][2];
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
    for(int i = 0; i < n; i++){
        cin >> s;
        for(int j = 0; j < m; j++)
            board[i][j] = s[j] - '0';
    }


    queue <pos> Q;
    pos temp = {0, 0, 1, 0};
    Q.push(temp);

    while(!Q.empty())
    {
        temp = Q.front(); Q.pop();
        if(temp.x == n-1 && temp.y == m-1) {
            cout << temp.distance;
            return 0;
        }

        for(int dir = 0; dir < 4; dir++){
            int nx = temp.x + dx[dir];
            int ny = temp.y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 벽인데 부순 적이 없고, 방문한 적 없는 경우
            if(board[nx][ny] == 1 && !temp.breaking && !vis[nx][ny][1]){
                pos temp2 = {nx, ny, temp.distance + 1, true};
                vis[nx][ny][1] = true;
                Q.push(temp2);
            }

            // 일반 길인데 방문한 적 없는 경우
            if(!board[nx][ny] && !vis[nx][ny][temp.breaking]){
                pos temp2 = {nx, ny, temp.distance + 1, temp.breaking};
                vis[nx][ny][temp.breaking] = true;
                Q.push(temp2);
            }
        }
    }

    cout << -1;
    return 0;
}
