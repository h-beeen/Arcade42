#include <bits/stdc++.h>

using namespace std;

int dp_zero[1000005];
int dp_notzero[1000005];
int mx = 0;

int solution(vector<int> money) {
    int sz = money.size();
    
    if (sz == 3)
        return (max({money[0], money[1], money[2]}));
        
    dp_zero[0] = money[0];
    dp_zero[1] = 0;
    
    dp_notzero[0] = 0;
    dp_notzero[1] = money[1];

    
    for(int i = 2; i < sz; i++)
    {
        dp_zero[i] = max({dp_zero[i-2] + money[i], dp_zero[i-1]});
        dp_notzero[i] = max({dp_notzero[i-2] + money[i], dp_notzero[i-1]});
    }
    
    mx = max({dp_zero[sz-2], dp_notzero[sz-1]});
    return mx;
}