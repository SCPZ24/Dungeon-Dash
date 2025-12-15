package core.Utils;

public enum Direction {
    UP(0,1),
    DOWN(0,-1),
    LEFT(-1,0),
    RIGHT(1,0);

    public final int dirX;
    public final int dirY;

    Direction(int x, int y){
        dirX = x;
        dirY = y;
    }
}
