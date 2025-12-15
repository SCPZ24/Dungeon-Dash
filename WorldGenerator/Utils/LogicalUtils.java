package WorldGenerator.Utils;

import java.util.ArrayList;
import java.util.List;

public class LogicalUtils {
    public static int[] unionSortedArrays(List<int[]> arrays) {
        int[] pointers = new int[arrays.size()];
        List<Integer> result = new ArrayList<>();

        while (true) {
            boolean hasReachedEnd = false;
            for (int i = 0; i < pointers.length; i++) {
                if (pointers[i] >= arrays.get(i).length) {
                    hasReachedEnd = true;
                    break;
                }
            }
            if (hasReachedEnd) {
                break;
            }

            int firstVal = arrays.getFirst()[pointers[0]];
            boolean allEqual = true;

            for (int i = 1; i < arrays.size(); i++) {
                int currentVal = arrays.get(i)[pointers[i]];
                if (currentVal != firstVal) {
                    allEqual = false;
                    break;
                }
            }

            if (allEqual) {
                result.add(firstVal);
                for (int i = 0; i < pointers.length; i++) {
                    pointers[i]++;
                }
            } else {
                int minVal = firstVal;
                for (int i = 1; i < arrays.size(); i++) {
                    int currentVal = arrays.get(i)[pointers[i]];
                    if (currentVal < minVal) {
                        minVal = currentVal;
                    }
                }

                for (int i = 0; i < arrays.size(); i++) {
                    if (arrays.get(i)[pointers[i]] == minVal) {
                        pointers[i]++;
                    }
                }
            }
        }

        int[] intersection = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            intersection[i] = result.get(i);
        }

        return intersection;
    }

    public static double calculateEntropy(int[] indexes) {
        if(indexes.length==0){
            return Double.POSITIVE_INFINITY;
        }
        double[] probabilities = SampleUtils.scaleToOne(SampleUtils.collectProbabilities(indexes));
        return MathUtils.calculateEntropy(probabilities);
    }
}
