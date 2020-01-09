package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    static Map<String, String> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String expression = bufferedReader.readLine();
            if (!expression.equals("")) {
                if (expression.equals("/exit")) {
                    System.out.println("Bye!");
                    return;
                }

                if (expression.matches("\\/+[a-zA-Z]+")) {
                    System.out.println("Unknown command");
                    continue;
                }

                if (expression.matches("\\s*[a-zA-Z]+\\s*=\\s*\\d+\\s*")) {
                    lettersToDigits(expression);
                    continue;
                }

                expression = replacement(expression);

                if (expression.equals("Invalid expression")) {
                    System.out.println("Invalid expression");
                    continue;
                }

                if (expression.matches(".+[a-zA-Z]+.+")) {
                    expression = equationLettersToEquationDigits(expression);
                }

                String result = infixToPostfix(expression);
                if (!result.equals("Invalid expression")) {
                    System.out.println(postfixToAnswer(result));
                } else {
                    System.out.println("Invalid expression");
                }
            }
        }
    }

    static String equationLettersToEquationDigits(String expression) {
        StringBuilder stringBuilder = new StringBuilder();

        String[] strings = expression.split("\\s+");
        for (int i = 1; i < strings.length; i++) {
            if (strings[i].matches("[a-zA-Z]+")){
                stringBuilder.append(" ").append(map.get(strings[i]));
            }else{
                stringBuilder.append(" ").append(strings[i]);
            }
        }
        return stringBuilder.toString();
    }

    static void lettersToDigits(String expression){
        String[] names = expression.split("\\s*=\\s*");
        map.put(names[0], names[1]);
    }


    static String replacement(String expression) {

        if (expression.matches(".+\\*{2,}.+") || expression.matches(".+\\/{2,}.+")) {
            return "Invalid expression";
        }else {
            expression = expression.replaceAll("\\s*\\-{3}\\s*", "-");
            expression = expression.replaceAll("\\s*\\-{2}\\s*", "+");
            expression = expression.replaceAll("\\s+\\+{1,}\\s*", "+");
            expression = expression.replaceAll("\\s*(\\w+|\\(|\\))\\s*", " $0 ");
            return expression;
        }
    }

    static int postfixToAnswer(String postfix) {
        Stack<Integer> stack = new Stack<>();
        String[] post = postfix.split("\\s+");

        for (int i = 1; i < post.length; i++) {
            if (post[i].matches("\\d+")){
                stack.push(Integer.parseInt(post[i]));
            }else if (stack.size() >= 2){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(calculate(post[i], a, b));
            }else {
                int a = stack.pop();
                stack.push(calculate(post[i], 0, a));
            }
        }
        return stack.pop();
    }

    static String infixToPostfix(String expression){
        StringBuilder result = new StringBuilder();
        Stack<String> stack = new Stack<>();

        String expressions[] = expression.split("\\s+");
        for (int i = 0; i < expressions.length; i++) {
            String ex = expressions[i];

            if (ex.matches("\\d+")){
                result.append(" ").append(ex);
            }else if (ex.equals("(")){
                stack.push(ex);
            }else if (ex.equals(")")){
                while (!stack.isEmpty() && !stack.peek().equals("(")){
                    result.append(" ").append(stack.pop());
                }

                if (!stack.isEmpty() && !stack.peek().equals("(")){
                    return "Invalid expression";
                }else if (stack.isEmpty()){
                    return "Invalid expression";
                }else {
                    stack.pop();
                }

            }else {
                while (!stack.isEmpty() && operator(ex) <= operator(stack.peek())){
                    if (stack.peek().equals("(")){
                        return "Invalid expression";
                    }else {
                        result.append(" ").append(stack.pop());
                    }
                }
                stack.push(ex);
            }
        }

        while (!stack.isEmpty()){
            if (stack.peek().equals("(")){
                return "Invalid expression";
            }else {
                result.append(" ").append(stack.pop());
            }
        }

        return result.toString();
    }

    static int calculate(String operation, int a , int b) {
        switch (operation){
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return -1;
    }

    static int operator(String ex) {
        switch (ex) {
            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
                return 2;
        }
        return -1;
    }
}
