"""
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
"""

def main():
    """Driving code"""
    solution = Solution()
    print(solution.isValid("()"))
    print(solution.isValid("()[]{}"))
    print(solution.isValid("(]"))
    print(solution.isValid("([])"))


class Solution:
    def isValid(self, s: str) -> bool:
        bracket_stack: list = []
        bracket_map: dict = {')': '(', '}': '{', ']': '['}
        for c in s:
            if c in bracket_map:
                if not bracket_stack or bracket_stack.pop() != bracket_map[c]:
                    return False
            else:
                bracket_stack.append(c)

        return not bracket_stack

if __name__ == "__main__":
    main()
