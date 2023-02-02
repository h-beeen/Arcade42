#include <iostream>
#include <queue>
using namespace std;
#define ios_sync ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);

char board[105][105];
int dx[8] = {1, 1, 1, 0, 0, -1, -1, -1};
int dy[8] = {1, 0, -1, 1, -1, -1, 0, -1};
int n;
bool flag = false;

void search_minefield(int x, int y){
    flag = false;
    int mine = 0;
    int sharp = 0;
    queue <pair<int, int> > sharp_idx;
    if(x == 1 && y == n) flag = true;
    if(x == 1 && y == 1) flag = true;
    if(x == n && y == 1) flag = true;
    if(x == n && y == n) flag = true;

    for(int i = 0; i < 8; i++)
    {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if(nx < 1 || nx > n || ny < 1 || ny > n) continue;

        if (board[nx][ny] == '#'){
            sharp_idx.push(make_pair(nx, ny));
            sharp++;
        }
        if (board[nx][ny] == '+')
            mine++;
    }

    if (board[x][y]-48 == mine) // 찾은 지뢰의 개수랑 보드의 지뢰 개수가 같아서, 이미 모든 지뢰를 찾았을 때
    {
        while(!sharp_idx.empty())
        {
            int temp1 = sharp_idx.front().first;
            int temp2 = sharp_idx.front().second;
            board[temp1][temp2] = '-';
            sharp_idx.pop();
        }
    }
    else if ((board[x][y]-48) == (mine + sharp))
    {
        while (!sharp_idx.empty())
        {
            int temp1 = sharp_idx.front().first;
            int temp2 = sharp_idx.front().second;
            board[temp1][temp2] = '+';
            sharp_idx.pop();
        }
    }
    else if (board[x][y] == '0') {
        while (!sharp_idx.empty()) {
            int temp1 = sharp_idx.front().first;
            int temp2 = sharp_idx.front().second;
            board[temp1][temp2] = '-';
            sharp_idx.pop();
        }
    }
}

int main() {
    ios_sync;
    cin >> n;

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            cin >> board[i][j];


    for(int i = 1; i <= n; i++)
    {
        search_minefield(i, 1);
        search_minefield(n, i);
        search_minefield(1, i);
        search_minefield(i, n);
    }

//    for (int i = 1; i <= n; i++) {
//         cout << endl;
//        for (int j = 1; j <= n; j++)
//            cout << board[i][j];
//    }

    int res = 0;
    for (int i = 1; i < n; i++)
        for (int j = 1; j < n; j++)
            if (board[i][j] == '#' || board[i][j] == '+') res++;

    cout << res;
}