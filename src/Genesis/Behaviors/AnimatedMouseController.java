package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.Graphics.IAnimation;
import Genesis.Math.Vector2;
import Genesis.Toolkit;
import sun.security.util.AuthResources_it;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AnimatedMouseController extends GameBehavior {

    private int MouseKeybind = MouseEvent.BUTTON1;
    private IAnimation North;
    private IAnimation NorthEast;
    private IAnimation East;
    private IAnimation SouthEast;
    private IAnimation South;
    private IAnimation Southwest;
    private IAnimation West;
    private IAnimation NorthWest;
    private IAnimation Idle;
    private Vector2 Location;
    private boolean move;
    private int speed = 1;

    public AnimatedMouseController(IAnimation north, IAnimation northEast, IAnimation east, IAnimation southEast, IAnimation south, IAnimation southwest, IAnimation west, IAnimation northWest, IAnimation idle) {
        North = north;
        NorthEast = northEast;
        East = east;
        SouthEast = southEast;
        South = south;
        Southwest = southwest;
        West = west;
        NorthWest = northWest;
        idle = idle;
    }

    public AnimatedMouseController() {

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

        if(move && !Genesis.Toolkit.ContainsCoord(Location.getX(), ElementX, speed *2))
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

        if(move && !Genesis.Toolkit.ContainsCoord(Location.getY(), ElementY, speed *2))
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
            if(Genesis.Toolkit.ContainsCoord(Location.getX(), ElementX, 10) && ElementY > Location.getY())
            {
                // System.out.println("Direction: North");
                super.getParent().setSprite(North.getNextTimedFrame());
            }
            else if(Genesis.Toolkit.ContainsCoord(Location.getX(), ElementX, 10 ) && ElementY < Location.getY())
            {
                // System.out.println("Direction: South");
                super.getParent().setSprite(South.getNextTimedFrame());
            }
            else if(ElementX < Location.getX() && Genesis.Toolkit.ContainsCoord(Location.getY(), ElementY, 10 ))
            {
                // System.out.println("Direction: East");
                super.getParent().setSprite(South.getNextTimedFrame());
            }
            else if(ElementX > Location.getX() && Genesis.Toolkit.ContainsCoord(Location.getY(), ElementY, 10 ))
            {
                // System.out.println("Direction: West");
                super.getParent().setSprite(South.getNextTimedFrame());
            }
            else if((ElementX > Location.getX() && ElementY > Location.getY()) && ( !Genesis.Toolkit.ContainsCoord(Location.getX(), ElementX, 10) || !Genesis.Toolkit.ContainsCoord(Location.getY(), ElementY, 10)))
            {
                // System.out.println("Direction: Northwest");
                super.getParent().setSprite(South.getNextTimedFrame());
            }
            else if(ElementX < Location.getX() && ElementY > Location.getY())
            {
                // System.out.println("Direction: Northeast");
                super.getParent().setSprite(NorthEast.getNextTimedFrame());
            }
            else if(ElementX > Location.getX() && ElementY < Location.getY())
            {
                // System.out.println("Direction: Southwest");
                super.getParent().setSprite(Southwest.getNextTimedFrame());
            }
            else if(ElementX < Location.getX() && ElementY < Location.getY())
            {
                // System.out.println("Direction: Southeast");
                super.getParent().setSprite(SouthEast.getNextTimedFrame());
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

    public IAnimation getNorth() {
        return North;
    }

    public void setNorth(IAnimation north) {
        North = north;
    }

    public IAnimation getNorthEast() {
        return NorthEast;
    }

    public void setNorthEast(IAnimation northEast) {
        NorthEast = northEast;
    }

    public IAnimation getEast() {
        return East;
    }

    public void setEast(IAnimation east) {
        East = east;
    }

    public IAnimation getSouthEast() {
        return SouthEast;
    }

    public void setSouthEast(IAnimation southEast) {
        SouthEast = southEast;
    }

    public IAnimation getSouth() {
        return South;
    }

    public void setSouth(IAnimation south) {
        South = south;
    }

    public IAnimation getSouthwest() {
        return Southwest;
    }

    public void setSouthwest(IAnimation southwest) {
        Southwest = southwest;
    }

    public IAnimation getWest() {
        return West;
    }

    public void setWest(IAnimation west) {
        West = west;
    }

    public IAnimation getNorthWest() {
        return NorthWest;
    }

    public void setNorthWest(IAnimation northWest) {
        NorthWest = northWest;
    }

    public IAnimation getIdle() {
        return Idle;
    }

    public void setIdle(IAnimation idle) {
        Idle = idle;
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
