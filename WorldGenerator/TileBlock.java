package WorldGenerator;

import tileengine.TETile;

public class TileBlock {
    private final TETile[][] tile;

    private final int[] upNeighbors;
    private final int[] downNeighbors;
    private final int[] leftNeighbors;
    private final int[] rightNeighbors;

    private final double probability;

    public int[] getUpNeighbors() {
        return upNeighbors;
    }

    public int[] getDownNeighbors() {
        return downNeighbors;
    }

    public int[] getLeftNeighbors() {
        return leftNeighbors;
    }

    public int[] getRightNeighbors() {
        return rightNeighbors;
    }

    public double getProbability() {
        return probability;
    }

    public TETile[][] getTile() {
        return tile;
    }

    public TileBlock(TETile[][] tile, int[] upNeighbors, int[] downNeighbors, int[] leftNeighbors, int[] rightNeighbors, double probability) {
        this.tile = tile;
        this.upNeighbors = upNeighbors;
        this.downNeighbors = downNeighbors;
        this.leftNeighbors = leftNeighbors;
        this.rightNeighbors = rightNeighbors;
        this.probability = probability;
    }
}
