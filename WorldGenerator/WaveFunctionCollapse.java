package WorldGenerator;

import WorldGenerator.Utils.LogicalUtils;
import WorldGenerator.Utils.SampleUtils;
import utils.RandomUtils;

import java.util.*;

public class WaveFunctionCollapse {
    public static final int size = 32;

    private final TileBlocks[][] tileBlocks;
    private final PriorityQueue<Block> queue;
    private final Random random;
    private boolean collapseFinished;
    private int blockCount;

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public WaveFunctionCollapse() {
        tileBlocks = new TileBlocks[size][size];
        for(int i = 0 ; i < size ; ++i){
            Arrays.fill(tileBlocks[i],null);
        }
        queue = new PriorityQueue<>();
        random = new Random();
        collapseFinished = false;
        blockCount = 0;
    }
    public WaveFunctionCollapse(int seed) {
        tileBlocks = new TileBlocks[size][size];
        for(int i = 0 ; i < size ; ++i){
            Arrays.fill(tileBlocks[i],null);
        }
        queue = new PriorityQueue<>();
        random = new Random(seed);
        collapseFinished = false;
        blockCount = 0;
    }

    public TileBlocks[][] getTileBlocks() {
        return tileBlocks;
    }

    private static class Block implements Comparable<Block> {
        int x;
        int y;
        double entropy;
        public Block(int x, int y, double entropy) {
            this.x = x;
            this.y = y;
            this.entropy = entropy;
        }

        @Override
        public int compareTo(Block o) {
            return Double.compare(entropy, o.entropy);
        }

        public void printInfo(){
            System.out.println("x: " + x + " y: " + y + " entropy: " + entropy);
        }
    }

    public void initializeGenerate(){
        for(int i = 0 ; i < size ; ++i){
            Arrays.fill(tileBlocks[i],null);
        }
        queue.clear();
        collapseFinished = false;
        blockCount = 0;

        int x = RandomUtils.uniform(random,size);
        int y = RandomUtils.uniform(random,size);
        double[] probability = new double[24];
        for (int i = 0; i < probability.length; ++i) {
            probability[i] = TileBlocks.values()[i].tileBlock.getProbability();
        }

        int targetIndex = SampleUtils.getByPossibilityArray(random,probability);
        fillBlock(x, y, targetIndex);

        startX = x;
        startY = y;
    }

    public void continueGenerate(){
        Block block;
        int[] possibleBlocks;
        do {
            do {
                if(queue.isEmpty()){
                    collapseFinished = true;
                    return;
                }
                block = queue.poll();
            } while (block == null);
            possibleBlocks = collectPossibleBlocks(block.x, block.y);
        }while(tileBlocks[block.x][block.y] != null || possibleBlocks.length == 0);

        double[] probabilities = SampleUtils.scaleToOne(SampleUtils.collectProbabilities(possibleBlocks));

        fillBlock(block.x, block.y, possibleBlocks[SampleUtils.getByPossibilityArray(random, probabilities)]);

        endX = block.x;
        endY = block.y;
    }

    private void fillBlock(int x, int y, int kindIndex){
        tileBlocks[x][y] = TileBlocks.values()[kindIndex];

        double entropy;
        int[] possibleBlocks;
        if(isToFill(x-1,y)){
            possibleBlocks = collectPossibleBlocks(x-1,y);
            if(possibleBlocks.length > 0){
                entropy = LogicalUtils.calculateEntropy(possibleBlocks);
                queue.offer(new Block(x-1, y, entropy));
            }
        }
        if(isToFill(x+1,y)){
            possibleBlocks = collectPossibleBlocks(x+1,y);
            if(possibleBlocks.length > 0) {
                entropy = LogicalUtils.calculateEntropy(possibleBlocks);
                queue.offer(new Block(x + 1, y, entropy));
            }
        }
        if(isToFill(x,y-1)){
            possibleBlocks = collectPossibleBlocks(x,y-1);
            if(possibleBlocks.length > 0) {
                entropy = LogicalUtils.calculateEntropy(possibleBlocks);
                queue.offer(new Block(x, y - 1, entropy));
            }
        }
        if(isToFill(x,y+1)){
            possibleBlocks = collectPossibleBlocks(x,y+1);
            if(possibleBlocks.length > 0) {
                entropy = LogicalUtils.calculateEntropy(possibleBlocks);
                queue.offer(new Block(x, y + 1, entropy));
            }
        }

        ++blockCount;
    }

    private boolean isToFill(int x, int y){
        return x >= 0 && y >= 0 && x < size && y < size && tileBlocks[x][y] == null;
    }
    private TileBlocks getFilledBlock(int x, int y){
        if(x <  0 || y < 0 || x >= size || y >= size){
            return null;
        }
        return tileBlocks[x][y];
    }

    private int[] collectPossibleBlocks(int x, int y){
        List<int[]> possibleBlocks = new ArrayList<>();
        TileBlocks tempBlock;
        tempBlock = getFilledBlock(x-1,y);
        if(tempBlock != null){
            possibleBlocks.add(tempBlock.tileBlock.getRightNeighbors());
        }
        tempBlock = getFilledBlock(x+1,y);
        if(tempBlock != null){
            possibleBlocks.add(tempBlock.tileBlock.getLeftNeighbors());
        }
        tempBlock = getFilledBlock(x,y-1);
        if(tempBlock != null){
            possibleBlocks.add(tempBlock.tileBlock.getUpNeighbors());
        }
        tempBlock = getFilledBlock(x,y+1);
        if(tempBlock != null){
            possibleBlocks.add(tempBlock.tileBlock.getDownNeighbors());
        }
        return LogicalUtils.unionSortedArrays(possibleBlocks);
    }

    public boolean isCollapsing(){
        return !collapseFinished;
    }

    public int getBlockCount(){
        return blockCount;
    }

    public Random getRandom(){
        return random;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
