package Genesis.Graphics;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class IAnimation {
    private Vector<BufferedImage> Keyframes;
    private String Name;
    private  int CurrentKeyframe = 0;
    private  int CurrentFrezze =0;
    private  int Freeze = 2;

    public IAnimation(String name) {
        Name = name;
        Keyframes = new Vector<BufferedImage>();
    }

    public void setKeyframes(Vector<BufferedImage> keyframes) {
        Keyframes = keyframes;
    }

    public void  addKeyframe(BufferedImage Image)
    {
        this.Keyframes.add(Image);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCurrentKeyframe() {
        return CurrentKeyframe;
    }

    public void setCurrentKeyframe(int currentKeyframe) {
        CurrentKeyframe = currentKeyframe;
    }

    public int getFreeze() {
        return Freeze;
    }

    public void setFreeze(int freeze) {
        Freeze = freeze;
    }

    public  int getKeyframes() {
        return Keyframes.size();
    }

    public BufferedImage getNextFrame() {
        CurrentKeyframe += 1;
        if(this.CurrentKeyframe == Keyframes.size() - 1)
        {
            CurrentKeyframe = 0;
        }
        return Keyframes.get(CurrentKeyframe);
    }

    public  BufferedImage getCurrentFrame() {
        return  Keyframes.get(CurrentKeyframe);
    }

    public BufferedImage getNextTimedFrame()
    {
        CurrentFrezze += 1;
        if(CurrentFrezze == Freeze)
        {
            CurrentFrezze = 0;
            return getNextFrame();
        }
        else
        {
            return getCurrentFrame();
        }
    }

    public  void  play() {

    }
}
