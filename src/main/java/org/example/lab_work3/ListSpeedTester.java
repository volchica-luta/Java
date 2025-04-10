package org.example.lab_work3;

import java.util.ArrayList;
import java.util.LinkedList;

class ListSpeedTester {
    private static final int[] SIZES = new int[]{1000, 2000, 4000, 5000, 6000, 7000, 8000};
    private static final int TEST_SIZE = 100000;

    static void runComparison() {
        var arrayBased = new ArrayList<Integer>(TEST_SIZE);
        var nodeBased = new LinkedList<Integer>();
        for (int i = 0; i < TEST_SIZE; ++i) {
            arrayBased.add(0);
            nodeBased.add(0);
        }
        for (int i : SIZES) {
            testOperations(arrayBased, nodeBased, i);
            System.out.println("\n");
        }
    }

    private static void testOperations(ArrayList<Integer> arrList, LinkedList<Integer> linkList, int opsCount) {
        System.out.print("Operations count: " + opsCount + "\n");
        System.out.print("Operation          | ArrayList | LinkedList\n");

        System.out.print("Add at start       | " + timeTest(opsCount, () -> arrList.add(0)) + " | " +
                timeTest(opsCount, () -> linkList.add(0)) + "\n");

        System.out.print("Add at middle      | " + timeTest(opsCount, () -> arrList.add(arrList.size() / 2, 0)) + " | " +
                timeTest(opsCount, () -> linkList.add(linkList.size() / 2, 0)) + "\n");

        System.out.print("Add at end         | " + timeTest(opsCount, () -> arrList.add(arrList.size(), 0)) + " | " +
                timeTest(opsCount, () -> linkList.add(linkList.size(), 0)) + "\n");

        System.out.print("Remove from start  | " + timeTest(opsCount, () -> arrList.remove(0)) + " | " +
                timeTest(opsCount, linkList::removeFirst) + "\n");

        System.out.print("Remove from middle | " + timeTest(opsCount, () -> arrList.remove(arrList.size() / 2)) + " | " +
                timeTest(opsCount, () -> linkList.remove(linkList.size() / 2)) + "\n");

        System.out.print("Remove from end    | " + timeTest(opsCount, () -> arrList.remove(arrList.size() - 1)) + " | " +
                timeTest(opsCount, linkList::removeLast) + "\n");

        System.out.print("Get from start     | " + timeTest(opsCount, () -> arrList.get(0)) + " | " +
                timeTest(opsCount, () -> linkList.get(0)) + "\n");

        System.out.print("Get from middle    | " + timeTest(opsCount, () -> arrList.get(arrList.size() / 2)) + " | " +
                timeTest(opsCount, () -> linkList.get(linkList.size() / 2)) + "\n");

        System.out.print("Get from end       | " + timeTest(opsCount, () -> arrList.get(arrList.size() - 1)) + " | " +
                timeTest(opsCount, linkList::getLast) + "\n");
    }

    private static long timeTest(int repeats, Runnable action) {
        return measureDuration(() -> {
            for (int i = 0; i < repeats; ++i) {
                action.run();
            }
        });
    }

    private static long measureDuration(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        long finish = System.nanoTime();
        return finish - start;
    }
}