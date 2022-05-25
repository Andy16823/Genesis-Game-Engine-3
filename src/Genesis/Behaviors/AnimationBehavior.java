package Genesis.Behaviors;

import Genesis.Game;
import Genesis.GameBehavior;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class AnimationBehavior extends GameBehavior {
    private BufferedImage OldSprite;
    private Vector<Animation> Animations;
    private int FrameWidth = 0;
    private int FrameHeight = 0;
    private boolean AutoPlay = false;
    private String AutoPlayAnimation;

    public AnimationBehavior() {
        this.Animations = new Vector<Animation>();
    }

    public AnimationBehavior(int FrameWidth, int FrameHeight) {
        this.FrameHeight = FrameHeight;
        this.FrameWidth = FrameWidth;
        this.Animations = new Vector<Animation>();
    }

    public AnimationBehavior(String AutoPlayAnimationName, int FrameWidth, int FrameHeight) {
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

    public void addAnimation(Animation animation) {
        this.Animations.add(animation);
    }

    public void playAnimation(String Name) {
        Animation animation = this.getAnimation(Name);

        if(animation != null) {
            int frameX = this.FrameWidth * (animation.getStartColumn() + animation.getCurrentFrame());
            int frameY = this.FrameHeight * animation.getStartRow();
            this.getParent().setSprite(animation.getAnimationSheet().getSubimage(frameX, frameY, this.FrameWidth, this.FrameHeight));
            animation.nextFrame();
        }
    }

    @Override
    public void onUpdate(Game game) {
        super.onUpdate(game);

        if(AutoPlay) {
            this.playAnimation(this.AutoPlayAnimation);
        }
    }

    @Override
    public void onInit() {
        super.onInit();
        this.OldSprite = this.getParent().getSprite();
        if(this.FrameHeight == 0 && this.FrameWidth == 0) {
            this.FrameHeight = this.getParent().getSize().getX();
            this.FrameWidth = this.getParent().getSize().getY();
        }
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
