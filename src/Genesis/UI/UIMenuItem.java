package Genesis.UI;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class UIMenuItem {
    private String Name;
    private String Tag;
    private String Text;
    private Vector2 Location;
    private Vector2 Size;
    private BufferedImage Image;
    private Vector<UIMenuItem> Subitems;
    private UIMenu ParentMenu;
    private UIMenuItem ParentItem;
    private int Padding = 10;
    private UIMenuItemActionListener ActionListener;

    public UIMenuItem(String Name, String Text) {
        this.Name = Name;
        this.Subitems = new Vector<UIMenuItem>();
        this.Location = new Vector2(0,0);
        this.Size = new Vector2(100,100);
    }

    public UIMenuItem(String Name, String Text, BufferedImage image) {
        this.Name = Name;
        this.Text = Text;
        this.Subitems = new Vector<UIMenuItem>();
        this.Image = image;
        this.Location = new Vector2(0,0);
        this.Size = new Vector2(100,100);
    }

    public void addItem(UIMenuItem item) {
        this.Subitems.add(item);
        item.setParentMenu(this.ParentMenu);
        item.setParentItem(this);
    }

    public void renderItem(Graphics2D g) {

    }

    public BufferedImage renderItemAsImage(int width, int height, int padding) {
        BufferedImage renderTarget = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        int imgWidth = width - (2 * padding);
        int imgHeight = height - (2 * padding);
        g2d.drawImage(this.getImage(), padding,padding, imgWidth, imgHeight, null);

        FontMetrics fm = g2d.getFontMetrics();
        double textX = (width / 2) - (fm.stringWidth(this.getText()) / 2);
        double textY = height - fm.getHeight() - 5;
        g2d.drawString(this.getText(), (int) textX, (int) textY);

        return renderTarget;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public BufferedImage getImage() {
        return Image;
    }

    public void setImage(BufferedImage image) {
        Image = image;
    }

    public Vector<UIMenuItem> getSubitems() {
        return Subitems;
    }

    public void setSubitems(Vector<UIMenuItem> subitems) {
        Subitems = subitems;
    }

    public void addMenuItem(UIMenuItem item) {
        this.Subitems.add(item);
    }

    public UIMenu getParentMenu() {
        return ParentMenu;
    }

    public void setParentMenu(UIMenu parentMenu) {
        ParentMenu = parentMenu;
    }

    public UIMenuItem getParentItem() {
        return ParentItem;
    }

    public void setParentItem(UIMenuItem parentItem) {
        ParentItem = parentItem;
    }

    public Vector2 getLocation() {
        return Location;
    }

    public void setLocation(Vector2 location) {
        Location = location;
    }

    public void setLocation(int x, int y) {
        this.Location.set(x,y);
    }

    public Vector2 getSize() {
        return Size;
    }

    public void setSize(Vector2 size) {
        Size = size;
    }

    public int getPadding() {
        return Padding;
    }

    public void setPadding(int padding) {
        Padding = padding;
    }

    public boolean containsCoord(int x, int y) {
        if(x >= this.getLocation().getX() && x <= this.getSize().getX() + this.getLocation().getX() && y >= this.getLocation().getY() && y <= this.getSize().getY() + this.getLocation().getY())
        {
            return true;
        }
        return false;
    }

    public void onClick() {
        if(this.ActionListener != null)
        {
            this.ActionListener.ON_CLICK(this);
        }
    }

}
