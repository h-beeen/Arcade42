#include <deque>
#include <vector>
#include <iostream>
#include <sstream>
using namespace std;
#define ios_sync ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)


// 정수 배열을 저장할 deque 자료구조 string 형 선언

bool flag, empty_flag;
int direction = 1;
// direction이 1일 경우 pop_front, 정방향 출력
// direction이 -1일 경우 pop_back, 역방향 출력

int test_case, num_length;
string func, num;

int main()
{
    ios_sync;
    cin >> test_case;

    for(int i = 0; i < test_case; i++) // test_case번 반복
    {
        direction = 1;
        empty_flag = false;
        deque <string> arr;
        flag = false;
        while (!arr.empty())    // 필요여부 검토
            arr.pop_back();     // 필요여부 검토

        cin >> func >> num_length >> num;   // input

        // str tokenize
        num.erase(num.length()-1, 1);           // 닫는 괄호 삭제
        num.erase(0, 1);                        // 여는 괄호 삭제

        stringstream ss(num);
        string token;
        while(getline(ss, token, ','))
            arr.push_back(token);

        for(int j = 0; j < func.length(); j++)  // func 전체 탐색
        {
            if (func[j] == 'R')
                direction *= -1;
            else if (func[j] == 'D' && !arr.empty())
            {
                if (direction == 1)
                    arr.pop_front();
                if (direction == -1)
                    arr.pop_back();
            }
            else if (func[j] == 'D' && arr.empty())
            {
                cout << "error\n";
                flag = true;
                break;
            }
        }
        if(!flag)
            cout << '[';
        if(direction == 1 && num_length > 0)
        {
            while(arr.size() != 1 && !arr.empty())
                {
                    cout << arr.front() << ',';
                    arr.pop_front();
                }
                if(!arr.empty()) {
                    cout << arr.front();
                    arr.pop_front();
                }
            }
        else if(direction == -1 && num_length > 0)
        {
            while(arr.size() != 1 && !arr.empty())
            {
                cout << arr.back() << ',';
                arr.pop_back();
            }
            if(!arr.empty())
            {
                cout << arr.back();
                arr.pop_back();
            }
        }
        if(!flag)
            cout << "]\n";
    }
}