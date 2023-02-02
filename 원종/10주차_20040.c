#include <stdio.h>


int p[1000001];
int n, m;
int min;
int try = 1;
int	find(int x)
{
	if (p[x] == x)
		return x;
	p[x] = find(p[x]);
	return p[x];
}

void union_(int x, int y)
{
	x = find(x);
	y = find(y);
	if (x != y)
		p[y] = x;
	if (x == y && min == 0)
		min = try;
	try++;
}

int main() 
{
	int num, a, b;

	scanf("%d %d", &n, &m);
	for (int i = 0; i <= n; i++)
		p[i] = i;
	while (m--)
	{
		scanf("%d %d", &a, &b);
		union_(a, b);
	}
	printf("%d\n", min);
	return 0;
}
