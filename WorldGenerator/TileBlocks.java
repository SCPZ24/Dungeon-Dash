package WorldGenerator;

/*

0       1%
#.#
...
#.#

1       2       24%
###     #.#
...     #.#
###     #.#

3       4       5       6       4%
#.#     #.#     ###     ###
..#     #..     ..#     #..
###     ###     #.#     #.#

7       8       9       10      4%
###     #.#     #.#     #.#
...     ...     ..#     #..
#.#     ###     #.#     #.#

11      12      13      14      8%
#..     ..#     ...     #.#
...     ...     ...     ...
#..     ..#     #.#     ...

15      16      17      18      20%
###     ###     ..#     #..
#..     ..#     ..#     #..
#..     ..#     ###     ###

19      20      21      22      24%
###     #..     ..#     ...
...     #..     ..#     ...
...     #..     ..#     ###

23      15%
...
...
...

 */

import tileengine.TETile;
import tileengine.Tileset;

public enum TileBlocks {
    BLOCK00(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{0,2,5,6,7,9,10,13},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{0,1,3,5,7,8,9,11},
            0.01
    ),
    BLOCK01(new TETile[][]{
        {Tileset.WALL,Tileset.WALL,Tileset.WALL},
        {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
        {Tileset.WALL,Tileset.WALL,Tileset.WALL}},
            new int[]{},
            new int[]{},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{0,1,3,5,7,8,9,11},
            0.12
    ),
    BLOCK02(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{0,2,5,6,7,9,10,13},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{},
            new int[]{},
            0.12
    ),
    BLOCK03(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
            {Tileset.WALL,Tileset.WALL,Tileset.WALL}},
            new int[]{0,2,5,6,7,9,10,13},
            new int[]{},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{},
            0.01
    ),
    BLOCK04(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.WALL,Tileset.WALL}},
            new int[]{0,1,2,3,5,8,9,12},
            new int[]{},
            new int[]{},
            new int[]{0,1,3,5,7,8,9,11},
            0.01
    ),
    BLOCK05(new TETile[][]{
            {Tileset.WALL,Tileset.WALL,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{},
            0.01
    ),
    BLOCK06(new TETile[][]{
            {Tileset.WALL,Tileset.WALL,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{},
            new int[]{0,1,3,5,7,8,9,11},
            0.01
    ),
    BLOCK07(new TETile[][]{
            {Tileset.WALL,Tileset.WALL,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{0,1,3,5,7,8,9,11},
            0.01
    ),
    BLOCK08(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.WALL,Tileset.WALL}},
            new int[]{0,2,5,6,7,9,10,13},
            new int[]{},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{0,1,3,5,7,8,9,11},
            0.01
    ),
    BLOCK09(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{0,2,5,6,7,9,10,13},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{},
            0.01
    ),
    BLOCK10(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{0,2,5,6,7,9,10,13},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{},
            new int[]{0,1,3,5,7,8,9,11},
            0.01
    ),
    BLOCK11(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR}},
            new int[]{11,15,20},
            new int[]{11,18,20},
            new int[]{0,1,4,6,7,8,10,12},
            new int[]{12,21,23},
            0.02
    ),
    BLOCK12(new TETile[][]{
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL}},
            new int[]{12,16,21},
            new int[]{12,17,21},
            new int[]{11,20,23},
            new int[]{0,1,3,5,7,8,9,11},
            0.02
    ),
    BLOCK13(new TETile[][]{
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL}},
            new int[]{14,19,23},
            new int[]{0,2,3,4,8,9,10,14},
            new int[]{13,18,22},
            new int[]{13,17,22},
            0.02
    ),
    BLOCK14(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR}},
            new int[]{0,2,5,6,7,9,10,13},
            new int[]{13,22,23},
            new int[]{14,15,19},
            new int[]{14,16,19},
            0.02
    ),
    BLOCK15(new TETile[][]{
            {Tileset.WALL,Tileset.WALL,Tileset.WALL},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR}},
            new int[]{},
            new int[]{11,18,20},
            new int[]{},
            new int[]{14,16,19},
            0.05
    ),
    BLOCK16(new TETile[][]{
            {Tileset.WALL,Tileset.WALL,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL}},
            new int[]{},
            new int[]{12,17,21},
            new int[]{14,15,19},
            new int[]{},
            0.05
    ),
    BLOCK17(new TETile[][]{
        {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
        {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
        {Tileset.WALL,Tileset.WALL,Tileset.WALL}},
            new int[]{12,16,21},
            new int[]{},
            new int[]{13,18,22},
            new int[]{},
            0.05
    ),
    BLOCK18(new TETile[][]{
        {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
        {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
        {Tileset.WALL,Tileset.WALL,Tileset.WALL}},
            new int[]{11,15,20},
            new int[]{},
            new int[]{},
            new int[]{13,17,22},
            0.05
    ),
    BLOCK19(new TETile[][]{
            {Tileset.WALL,Tileset.WALL,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR}},
            new int[]{},
            new int[]{13,22,23},
            new int[]{14,15,19},
            new int[]{14,16,19},
            0.06
    ),
    BLOCK20(new TETile[][]{
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.FLOOR,Tileset.FLOOR}},
            new int[]{11,15,20},
            new int[]{11,18,20},
            new int[]{},
            new int[]{12,21,23},
            0.06
    ),
    BLOCK21(new TETile[][]{
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.WALL}},
            new int[]{12,16,21},
            new int[]{12,17,21},
            new int[]{11,20,23},
            new int[]{},
            0.06
    ),
    BLOCK22(new TETile[][]{
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.WALL,Tileset.WALL,Tileset.WALL}},
            new int[]{14,19,23},
            new int[]{},
            new int[]{13,18,22},
            new int[]{13,17,22},
            0.06
    ),
    BLOCK23(new TETile[][]{
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR},
            {Tileset.FLOOR,Tileset.FLOOR,Tileset.FLOOR}},
            new int[]{14,19,23},
            new int[]{13,22,23},
            new int[]{11,20,23},
            new int[]{12,21,23},
            0.15
    );

    public final TileBlock tileBlock;

    TileBlocks(TETile[][] tile, int[] upNeighbors, int[] downNeighbors, int[] leftNeighbors, int[] rightNeighbors, double probability) {
        this.tileBlock = new TileBlock(tile, upNeighbors, downNeighbors, leftNeighbors, rightNeighbors, probability);
    }
}
