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

    public void onUpdate(Game game) {
        for(GameElement e : this.elements) {
            e.beforeUpdate();
            if(e.isEnabled()){
                e.onUpdate(game);
            }
            e.afterUpdate();
        }
    }

    public void RenderLayer(Graphics2D g2d) {
        for(GameElement e : this.elements) {
            if(e.getRender_mode() == RenderMode.DYNAMIC && e.isEnabled())
            {
                e.renderGameElement(g2d);
            }
        }
    }

    public void RenderStaticElements(Graphics2D g2d) {
        for(GameElement e : this.elements) {
            if(e.isEnabled() && e.getRender_mode() == RenderMode.STATIC) {
                e.renderGameElement(g2d);
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
                element.onKeyDown(e);
            }
        }
    }

    public void OnKeyUp(KeyEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onKeyUp(e);
            }
        }
    }

    public void OnMouseClick(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseClick(e);
            }
        }
    }

    public void OnMouseDown(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseDown(e);
            }
        }
    }

    public void OnMouseUp(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseUp(e);
            }
        }
    }

    public void OnMouseEnter(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseEnter(e);
            }
        }
    }

    public void OnMouseLeave(MouseEvent e) {
        for(GameElement element : this.elements)
        {
            if(element.isEnabled())
            {
                element.onMouseLeave(e);
            }
        }
    }

    public void setElements(Vector<GameElement> elements) {
        this.elements = elements;
    }

    public GameElement getElementAt(int x, int y) {
        for(GameElement element : this.elements) {
            if(element.contains(x, y)){
                return  element;
            }
        }
        return  null;
    }

}
