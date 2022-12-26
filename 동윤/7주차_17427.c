#include <stdio.h>

int	main(void)
{
	int	n;
	long long	res;

	scanf("%d", &n);
	res = 0;
	for (int i = 1; i <= n; i++)
		res += n / i * i;
	printf("%lld\n", res);
}
