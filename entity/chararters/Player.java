package entity.chararters;

import tileengine.TETile;
import tileengine.Tileset;

public class Player extends Character {
    public Player(int x, int y) {
        super(x, y);
        tetile = Tileset.AVATAR;
    }

    @Override
    public void moveUp(TETile[][] board){
        if(board[x][y+1] == Tileset.FLOOR || board[x][y+1] == Tileset.UNLOCKED_DOOR){
            super.moveUp(board);
        }
    }
    @Override
    public void moveDown(TETile[][] board){
        if(board[x][y-1] == Tileset.FLOOR || board[x][y-1] == Tileset.UNLOCKED_DOOR){
            super.moveDown(board);
        }
    }
    @Override
    public void moveLeft(TETile[][] board){
        if(board[x-1][y] == Tileset.FLOOR || board[x-1][y] == Tileset.UNLOCKED_DOOR){
            super.moveLeft(board);
        }
    }
    @Override
    public void moveRight(TETile[][] board){
        if(board[x+1][y] == Tileset.FLOOR || board[x+1][y] == Tileset.UNLOCKED_DOOR){
            super.moveRight(board);
        }
    }
}
