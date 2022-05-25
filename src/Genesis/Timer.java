/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy
 */
public class Timer extends Thread{
    private long maxFps;
    private Game game;
    private long timestamp;
    private int fps;
    private int stableFps;
    private long lastFrame;
    private long deltaTime;
    private long currentFrame;
    private long frameTime;

    public Timer(long maxFps, Game parent) {
        this.maxFps = maxFps;
        this.game = parent;
        this.timestamp = 0;
        this.fps = 0;
        this.stableFps = 0;
    }
    
    @Override
    public void run() {
        this.timestamp = System.currentTimeMillis() / 1000;
        while(!game.isbQuit())
        {
            this.frameTime = 1000 / maxFps;
            this.currentFrame = System.currentTimeMillis();
            long currentTime = currentFrame / 1000;

            if(this.currentFrame > this.lastFrame + this.frameTime) {
                if(currentTime >= timestamp + 1)
                {
                    this.stableFps = fps;
                    this.fps = 0;
                    this.timestamp = System.currentTimeMillis() / 1000;
                }
                else {
                    this.fps += 1;
                }

                this.game.UpdateGame();
                this.game.repaint();
                this.deltaTime = currentFrame - lastFrame;
                this.lastFrame = currentFrame;
            }
        }
    }

    public int getFps() {
        return stableFps;
    }

    public long getDeltaTime() {
        return deltaTime;
    }
}
