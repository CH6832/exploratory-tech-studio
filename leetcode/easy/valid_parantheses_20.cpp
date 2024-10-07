#include <iostream>
#include <stack>
#include <unordered_map>
#include <string>

class Solution {
public:
    bool isValid(std::string s) {
        std::stack<char> bracketStack;
        std::unordered_map<char, char> bracketMap = {
            {')', '('},
            {'}', '{'},
            {']', '['}
        };

        for (char c : s) {
            if (bracketMap.count(c)) {
                if (bracketStack.empty() || bracketStack.top() != bracketMap[c]) {
                    return false;
                }
                bracketStack.pop(); 
            } else {
                bracketStack.push(c);
            }
        }
        
        return bracketStack.empty();
    }
};

int main() {
    Solution solution;
    std::cout << std::boolalpha;
    std::cout << solution.isValid("()") << std::endl;
    std::cout << solution.isValid("()[]{}") << std::endl;
    std::cout << solution.isValid("(]") << std::endl; 
    std::cout << solution.isValid("([])") << std::endl;
    return 0;
}

