package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class MultilineLabel extends UIElement {
    private Vector<String> lines;
    private int lineHeight;
    private int lineSpaceing;
    private TextAlign textAlign = TextAlign.LEFT;

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
    public void onRender(Graphics g) {
        super.onRender(g);
        if(this.isEnabled())
        {
            BufferedImage buffer = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = buffer.createGraphics();
            g2d.setColor(this.getForegroundColor());
            FontMetrics fontMetrics = g2d.getFontMetrics();
            this.lineHeight = fontMetrics.getHeight() + lineSpaceing;

            int currentLine = 0;
            for(String line : this.lines) {
                int textHeight = fontMetrics.getHeight();
                int x = 0;
                int y = ((lineHeight * currentLine) + lineHeight);
                if(this.textAlign == TextAlign.CENTER) {
                    int textWidth = fontMetrics.stringWidth(line);
                    x = (this.getSize().getX() / 2) - (textWidth / 2);
                }
                else if(this.textAlign == TextAlign.RIGHT) {
                    int textWidth = fontMetrics.stringWidth(line);
                    x = this.getSize().getX() - textWidth;
                }
                else {
                    x = 0;
                }
                g2d.drawString(line, x, y);
                currentLine += 1;
            }
            g.drawImage(buffer, this.getLocation().getX(), this.getLocation().getY(), this.getSize().getX(), this.getSize().getY(), null);
        }
    }

    private void calculateHeight() {
        this.getSize().setY((lines.size() * lineHeight) + (lines.size() * lineSpaceing));
    }

    @Override
    public void onUpdate() {
        super.onUpdate(); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public void setText(String text) {
        super.setText(text);
        this.lines.clear();
        for(String line : text.split("\n")) {
            lines.add(line);
        }
    }

    public TextAlign getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(TextAlign textAlign) {
        this.textAlign = textAlign;
    }
}
