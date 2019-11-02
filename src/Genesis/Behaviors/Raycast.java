package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.GameElement;
import Genesis.Math.Vector2;
import Genesis.Toolkit;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.rmi.MarshalException;
import java.util.Vector;
import java.util.function.ToLongBiFunction;

public class Raycast extends GameBehavior {
    private boolean debug_mode = false;
    private float sX;
    private float sY;
    private float dX;
    private float dY;


    public boolean isHit(GameElement e, float min, float max, float width) {

        float hitXmin = min;
        float hitXmax = 360 - max;
        float hitYmin = 180 - min;
        float hitYmax = 180 + max;

        /**
         * calculates the middle of both game elements
         */
        int oX = this.getParent().getLocation().getX() + this.getParent().getSize().getX();
        int oY = this.getParent().getLocation().getY() + this.getParent().getSize().getY()  / 2;
        int eX = e.getLocation().getX() + (e.getSize().getX() /2 );
        int eY = e.getLocation().getY() + (e.getSize().getY() /2 );

        float rAngel = (float) Math.atan2(eY - oY, eX - oX);
        float Angel = (float) Math.toDegrees(rAngel) + (float) this.getParent().getRotation();

        if(Angel < 0)
        {
            Angel += 360;
        }

        if(debug_mode)
        {
            System.out.println("Raycast from " + this.getParent().getName() + " Angel to destination ('" + e.getName() + "') : " + Angel);
        }

        if((Angel < min && Angel > 0) || Angel > max) {

            float xDist = (this.getParent().getLocation().getX() + this.getParent().getSize().getX() / 2) - (e.getLocation().getX() + e.getSize().getX() /2);
            float yDist = (this.getParent().getLocation().getY() + this.getParent().getSize().getY() / 2) - (e.getLocation().getY() + e.getSize().getY() /2);
            float dist = (float) Math.sqrt((xDist * xDist) + (yDist * yDist));

            if(dist < width)
            {
                return true;
            }
        }
        return  false;
    }

    public boolean sendRay(GameElement e, float width) {
        int xR = this.getParent().getCenterLocation().getX();
        int yR = this.getParent().getCenterLocation().getY();


        sX = this.getParent().getCenterLocation().getX();
        sY = this.getParent().getCenterLocation().getY();
        dX = (float) (sX + (width * Math.cos(Math.toRadians(this.getParent().getRotation()))));
        dY = (float) (sY + (width * Math.sin(Math.toRadians(this.getParent().getRotation()))));

        for(int i = 0; i < width; i++)
        {
            xR = (int) (this.getParent().getCenterLocation().getX() +  (i * Math.cos(Math.toRadians(this.getParent().getRotation()))));
            yR = (int) (this.getParent().getCenterLocation().getY() +  (i * Math.sin(Math.toRadians(this.getParent().getRotation()))));

            if(e.isEnabled() && Toolkit.Contains(e.getLocation(), new Vector2(xR, yR), e.getSize()))
            {
                return  true;
            }

        }
        return  false;
    }

    public boolean sendRay(GameElement e, float width, float radius) {
        int xR = this.getParent().getCenterLocation().getX();
        int yR = this.getParent().getCenterLocation().getY();


        sX = this.getParent().getCenterLocation().getX();
        sY = this.getParent().getCenterLocation().getY();
        dX = (float) (sX + (width * Math.cos(Math.toRadians(this.getParent().getRotation()))));
        dY = (float) (sY + (width * Math.sin(Math.toRadians(this.getParent().getRotation()))));

        for(int i = 0; i < width; i++)
        {
            xR = (int) (this.getParent().getCenterLocation().getX() +  (i * Math.cos(Math.toRadians(radius))));
            yR = (int) (this.getParent().getCenterLocation().getY() +  (i * Math.sin(Math.toRadians(radius))));

            if(e.isEnabled() && Toolkit.Contains(e.getLocation(), new Vector2(xR, yR), e.getSize()))
            {
                return  true;
            }

        }
        return  false;
    }

    public boolean sendCone(GameElement e, float angle, float width) {

        int xR;
        int yR;
        float startAngle = (float) (this.getParent().getRotation() - (angle / 2));
        float endAngle = (float) (this.getParent().getRotation() - (angle /2));
        int aStart = (int) (this.getParent().getRotation() - (angle / 2));
        int aEnd = (int) (this.getParent().getRotation() + (angle /2));
        System.out.println("Start X " + aStart + " End A : " + aEnd);

        sX = this.getParent().getCenterLocation().getX();
        sY = this.getParent().getCenterLocation().getY();
        dX = (float) (sX + (width * Math.cos(Math.toRadians(this.getParent().getRotation()))));
        dY = (float) (sY + (width * Math.sin(Math.toRadians(this.getParent().getRotation()))));

        for(int a = aStart; a < aEnd; a++ )
        {
            if(this.sendRay(e, width, a))
            {
                return  true;
            }
        }
        return  false;
    }

    public Vector<GameElement> getHitetElements(Vector<GameElement> elements, float min, float max, float width)
    {
        Vector<GameElement> result = new Vector<GameElement>();
        for(GameElement e : elements)
        {
            if(this.isHit(e, min, max, width) && !e.getName().equals(this.getParent().getName()))
            {
                result.add(e);
            }
        }
        return  result;
    }

    public boolean isDebug_mode() {
        return debug_mode;
    }

    public void setDebug_mode(boolean debug_mode) {
        this.debug_mode = debug_mode;
    }

    @Override
    public void BEVORE_RENDER(Graphics g) {
        super.BEVORE_RENDER(g);
    }

    @Override
    public void AFTER_RENDER(Graphics g) {
        super.AFTER_RENDER(g);

        if(debug_mode) {
            g.drawLine((int) sX,(int)  sY,(int)  dX,(int)  dY);
        }

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
    }
}
