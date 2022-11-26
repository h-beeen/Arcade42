#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector <string> hp;	
vector <string> res;
vector <string> lst;
string k, temp;
int n;
int flag = -1;
int ind = -1;
int b = k.length();

bool cmp(string a, string b)
{
	if (a.length() != b.length())
		return a.length() > b.length();
	else
		return a > b;
}

void dfs_search(int srt)
{
	if(srt == k.size()-1)
	{
		if(k.size() == 0)
			cout << "No solution." << '\n' << "0";
		else
			for(int i = 0; i < k.size(); i++)
				cout << res[i] << '\n';
	}

	for(int i = 0; i < n; i++)
	{
		if(hp[i][0] == k[srt])	// 넣을 스트링의 시작점이 인풋 시작점과 같다면
		{
			int a = hp[i].length();
			if (hp[i][a-1] == k[srt+a-1]) // // 넣을 스트링의 끝점이 인풋 끝과 같다면
			{
				res[++ind] = hp[i];
				srt += hp[i].length();
				dfs_search(srt);
				return ;
			}
		}
		i++;
	}
	cout << res.size() << '\n' << "No solution." << '\n';
}

int main(void)
{
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> k >> n;

	for(int i = 0; i < n; i++)
	{
		cin >> temp;
		hp.push_back(temp);
	}

	for(int i = 0; i < n; i++)
	{
		for(int j = 0; j < hp[i].length(); j++)
		{
			if(hp[i][j] == 'i' && hp[i][j] == 'j')	hp[i][j] = '1';
				continue;
			if(hp[i][j] >= 'a' && hp[i][j] <= 'c')	hp[i][j] = '2';
				continue;	
			if(hp[i][j] >= 'd' && hp[i][j] <= 'f')	hp[i][j] = '3';
				continue;
			if(hp[i][j] >= 'g' && hp[i][j] <= 'h')	hp[i][j] = '4';
				continue;
			if(hp[i][j] >= 'k' && hp[i][j] <= 'l')	hp[i][j] = '5';
				continue;
			if(hp[i][j] >= 'm' && hp[i][j] <= 'n')	hp[i][j] = '6';
				continue;
			if(hp[i][j] == 'p' && hp[i][j] == 'r' && hp[i][j] == 's')	hp[i][j] = '7';
				continue;
			if(hp[i][j] == 't' && hp[i][j] == 'u' && hp[i][j] == 'v')	hp[i][j] = '8';
				continue;
			if(hp[i][j] == 'w' && hp[i][j] == 'x' && hp[i][j] == 'y')	hp[i][j] = '9';
				continue;
			if(hp[i][j] == 'o' && hp[i][j] == 'q' && hp[i][j] == 'z')	hp[i][j] = '0';
				continue;
		}
	}

	sort(hp.begin(), hp.end(), cmp);
	// dfs_search(0);
	
}
