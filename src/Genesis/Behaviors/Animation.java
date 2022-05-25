package Genesis.Behaviors;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private String Name;
    private int StartColumn;
    private int StartRow;
    private int Frames;
    private int currentFrame;
    private int FrameLength;
    private long lastFrameTime;
    private BufferedImage animationSheet;


    public Animation(String name, int startColumn, int startRow, int frames, BufferedImage animationSheet) {
        Name = name;
        StartColumn = startColumn;
        StartRow = startRow;
        Frames = frames;
        FrameLength = 100;
        this.animationSheet = animationSheet;
    }

    public void nextFrame() {
        long now = System.currentTimeMillis();

        if(now >= (lastFrameTime + FrameLength)){
            this.currentFrame = currentFrame + 1;
            if(this.currentFrame >= Frames) {
                this.currentFrame = 0;
            }
            this.lastFrameTime = now;
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStartColumn() {
        return StartColumn;
    }

    public void setStartColumn(int startColumn) {
        StartColumn = startColumn;
    }

    public int getStartRow() {
        return StartRow;
    }

    public void setStartRow(int startRow) {
        StartRow = startRow;
    }

    public int getFrames() {
        return Frames;
    }

    public void setFrames(int frames) {
        Frames = frames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getFrameLength() {
        return FrameLength;
    }

    public void setFrameLength(int frameLength) {
        FrameLength = frameLength;
    }

    public long getLastFrameTime() {
        return lastFrameTime;
    }

    public void setLastFrameTime(long lastFrameTime) {
        this.lastFrameTime = lastFrameTime;
    }

    public BufferedImage getAnimationSheet() {
        return animationSheet;
    }

    public void setAnimationSheet(BufferedImage animationSheet) {
        this.animationSheet = animationSheet;
    }
}
