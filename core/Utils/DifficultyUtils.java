package core.Utils;

public class DifficultyUtils {
    public static int[] getBoundaries(int level){
        return switch (level) {
            case 1, 6 -> new int[]{150, 200};
            case 2, 7 -> new int[]{200, 300};
            case 3, 8 -> new int[]{300, 400};
            case 4, 9 -> new int[]{400, 500};
            case 5, 10 -> new int[]{500, 600};
            default -> new int[]{600, 800};
        };
    }

    public static long[] getEnemyTimeDelta(int level){
        return switch (level){
            case 1, 6 -> new long[]{5000, 500};
            case 2, 7 -> new long[]{4000, 400};
            case 3, 8 -> new long[]{3000, 350};
            case 4, 9 -> new long[]{2500, 300};
            case 5, 10 -> new long[]{2500, 250};
            default -> new long[]{2000, 200};
        };
    }

    public static int getSightDistance(int level){
        return switch (level){
            case 6 -> 20;
            case 7 -> 15;
            case 8 -> 10;
            case 9 -> 9;
            default -> 8;
        };
    }
}
