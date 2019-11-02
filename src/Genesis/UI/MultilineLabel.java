package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class MultilineLabel extends UIElement {
    private Vector<String> lines;
    private int lineHeight;
    private int lineSpaceing;

    public MultilineLabel(String Name, Vector2 Location, int width)
    {
        this.lines = new Vector<String>();
        this.lineHeight = 25;
        this.lineSpaceing = 2;
        this.setName(Name);
        this.setEnabled(true);
        this.setBackgroundColor(null);
        this.setForegroundColor(new Color(255,255,255));
        this.setSize(new Vector2(width, 0));
        this.setLocation(Location);
    }

    public MultilineLabel(String Name, Vector2 Location, int width ,int lines)
    {
        this.lines = new Vector<String>();
        this.lineHeight = 25;
        this.lineSpaceing = 2;
        this.setName(Name);
        this.setEnabled(true);
        this.setBackgroundColor(null);
        this.setForegroundColor(new Color(255,255,255));
        this.setSize(new Vector2(width, 0));
        this.setLocation(Location);

        for(int i = 0; i < lines; i++)
        {
            this.lines.add("Empty line");
        }
        this.calculateHeight();
    }

    public void AddLine(String value) {
        this.lines.add(value);
        this.calculateHeight();
    }

    public void printLine(String value, int index) {
        this.lines.setElementAt(value, index);
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g);
        if(this.isEnabled())
        {
            BufferedImage buffer = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffer.createGraphics();
            g2d.setColor(this.getForegroundColor());
            int currentLine = 0;

            for(String line : this.lines) {
                g2d.drawString(line, 0, ((this.lineHeight * currentLine) + lineHeight));
                currentLine += 1;
            }
            g.drawImage(buffer, this.getLocation().getX(), this.getLocation().getY(), this.getSize().getX(), this.getSize().getY(), null);
        }
    }

    private void calculateHeight() {
        this.getSize().setY((lines.size() * lineHeight) + (lines.size() * lineSpaceing));
    }

    @Override
    public void OnUpdate() {
        super.OnUpdate(); //To change body of generated methods, choose Tools | Templates.
    }

    public Vector<String> getLines() {
        return lines;
    }

    public void setLines(Vector<String> lines) {
        this.lines = lines;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public int getLineSpaceing() {
        return lineSpaceing;
    }

    public void setLineSpaceing(int lineSpaceing) {
        this.lineSpaceing = lineSpaceing;
    }
}
