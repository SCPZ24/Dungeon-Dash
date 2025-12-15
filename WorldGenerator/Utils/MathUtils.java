package WorldGenerator.Utils;

import java.util.Arrays;

public class MathUtils {
    public static final int totalBlocks = 24;

    public static double calculateEntropy(double[] possibilities){
        double[] toCalculate = new double[totalBlocks];
        Arrays.fill(toCalculate, 0.0);
        System.arraycopy(possibilities, 0, toCalculate, 0, possibilities.length);

        double sum = 0.0;
        for(int i = 0 ; i < totalBlocks ; ++i){
            sum += Negative_NlogN(toCalculate[i]);
        }
        return sum / totalBlocks;
    }

    private static double Negative_NlogN(double N){
        if(N == 0){
            return 0;
        }
        return - Math.log(N) * N;
    }
}
