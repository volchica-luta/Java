package org.example.lab_work2;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Класс ExpressionEvaluator выполняет вычисление математических выражений,
 * записанных в стандартной инфиксной нотации. Поддерживаются базовые арифметические операции:
 * сложение (+), вычитание (-), умножение (*), деление (/) и использование скобок для задания приоритета.
 */
public class ExpressionEvaluator {

    /**
     * Вычисляет результат математического выражения, переданного в виде строки.
     *
     * @param input строка с математическим выражением (например, "2 + 3 * (4 - 1)").
     * @return результат вычисления выражения в виде числа с плавающей точкой.
     * @throws IllegalArgumentException если выражение пустое, содержит недопустимые символы или имеет синтаксические ошибки.
     * @throws ArithmeticException если в выражении происходит деление на ноль.
     */
    public double compute(String input) {
        input = input.replaceAll("\\s", "");

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Выражение не может быть пустым.");
        }

        List<String> postfixTokens = convertToPostfix(input);
        Stack<Double> numbers = new Stack<>();

        for (String token : postfixTokens) {
            if (isNumber(token)) {
                numbers.push(Double.parseDouble(token));
            } else {
                if (numbers.size() < 2) {
                    throw new IllegalArgumentException("Недостаточно операндов для оператора: " + token);
                }
                double second = numbers.pop();
                double first = numbers.pop();
                numbers.push(calculate(token.charAt(0), first, second));
            }
        }

        if (numbers.size() != 1) {
            throw new IllegalArgumentException("Некорректное выражение: несбалансированное количество операторов и операндов.");
        }

        return numbers.pop();
    }

    /**
     * Преобразует инфиксное выражение в постфиксную нотацию (обратная польская запись).
     *
     * @param input строка с инфиксным выражением.
     * @return список токенов в постфиксной нотации.
     * @throws IllegalArgumentException если выражение содержит ошибки (например, несбалансированные скобки).
     */
    private List<String> convertToPostfix(String input) {
        List<String> result = new ArrayList<>();
        Stack<Character> operators = new Stack<>();
        boolean expectNumber = true;

        try {
            for (int i = 0; i < input.length(); i++) {
                char current = input.charAt(i);
                if (Character.isDigit(current) || (current == '-' && expectNumber && (i == 0 || !Character.isDigit(input.charAt(i - 1))))) {
                    StringBuilder number = new StringBuilder();
                    number.append(current);
                    while (i + 1 < input.length() && (Character.isDigit(input.charAt(i + 1)) || input.charAt(i + 1) == '.')) {
                        number.append(input.charAt(++i));
                    }
                    result.add(number.toString());
                    expectNumber = false;
                } else if (current == '(') {
                    operators.push(current);
                    expectNumber = true;
                } else if (current == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        result.add(String.valueOf(operators.pop()));
                    }
                    if (operators.isEmpty()) {
                        throw new IllegalArgumentException("Несбалансированные скобки в выражении.");
                    }
                    operators.pop(); // Удаляем '('
                    expectNumber = false;
                } else if (isOperator(current)) {
                    while (!operators.isEmpty() && getPrecedence(current) <= getPrecedence(operators.peek())) {
                        result.add(String.valueOf(operators.pop()));
                    }
                    operators.push(current);
                    expectNumber = true;
                } else {
                    throw new IllegalArgumentException("Недопустимый символ в выражении: " + current);
                }
            }

            while (!operators.isEmpty()) {
                if (operators.peek() == '(') {
                    throw new IllegalArgumentException("Несбалансированные скобки в выражении.");
                }
                result.add(String.valueOf(operators.pop()));
            }
        } catch (EmptyStackException e) {
            throw new IllegalArgumentException("Некорректный формат выражения.");
        }

        return result;
    }

    /**
     * Определяет приоритет оператора.
     *
     * @param operator символ оператора (+, -, *, /).
     * @return числовое значение приоритета (чем выше, тем приоритетнее оператор).
     */
    private int getPrecedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    /**
     * Проверяет, является ли строка числом (целым или дробным).
     *
     * @param str строка для проверки.
     * @return true, если строка представляет число, иначе false.
     */
    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли символ допустимым оператором.
     *
     * @param operator символ для проверки.
     * @return true, если символ является оператором (+, -, *, /), иначе false.
     */
    private boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/';
    }

    /**
     * Выполняет арифметическую операцию над двумя числами.
     *
     * @param operator символ оператора (+, -, *, /).
     * @param first первое число.
     * @param second второе число.
     * @return результат операции.
     * @throws ArithmeticException если происходит деление на ноль.
     * @throws IllegalArgumentException если оператор не поддерживается.
     */
    private double calculate(char operator, double first, double second) {
        return switch (operator) {
            case '+' -> first + second;
            case '-' -> first - second;
            case '*' -> first * second;
            case '/' -> {
                if (second == 0) {
                    throw new ArithmeticException("Деление на ноль недопустимо.");
                }
                yield first / second;
            }
            default -> throw new IllegalArgumentException("Неподдерживаемый оператор: " + operator);
        };
    }
}