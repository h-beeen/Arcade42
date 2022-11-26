#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

vector<bool> TF(10, false);

bool check(int now)
{
    string s = to_string(now);
    for(int i = 0; i < s.length(); i++)
        if(TF[s[i] - 48])
            return false;
    return true;
}

int main(void)
{
    int n, c, t;
    cin >> n >> c;

    for(int i = 0; i < c; i++)
	{
        cin >> t;
        TF[t] = true;
    }
    string st = to_string(n);
    int min_val = abs(n - 100);
    for(int i = 0; i <= 1000000; i++)
	{
        if(check(i))
		{
            int t = abs(n - i) + to_string(i).length();
            min_val = min(min_val,t);
        }
    }
    cout << min_val <<endl;
    return (0);
    {

    }
}