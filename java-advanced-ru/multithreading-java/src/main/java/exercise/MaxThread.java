package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {

    private final int[] numbers;
    private int result;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        result = Arrays.stream(numbers)
                .reduce(numbers[0], Math::max);
    }

    public int getResult() {
        return result;
    }
}
// END
