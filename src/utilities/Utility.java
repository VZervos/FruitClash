package utilities;

public class Utility {
    public static int getRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
