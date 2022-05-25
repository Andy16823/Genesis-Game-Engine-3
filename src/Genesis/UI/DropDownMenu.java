package Genesis.UI;

import Genesis.Input;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class DropDownMenu extends UIElement {
    private int itemHeight = 50;
    private Vector<DropDownMenuElement> items;
    private String title;
    private String subtitle;
    private Color titleBackground;
    private Color subtitleBackground;
    private int titleHeight = 60;
    private int subtitleHeight = 30;
    private Font titleFont;
    private Font subtitleFont;
    private Color titleColor = Color.white;
    private Color subtitleColor = Color.white;
    private int selectedIndex = 0;

    public DropDownMenu(String title, String subtitle) {
        this.items = new Vector<DropDownMenuElement>();
        this.title = title;
        this.subtitle = subtitle;
        this.titleBackground = new Color(255,165,0);
        this.subtitleBackground = Color.BLACK;
        this.titleFont = new Font("Arial", Font.PLAIN, 30);
        this.subtitleFont = new Font("Arial", Font.PLAIN, 15);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public void onRender(Graphics g) {
        super.onRender(g);

        int height = titleHeight + subtitleHeight + getItemsHight();
        int width = this.getSize().getX();

        BufferedImage renderTarget = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();
        Color oldColor = g2d.getColor();
        Font oldFont = g2d.getFont();

        // Title Background
        g2d.setColor(this.titleBackground);
        g2d.fillRect(0,0, width, this.titleHeight);
        // Title String
        g2d.setColor(this.titleColor);
        g2d.setFont(this.titleFont);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(this.title, (width / 2) - (fm.stringWidth(title) / 2), (titleHeight / 2) + (fm.getHeight() / 2));
        // Draw Items
        int startY = this.titleHeight;
        for(int i = 0; i < this.items.size(); i++)
        {
            int x = 0;
            int y = startY + (itemHeight * i);
            this.items.get(i).Render(g2d, new Vector2(x,y));
            if(i == this.selectedIndex) {
                renderSelectedItem(g2d, new Vector2(x,y));
            }

        }

        g.drawImage(renderTarget, this.getLocation().getX(), this.getLocation().getY(), null);
    }

    private void renderSelectedItem(Graphics2D g, Vector2 loc) {
        int width = this.getSize().getX();
        int height = this.getItemHeight();
        Color oc = g.getColor();
        g.setColor(this.titleBackground);
        g.fillRect(loc.getX(), loc.getY(), 5, height);
        g.setColor(oc);
    }

    @Override
    public void keyPressed(Input e) {
        if(this.isEnabled()) {
            if(e.isIsInput()) {
                if(e.getInputKey() == KeyEvent.VK_UP) {
                    this.selectedIndex -= 1;
                    if(this.selectedIndex < 0) {
                        this.selectedIndex = this.items.size();
                    }
                    this.items.get(selectedIndex).activate();
                }
                else if(e.getInputKey() == KeyEvent.VK_DOWN) {
                    this.selectedIndex +=1;
                    if(this.selectedIndex > (this.items.size() -1))
                    {
                        this.selectedIndex = 0;
                    }
                    this.items.get(selectedIndex).activate();
                }
                else if(e.getInputKey() == KeyEvent.VK_ENTER) {
                    this.items.get(selectedIndex).activate();
                }
            }
        }
    }

    @Override
    public void keyReleased(Input e) {
        super.keyReleased(e);
    }

    @Override
    public void onHover(MouseEvent e) {
        super.onHover(e);
    }

    @Override
    public void onMouseLeave(MouseEvent e) {
        super.onMouseLeave(e);
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        super.onMouseClick(e);
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    public void addItem(DropDownMenuElement item) {
        item.setParent(this);
        items.add(item);
    }

    public void addItem(DropDownMenuElement item, DropDownMenuItemActionListener al) {
        item.addActionListener(al);
        item.setParent(this);
        this.items.add(item);
    }

    public int getItemsHight() {
        return this.itemHeight * this.items.size();
    }

}
