#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

long long n, sum_value, min_value, st, en;
vector <long long> res(3);

int main(void)
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    vector <long long> arr(n);
    for(int i = 0; i < n; i++)
        cin >> arr[i];

    min_value = 4000000000;

    sort(arr.begin(), arr.end());

    for(int i = 0; i < n; i++)  // 투 포인터 기준점 i
    {
        st = i + 1;             // 투 포인터 start index
        en = n - 1;             // 투 포인터 end index 
        while(st < en)
        {
            sum_value = arr[i] + arr[st] + arr[en];
            if(min_value > abs(sum_value))
            {
                min_value = abs(sum_value);
                res[0] = arr[i];
                res[1] = arr[st];
                res[2] = arr[en];
            }

            if(sum_value == 0)  break;
            if(sum_value < 0)   st++;
            else                en--;
        }
    }
    cout << res[0] << ' ' << res[1] << ' ' << res[2];
}