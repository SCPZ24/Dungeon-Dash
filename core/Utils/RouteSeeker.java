package core.Utils;

import entity.chararters.Player;
import entity.specialBlocks.LightenBlock;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.*;

import static core.Utils.Direction.*;

public class RouteSeeker {
    public static Direction[] directions = new Direction[]{UP,DOWN,LEFT,RIGHT};

    private static class Point{
        int x;
        int y;
        Point prev;

        Point(int x,int y,Point prev){
            this.x=x;
            this.y=y;
            this.prev=prev;
        }
    }

    public static Stack<Direction> findRoute(TETile[][] board, int startX, int startY, int endX, int endY) {
        if(startX == endX && startY == endY){
            return null;
        }

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0 ; i < board.length ; ++i){
            Arrays.fill(visited[i], false);
        }

        queue.offer(new Point(startX,startY,null));
        visited[startX][startY] = true;

        Point current;
        int newX, newY;
        while(!queue.isEmpty()){
            current = queue.poll();
            for(Direction dir : directions){
                newX = current.x + dir.dirX;
                newY = current.y + dir.dirY;
                if((board[newX][newY] != Tileset.FLOOR && board[newX][newY] != Tileset.UNLOCKED_DOOR && board[newX][newY] != Tileset.AVATAR) || visited[newX][newY]){
                    continue;
                }
                Point nextPoint = new Point(newX,newY,current);
                queue.offer(nextPoint);
                visited[newX][newY] = true;

                if(newX == endX && newY == endY){
                    return redirect(nextPoint);
                }
            }
        }
        return null;
    }

    private static Stack<Direction> redirect(Point current){
        Stack<Direction> stack = new Stack<>();
        Point pre;
        int dirX, dirY;
        while(current.prev != null){
            pre = current.prev;
            dirX = current.x - pre.x;
            dirY = current.y - pre.y;
            if(dirX == 1){
                stack.push(RIGHT);
            }
            if(dirX == -1){
                stack.push(LEFT);
            }
            if(dirY == 1){
                stack.push(UP);
            }
            if(dirY == -1){
                stack.push(DOWN);
            }

            current = pre;
        }
        return stack;
    }

    public static void seekLightenBlock(List<LightenBlock> lightenBlocks, int distance, int startX, int startY, boolean[][] isVisited, TETile[][] board){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, distance});
        isVisited[startX][startY] = true;
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int x,y,dist;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            x = current[0];
            y = current[1];
            dist = current[2];
            lightenBlocks.add(new LightenBlock(x,y));
            if(dist <= 0) continue;
            for(int[] dir : directions){
                int newX = x + dir[0], newY = y + dir[1];
                if(newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length) {
                    continue;
                }
                if(isVisited[newX][newY]) {
                    continue;
                }
                isVisited[newX][newY] = true;
                if(board[newX][newY] != Tileset.WALL){
                    queue.add(new int[]{newX, newY, dist-1});
                }else{
                    lightenBlocks.add(new LightenBlock(newX, newY));
                }
            }
        }
    }

    private static int ManhattanDistance(int x, int y, Player player) {
        return Math.abs(x-player.getX())+Math.abs(y-player.getY());
    }
}
