#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <deque>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> res;
    deque<int> pq;

    for (int i = 0; i < operations.size(); i++) {
        if (operations[i][0] == 'I') {  // when insert
            pq.push_back(stoi(operations[i].substr(2)));
            sort(pq.begin(), pq.end());
        } else if (operations[i] == "D 1") {
            if (!pq.empty()) {
                pq.pop_back();
            }
        } else if (!pq.empty())
            pq.pop_front();
    }

    int sz = (int) pq.size();

    if (pq.empty()) {
        res.push_back(0);
        res.push_back(0);
        return res;
    }
    if (sz == 1){
        res.push_back(pq[0]);
        res.push_back(pq[0]);
        return res;
    }

    res.push_back(pq[sz-1]);
    res.push_back(pq[0]);

    return res;
}