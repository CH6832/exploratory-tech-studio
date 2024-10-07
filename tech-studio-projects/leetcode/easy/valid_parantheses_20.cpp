/**
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Constraints:
    1 <= s.length <= 104
    s consists of parentheses only '()[]{}'.
*/

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

