package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SideTabMenu extends UIMenu {
    private int ItemCellsX = 4;
    private int ItemWidth = 100;
    private int ItemHeight = 100;

    public SideTabMenu(String Name, Vector2 Location, Vector2 Size) {
        super(Name,"unnamed", Location, Size);
    }

    public SideTabMenu(String Name, String Text, Vector2 Location, Vector2 Size) {
        super(Name, Text, Location, Size);
    }

    public static enum AutoSortMode {
        List,
        Grid
    }

    public void autoSortItems(AutoSortMode mode) {
        if(mode == AutoSortMode.Grid) {
            for(UIMenuItem header : this.getMenuItems()) {
                int row = 0;
                int cell = 0;
                for(UIMenuItem item : header.getSubitems()) {
                    item.getSize().set(this.ItemWidth, this.ItemHeight);
                    int itemX = cell * item.getSize().getX();
                    int itemY = row * item.getSize().getY();
                    item.setLocation(itemX, itemY);

                    cell++;
                    if(cell == this.ItemCellsX) {
                        cell = 0;
                        row++;
                    }
                }
            }
        }
    }

    @Override
    public void onRender(Graphics g) {
        super.onRender(g);

        BufferedImage renderTarget = new BufferedImage(this.getSize().getX(),this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        // Background
        g2d.setColor(this.getBackgroundColor());
        g2d.fillRect(0,0, this.getSize().getX(), this.getSize().getY());
        // Tabhead
        g2d.setColor(this.getBorderColor());
        g2d.fillRect(0,0, this.getSize().getX(), this.getTabHeight());
        // Titel
        g2d.setColor(this.getForegroundColor());
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.drawString(this.getText(), 15,(this.getTabHeight() / 2) + (metrics.getHeight() / 2));
        // Menu List
        g2d.setColor(this.getBorderColor());
        g2d.fillRect(0, this.getTabHeight(), 100, this.getSize().getY());
        // Menu
        int index = 0;
        for(UIMenuItem item : this.getMenuItems()) {
            g2d.drawImage(this.renderMenuHeader(item, index), 0, index * this.getHeaderHeight() + this.getTabHeight(), null);
            index++;
        }

        // Scrollbar
        int ScrollbarX = this.getSize().getX() - this.getScrollbarWidth();
        int ScrollbarY = this.getTabHeight() + this.getScrollValue();
        int ScrollbarHeight = (this.getSize().getY() - this.getTabHeight() - this.getMaxScrollValue());
        g2d.setColor(this.getScrollbarBackgroundColor());
        g2d.fillRect(this.getSize().getX() - this.getScrollbarWidth(), this.getTabHeight(), this.getScrollbarWidth(), this.getSize().getX() - this.getTabHeight());
        g2d.setColor(this.getScrollbarColor());
        g2d.fillRect(ScrollbarX, ScrollbarY, this.getScrollbarWidth(), ScrollbarHeight);

        // Subitems
        if(this.getSelectedItem() != null) {
            renderMenuItems(g2d);
        }

        g.drawImage(renderTarget, this.getLocation().getX(), this.getLocation().getY(), null);
    }

    /**
     * Render the menu item
     * @param item
     * @return
     */
    public BufferedImage renderMenuHeader(UIMenuItem item, int index) {
        BufferedImage renderTarget = new BufferedImage(this.getHeaderWidth(), this.getHeaderHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        if(index == this.getSelectedItemIndex()) {
            g2d.setColor(this.getSelectedItemBackgroundColor());
            g2d.fillRect(0,0,this.getHeaderWidth(), this.getHeaderHeight());
            g2d.setColor(this.getSelectedItemBorderColor());
            g2d.fillRect(0,this.getHeaderHeight() - 5,this.getHeaderWidth(), 5);
        }

        int itemWidth = this.getHeaderWidth() - (this.getHeaderPadding() * 2);
        int itemHeight = this.getHeaderHeight() - (this.getHeaderPadding() * 2);
        g2d.drawImage(item.getImage(), this.getHeaderPadding(), this.getHeaderPadding(), itemWidth, itemHeight, null);

        FontMetrics fm = g2d.getFontMetrics();
        double headerX = (this.getHeaderWidth() / 2) - (fm.stringWidth(item.getText())/ 2);
        double headerY = this.getHeaderHeight() - fm.getHeight();

        if(index == this.getSelectedItemIndex()) {
            g2d.setColor(this.getSelectedForegroundColor());
        }
        else
        {
            g2d.setColor(this.getForegroundColor());
        }
        g2d.drawString(item.getText(), (int) headerX, (int) headerY);

        return  renderTarget;
    }

    public void renderMenuItems(Graphics2D g) {
        int rows =(this.getSelectedItem().getSubitems().size() / this.ItemCellsX) + 1;
        int width = this.getSize().getX() - this.getHeaderWidth() - this.getScrollbarWidth();
        int height = rows * this.ItemHeight;

        BufferedImage renderTarget = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        for(UIMenuItem item : this.getSelectedItem().getSubitems()) {
            item.renderItem(g2d);
        }
        g.drawImage(renderTarget, this.getHeaderWidth(), this.getTabHeight(), null);
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        super.onMouseClick(e);
        int MouseX = e.getX() - this.getCanvas().getLocation().getX() - this.getLocation().getX();
        int MouseY = e.getY() - this.getCanvas().getLocation().getY() - this.getLocation().getY();

        if(MouseX >= 0 && MouseX <= this.getHeaderWidth() && MouseY > 0 && MouseY <= this.getSize().getY()) {
            changeItemIndex(MouseX, MouseY);
        }

        if(MouseX >= this.getHeaderWidth() && MouseX <= this.getSize().getX() - this.getScrollbarWidth() && MouseY >= this.getTabHeight() && MouseY <= this.getSize().getY())
        {

            for(UIMenuItem item : this.getSelectedItem().getSubitems()) {
                if(item.containsCoord((MouseX - this.getHeaderWidth()), (MouseY - this.getTabHeight())))
                {
                    if(this.getActionListener() != null) {
                        this.getActionListener().ON_ITEM_CLICK(item);
                    }
                    item.onClick();
                }
            }
        }

    }

    public void changeItemIndex(int MouseX, int MouseY) {
        int index = 0;
        for(UIMenuItem item : this.getMenuItems())
        {
            int itemY = (index * this.getHeaderHeight()) + this.getTabHeight();
            int itemEndY = itemY + this.getHeaderHeight();
            if(MouseY >= itemY && MouseY <= itemEndY) {
                this.setSelectedItemIndex(index);
            }
            index++;
        }
    }

    public int getItemCellsX() {
        return ItemCellsX;
    }

    public void setItemCellsX(int itemCellsX) {
        ItemCellsX = itemCellsX;
    }

    public int getItemWidth() {
        return ItemWidth;
    }

    public void setItemWidth(int itemWidth) {
        ItemWidth = itemWidth;
    }

    public int getItemHeight() {
        return ItemHeight;
    }

    public void setItemHeight(int itemHeight) {
        ItemHeight = itemHeight;
    }
}
