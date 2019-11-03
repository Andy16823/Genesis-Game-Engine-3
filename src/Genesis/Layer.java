package Genesis;

import Genesis.Graphics.RenderMode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Vector;

public class Layer {
    private String name;
    private Scene parentScene;
    private boolean active;
    private Vector<GameElement> elements;

    public Layer(String name) {
        this.elements = new Vector<GameElement>();
        this.name = name;
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vector<GameElement> getElements() {
        return elements;
    }

    public Scene getParentScene() {
        return parentScene;
    }

    public void setParentScene(Scene parentScene) {
        this.parentScene = parentScene;
    }

    public void addGameElement(GameElement element) {
        this.elements.add(element);
    }

    public void removeGameElement(GameElement element) {
        this.elements.remove(element);
    }

    public void removeGameElementByName(String name) {
        GameElement elementToRemove = null;
        for (GameElement e : this.elements) {
            if(e.getName().equals(name)) {
                elementToRemove = e;
            }
        }
        if(elementToRemove != null) {
            this.elements.remove(elementToRemove);
        }
    }

    public GameElement getGameElement(String name) {
        for (GameElement e : this.elements) {
            if(e.getName().equals(name)) {
                return  e;
            }
        }
        return  null;
    }

    public void OnUpdate() {
        for(GameElement e : this.elements) {
            e.BeforeUpdate();
            if(e.isEnabled()){
                e.OnUpdate();
            }
            e.AfterUpdate();
        }
    }

    public void RenderLayer(Graphics2D g2d) {
        for(GameElement e : this.elements) {
            if(e.getRender_mode() == RenderMode.DYNAMIC && e.isEnabled())
            {
                e.BevoreRender(g2d);
                AffineTransform oldTransform = g2d.getTransform();
                AffineTransform newtransform = new AffineTransform();
                newtransform.rotate(Math.toRadians(e.getRotation()), (e.getLocation().getX() + (e.getSize().getX() / 2)), (e.getLocation().getY() + (e.getSize().getY() / 2)));
                g2d.setTransform(newtransform);
                g2d.drawImage(e.getSprite(), e.getLocation().getX(), e.getLocation().getY(), e.getSize().getX(), e.getSize().getY(),null);
                g2d.setTransform(oldTransform);
                e.AfterRender(g2d);
            }
        }
    }

    public void RenderStaticElements(Graphics2D g2d) {
        for(GameElement e : this.elements) {
            if(e.isEnabled() && e.getRender_mode() == RenderMode.STATIC) {
                e.BevoreRender(g2d);
                g2d.drawImage(e.getSprite(), e.getLocation().getX(), e.getLocation().getY(), e.getSize().getX(), e.getSize().getY(), null);
                e.AfterRender(g2d);
            }
        }
    }

    public void TransformLayer(int x, int y) {
        for(GameElement e : this.elements) {
            if(!e.isIgnoreSceneTranform()) {
                e.getLocation().addX(x);
                e.getLocation().addY(y);
            }
        }
    }

    public void OnKeyDown(KeyEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnKeyDown(e);
            }
        }
    }

    public void OnKeyUp(KeyEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnKeyUp(e);
            }
        }
    }

    public void OnMouseClick(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseClick(e);
            }
        }
    }

    public void OnMouseDown(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseDown(e);
            }
        }
    }

    public void OnMouseUp(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseUp(e);
            }
        }
    }

    public void OnMouseEnter(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseEnter(e);
            }
        }
    }

    public void OnMouseLeave(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.OnMouseLeave(e);
            }
        }
    }



}
