package core.Utils;

import java.util.Arrays;

public class DisjointSet {
    int[] array;

    public DisjointSet(int N) {
        array = new int[N];
        Arrays.fill(array, -1);
    }

    public int sizeOf(int v) {
        return -array[find(v)];
    }

    public boolean connected(int v1, int v2) {
        return find(v2) == find(v1);
    }

    private int find(int v) {
        if(v < 0 || v >= array.length){
            throw new IllegalArgumentException("Invalid argument!");
        }
        int current = v , lastIndex = 0;
        while (current >= 0) {
            lastIndex = current;
            current = array[current];
        }
        current = v;
        while(current != lastIndex){
            array[current] = lastIndex;
            current = array[current];
        }
        return lastIndex;
    }

    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 == root2) {
            return;
        }

        int sizeV1 = array[root1];
        int sizeV2 = array[root2];

        if(sizeV1 >= sizeV2) {
            array[root1] = root2;
            array[root2] = sizeV2 + sizeV1;
        }else{
            array[root2] = root1;
            array[root1] = sizeV2 + sizeV1;
        }
    }
}
