/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.Math.Vector2;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Listview extends UIElement{
    private Vector<ListviewItem> ListviewItems;
    private Vector2 ItemSize;
    
    public Listview() {
        this.ListviewItems = new Vector<ListviewItem>();
    }
    
    @Override
    public void Render(Graphics g) {
        super.Render(g); //To change body of generated methods, choose Tools | Templates.
        this.ItemSize = new Vector2(this.getSize().getX(), 50);
        BufferedImage buffer = new BufferedImage(this.getSize().getX(), (this.ListviewItems.size() * this.ItemSize.getY()), BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = buffer.createGraphics();
        for(ListviewItem item : this.ListviewItems)
        {
            if(item.isEnabled())
            {
                item.Render(g2);
            }
        }
        
        g.drawImage(buffer, this.getLocation().getX(), this.getLocation().getY(), null);
    }

    public void addItem(ListviewItem item) {
        item.setIndex(this.ListviewItems.size() + 1);
        item.setParent(this);
        this.ListviewItems.add(item);
    }
    
    public Vector<ListviewItem> getListviewItems() {
        return ListviewItems;
    }

    public void setListviewItems(Vector<ListviewItem> ListviewItems) {
        this.ListviewItems = ListviewItems;
    }

    public Vector2 getItemSize() {
        return ItemSize;
    }

    public void setItemSize(Vector2 ItemSize) {
        this.ItemSize = ItemSize;
    }   
    
}
