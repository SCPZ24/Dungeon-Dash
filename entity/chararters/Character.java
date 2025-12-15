package entity.chararters;

import tileengine.TETile;
import tileengine.Tileset;

public class Character {
    protected TETile tetile;
    protected TETile lastStep;
    int x;
    int y;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        lastStep = Tileset.FLOOR;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void moveUp(TETile[][] board) {
        board[x][y] = lastStep;
        ++y;
        lastStep = board[x][y];
        board[x][y] = tetile;
    }
    public void moveDown(TETile[][] board) {
        board[x][y] = lastStep;
        --y;
        lastStep = board[x][y];
        board[x][y] = tetile;
    }
    public void moveLeft(TETile[][] board) {
        board[x][y] = lastStep;
        --x;
        lastStep = board[x][y];
        board[x][y] = tetile;
    }
    public void moveRight(TETile[][] board) {
        board[x][y] = lastStep;
        ++x;
        lastStep = board[x][y];
        board[x][y] = tetile;
    }
}
