package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.util.Vector;

public abstract class DropDownMenuElement {
    private String name;
    private String text;
    private DropDownMenu parent;
    private Font font;
    private Vector<DropDownMenuItemActionListener> actionListeners;

    public DropDownMenuElement(String name, String text) {
        this.name = name;
        this.text = text;
        this.font = new Font("Arial", Font.PLAIN, 15);
        this.actionListeners = new Vector<DropDownMenuItemActionListener>();
    }

    public void Render(Graphics2D g, Vector2 location) {

    }

    public void addActionListener(DropDownMenuItemActionListener al) {
        this.actionListeners.add(al);
    }

    public Vector<DropDownMenuItemActionListener> getActionListeners() {
        return actionListeners;
    }

    public void activate(){}

    public void select(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DropDownMenu getParent() {
        return parent;
    }

    public void setParent(DropDownMenu parent) {
        this.parent = parent;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
