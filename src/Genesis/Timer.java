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
    private long sleep;
    private Game game;
    private long timestamp;
    private int fps;
    private int stableFps;
    private long lastFrame;
    private long deltaTime;
    
    public Timer(long SleepTime, Game parent) {
        this.sleep = SleepTime;
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
            long currentTime = System.currentTimeMillis() / 1000;
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
            try {
                this.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
            long currentTimeMillis = System.currentTimeMillis();
            this.deltaTime = currentTimeMillis - lastFrame;
            this.lastFrame = currentTimeMillis;
        }
    }

    public int getFps() {
        return stableFps;
    }

    public long getDeltaTime() {
        return deltaTime;
    }
}
