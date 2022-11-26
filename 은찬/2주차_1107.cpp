#include<iostream>
#include<algorithm>
#include<string>
#include<vector>
using namespace std;


int n;
int m;
int mini = 9999999;
vector<bool> mal(10, false);

int isHas(string tmp){
    for (int i=0; i < tmp.length(); i++){
        int index = (int) tmp[i] - '0';
        if (mal[index] == true) return 1;
    }
    return 0;
}

int main(){
    ios::sync_with_stdio(0);
   cin.tie(0);
   cout.tie(0);

    cin >> n;
    cin >> m;
    int t;
    for(int i = 0; i < m; i++){
        cin >> t;
        mal[t] = true;
    }

    mini = abs(100 - n);

    for(int i=0; i <= 1000000; i++){
        if (!isHas(to_string(i))){
            int k = abs(n - i) + to_string(i).length();
            mini = min(mini, k);
        }
    }

    cout << mini;

    return 0;
}
