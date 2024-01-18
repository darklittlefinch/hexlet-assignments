package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        var minThread = new MinThread(numbers);
        var maxThread = new MaxThread(numbers);

        LOGGER.setLevel(Level.INFO);

        LOGGER.info("Thread " + minThread.getName() + " started");
        minThread.start();

        LOGGER.info("Thread " + maxThread.getName() + " started");
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("Thread " + minThread.getName() + " finished");
        LOGGER.info("Thread " + maxThread.getName() + " finished");

        return Map.of("min", minThread.getResult(),
                "max", maxThread.getResult());
    }
    // END
}
