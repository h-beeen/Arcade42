#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <tuple>
using namespace std;
#define sync_ios ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);

int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int n;

// student[i] : i번째 앉힐 학생
int student[400];

// prefer[i][j] : i번 학생이 j를 선호 하는지 여부 (T/F)
bool prefer[400][400] = {false};

// visited[i][j] : (i, j) 좌표에 앉은 학생
int visited[20][20];


void sit(int idx)
{
    int student_num = student[idx];
    vector<pair<pair<int, int>, pair<int, int> > > promising;
}

int main() {
    sync_ios;           // sync tie with stdio;
    cin >> n;           // 행, 열의 수
    int cnt = n * n;    // cnt = 총 학생 수

    for (int i = 0; i < cnt; i++) {
        cin >> student[i];
        for (int j = 0; j < 5; j++) {
            int prefer_student;
            cin >> prefer_student;
            prefer[student[i]][prefer_student] = true;
        }
    }
    for (int i = 0; i < cnt; i++)
        sit(i);
}
