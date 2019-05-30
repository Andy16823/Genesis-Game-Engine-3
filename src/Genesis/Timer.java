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
    
    public Timer(long SleepTime, Game parent) {
        this.sleep = SleepTime;
        this.game = parent;
    }
    
    @Override
    public void run() {
        while(!game.isbQuit())
        {
            this.game.UpdateGame();
            this.game.repaint();
            try {
                this.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
