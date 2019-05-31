package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.GameElement;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class Raycast extends GameBehavior {

    public boolean isHit(GameElement e, float min, float max, float width) {
        /**
         * calculates the middle of both game elements
         */
        int oX = this.getParent().getLocation().getX() + this.getParent().getSize().getX() / 2;
        int oY = this.getParent().getLocation().getY() + this.getParent().getSize().getY()  / 2;
        int eX = e.getLocation().getX() + (e.getSize().getX() /2 );
        int eY = e.getLocation().getY() + (e.getSize().getY() /2 );

        float rAngel = (float) Math.atan2(eY - oY, eX - oX);
        float Angel = (float) Math.toDegrees(rAngel) - (float) this.getParent().getRotation();


        if(Angel > min && Angel < max) {
            System.out.println(this.getParent().getName() + " " + Angel);
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
