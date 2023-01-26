#include <iostream>
using namespace std;
#define ios_sync ios::sync_with_stdio(0), cin.tie(0), cout.tie(0);

// 알고리즘 분류 : 분리 집합 (Disjoint Set)

using namespace std;

int n, m, node_1, node_2;
// n개의 점 -> parent[0~n-1]
// m번의 플레이 -> m번의 find

int parent[500000];
// 부모노드 저장 배열

int ans;
// 횟수 cnt

int find(int node)
{
    if (parent[node] == node)
        return (node);
    else
        return (parent[node] = find(parent[node]));
}

bool union_find(int dest, int src)
{
    int t1 = find(dest);
    int t2 = find(src);

    if (t1 == t2) // 부모 노드가 같으면 사이클
        return (true);
    else          // 부모 노드가 다르면 Union_find
    {
        parent[t1] = t2;
        return (false);
    }
}


int main(){
    ios_sync; // sync tie(0) with <stdio.h>

    cin >> n >> m;
    for(int i = 0; i < n; i++) // 부모노드 초기값 = 자기 자신
        parent[i] = i;

    for(int i = 1; i <= m; i++) {
        cin >> node_1 >> node_2;
        if(union_find(node_1, node_2))
        {
            ans = i;
            break;
        }
    }
    cout << ans;
}
