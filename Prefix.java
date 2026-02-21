public static String convertToPostfix(String infix) throws IllegalArgumentException {
    StringBuilder output = new StringBuilder();
    Stack<Character> stack = new Stack<>();

    if (infix.trim().isEmpty()) {
        throw new IllegalArgumentException("Expression cannot be empty.");
    }

    String[] tokens = infix.split("\\s+");

    for (String token : tokens) {

        // 1. Numbers → append directly
        if (isNumeric(token)) {
            output.append(token).append(" ");
        }

        // 2. '(' → push
        else if (token.equals("(")) {
            stack.push('(');
        }

        // 3. ')' → pop until '('
        else if (token.equals(")")) {
            boolean foundOpening = false;

            while (!stack.isEmpty()) {
                char top = stack.pop();
                if (top == '(') {
                    foundOpening = true;
                    break;
                }
                output.append(top).append(" ");
            }

            if (!foundOpening) {
                throw new IllegalArgumentException("Mismatched parentheses: missing '('");
            }
        }

        // 4. Operators
        else if (token.length() == 1 && "+-*/".indexOf(token.charAt(0)) >= 0) {
            char op = token.charAt(0);

            while (!stack.isEmpty() &&
                   stack.peek() != '(' &&
                   getPrecedence(stack.peek()) >= getPrecedence(op)) {
                output.append(stack.pop()).append(" ");
            }

            stack.push(op);
        }

        // 5. Invalid token
        else {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
    }

    // 6. Cleanup loop
    while (!stack.isEmpty()) {
        char top = stack.pop();
        if (top == '(') {
            throw new IllegalArgumentException("Mismatched parentheses: unclosed '('");
        }
        output.append(top).append(" ");
    }

    return output.toString().trim();
}
