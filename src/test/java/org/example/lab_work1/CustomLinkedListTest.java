package org.example.lab_work1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс CustomLinkedListTest содержит набор тестов для проверки функциональности класса CustomLinkedList.
 * Эти тесты покрывают различные сценарии использования связанного списка, включая добавление, получение и удаление элементов.
 *
 */
public class CustomLinkedListTest {

    /**
     * Тестирование добавления элементов в список и их последующего получения.
     * Проверяет, что элементы добавляются и получаются корректно.
     */
    @Test
    public void testInsertAndGet() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        list.insert(20);
        assertEquals(10, list.retrieve(0));
        assertEquals(20, list.retrieve(1));
    }

    /**
     * Тестирование добавления нескольких элементов в список.
     * Проверяет, что все элементы добавляются и получаются корректно.
     */
    @Test
    public void testInsertMultiple() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.insert("Apple");
        list.insert("Banana");
        list.insert("Cherry");
        assertEquals("Apple", list.retrieve(0));
        assertEquals("Banana", list.retrieve(1));
        assertEquals("Cherry", list.retrieve(2));
    }

    /**
     * Тестирование удаления первого элемента из списка.
     * Проверяет, что после удаления первого элемента список содержит остальные элементы в правильном порядке.
     */
    @Test
    public void testDeleteFirst() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.delete(0);
        assertEquals(20, list.retrieve(0));
    }

    /**
     * Тестирование удаления последнего элемента из списка.
     * Проверяет, что после удаления последнего элемента список содержит остальные элементы в правильном порядке.
     */
    @Test
    public void testDeleteLast() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.delete(1);
        assertEquals(10, list.retrieve(0));
    }

    /**
     * Тестирование удаления элемента из середины списка.
     * Проверяет, что после удаления элемента из середины список содержит остальные элементы в правильном порядке.
     */
    @Test
    public void testDeleteMiddle() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.delete(1);
        assertEquals(10, list.retrieve(0));
        assertEquals(30, list.retrieve(1));
    }

    /**
     * Тестирование попытки получить элемент из пустого списка.
     * Проверяет, что при попытке получить элемент из пустого списка выбрасывается исключение IndexOutOfBoundsException.
     */
    @Test
    public void testRetrieveEmptyList() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.retrieve(0));
    }

    /**
     * Тестирование попытки удалить элемент из пустого списка.
     * Проверяет, что при попытке удалить элемент из пустого списка выбрасывается исключение IllegalStateException.
     */
    @Test
    public void testDeleteEmptyList() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertThrows(IllegalStateException.class, () -> list.delete(0));
    }

    /**
     * Тестирование попытки получить элемент по отрицательному индексу.
     * Проверяет, что при попытке получить элемент по отрицательному индексу выбрасывается исключение IndexOutOfBoundsException.
     */
    @Test
    public void testRetrieveNegativeIndex() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.retrieve(-1));
    }

    /**
     * Тестирование попытки получить элемент по индексу, выходящему за пределы списка.
     * Проверяет, что при попытке получить элемент по индексу, превышающему размер списка, выбрасывается исключение IndexOutOfBoundsException.
     */
    @Test
    public void testRetrieveIndexOutOfRange() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.retrieve(1));
    }

    /**
     * Тестирование попытки удалить элемент по отрицательному индексу.
     * Проверяет, что при попытке удалить элемент по отрицательному индексу выбрасывается исключение IndexOutOfBoundsException.
     */
    @Test
    public void testDeleteNegativeIndex() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(-1));
    }

    /**
     * Тестирование попытки удалить элемент по индексу, выходящему за пределы списка.
     * Проверяет, что при попытке удалить элемент по индексу, превышающему размер списка, выбрасывается исключение IndexOutOfBoundsException.
     */
    @Test
    public void testDeleteIndexOutOfRange() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(1));
    }

    /**
     * Тестирование метода toString для списка с элементами.
     * Проверяет, что метод toString возвращает строковое представление списка с элементами, разделенными пробелами.
     */
    @Test
    public void testToString() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.insert("Hello");
        list.insert("World");
        assertEquals("Hello World", list.toString());
    }

    /**
     * Тестирование метода toString для пустого списка.
     * Проверяет, что метод toString для пустого списка возвращает пустую строку.
     */
    @Test
    public void testToStringEmptyList() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        assertEquals("", list.toString());
    }
}

