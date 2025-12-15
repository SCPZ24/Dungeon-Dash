package core;

import WorldGenerator.TileBlocks;
import WorldGenerator.WaveFunctionCollapse;
import core.Utils.DifficultyUtils;
import core.Utils.DisjointSet;
import core.Utils.GeometryUtils;
import core.Utils.RouteSeeker;
import entity.specialBlocks.LightenBlock;
import utils.StdDraw;
import entity.chararters.Enemy;
import entity.chararters.Player;
import entity.specialBlocks.EscapeHole;
import entity.specialBlocks.Flower;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static core.Main.pauseFont;

public class World {
    public static int EDGE_SIZE = 96;
    public final int EDGE_SIZE_SQUARE = EDGE_SIZE*EDGE_SIZE;
    public static double FLOWER_GENERATION_RATE = 0.01;
    public static final double[] voidEndPMF = new double[]{0.4,0.3,0.3};    //WALL,TREE,FLOWER

    private final TERenderer renderer;

    private final WaveFunctionCollapse waveFunctionCollapse;

    TETile[][] board;
    private DisjointSet disjointSet;

    private final List<Flower> flowers;
    private int flowersToCollect;
    private Player player;
    private Enemy enemy;
    private EscapeHole escapeHole;

    private boolean gameOver;
    private boolean gamePaused;
    private int level;
    private int sight;

    public void clear(){
        disjointSet = null;
        waveFunctionCollapse.initializeGenerate();
        flowers.clear();
        player = null;
        escapeHole = null;
        enemy = null;
        for(int y = 0; y < EDGE_SIZE; ++y){
            Arrays.fill(board[y],Tileset.NOTHING);
        }
    }

    public void nextLevel(){
        ++level;
        gamePaused = false;
        int[] boundaries = DifficultyUtils.getBoundaries(level);
        generateWorldWithinRange(boundaries[0], boundaries[1]);
        flowersToCollect = flowers.size() * 2 / 3;
        long[] frequencies = DifficultyUtils.getEnemyTimeDelta(level);
        enemy.setFrequency(frequencies[0], frequencies[1]);
        if(level > 5){
            sight = DifficultyUtils.getSightDistance(level);
        }
    }

    public World(){
        board = new TETile[EDGE_SIZE][EDGE_SIZE];
        for(int y = 0; y < EDGE_SIZE; ++y){
            Arrays.fill(board[y],Tileset.NOTHING);
        }
        disjointSet = new DisjointSet(EDGE_SIZE_SQUARE + 1);  //0~EDGE_SIZE_SQUARE-1 : TETiles; EDGE_SIZE_SQUARE : outer
        waveFunctionCollapse = new WaveFunctionCollapse();
        renderer = new TERenderer();
        flowers = new ArrayList<>();
        gamePaused = true;
        gameOver = false;
        level = 0;
        renderer.initialize(EDGE_SIZE,EDGE_SIZE+4);
    }

    private void generateWorldWithinRange(int lowestBoundary, int highestBoundary){
        clear();
        generateSkeletonOnce();
        if(waveFunctionCollapse.getBlockCount() <= lowestBoundary || waveFunctionCollapse.getBlockCount() >= highestBoundary){
            generateWorldWithinRange(lowestBoundary, highestBoundary);
            return;
        }
        flushToBoard();
        disjointSet = new DisjointSet(EDGE_SIZE_SQUARE + 1);
        GeometryUtils.unionWorld(board,disjointSet,EDGE_SIZE_SQUARE);
        player = new Player(3 * waveFunctionCollapse.getStartX() + 1, 3 * waveFunctionCollapse.getStartY() + 1);
        escapeHole = new EscapeHole(3 * waveFunctionCollapse.getEndX() + 1, 3 * waveFunctionCollapse.getEndY() + 1);
        if(!disjointSet.connected(EDGE_SIZE * player.getY() + player.getX(),EDGE_SIZE * escapeHole.getY() + escapeHole.getX())){
            generateWorldWithinRange(lowestBoundary, highestBoundary);
            return;
        }
        board[player.getX()][player.getY()] = Tileset.AVATAR;
        board[escapeHole.getX()][escapeHole.getY()] = Tileset.UNLOCKED_DOOR;
        GeometryUtils.fillPoolAndMountains(board,disjointSet,EDGE_SIZE_SQUARE,waveFunctionCollapse.getRandom());
        GeometryUtils.fillVoid(board,waveFunctionCollapse.getRandom(),flowers);
        if(flowers.isEmpty()){
            generateWorldWithinRange(lowestBoundary, highestBoundary);
            return;
        }
        enemy = GeometryUtils.generateEnemy(board, flowers.getFirst().getX(), flowers.getFirst().getY(), player);
        if(enemy.getY() == player.getY() && enemy.getX() == player.getX()){
            generateWorldWithinRange(lowestBoundary, highestBoundary);
            return;
        }
        board[enemy.getX()][enemy.getY()] = Tileset.ENEMY;
    }

