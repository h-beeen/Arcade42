#include <iostream>
#include <stack>
using namespace std;

int main (void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n, k;
	stack <pair<int, int> > st;			// pair -> 2 argv input // >> blank Warning
										// pair first = 탑 번호, secont = 높이
	
	cin >> n;

	for(int i = 1; i <= n; i++)
	{
		cin >> k;
		while(!st.empty())	 			// 스택이 비어있지 않을 때 Loop
		{
			if(st.top().second > k) 	// pair의 second[height]를 기준으로 입력값보다 크다면 
			{
				cout << st.top().first << ' '; // pair first[number]를 출력
				break;					// 반복문 탈출
			}
			else	st.pop();			// 입력값이 더 작다면, 
		}

		if(st.empty()) cout << "0 ";	// 스택이 비어있다면 print 0 
		st.push(make_pair(i, k));		// 스택에 현재 인덱스, 높이  저장
	}	
}
