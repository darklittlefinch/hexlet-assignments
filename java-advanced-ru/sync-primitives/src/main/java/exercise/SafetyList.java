package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private Integer[] array;

    SafetyList() {
        array = new Integer[0];
    }

    public synchronized void add(Integer element) {
        var newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length - 1] = element;
        array = newArray;
    }

    public Integer get(int index) {
        return array[index];
    }

    public int getSize() {
        return array.length;
    }
    // END
}
