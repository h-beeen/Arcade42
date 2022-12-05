#include <stdio.h>


typedef long long ll;
ll arr[5001];

ll abs_(ll n)
{
	return (n < 0 ? -n : n);
}

void merge(int left, int mid, int right)
{
	int l, m, t;
	ll temp[5001];

	l = t = left;
	m = mid + 1;
	while (l <= mid && m <= right)
	{
		if (arr[l] < arr[m])
			temp[t++] = arr[l++];
		else
			temp[t++] = arr[m++];
	}
	while (l <= mid)
		temp[t++] = arr[l++];
	while (m <= right)
		temp[t++] = arr[m++];
	while (left <= right)
	{
		arr[left] = temp[left];
		left++;
	}
}

void merge_sort(int left, int right)
{
	int mid;

	if (left >= right) 
		return ;
	mid = (left + right) / 2;
	merge_sort(left, mid);
	merge_sort(mid + 1, right);
	merge(left, mid, right);
}

int main()
{
	int n;
	ll temp = 3000000001;
	ll a, b, c;
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		scanf("%lld", &arr[i]);
	merge_sort(0, n - 1);

	for (int i = 0; i < n; i++)
	{
		int start = i + 1;
		int end = n - 1;
		while (start < end)
		{
			ll ans = arr[start] + arr[i] + arr[end];
			if (abs_(ans) < abs_(temp))
			{
				temp = ans;
				a = arr[i];
				b = arr[start];
				c = arr[end];
			}
			if (ans > 0)
				end--;
			if (ans < 0)
				start++;
			if (ans == 0) break;
		}
	}
	printf("%lld %lld %lld\n", a, b, c);
	return 0;
}
