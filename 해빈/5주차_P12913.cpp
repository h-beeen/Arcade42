#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;

int res[100005][4];

int solution(vector<vector<int> > land)
{
    int size = land.size();
    
    res[0][0] = land[0][0];
    res[0][1] = land[0][1];
    res[0][2] = land[0][2];
    res[0][3] = land[0][3];
    
    for(int i = 1; i < size; i++) // res[1][j]
    {
        for(int j = 0; j < 4; j++)
        {
            if(j == 0)
                res[i][j] = max({res[i-1][1], res[i-1][2], res[i-1][3]}) + land[i][j];
            if(j == 1)
                res[i][j] = max({res[i-1][0], res[i-1][2], res[i-1][3]}) + land[i][j];
            if(j == 2)
                res[i][j] = max({res[i-1][0], res[i-1][1], res[i-1][3]}) + land[i][j];
            if(j == 3)
                res[i][j] = max({res[i-1][0], res[i-1][1], res[i-1][2]}) + land[i][j];
        }
    }
    int ans = max({res[size-1][0], res[size-1][1], res[size-1][2], res[size-1][3]});
    return (ans);
}