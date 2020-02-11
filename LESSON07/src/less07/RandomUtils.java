package less07;

import java.util.Random;

public final class RandomUtils {

    private static final Random RANDOM = new Random();

    private RandomUtils() {}

    public static boolean rollTheDice(int probability) {
        return RANDOM.nextInt(100) < probability;
    }

    public static void sleepRandomTime(int min, int max) {
        int time = min + RANDOM.nextInt(max - min);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}