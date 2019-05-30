package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.Math.Vector2;
import Genesis.Toolkit;

import javax.tools.Tool;
import javax.xml.bind.Element;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MouseController extends GameBehavior {
    private int MouseKeybind = MouseEvent.BUTTON1;
    private BufferedImage TextureNorth;
    private BufferedImage TextureNorthEast;
    private BufferedImage TextureEast;
    private BufferedImage TextureSouthEast;
    private BufferedImage TextureSouth;
    private BufferedImage TextureSouthwest;
    private BufferedImage TextureWest;
    private BufferedImage TextureNorthWest;
    private Vector2 Location;
    private boolean move;
    private int speed = 1;

    public MouseController(BufferedImage texture) {
        TextureNorth = texture;
        TextureNorthEast = texture;
        TextureEast = texture;
        TextureSouthEast = texture;
        TextureSouth = texture;
        TextureSouthwest = texture;
        TextureWest = texture;
        TextureNorthWest = texture;
    }

    public MouseController(BufferedImage textureNorth, BufferedImage textureNorthEast, BufferedImage textureEast, BufferedImage textureSouthEast, BufferedImage textureSouth, BufferedImage textureSouthwest, BufferedImage textureWest, BufferedImage textureNorthWest) {
        TextureNorth = textureNorth;
        TextureNorthEast = textureNorthEast;
        TextureEast = textureEast;
        TextureSouthEast = textureSouthEast;
        TextureSouth = textureSouth;
        TextureSouthwest = textureSouthwest;
        TextureWest = textureWest;
        TextureNorthWest = textureNorthWest;
    }

    @Override
    public void BEVORE_RENDER(Graphics g) {
        super.BEVORE_RENDER(g);
    }

    @Override
    public void AFTER_RENDER(Graphics g) {
        super.AFTER_RENDER(g);
    }

    @Override
    public void ON_KEY_DOWN(KeyEvent e) {
        super.ON_KEY_DOWN(e);

    }

    @Override
    public void ON_KEY_RELEASE(KeyEvent e) {
        super.ON_KEY_RELEASE(e);
    }

    @Override
    public void ON_MOUSE_CLICK(MouseEvent e) {
        super.ON_MOUSE_CLICK(e);
    }

    @Override
    public void ON_MOUSE_DOWN(MouseEvent e) {
        super.ON_MOUSE_DOWN(e);
        if(e.getButton() == this.MouseKeybind) {
            this.move = true;
            this.Location = new Vector2(e.getX(), e.getY());
            System.out.println("Mouse Controller: Move: " + move + " Location (X) " + Location.getX() + " (Y) " + Location.getY());
        }
    }

    @Override
    public void ON_MOUSE_RELEASE(MouseEvent e) {
        super.ON_MOUSE_RELEASE(e);
    }

    @Override
    public void ON_MOUSE_ENTER(MouseEvent e) {
        super.ON_MOUSE_ENTER(e);
    }

    @Override
    public void ON_MOUSE_LEAVE(MouseEvent e) {
        super.ON_MOUSE_LEAVE(e);
    }

    @Override
    public void ON_UPDATE() {
        super.ON_UPDATE();

        int ElementX = (super.getParent().getLocation().getX() + (super.getParent().getSize().getX() / 2));
        int ElementY = (super.getParent().getLocation().getY() + (super.getParent().getSize().getY() / 2));
        Vector2 ElementPos = new Vector2(ElementX, ElementY);

        if(move && !Toolkit.ContainsCoord(Location.getX(), ElementX, speed *2))
        {
            if(ElementPos.getX() > (Location.getX() - (speed / 2)))
            {
                super.getParent().getLocation().addX(-speed);
            }
            if(ElementPos.getX() < Location.getX() + (speed / 2))
            {
                super.getParent().getLocation().addX(speed);
            }
        }

        if(move && !Toolkit.ContainsCoord(Location.getY(), ElementY, speed *2))
        {
            if(ElementPos.getY() > Location.getY())
            {
                super.getParent().getLocation().addY(-speed);
            }
            if(ElementPos.getY() < Location.getY())
            {
                super.getParent().getLocation().addY(speed);
            }
        }


        if(Location != null)
        {
            if(Toolkit.ContainsCoord(Location.getX(), ElementX, 10) && ElementY > Location.getY())
            {
                // System.out.println("Direction: North");
                super.getParent().setSprite(TextureNorth);
            }
            else if(Toolkit.ContainsCoord(Location.getX(), ElementX, 10 ) && ElementY < Location.getY())
            {
                // System.out.println("Direction: South");
                super.getParent().setSprite(TextureSouth);
            }
            else if(ElementX < Location.getX() && Toolkit.ContainsCoord(Location.getY(), ElementY, 10 ))
            {
                // System.out.println("Direction: East");
                super.getParent().setSprite(TextureEast);
            }
            else if(ElementX > Location.getX() && Toolkit.ContainsCoord(Location.getY(), ElementY, 10 ))
            {
                // System.out.println("Direction: West");
                super.getParent().setSprite(TextureWest);
            }
            else if((ElementX > Location.getX() && ElementY > Location.getY()) && ( !Toolkit.ContainsCoord(Location.getX(), ElementX, 10) || !Toolkit.ContainsCoord(Location.getY(), ElementY, 10)))
            {
                // System.out.println("Direction: Northwest");
                super.getParent().setSprite(TextureNorthWest);
            }
            else if(ElementX < Location.getX() && ElementY > Location.getY())
            {
                // System.out.println("Direction: Northeast");
                super.getParent().setSprite(TextureNorthEast);
            }
            else if(ElementX > Location.getX() && ElementY < Location.getY())
            {
                // System.out.println("Direction: Southwest");
                super.getParent().setSprite(TextureSouthwest);
            }
            else if(ElementX < Location.getX() && ElementY < Location.getY())
            {
                // System.out.println("Direction: Southeast");
                super.getParent().setSprite(TextureSouthEast);
            }
            else if(Toolkit.Contains2(Location, ElementPos, new Vector2(speed , 10 )))
            {
                System.out.println("None");
            }
        }

    }

    public int getMouseKeybind() {
        return MouseKeybind;
    }

    public void setMouseKeybind(int mouseKeybind) {
        MouseKeybind = mouseKeybind;
    }

    public BufferedImage getTextureNorth() {
        return TextureNorth;
    }

    public void setTextureNorth(BufferedImage textureNorth) {
        TextureNorth = textureNorth;
    }

    public BufferedImage getTextureNorthEast() {
        return TextureNorthEast;
    }

    public void setTextureNorthEast(BufferedImage textureNorthEast) {
        TextureNorthEast = textureNorthEast;
    }

    public BufferedImage getTextureEast() {
        return TextureEast;
    }

    public void setTextureEast(BufferedImage textureEast) {
        TextureEast = textureEast;
    }

    public BufferedImage getTextureSouthEast() {
        return TextureSouthEast;
    }

    public void setTextureSouthEast(BufferedImage textureSouthEast) {
        TextureSouthEast = textureSouthEast;
    }

    public BufferedImage getTextureSouth() {
        return TextureSouth;
    }

    public void setTextureSouth(BufferedImage textureSouth) {
        TextureSouth = textureSouth;
    }

    public BufferedImage getTextureSouthwest() {
        return TextureSouthwest;
    }

    public void setTextureSouthwest(BufferedImage textureSouthwest) {
        TextureSouthwest = textureSouthwest;
    }

    public BufferedImage getTextureWest() {
        return TextureWest;
    }

    public void setTextureWest(BufferedImage textureWest) {
        TextureWest = textureWest;
    }

    public BufferedImage getTextureNorthWest() {
        return TextureNorthWest;
    }

    public void setTextureNorthWest(BufferedImage textureNorthWest) {
        TextureNorthWest = textureNorthWest;
    }

    public Vector2 getLocation() {
        return Location;
    }

    public void setLocation(Vector2 location) {
        Location = location;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
