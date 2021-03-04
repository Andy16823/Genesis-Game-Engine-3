package Genesis.Behaviors;

import Genesis.GameBehavior;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MovementBehavior extends GameBehavior {
    private BufferedImage TextureUp;
    private BufferedImage TextureDown;
    private BufferedImage TextureLeft;
    private BufferedImage TextureRight;
    private int KeybindUp = KeyEvent.VK_UP;
    private int KeybindDown = KeyEvent.VK_DOWN;
    private int KeybindLeft = KeyEvent.VK_LEFT;
    private int KeybindRight = KeyEvent.VK_RIGHT;
    private int MovementSpeed = 10;

    public MovementBehavior(BufferedImage textureUp, BufferedImage textureDown, BufferedImage textureLeft, BufferedImage textureRight) {
        TextureUp = textureUp;
        TextureDown = textureDown;
        TextureLeft = textureLeft;
        TextureRight = textureRight;
    }

    public BufferedImage getTextureUp() {
        return TextureUp;
    }

    public void setTextureUp(BufferedImage textureUp) {
        TextureUp = textureUp;
    }

    public BufferedImage getTextureDown() {
        return TextureDown;
    }

    public void setTextureDown(BufferedImage textureDown) {
        TextureDown = textureDown;
    }

    public BufferedImage getTextureLeft() {
        return TextureLeft;
    }

    public void setTextureLeft(BufferedImage textureLeft) {
        TextureLeft = textureLeft;
    }

    public BufferedImage getTextureRight() {
        return TextureRight;
    }

    public void setTextureRight(BufferedImage textureRight) {
        TextureRight = textureRight;
    }

    public int getKeybindUp() {
        return KeybindUp;
    }

    public void setKeybindUp(int keybindUp) {
        KeybindUp = keybindUp;
    }

    public int getKeybindDown() {
        return KeybindDown;
    }

    public void setKeybindDown(int keybindDown) {
        KeybindDown = keybindDown;
    }

    public int getKeybindLeft() {
        return KeybindLeft;
    }

    public void setKeybindLeft(int keybindLeft) {
        KeybindLeft = keybindLeft;
    }

    public int getKeybindRight() {
        return KeybindRight;
    }

    public void setKeybindRight(int keybindRight) {
        KeybindRight = keybindRight;
    }

    public int getMovementSpeed() {
        return MovementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        MovementSpeed = movementSpeed;
    }

    @Override
    public void onKeyDown(KeyEvent e) {
        super.onKeyDown(e);
        if(e.getKeyCode() == KeybindUp)
        {
            super.getParent().setSprite(this.TextureUp);
            super.getParent().getLocation().addY(-MovementSpeed);
        }
        else if(e.getKeyCode() == KeybindDown)
        {
            super.getParent().setSprite(this.TextureDown);
            super.getParent().getLocation().addY(MovementSpeed);
        }
        else if(e.getKeyCode() == KeybindLeft)
        {
            super.getParent().setSprite(this.TextureLeft);
            super.getParent().getLocation().addX(-MovementSpeed);
        }
        else if(e.getKeyCode() == KeybindRight)
        {
            super.getParent().setSprite(this.TextureRight);
            super.getParent().getLocation().addX(MovementSpeed);
        }
    }
}
