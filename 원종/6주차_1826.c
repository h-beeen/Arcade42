#include <stdio.h>

typedef struct {

	int dis;
	int fuel;
} St;

St arr[10001];
St temp[10001];
int d[10001];
int v[10001];

void merge(int left, int mid, int right) {
	int l, m, t;
	t = l = left;
	m = mid + 1;
	while (l <= mid && m <= right)
	{
		if (arr[l].dis < arr[m].dis)
			temp[t++] = arr[l++];
		else
			temp[t++] = arr[m++];
	}
	while (l <= mid)
		temp[t++] = arr[l++];
	while (m <= right)
		temp[t++] = arr[m++];
	while (left <= right) {
		arr[left] = temp[left];
		left++;
	}
}
void merge_sort(int left, int right) {

	if (left >= right)
		return ;
	int mid = (left + right) / 2;
	merge_sort(left, mid);
	merge_sort(mid + 1, right);
	merge(left, mid, right);
}

int main() {

	int n;
	int a, b, max;
	int max_index = 0;
	int flag = 0;

	scanf("%d", &n);
	for (int i = 1; i <= n; i++) {
		scanf("%d %d", &a, &b);
		arr[i].dis = a;
		arr[i].fuel = b;
	}
	scanf("%d %d", &a, &b);
	merge_sort(1, n);
	d[0] = b;
	for (int i = 1; i <= n; i++) {
		max = 0;
		for (int j = 1; j <= n; j++) {
			if (v[j]) continue;
			if (arr[j].dis > d[i - 1]) break;
			if (max < arr[j].fuel) {
				max = arr[j].fuel;
				max_index = j;
			}
		}
		v[max_index] = 1;
		d[i] = d[i - 1] + max;
	}
	for (int i = 0; i <= n; i++) {
		if (d[i] >= a) {
			flag = 1;
			max_index = i;
			break;
		}
	}
	if (flag)
		printf("%d", max_index);
	else
		printf("-1");
	return (0);
}
