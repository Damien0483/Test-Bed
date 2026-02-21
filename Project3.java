public double evaluatePostfix(String expression) {

    Stack<Double> stack = new Stack<>();
    String[] tokens = expression.split(" ");

    for (String token : tokens) {

        // Skip empty tokens (in case user types extra spaces)
        if (token.trim().isEmpty()) {
            continue;
        }

        // Check if token is a number
        try {
            double num = Double.parseDouble(token);
            stack.push(num);
            continue;
        } catch (NumberFormatException e) {
            // Not a number — must be an operator
        }

        // Validate operator
        if (!token.equals("+") && !token.equals("-") &&
            !token.equals("*") && !token.equals("/")) {
            System.out.println("Error: Invalid token → " + token);
            return Double.NaN;
        }

        // Ensure two operands exist
        if (stack.size() < 2) {
            System.out.println("Error: Insufficient operands for operator → " + token);
            return Double.NaN;
        }

        double y = stack.pop(); // second operand
        double x = stack.pop(); // first operand
        double result = 0;

        switch (token) {
            case "+":
                result = x + y;
                break;

            case "-":
                result = x - y;
                break;

            case "*":
                result = x * y;
                break;

            case "/":
                if (y == 0) {
                    System.out.println("Error: Division by zero.");
                    return Double.NaN;
                }
                result = x / y;
                break;
        }

        stack.push(result);
    }

    // After processing all tokens, exactly one value must remain
    if (stack.size() != 1) {
        System.out.println("Error: Incomplete or malformed expression.");
        return Double.NaN;
    }

    return stack.pop();
}
