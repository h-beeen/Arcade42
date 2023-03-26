#include <iostream>
#include <algorithm>
#include <climits>
#include <queue>

using namespace std;

typedef struct {
    int y;
    int x;
} treasure;

int n, m;
char c, board[52][52];
int visited[52][52];
int max_result = INT_MIN;
int bfs_result = INT_MIN;

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
queue<treasure> q;


int bfs(treasure dst) {
    // BFS 전 방문 배열 초기화
    fill_n(&visited[0][0], 52 * 52, 0);
    bfs_result = INT_MIN;

    visited[dst.y][dst.x] = 1;
    treasure temp = {dst.y, dst.x};
    q.push(temp);

    while (!q.empty()) {
        treasure fr = q.front();
        q.pop();
        for (int dir = 0; dir < 4; dir++) {
            int ny = fr.y + dy[dir];
            int nx = fr.x + dx[dir];

            if (ny < 0 || nx < 0 || ny >= n | nx >= m) continue;
            if (visited[ny][nx] || board[ny][nx] == 'W') continue;

            visited[ny][nx] = visited[fr.y][fr.x] + 1;
            bfs_result = max(visited[ny][nx], bfs_result);
            treasure temp1 = {ny, nx};
            q.push(temp1);
        }
    }
    return bfs_result;
}


int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;
    for (int iy = 0; iy < n; iy++)
        for (int ix = 0; ix < m; ix++)
            cin >> board[iy][ix];

    for (int iy = 0; iy < n; iy++) {
        for (int ix = 0; ix < m; ix++) {
            if (board[iy][ix] == 'L') {
                treasure temp = {iy, ix};
                max_result = max(max_result, bfs(temp));
            }
        }
    }
    cout << max_result - 1;
}