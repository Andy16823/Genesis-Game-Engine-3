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

/**
 *
 * @author Andy
 */
public class ListviewItem extends UIElement{
    private Listview Parent;
    private BufferedImage Image;
    private Vector2 ImageSize;
    private int Padding;
    private boolean ShowImage;
    private int Index;

    public ListviewItem(String Text) {
        this.setText(Text);
        this.ImageSize = new Vector2(48, 48);
        this.Padding = 5;
        this.ShowImage = false;
        this.setEnabled(true);
    }

    public ListviewItem(String Text, BufferedImage Image) {
        this.Image = Image;
        this.setText(Text);
        this.ImageSize = new Vector2(48, 48);
        this.Padding = 5;
        this.ShowImage = true;
        this.setEnabled(true);
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g); 
        
        BufferedImage buffer = new BufferedImage(this.Parent.getItemSize().getX(), this.Parent.getItemSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();
        Vector2 textpos = new Vector2(this.Padding, this.Padding + 15);
        
        if(this.ShowImage)
        {
            textpos.addX((this.ImageSize.getX() + this.Padding));
            g2d.drawImage(Image, Padding, Padding, this.ImageSize.getX(), this.ImageSize.getY(), null);
        }
        
        g2d.setFont(this.getFont());
        g2d.setColor(this.getForegroundColor());
        g2d.drawString(this.getText(), textpos.getX(), textpos.getY());
        
        
        g.drawImage(buffer, 0, (this.Parent.getItemSize().getY() * this.Index), null);
    }

    public Listview getParent() {
        return Parent;
    }

    public void setParent(Listview Parent) {
        this.Parent = Parent;
    }

    public BufferedImage getImage() {
        return Image;
    }

    public void setImage(BufferedImage Image) {
        this.Image = Image;
    }

    public Vector2 getImageSize() {
        return ImageSize;
    }

    public void setImageSize(Vector2 ImageSize) {
        this.ImageSize = ImageSize;
    }

    public int getPadding() {
        return Padding;
    }

    public void setPadding(int Padding) {
        this.Padding = Padding;
    }

    public boolean isShowImage() {
        return ShowImage;
    }

    public void setShowImage(boolean ShowImage) {
        this.ShowImage = ShowImage;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int Index) {
        this.Index = Index;
    } 
}
