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
