package entity.chararters;

import core.Utils.Direction;
import core.Utils.RouteSeeker;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Stack;

public class Enemy extends Character {
    private long routeTimeDelta;
    private long moveTimeDelta;
    private long lastRouteTime;
    private long lastMoveTime;

    private final Player player;
    private Stack<Direction> stack;

    public Enemy(int x, int y, Player player) {
        super(x, y);
        tetile = Tileset.ENEMY;
        stack = new Stack<>();
        this.player = player;

        lastMoveTime = System.currentTimeMillis();
        lastRouteTime = lastMoveTime;
    }

    public void operate(TETile[][] board){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastRouteTime > routeTimeDelta || stack.empty()){
            lastRouteTime = currentTime;
            route(board);
        }
        if(currentTime - lastMoveTime > moveTimeDelta){
            lastMoveTime = currentTime;
            move(board);
        }
    }

    public void setFrequency(long routeTimeDelta, long moveTimeDelta){
        this.routeTimeDelta = routeTimeDelta;
        this.moveTimeDelta = moveTimeDelta;
    }

    private void route(TETile[][] board){
        stack = RouteSeeker.findRoute(board, x, y, player.x, player.y);
    }

    private void move(TETile[][] board){
        switch(stack.pop()){
            case UP -> moveUp(board);
            case DOWN -> moveDown(board);
            case LEFT -> moveLeft(board);
            case RIGHT -> moveRight(board);
        }
    }
}
