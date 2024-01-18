package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private final int[] numbers;
    private int result;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        result = Arrays.stream(numbers)
                .reduce(numbers[0], Math::min);
    }

    public int getResult() {
        return result;
    }
}
// END
