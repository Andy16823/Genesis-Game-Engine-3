/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import java.awt.image.BufferedImage;

/**
 *
 * @author Andy
 */
public class GridViewItem {
    private String Name;
    private String Tag;
    private String Text;
    private BufferedImage Texture;

    public GridViewItem(String Name, String Text, BufferedImage Texture) {
        this.Name = Name;
        this.Text = Text;
        this.Texture = Texture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String Tag) {
        this.Tag = Tag;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public BufferedImage getTexture() {
        return Texture;
    }

    public void setTexture(BufferedImage Texture) {
        this.Texture = Texture;
    }
        
}
