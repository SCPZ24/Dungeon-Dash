package core.Utils;

import core.World;
import entity.chararters.Enemy;
import entity.chararters.Player;
import entity.specialBlocks.Flower;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
Map From DisjointSet to 2D position:
12  13  14  15
8   9   10  11
4   5   6   7
0   1   2   3
......
 */

public class GeometryUtils {
    public static void unionWorld(TETile[][] board, DisjointSet disjointSet, int disjointSetEndIndex){
        int edgeSize = board.length;
        int lastRowStep = (edgeSize - 1) * edgeSize;
        int edgeSizeMuli;
        for(int i = 0 ; i < edgeSize ; ++i){
            edgeSizeMuli = edgeSize * i;
            if(board[i][0] == Tileset.NOTHING) {
                disjointSet.union(disjointSetEndIndex, i);
            }else{
                board[i][0] = Tileset.WALL;
            }
            if(board[i][edgeSize - 1] == Tileset.NOTHING) {
                disjointSet.union(disjointSetEndIndex, lastRowStep + i);
            }else{
                board[i][edgeSize - 1] = Tileset.WALL;
            }
            if(board[0][i] == Tileset.NOTHING) {
                disjointSet.union(disjointSetEndIndex, edgeSizeMuli);
            }else{
                board[0][i] = Tileset.WALL;
            }
            if(board[edgeSize - 1][i] == Tileset.NOTHING) {
                disjointSet.union(disjointSetEndIndex, edgeSizeMuli + edgeSize - 1);
            }else{
                board[edgeSize - 1][i] = Tileset.WALL;
            }
        }
        int temp;
        for(int i = 0 ; i < edgeSize ; ++i){
            for(int j = 0 ; j < edgeSize-1 ; ++j){
                if(board[i][j] == board[i][j+1]){
                    temp = edgeSize * j;
                    disjointSet.union(temp + i, temp + edgeSize + i);
                }
                if(board[j][i] == board[j+1][i]){
                    temp = edgeSize * i;
                    disjointSet.union(temp + j, temp + j + 1);
                }
            }
        }
    }

    public static void fillPoolAndMountains(TETile[][] board, DisjointSet disjointSet, int disjointSetEndIndex, Random random){
        int edgeSize = board.length;
        for(int y = 0 ; y < edgeSize ; ++y){
            for(int x = 0 ; x < edgeSize ; ++x){
                if(board[x][y] == Tileset.NOTHING){
                    if(disjointSet.connected(y * edgeSize + x, disjointSetEndIndex)){
                        if(SampleUtils.possibleEvent(World.FLOWER_GENERATION_RATE,random)){
                            board[x][y] = Tileset.MOUNTAIN;
                        }
                    }else{
                        board[x][y] = Tileset.WATER;
                    }
                }
            }
        }
    }

    public static void fillVoid(TETile[][] board, Random random, List<Flower> flowers){
        int edgeSize = board.length;
        TETile temp;
        for(int y = 1 ; y < edgeSize-1 ; ++y){
            for(int x = 1 ; x < edgeSize-1 ; ++x){
                if(board[x][y] == Tileset.NOTHING){
                    if(board[x-1][y] == Tileset.FLOOR){
                        temp = detectFloor(board, x-1, y, random);
                        board[x][y] = temp;
                        if(temp == Tileset.FLOWER){
                            flowers.add(new Flower(x, y));
                        }
                    }
                    if(board[x+1][y] == Tileset.FLOOR){
                        temp = detectFloor(board, x+1, y, random);
                        board[x][y] = temp;
                        if(temp == Tileset.FLOWER){
                            flowers.add(new Flower(x, y));
                        }
                    }
                    if(board[x][y-1] == Tileset.FLOOR){
                        temp = detectFloor(board, x, y-1, random);
                        board[x][y] = temp;
                        if(temp == Tileset.FLOWER){
                            flowers.add(new Flower(x, y));
                        }
                    }
                    if(board[x][y+1] == Tileset.FLOOR){
                        temp = detectFloor(board, x, y+1, random);
                        board[x][y] = temp;
                        if(temp == Tileset.FLOWER){
                            flowers.add(new Flower(x, y));
                        }
                    }
                }
            }
        }
        Collections.shuffle(flowers);
    }

    private static TETile detectFloor(TETile[][] board, int x, int y, Random random){
        if(NeighborFLOORCount(board,x,y) > 1){
            return Tileset.WALL;
        }else{
            switch(WorldGenerator.Utils.SampleUtils.getByPossibilityArray(random, World.voidEndPMF)){
                case 0:
                    return Tileset.WALL;
                case 1:
                    return Tileset.TREE;
                case 2:
                    return Tileset.FLOWER;
                default:
                    break;
            }
        }
        return Tileset.WALL;
    }

    public static void replaceNOTHINGWithGRASS(TETile[][] board){
        int edgeSize = board.length;
        for(int y = 0 ; y < edgeSize ; ++y){
            for(int x = 0 ; x < edgeSize ; ++x){
                if(board[x][y] == Tileset.NOTHING){
                    board[x][y] = Tileset.GRASS;
                }
            }
        }
    }

    private static int NeighborFLOORCount(TETile[][] board, int x, int y){
        int cnt = 0;
        if(board[x-1][y] == Tileset.FLOOR){
            ++cnt;
        }
        if(board[x+1][y] == Tileset.FLOOR){
            ++cnt;
        }
        if(board[x][y-1] == Tileset.FLOOR){
            ++cnt;
        }
        if(board[x][y+1] == Tileset.FLOOR){
            ++cnt;
        }
        return cnt;
    }

    public static Enemy generateEnemy(TETile[][] board, int x, int y, Player player){
        Enemy enemy;
        if(board[x-1][y] == Tileset.FLOOR){
            enemy = new Enemy(x-1,y,player);
        }else if(board[x+1][y] == Tileset.FLOOR){
            enemy = new Enemy(x+1,y, player);
        }else if(board[x][y-1] == Tileset.FLOOR){
            enemy = new Enemy(x,y-1, player);
        }else{
            enemy = new Enemy(x,y+1, player);
        }
        return enemy;
    }
}
