#include <iostream>
#include <queue>

using namespace std;

#define UNKNOWN_BLANK 0
#define CHEESE 1
#define OUTER_BLANK 2
#define MELTING_CHEESE 3

int n, m, result;
int board[105][105];
int dirY[4] = {0, 0, -1, 1};
int dirX[4] = {-1, 1, 0, 0};
bool visited[105][105];
queue<pair<int, int> > q;
vector<pair<int, int> > meltingPos;
bool flag = true;

void outerBfs() {
    for (int y = 0; y < n; y++)
        for (int x = 0; x < m; x++)
            visited[y][x] = false;

    q.push(make_pair(0, 0));
    board[0][0] = OUTER_BLANK;
    visited[0][0] = true;

    while (!q.empty()) {
        int tempY = q.front().first;
        int tempX = q.front().second;
        q.pop();

        for (int dir = 0; dir < 4; dir++) {
            int nextY = tempY + dirY[dir];
            int nextX = tempX + dirX[dir];
            if (nextX < 0 || nextY < 0 || nextY >= n || nextX >= m || visited[nextY][nextX]) continue;
            if (board[nextY][nextX] == CHEESE) continue;
            board[nextY][nextX] = OUTER_BLANK;
            visited[nextY][nextX] = true;
            q.push(make_pair(nextY, nextX));
        }

    }

}

void melting() {
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (board[y][x] != CHEESE)
                continue;
            int cnt = 0;
            for (int dir = 0; dir < 4; dir++) {
                int nextY = y + dirY[dir];
                int nextX = x + dirX[dir];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m) continue;
                if (board[nextY][nextX] == OUTER_BLANK) {
                    cnt++;
                    if (cnt == 2) {
                        board[y][x] = MELTING_CHEESE;
                        meltingPos.push_back(make_pair(y, x));
                        continue;
                    }
                }
            }
        }
    }
    for (int i = 0; i < meltingPos.size(); i++)
        board[meltingPos[i].first][meltingPos[i].second] = OUTER_BLANK;
    result++;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m; // n = y, m = x;
    for (int y = 0; y < n; y++)
        for (int x = 0; x < m; x++)
            cin >> board[y][x];

    while (flag) {
        outerBfs();
        melting();
        flag = false;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (board[y][x] == CHEESE)
                    flag = true;
            }
        }
    }

    cout << result;
}