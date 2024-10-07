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

import java.util.Stack;
import java.util.HashMap;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> bracketStack = new Stack<>();  // Stack to hold opening brackets
        HashMap<Character, Character> bracketMap = new HashMap<>();  // Map for closing and opening brackets

        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');

        for (char c : s.toCharArray()) {  
            if (bracketMap.containsKey(c)) { 
                if (bracketStack.isEmpty() || bracketStack.pop() != bracketMap.get(c)) {
                    return false;
                }
            } else {
                bracketStack.push(c);
            }
        }
        
        return bracketStack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
        System.out.println(solution.isValid("([])"));
    }
}
