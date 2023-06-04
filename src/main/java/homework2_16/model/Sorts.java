package homework2_16.model;

import java.util.Random;

public class Sorts {
    public static void main(String[] args) {
        sortTest();
    }

    public static Integer[] runSort(Integer[] array) {
        int begin = 0;
        int end = array.length - 1;

        quickSort(array, begin, end);
        return array;
    }

    private static void sortTest() {
        Integer[] array = new Integer[100_00];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(50_000);
        }

        long start = System.currentTimeMillis();
        bubbleSort(array.clone());
        System.out.println("Bubble sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

        start = System.currentTimeMillis();
        selectionSort(array.clone());
        System.out.println("Selection sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

        start = System.currentTimeMillis();
        insertionSort(array.clone());
        System.out.println("Insertion sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

        start = System.currentTimeMillis();
        quickSort(array.clone(), 0, array.length - 1);
        System.out.println("Quick sort: " + ((System.currentTimeMillis() - start) / 1000.0) + " s");

    }

    // пузырек
    private static Integer[] bubbleSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    swapElements(array, i, j);
                }
            }
        }
        return array;
    }
    // выбором

    private static Integer[] selectionSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int indexMin = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    indexMin = j;
                }
            }
            swapElements(array, i, indexMin);
        }

        return array;
    }
    // вставками

    private static Integer[] insertionSort(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int currIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[currIndex] < array[j]) {
                    swapElements(array, currIndex, j);
                    currIndex--;
                } else {
                    break;
                }
            }
        }

        return array;
    }

    private static void quickSort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] array, int begin, int end) {
        int pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swapElements(array, i, j);
            }
        }

        swapElements(array, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
