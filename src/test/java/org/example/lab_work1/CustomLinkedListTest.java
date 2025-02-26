package org.example.lab_work1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomLinkedListTest {

    @Test
    public void testInsertAndGet() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        list.insert(20);
        assertEquals(10, list.retrieve(0));
        assertEquals(20, list.retrieve(1));
    }

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

    @Test
    public void testDeleteFirst() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.delete(0);
        assertEquals(20, list.retrieve(0));
    }

    @Test
    public void testDeleteLast() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        list.insert(20);
        list.delete(1);
        assertEquals(10, list.retrieve(0));
    }

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

    @Test
    public void testRetrieveEmptyList() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.retrieve(0));
    }

    @Test
    public void testDeleteEmptyList() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        assertThrows(IllegalStateException.class, () -> list.delete(0));
    }

    @Test
    public void testRetrieveNegativeIndex() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.retrieve(-1));
    }

    @Test
    public void testRetrieveIndexOutOfRange() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.retrieve(1));
    }

    @Test
    public void testDeleteNegativeIndex() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(-1));
    }

    @Test
    public void testDeleteIndexOutOfRange() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.insert(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(1));
    }

    @Test
    public void testToString() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.insert("Hello");
        list.insert("World");
        assertEquals("Hello World", list.toString());
    }

    @Test
    public void testToStringEmptyList() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        assertEquals("", list.toString());
    }
}
