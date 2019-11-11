import java.util.Scanner;
import java.util.Stack;
/*
Which brackets are balanced

Given a string consisting of brackets, write a program to examine whether the pairs and the orders of "{", "}", "(", ")", "[", "]" are correct (balanced). For example, the program should print true for the string [()]{}{[()()]()} and false for ()[]}.

The classic algorithm for solving this problem relies on using a stack.

create an instance of a stack;
traverse the input string;
if the current character is a starting bracket "(" or "{" or "[" then push it to the stack;
if the current is a closing bracket ")" or "}" or "]" then remove (pop) the top element from the stack; if the popped bracket is the matching starting bracket then fine else parenthesis are not balanced;
after completing traversal, if there are some starting brackets left in the stack, then the parenthesis are not balanced.

Sample Input:
([][])
Sample Output:
true

Sample Input:
([](){([])})
Sample Output:
true

Sample Input:
{{[()]]
Sample Output:
false
*/
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();

        String brackets = scanner.next();

        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(' || brackets.charAt(i) == '{'
                    || brackets.charAt(i) == '['){
                stack.push(brackets.charAt(i));
            }else {
                if (!stack.isEmpty()){
                    stack.pop();
                }else {
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(stack.empty());
    }
}
