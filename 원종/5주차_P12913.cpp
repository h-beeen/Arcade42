#include <iostream>
#include <vector>
using namespace std;

int d[100001][4];
int solution(vector<vector<int> > land)
{
    int max = -1;
    int idx = 0;
    int len = land.size();

    for (int i = 0; i < 4; i++)
        d[0][i] = land[0][i]; 
    for (int i = 1; i < len; i++) {
        for (int j = 0; j < 4; j++) {
            max = -1;
            for (int k = 0; k < 4; k++) {
                if (k == j) continue;
                if (max < d[i - 1][k]) max = d[i - 1][k];
            }
            d[i][j] = land[i][j] + max;
        }
    }
    idx = land.size();
    max = -1;
    for (int i = 0; i < 4; i++) {
        if (max < d[idx - 1][i])
            max = d[idx - 1][i];
    } 
    return max;
}
