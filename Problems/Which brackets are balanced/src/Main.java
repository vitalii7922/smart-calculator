import java.util.Scanner;
import java.util.Stack;

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
