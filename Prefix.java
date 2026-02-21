public static String convertToPostfix(String infix) throws IllegalArgumentException {
    StringBuilder output = new StringBuilder();
    Stack<Character> stack = new Stack<>();

    if (infix.trim().isEmpty()) {
        throw new IllegalArgumentException("Expression cannot be empty.");
    }

    String[] tokens = infix.split("\\s+");

    for (String token : tokens) {

        // 1. Handle Numbers
        if (isNumeric(token)) {
            output.append(token).append(" ");
            continue;
        }

        // Token should be a single character if not numeric
        if (token.length() != 1) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

        char ch = token.charAt(0);

        // 2. Handle '('
        if (ch == '(') {
            stack.push(ch);
            continue;
        }

        // 3. Handle ')'
        if (ch == ')') {
            boolean foundParen = false;

            while (!stack.isEmpty()) {
                char top = stack.pop();
                if (top == '(') {
                    foundParen = true;
                    break;
                }
                output.append(top).append(" ");
            }

            if (!foundParen) {
                throw new IllegalArgumentException("Mismatched parentheses.");
            }
            continue;
        }

        // 4. Handle Operators
        if ("+-*/".indexOf(ch) != -1) {
            while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(ch)) {
                output.append(stack.pop()).append(" ");
            }
            stack.push(ch);
            continue;
        }

        // 5. Invalid token
        throw new IllegalArgumentException("Invalid token: " + token);
    }

    // 6. Cleanup Loop
    while (!stack.isEmpty()) {
        char top = stack.pop();
        if (top == '(') {
            throw new IllegalArgumentException("Unclosed parenthesis.");
        }
        output.append(top).append(" ");
    }

    return output.toString().trim();
}