    private void generateSkeletonOnce(){
        waveFunctionCollapse.initializeGenerate();
        while(waveFunctionCollapse.isCollapsing()){
            waveFunctionCollapse.continueGenerate();
        }
    }

    public void handle(char key){
        switch(key){
            case 'w' -> player.moveUp(board);
            case 's' -> player.moveDown(board);
            case 'a' -> player.moveLeft(board);
            case 'd' -> player.moveRight(board);
            case ' ' -> handleSpace();
            default -> {}
        }
    }

    private void handleSpace(){
        int x = player.getX();
        int y = player.getY();

        if(flowersToCollect == 0 && x == escapeHole.getX() && y == escapeHole.getY()){
            gamePaused = true;
            return;
        }
        if(board[x+1][y] == Tileset.FLOWER){
            collectFlower(x+1,y);
        }
        if(board[x-1][y] == Tileset.FLOWER){
            collectFlower(x-1,y);
        }
        if(board[x][y+1] == Tileset.FLOWER){
            collectFlower(x,y+1);
        }
        if(board[x][y-1] == Tileset.FLOWER){
            collectFlower(x,y-1);
        }
    }

    public void update(){
        enemy.operate(board);

        if(player.getX() == enemy.getX() && player.getY() == enemy.getY()){
            gamePaused = true;
            gameOver = true;
        }
    }

    public void showText(){
        StdDraw.setPenColor(Color.white);
        if(level > 1){
            StdDraw.text(48,56,"You've passed level "+level);
        }
        StdDraw.text(48,48,"Press SPACE to continue.");
        StdDraw.show();
    }

    public void render(){
        if(level <= 5){
            render_light();
        }else{
            render_dark();
        }
        StdDraw.setPenColor(Color.white);
        StdDraw.text(48,98,flowersToCollect + " flowers remain to collect");
        StdDraw.show();
    }

    private void render_light(){
        renderer.renderFrame(board);
    }
    private void render_dark(){
        StdDraw.clear(new Color(0, 0, 0));
        List<LightenBlock> lightenBlocks = new ArrayList<>();
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; ++i){
            Arrays.fill(isVisited[i], false);
        }
        RouteSeeker.seekLightenBlock(lightenBlocks, sight, player.getX(), player.getY(), isVisited, board);
        renderer.drawTiles(board,lightenBlocks);
    }

    private void flushToBoard(){
        TileBlocks[][] tileBlocks = waveFunctionCollapse.getTileBlocks();
        for(int i = 0; i < tileBlocks.length; ++i){
            for(int j = 0; j < tileBlocks[i].length; ++j){
                if(tileBlocks[i][j] == null){
                    for(int bi = 0 ; bi < 3 ; ++bi){
                        for(int bj = 0 ; bj < 3 ; ++bj){
                            board[3*i+bi][3*j+bj] = Tileset.NOTHING;
                        }
                    }
                }else{
                    for(int bi = 0 ; bi < 3 ; ++bi){
                        for(int bj = 0 ; bj < 3 ; ++bj){
                            board[3*i+bi][3*j+bj] = tileBlocks[i][j].tileBlock.getTile()[2-bj][bi];
                        }
                    }
                }
            }
        }
    }

    public void collectFlower(int x, int y){
        for(Flower flower : flowers){
            if(flower.getX() == x && flower.getY() == y){
                flowers.remove(flower);
                board[x][y] = Tileset.TREE;
                break;
            }
        }
        if(flowersToCollect > 0) {
            --flowersToCollect;
        }
    }

    public boolean isGameOver(){
        return gameOver;
    }
    public boolean isGamePaused(){
        return gamePaused;
    }

    public void showGameOver(){
        StdDraw.setFont(pauseFont);
        StdDraw.text(48,56,"GAME OVER");
        StdDraw.text(48,48,"You passed " + (level-1) + " levels");
        StdDraw.show();
    }
}
