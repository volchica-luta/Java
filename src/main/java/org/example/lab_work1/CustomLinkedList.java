package org.example.lab_work1;

/**
 * Класс CustomLinkedList представляет собой реализацию связанного списка.
 * Он поддерживает добавление элементов в конец списка, получение элементов по индексу и удаление элементов.
 *
 * @param <T> тип элементов, которые будут храниться в списке.
 */
class CustomLinkedList<T> {
    private Node<T> head; // Голова списка
    private int size = 0;     // Количество элементов в списке

    /**
     * Внутренний класс Node представляет узел связанного списка.
     * Каждый узел содержит данные и ссылку на следующий узел.
     *
     * @param <T> тип данных, хранящихся в узле.
     */
    private static class Node<T> {
        T data; // Данные узла
        Node<T> next; // Ссылка на следующий узел

        /**
         * Конструктор для создания узла с заданными данными.
         *
         * @param data данные, которые будут храниться в узле.
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Добавляет элемент в конец связанного списка.
     * Если список пуст, новый элемент становится головой списка.
     *
     * @param element элемент, который нужно добавить в список.
     */
    public void insert(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Получает элемент по заданному индексу.
     * Если индекс выходит за пределы списка, выбрасывает исключение IndexOutOfBoundsException.
     *
     * @param index индекс элемента, который нужно получить.
     * @return элемент на указанном индексе.
     * @throws IndexOutOfBoundsException если индекс отрицательный или превышает размер списка.
     */
    public T retrieve(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Удаляет элемент по заданному индексу.
     * Если список пуст, выбрасывает исключение IllegalStateException.
     * Если индекс выходит за пределы списка, выбрасывает исключение IndexOutOfBoundsException.
     *
     * @param index индекс элемента, который нужно удалить.
     * @throws IndexOutOfBoundsException если индекс отрицательный или превышает размер списка.
     * @throws IllegalStateException     если список пуст при попытке удаления.
     */
    public void delete(int index) throws IndexOutOfBoundsException, IllegalStateException {
        if (head == null) {
            throw new IllegalStateException("Список пуст");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    /**
     * Возвращает строковое представление элементов списка.
     * Элементы разделены пробелами.
     *
     * @return строка, содержащая элементы списка.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> p = head;
        while (p != null) {
            result.append(p.data).append(" ");
            p = p.next;
        }
        return result.toString().trim();
    }
}
