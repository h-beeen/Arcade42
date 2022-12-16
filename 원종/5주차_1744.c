#include <stdio.h>

int arr[1001];

void merge(int left, int mid, int right)
{
	int l, m, t;
	int temp[1001];

	l = left;
	t = left;
	m = mid + 1;
	while (l <= mid && m <= right)
	{
		if (arr[l] <= arr[m])
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

	mid = (left + right) / 2;
	if (left >= right)
		return ;
	merge_sort(left, mid);
	merge_sort(mid + 1, right);
	merge(left, mid, right);
}

int max_(int a, int b)
{
	return (a > b ? a : b);
}

int main()
{
	int n;
	int result;
	int index;

	result = 0;
	index = 0;
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		scanf("%d", &arr[i]);
	merge_sort(0, n - 1);
	if (n % 2 == 0)
	{
		for (int i = 0; i < n; i+=2)
			result += max_(arr[i] * arr[i + 1], arr[i] + arr[i + 1]);
	}
	else
	{
		//arr[i] arr[i + 1] 이 음수면 우선 곱한다
		//하나라도 양수가 나오면 인덱스를 저장하고 멈춘다.
		//맨 뒤의 인덱스부터 곱한다.
		for (int i = 0; i < n; i += 2)
		{
			if (i + 1 == n)
			{
				index = i;
				break;
			}
			if (arr[i] > 0 || arr[i + 1] > 0)
			{
				index = i;
				break;
			}
			result += max_(arr[i] * arr[i + 1], arr[i] + arr[i + 1]);
		}
		for (int i = n - 1; i > index; i -= 2)	
			result += max_(arr[i] * arr[i - 1], arr[i] + arr[i - 1]);
		result += arr[index];
	}
	printf("%d\n", result);
	return 0;
}
