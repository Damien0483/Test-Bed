public static int getPrecedence(char op) {
    switch (op) {
        case '*':
        case '/':
            return 2;
        case '+':
        case '-':
            return 1;
        default:
            return -1;
    }
}
