package core;

import utils.StdDraw;

import java.awt.*;

public class Main {
    public static Font pauseFont = new Font("Monaco", Font.BOLD, 30);
    public static Font gameFont = new Font("Monaco", Font.BOLD, 12);

    public static void main(String[] args) throws InterruptedException {
        World world = new World();

        while(!world.isGameOver()){
            while(world.isGamePaused()){
                StdDraw.setFont(pauseFont);
                if(StdDraw.hasNextKeyTyped()){
                    char key = StdDraw.nextKeyTyped();
                    if(key == ' '){
                        world.nextLevel();
                    }
                }
                world.showText();
                Thread.sleep(16);
            }
            while(!world.isGamePaused()){
                StdDraw.setFont(gameFont);
                if(StdDraw.hasNextKeyTyped()){
                    char key = StdDraw.nextKeyTyped();
                    world.handle(key);
                }
                world.render();
                world.update();
                Thread.sleep(16);
            }
        }
        world.showGameOver();
    }
}
