package WorldGenerator.Utils;

import WorldGenerator.TileBlocks;
import tileengine.TETile;

public class DebugUtils {
    public static void printBlockBoard(TileBlocks[][] tileBlocks){
        for(int j = tileBlocks[0].length - 1; j >= 0; --j){
            for (TileBlocks[] tileBlock : tileBlocks) {
                System.out.print(tileBlock[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printTileBoard(TETile[][] tiles){
        for(int j = tiles[0].length - 1; j >= 0; --j){
            for (TETile[] tile : tiles) {
                System.out.print(tile[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printGeneratedInfo(int newBlockIndex){
        System.out.println("New block index: " + newBlockIndex);
    }
}
