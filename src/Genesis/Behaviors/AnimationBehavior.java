package Genesis.Behaviors;

import Genesis.GameBehavior;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EmptyStackException;
import java.util.Vector;

public class AnimationBehavior extends GameBehavior {
    private BufferedImage AnimationSheet;
    private BufferedImage OldSprite;
    private Vector<Animation> Animations;
    private int FrameWidth;
    private int FrameHeight;
    private boolean AutoPlay = false;
    private String AutoPlayAnimation;

    public AnimationBehavior(BufferedImage AnimationSheet, int FrameWidth, int FrameHeight) {
        this.AnimationSheet = AnimationSheet;
        this.FrameHeight = FrameHeight;
        this.FrameWidth = FrameWidth;
        this.Animations = new Vector<Animation>();
    }

    public AnimationBehavior(BufferedImage AnimationSheet, String AutoPlayAnimationName, int FrameWidth, int FrameHeight) {
        this.AnimationSheet = AnimationSheet;
        this.FrameHeight = FrameHeight;
        this.FrameWidth = FrameWidth;
        this.Animations = new Vector<Animation>();
        this.AutoPlay = true;
        this.AutoPlayAnimation = AutoPlayAnimationName;
    }

    public Animation getAnimation(String name) {
        for(Animation animation : this.Animations) {
            if(animation.getName().equals(name))
            {
                return animation;
            }
        }
        return null;
    }

    public void AddAnimation(Animation animation) {
        this.Animations.add(animation);
    }

    public void PlayAnimation(String Name) {
        Animation animation = this.getAnimation(Name);

        if(animation != null) {
            int frameX = this.FrameWidth * (animation.getStartColumn() + animation.getCurrentFrame());
            int frameY = this.FrameHeight * animation.getStartRow();
            this.getParent().setSprite(this.AnimationSheet.getSubimage(frameX, frameY, this.FrameWidth, this.FrameHeight));
            animation.nextFrame();
        }
    }

    @Override
    public void ON_UPDATE() {
        super.ON_UPDATE();

        if(AutoPlay) {
            this.PlayAnimation(this.AutoPlayAnimation);
        }
    }

    @Override
    public void ON_INIT() {
        super.ON_INIT();
        this.OldSprite = this.getParent().getSprite();
    }

    public BufferedImage getAnimationSheet() {
        return AnimationSheet;
    }

    public void setAnimationSheet(BufferedImage animationSheet) {
        AnimationSheet = animationSheet;
    }

    public Vector<Animation> getAnimations() {
        return Animations;
    }

    public void setAnimations(Vector<Animation> animations) {
        Animations = animations;
    }

    public int getFrameWidth() {
        return FrameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        FrameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return FrameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        FrameHeight = frameHeight;
    }

    public boolean isAutoPlay() {
        return AutoPlay;
    }

    public void setAutoPlay(boolean autoPlay) {
        AutoPlay = autoPlay;
    }

    public String getAutoPlayAnimation() {
        return AutoPlayAnimation;
    }

    public void setAutoPlayAnimation(String autoPlayAnimation) {
        AutoPlayAnimation = autoPlayAnimation;
    }

    public void resetElement()
    {
        this.getParent().setSprite(this.OldSprite);
    }

}
