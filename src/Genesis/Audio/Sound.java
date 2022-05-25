package Genesis.Audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private String name;
    private String fileName;
    private Clip clip;

    public Sound(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
        try {
            this.clip = AudioSystem.getClip();
            InputStream is = getClass().getResourceAsStream(fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        if(this.clip != null) {
            if(clip.getMicrosecondPosition() > 0) {
                clip.setMicrosecondPosition(0);
            }
            clip.start();
        }
    }

    public void stop() {
        if(this.clip != null) {
            clip.stop();
        }
    }

    public void dispose() {
        this.clip.flush();
    }

}
