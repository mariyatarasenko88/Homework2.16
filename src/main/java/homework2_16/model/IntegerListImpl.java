package homework2_16.model;

import homework2_16.exception.MyIndexOutOfBoundsException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] array;
    private int arraySize;

    public IntegerListImpl() {
        array = new Integer[10];
        arraySize = 0;
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        if (isArrayFull()) {
            grow();
        }
        array[arraySize] = item;

        return array[arraySize++];
    }

    private boolean isArrayFull() {
        return arraySize == (array.length - 1);
    }

    @Override
    public Integer add(int index, Integer item) {
        validateItem(item);

        if (index < 0 || index > arraySize) {
            throw new MyIndexOutOfBoundsException("Введен некорректный индекс");
        }
        if (isArrayFull()) {
            grow();
        }

        if (arraySize - index >= 0) {
            System.arraycopy(array, index, array, index + 1, arraySize - index);
        }
        array[index] = item;
        arraySize++;
        return array[index];
    }
    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullPointerException("Значение не должно быть равно null");
        }
    }
    private void grow() {
        int newSize = (int) (array.length * 1.5) + 1;
        array = Arrays.copyOf(array, newSize);
    }

    @Override
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        array[index] = item;
        return array[index];
    }
    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new MyIndexOutOfBoundsException("Элемент не найден");
        }
        return remove(index);
    }
    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer element = array[index];
        if (arraySize - index >= 0) {
            System.arraycopy(array, index + 1, array, index, arraySize - index);
        }
        arraySize--;
        return element;
    }
    @Override
    public boolean contains(Integer item) {
        Integer[] tempArray = array.clone();

        tempArray = Sorts.runSort(getArray());
        System.out.println(Arrays.toString(tempArray));
        System.out.println(Arrays.toString(array));
        return !binarySearch(tempArray, item).equals(-1);
    }
    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < arraySize; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = arraySize-1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public Integer get(int index) {
        validateIndex(index);
        return array[index];
    }
    private Integer[] getArray() {
        Integer[] result = new Integer[arraySize];
        System.arraycopy(array, 0, result, 0, arraySize);
        return result;
    }
    @Override
    public boolean equals(IntegerList otherList) {
        if (arraySize != otherList.size()) {
            return false;
        }
        for (int i = 0; i < arraySize; i++) {
            if (!array[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public int size() {
        return arraySize;
    }
    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }
    @Override
    public void clear() {
        arraySize = 0;
    }
    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, arraySize);
    }
    private void validateIndex(int index) {
        try {
            if (array[index] == null || index >= arraySize) {
                throw new MyIndexOutOfBoundsException("Такого элемента не существует");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MyIndexOutOfBoundsException("Превышен размер внутреннего массива");
        }
    }
    private Integer binarySearch(Integer[] array, Integer value) {
        int low = 0;
        int high = array.length - 1;
        int middle;
        if (value > array[high] || value < array[low]) {
            return -1;
        }
        while (low <= high) {
            middle = (low + high) / 2;
            if (array[middle].equals(value)) {
                System.out.println(array[middle] + ": " + middle);
                return middle;
            }
            if (value > array[middle]) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
            System.out.println("value = " + value + ", low = " + array[low] + ", middle = " + array[middle] + ", high = " + array[high]);
        }
        return -1;
    }
}
