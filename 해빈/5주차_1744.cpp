#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool zero_check = false;
long long n, k, res, tmp1, tmp2;
vector <long long> v_plus;
vector <long long> v_minus;

bool cmp(long long a, long long b)
{
    return a > b;
}

int main(void)
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;

    for(int i = 0; i < n; i++)
    {
        cin >> k;
        if(k == 0)
            zero_check = true;
        else if(k == 1)
            res += 1;
        else if(k > 1)
            v_plus.push_back(k);
        else if(k < 0)
            v_minus.push_back(k);
    }

    sort(v_plus.begin(), v_plus.end());
    stable_sort(v_minus.begin(), v_minus.end(), cmp);

    if(v_plus.empty() == false)
    {
        if(v_plus.size() % 2 == 0)  // 양수 벡터가 짝수개라면
        {
            while(!v_plus.empty())
            {
                tmp1 = v_plus.back();
                v_plus.pop_back();
                tmp2 = v_plus.back();
                v_plus.pop_back();
                res = res + (tmp1 * tmp2);
            }
        }

        else if(v_plus.size() % 2 == 1)  // 양수 벡터가 홀수개라면
        {
            while(v_plus.size() != 1)
            {
                tmp1 = v_plus.back();
                v_plus.pop_back();
                tmp2 = v_plus.back();
                v_plus.pop_back();
                res = res + (tmp1 * tmp2);
            }
            res += v_plus.back();
            v_plus.pop_back();
        }
    }
    if(v_minus.empty() == false)
    {
        if(v_minus.size() % 2 == 0)  // 음수 벡터가 짝수개라면
        {
            while(!v_minus.empty())
            {
                tmp1 = v_minus.back();
                v_minus.pop_back();
                tmp2 = v_minus.back();
                v_minus.pop_back();
                res = res + (tmp1 * tmp2);
            }
        }

        else if(v_minus.size() % 2 == 1)  // 음수 벡터가 홀수개라면
        {
            while(v_minus.size() != 1)
            {
                tmp1 = v_minus.back();
                v_minus.pop_back();
                tmp2 = v_minus.back();
                v_minus.pop_back();
                res = res + (tmp1 * tmp2);
            }

            if(zero_check == false)
                res += v_minus.back();
        }
    }
    cout << res;
}