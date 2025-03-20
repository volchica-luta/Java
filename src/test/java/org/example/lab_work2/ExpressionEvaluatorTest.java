package org.example.lab_work2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс ExpressionEvaluatorTest содержит unit-тесты для проверки корректности работы класса ExpressionEvaluator.
 * Тесты охватывают различные сценарии, включая корректные выражения, ошибки в формате выражения и арифметические ошибки.
 */
class ExpressionEvaluatorTest {

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    /**
     * Проверяет обработку некорректного выражения, в котором отсутствует второй операнд для оператора.
     * Ожидается, что метод compute выбросит IllegalArgumentException.
     */
    @Test
    void testInvalidExpression() {
        assertThrows(IllegalArgumentException.class, () -> evaluator.compute("1 +"));
    }

    /**
     * Проверяет обработку пустого выражения.
     * Ожидается, что метод compute выбросит IllegalArgumentException.
     */
    @Test
    void testEmptyExpression() {
        assertThrows(IllegalArgumentException.class, () -> evaluator.compute(""));
    }

    /**
     * Проверяет обработку выражения с несбалансированными скобками.
     * Ожидается, что метод compute выбросит IllegalArgumentException.
     */
    @Test
    void testMismatchedParentheses() {
        assertThrows(IllegalArgumentException.class, () -> evaluator.compute("(1 + 2"));
    }

    /**
     * Проверяет обработку выражения с делением на ноль.
     * Ожидается, что метод compute выбросит ArithmeticException.
     */
    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> evaluator.compute("1 / 0"));
    }

    /**
     * Проверяет корректность вычисления простого выражения без скобок.
     * Ожидается, что метод compute вернет правильный результат.
     */
    @Test
    void testValidExpression() {
        assertEquals(3, evaluator.compute("1 + 2"));
    }

    /**
     * Проверяет корректность вычисления выражения со скобками.
     * Ожидается, что метод compute вернет правильный результат.
     */
    @Test
    void testValidExpressionWithParentheses() {
        assertEquals(6, evaluator.compute("2 * (1 + 2)"));
    }
}