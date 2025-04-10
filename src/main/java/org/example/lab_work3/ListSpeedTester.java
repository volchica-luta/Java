package org.example.lab_work3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс для сравнительного тестирования производительности операций
 * в ArrayList и LinkedList.
 * <p>
 * Класс выполняет замеры времени выполнения различных операций (добавление,
 * удаление, получение элементов) на разных позициях в списках и выводит
 * результаты сравнения в табличном формате.
 */
class ListSpeedTester {
    /**
     * Размеры тестовых выборок для проведения замеров производительности.
     */
    private static final int[] SIZES = new int[]{1000, 2000, 4000, 5000, 6000, 7000, 8000};

    /**
     * Размер тестового списка, который будет использоваться для замеров.
     */
    private static final int TEST_SIZE = 100000;

    /**
     * Запускает сравнительное тестирование ArrayList и LinkedList.
     * <p>
     * Метод инициализирует оба типа списков с тестовым количеством элементов,
     * затем последовательно выполняет тесты для каждого размера операций из массива SIZES.
     * </p>
     */
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

    /**
     * Выполняет тестирование операций для двух реализаций списка.
     *
     * @param arrList  тестируемый ArrayList
     * @param linkList тестируемый LinkedList
     * @param opsCount количество операций для выполнения в каждом тесте
     */
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

    /**
     * Выполняет переданное действие указанное количество раз и замеряет общее время выполнения.
     *
     * @param repeats количество повторений действия
     * @param action  тестируемое действие
     * @return общее время выполнения в наносекундах
     */
    private static long timeTest(int repeats, Runnable action) {
        return measureDuration(() -> {
            for (int i = 0; i < repeats; ++i) {
                action.run();
            }
        });
    }

    /**
     * Замеряет время выполнения операции.
     *
     * @param operation операция, время выполнения которой нужно измерить
     * @return время выполнения в наносекундах
     */
    private static long measureDuration(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        long finish = System.nanoTime();
        return finish - start;
    }
}