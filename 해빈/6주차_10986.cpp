#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;

int N, M, x;
long long cnt[1001];
long long sum, ans;

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		cin >> x;
		sum += x;
		cnt[sum % M]++;
	}

	for (int i = 0; i <= 1000; i++)
		ans += cnt[i] * (cnt[i] - 1) / 2;
	
	cout << cnt[0] + ans;
}