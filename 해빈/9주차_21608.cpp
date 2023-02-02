#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define sync_ios ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int n;


// student[i] : i번째 앉힐 학생
int student[405];

// prefer[i][j] : i번 학생이 j를 선호 하는지 여부 (T/F)
bool prefer[405][405];

// visited[i][j] : (i, j) 좌표에 앉은 학생
int visited[25][25] = {0};



bool cmp(pair<pair<int,int>,pair<int,int> > &a, pair<pair<int, int>, pair<int, int> > &b) {
    //second.first = x, second.second = y
    if (a.first.first == b.first.first) {
        if (a.first.second == b.first.second) {
            if (a.second.second == b.second.second) {
                return a.second.first < b.second.first;
            }
            return a.second.second < b.second.second;
        }
        return a.first.second > b.first.second;
    }
    return a.first.first > b.first.first;
}

void sit(int idx)
{
    vector <int, int, int, int>
    int student_num = student[idx];
    vector<pair<pair<int, int>, pair<int, int> > > promising;

    // [0][0] 부터 [n-1][n-1]까지의 모든 자리 탐색
    // 인접한 4방향에 대해 좋아하는 학생의 수 & 빈 자리 Count
    // vector< <좋아하는 학생 수, 비어있던 자리 수>, <x좌표, y좌표> >;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (visited[i][j]) continue;
            int prefer_student = 0; // 인접 학생 중 선호 학생 수
            int empty = 0;          //
            for(int k = 0; k < 4; k++) {
                int nx = i + dx[k];
                int ny = j + dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;   // 자리 밖 continue;
                if (prefer[student_num][visited[nx][ny]]) prefer_student++;
                else if (visited[nx][ny] == 0)
                    empty++;
            }
        }
    }
    sort(promising.begin(), promising.end(), cmp);

    int promising_x = promising[0].second.first;
    int promising_y = promising[0].second.second;
    visited[promising_x][promising_y] = student_num;
}

int satisfaction()
{
    int res = 0;
    int satisfaction[5] = {0, 1, 10, 100, 1000};
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            int prefer_student = 0;
            for(int k = 0; k < 4; k++){
                int nx = i + dx[k];
                int ny = j + dy[k];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;   // 자리 밖 continue;
                if (prefer[visited[i][j]][visited[nx][ny]]) prefer_student++;
            }
            res += satisfaction[prefer_student];
        }
    }
    return (res);
}

int main() {
    sync_ios;           // sync tie with stdio;
    cin >> n;           // 행, 열의 수
    int cnt = n * n;    // cnt = 총 학생 수

    for (int i = 0; i < cnt; i++) {
        cin >> student[i];
        for (int j = 0; j < 4; j++) {
            int prefer_student;
            cin >> prefer_student;
            prefer[student[i]][prefer_student] = true;
        }
    }
    for (int i = 0; i < cnt; i++)
        sit(i);
    cout << satisfaction();
}