#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

string s;
int breakingCnt;
int n, m, board[1005][1005];
bool visited[15][1005][1005];
int dirX[4] = {0, 0, -1, 1};
int dirY[4] = {1, -1, 0, 0};

typedef struct {
    int x;
    int y;
    int distance;
    int breaking;
} pos;

int main()
{
    //temp.distance가 짝수라면 밤
    //temp.distance가 홀수라면 낮
    ios::sync_with_stdio(0); cin.tie(0);

    cin >> n >> m >> breakingCnt;
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
            int nx = temp.x + dirX[dir];
            int ny = temp.y + dirY[dir];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 벽인데 부술 카운트가 존재하고, 낮이고, 현재 카운트로 방문한 적 없는 경우
            if(board[nx][ny] == 1 && temp.distance % 2 && temp.breaking < breakingCnt && !visited[temp.breaking][nx][ny]){
                pos temp2 = {nx, ny, temp.distance + 1, temp.breaking + 1};
                visited[temp.breaking][nx][ny] = true;
                Q.push(temp2);
            }

            // 일반 길인데 벽이 아니고, 방문한 적 없는 경우
            if(!board[nx][ny] && !visited[temp.breaking][nx][ny]){
                pos temp2 = {nx, ny, temp.distance + 1, temp.breaking};
                visited[temp.breaking][nx][ny] = true;
                Q.push(temp2);
            }

            // 밤인데 방문한 적 없고,벽에 막힌 경우
            if(board[nx][ny] && !visited[temp.breaking][nx][ny] && temp.distance % 2 == 0){
                pos temp2 = {temp.x, temp.y, temp.distance + 1, temp.breaking};
                Q.push(temp2);
            }
        }
    }

    cout << -1;
    return 0;
}