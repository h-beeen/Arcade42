#include <stack>
#include <iostream>
#include <algorithm>
using namespace std;

int n, value;
int NGE[1000005], v[1000005];
stack <int> s;

int main(void)
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    for(int i = 0; i < n; i++)
        cin >> v[i];

    s.push(v[n-1]);   // 마지막 값 푸쉬
    NGE[n-1] = -1;
    for(int i = n-2; i >= 0; i--)
    {
        value = v[i]; 
        while(!s.empty() && value >= s.top())  // 스택이 비어있지 않고, i번째 원소가 스택보다 크거나 같다면
            s.pop();                        // 스택 값 pop

        if(s.empty())                       // 스택 Empty = Value 우측에 오큰수가 없음
            NGE[i] = -1;
        else
            NGE[i] = s.top();               // 스택이 차 있다면, 오큰수 = s.top();
        s.push(value);
    }

    for(int i = 0; i < n; i++)
        cout << NGE[i] << ' ';
}