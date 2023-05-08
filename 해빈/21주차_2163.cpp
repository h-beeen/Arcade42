#include <iostream>
#include <queue>

using namespace std;

// 전체 덩어리 갯수 세기 = 1덩어리 기준
// 전체 덩어리 갯수가 1이 되는 시점에 + 1 return
// 분리되는 시점의 시간 return

int n, m, yearCnt;
int continent = 1;
int board[305][305];
bool visited[305][305];
int melt[305][305];
queue<pair<int, int> > q;

int dx[4] = {0, 0, 1, -1};
int dy[4] = {-1, 1, 0, 0};

void checkContinent() {
    continent = 0;
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (board[y][x] > 0 && !visited[y][x]) {
                continent++;
                q.push(make_pair(y, x));

                while (!q.empty()) {
                    int sy = q.front().first;
                    int sx = q.front().second;
                    q.pop();

                    for (int dir = 0; dir < 4; dir++) {
                        int ny = sy + dy[dir];
                        int nx = sx + dx[dir];

                        if (nx < 0 || ny < 0 || nx >= m || ny >= n || !board[ny][nx] || visited[ny][nx])
                            continue;

                        visited[ny][nx] = true;
                        q.push(make_pair(ny, nx));
                    }
                }
            }
        }
    }
    for (int y = 0; y < n; y++)
        for (int x = 0; x < m; x++)
            visited[y][x] = false;
}

void melting() {
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (board[y][x] == 0) {
                for (int dir = 0; dir < 4; dir++) {
                    int ny = y + dy[dir];
                    int nx = x + dx[dir];

                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || !board[ny][nx])
                        continue;
                    melt[ny][nx]++;
                }
            }
        }
    }

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (melt[y][x]) {
                board[y][x] -= melt[y][x];
                if (board[y][x] < 0)
                    board[y][x] = 0;
                melt[y][x] = 0;
            }
        }
    }
    yearCnt++;
    checkContinent();
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;
    for (int y = 0; y < n; y++)
        for (int x = 0; x < m; x++)
            cin >> board[y][x];

    while (continent == 1)
        melting();

    if (!continent)
        cout << 0;
    else
        cout << yearCnt;
}