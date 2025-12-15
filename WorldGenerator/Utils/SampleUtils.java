package WorldGenerator.Utils;

import WorldGenerator.TileBlocks;
import utils.RandomUtils;

import java.util.Random;

public class SampleUtils {
    public static double[] scaleToOne(double[] originalProbability){
        double sum = 0;
        for (double v : originalProbability) {
            sum += v;
        }
        double[] ret = new double[originalProbability.length];
        for(int i = 0; i < originalProbability.length; ++i){
            ret[i] = originalProbability[i] / sum;
        }
        return ret;
    }

    public static int getByPossibilityArray(Random random, double[] probability){
        double sample = RandomUtils.uniform(random,0.0,1.0);
        double sum = 0;
        int index = 0;
        while(sum <= sample && index <= probability.length){
            sum += probability[index++];
        }
        return index-1;
    }

    public static double[] collectProbabilities(int[] indexes){
        double[] ret = new double[indexes.length];
        TileBlocks[] values = TileBlocks.values();
        for(int i = 0; i < indexes.length; ++i){
            ret[i] = values[indexes[i]].tileBlock.getProbability();
        }
        return ret;
    }
}
