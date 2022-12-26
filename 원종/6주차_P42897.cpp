#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int d[2][2][1000001];
int solution(vector<int> money) {
    int answer = 0;
    int len = money.size();
    
    
    //d[첫번째 집 선택여부][현재 집 선택여부][현재 위치]
    d[0][1][1] = money[1];
    d[0][0][1] = 0;
    d[1][0][1] = money[0];
    //두개연속 선택불가
    d[1][1][1] = 0;
    
    for(int i = 2; i < len; i++) {
        //첫번째 집과 현재집을 선택 안 함
        d[0][0][i] = max(d[0][0][i - 1], d[0][1][i - 1]);
        //첫번쨰 집은 선택하지 않고 현재집은 선택
        d[0][1][i] = money[i] + d[0][0][i - 1];
        //첫번째 집도 선택하고 현재집도 선택함
        if (i != len - 1)
            d[1][1][i] = d[1][0][i - 1] + money[i];
        //첫번째 집은 선택했지만 현재집은 선택하지않음
        d[1][0][i] = max(d[1][1][i - 1], d[1][0][i - 1]);
    }
    answer = max({d[0][0][len - 1], d[0][1][len - 1], d[1][0][len - 1], d[1][1][len - 1]});
    return answer;
}
