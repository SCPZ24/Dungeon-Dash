package core.Utils;

import utils.RandomUtils;

import java.util.Random;

public class SampleUtils {
    public static boolean possibleEvent(double possibility, Random random){
        return possibility > RandomUtils.uniform(random);
    }
}
