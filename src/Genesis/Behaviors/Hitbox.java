package Genesis.Behaviors;

import Genesis.GameBehavior;
import Genesis.GameElement;
import Genesis.Math.Vector2;
import Genesis.Toolkit;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Hitbox extends GameBehavior {
    private Vector2 size;
    private Vector2 location;
    private Color color = Color.GREEN;
    private boolean debugmode = false;

    public Hitbox(Vector2 size) {
        this.size = size;
        this.location = new Vector2(0,0);
    }

    public Hitbox(Vector2 size, boolean debugmode) {
        this.size = size;
        this.debugmode = debugmode;
        this.location = new Vector2(0,0);
    }

    @Override
    public void BEVORE_RENDER(Graphics g) {
        super.BEVORE_RENDER(g);
    }

    @Override
    public void AFTER_RENDER(Graphics g) {
        super.AFTER_RENDER(g);
        if(debugmode)
        {
            Graphics2D g2d = (Graphics2D) g;
            Color oldcolor = g2d.getColor();
            g2d.setColor(this.color);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(this.location.getX(), this.location.getY(), this.size.getX(), this.size.getY());
            g2d.setColor(oldcolor);
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
        // calculate the hitbox position
        GameElement parent = this.getParent();
        int px = parent.getLocation().getX() + (parent.getSize().getX() / 2) - (this.size.getX() / 2);
        int py = parent.getLocation().getY() + (parent.getSize().getY() / 2) - (this.size.getY() / 2);
        this.location.set(px,py);
    }

    public boolean isHit(GameElement ref) {
        if(Toolkit.Contains(location, new Vector2(ref.getLocation().getX(), ref.getLocation().getY()), size))
        {
            return true;
        }
        else if(Toolkit.Contains(location, new Vector2(ref.getLocation().getX() + ref.getSize().getX(), ref.getLocation().getY()), size))
        {
            return  true;
        }
        else if(Toolkit.Contains(location, new Vector2(ref.getLocation().getX() + ref.getSize().getX(), ref.getLocation().getY() + ref.getSize().getY()), size))
        {
            return  true;
        }
        else if(Toolkit.Contains(location, new Vector2(ref.getLocation().getX(), ref.getLocation().getY() + ref.getSize().getY()), size))
        {
            return  true;
        }
        return  false;
    }
}
