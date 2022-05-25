/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.UI;

import Genesis.Math.Vector2;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class GridView extends UIElement {
    private Vector<GridViewItem> Items;
    private Vector<Rectangle> Grid;
    private Color HighliteColor;
    private Color HighliteBorderColor;
    private int HighliteBorderWith;
    private int SelectedItemIndex;
    private int Collumns;
    private int Rows;
    private int Spaceing;
    private int CellWidth;
    private int CellHeight;
    private boolean HighliteSelectedCell;
    
    /**
     * 
     * @param Name
     * @param Location
     * @param args Params: int Collumns, int Rows, int CellWidth, int CellHeight
     */
    public GridView(String Name, Vector2 Location, int Collumns, int Rows, int CellWidth, int CellHeight)
    {
        this.Items = new Vector<GridViewItem>();
        this.Grid = new Vector<Rectangle>();
        this.setName(Name);
        this.setLocation(Location);
        this.Collumns = Collumns;
        this.Rows = Rows;
        this.CellWidth = CellWidth;
        this.CellHeight = CellHeight;
        this.setBorder(true);
        this.setBorderColor(Color.BLACK);
        this.setBackgroundColor(Color.darkGray);
        this.setBorderWidth(1);
        this.HighliteSelectedCell = true;
        this.HighliteBorderWith = 2;
        this.HighliteBorderColor = new Color(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue(), 255);
        this.HighliteColor = new Color(Color.RED.getRed(), Color.RED.getGreen(), Color.RED.getBlue(), 80);
        this.CalculateSize();
        this.BuildGrid();
    }
    
    public void CalculateSize()
    {
        int Width = this.Collumns * this.CellWidth +1;
        int Height = this.Rows * this.CellHeight +1;
        this.setSize(new Vector2(Width, Height));
    }
    
    public void BuildGrid() {
        this.Grid.clear();
        for(int y = 0; y < this.Rows; y++)
        {
            for(int x = 0; x < this.Collumns; x++)
            {
                this.Grid.add(new Rectangle(x * this.CellWidth, y * this.CellHeight, this.CellWidth, this.CellHeight));
            }
        }
    }

    @Override
    public void onRender(Graphics g) {
        super.onRender(g); //To change body of generated methods, choose Tools | Templates.
        
        // Create Grapic device
        BufferedImage image = new BufferedImage(this.getSize().getX(), this.getSize().getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Render Background
        g2d.setColor(this.getBackgroundColor());
        g2d.fillRect(0, 0, this.getSize().getX(), this.getSize().getY());
        
        // Render Border and Items
        int i = 0;
        g2d.setColor(this.getBorderColor());
        g2d.setStroke(new BasicStroke(this.getBorderWidth()));
        
        for(Rectangle rect : this.Grid)
        {
            if(this.Items.size() > i)
            {
                g2d.drawImage(this.Items.get(i).getTexture(), rect.x, rect.y, rect.width, rect.height, null);
            }
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
            i++;
        }
        
        // Draw SelectedCell
        if(this.HighliteSelectedCell && this.Grid.size() > this.SelectedItemIndex)
        {
            Rectangle rect = this.Grid.get(this.SelectedItemIndex);
            g2d.setColor(this.HighliteColor);
            g2d.fillRect(rect.x + this.HighliteBorderWith +1, rect.y + this.HighliteBorderWith +1, rect.width - this.HighliteBorderWith -2, rect.height - this.HighliteBorderWith -2);
            g2d.setStroke(new BasicStroke(this.HighliteBorderWith));
            g2d.setColor(this.HighliteBorderColor);
            g2d.drawRect(rect.x + this.HighliteBorderWith +1, rect.y + this.HighliteBorderWith +1, rect.width - this.HighliteBorderWith -2, rect.height - this.HighliteBorderWith -2);
        }
               
        
        g.drawImage(image, this.getLocation().getX(), this.getLocation().getY(), null);
        
    }

    @Override
    public void onMouseClick(MouseEvent e) {
        int i = 0;
        int MouseX = e.getX() - this.getCanvas().getLocation().getX() - this.getLocation().getX();
        int MouseY = e.getY() - this.getCanvas().getLocation().getY() - this.getLocation().getY();
        
        if(this.getParent() != null)
        {
            MouseX = MouseX - this.getParent().getLocation().getX();
            MouseY = MouseY - this.getParent().getLocation().getY();
        }
        
        for(Rectangle rect : this.Grid)
        {
            if(rect.contains(MouseX, MouseY))
            {
                this.SelectedItemIndex = i;
            }
            i++;
        }
        super.onMouseClick(e);
    }
    
    

    public int getCollumns() {
        return Collumns;
    }

    public void setCollumns(int Collumns) {
        this.Collumns = Collumns;
        this.CalculateSize();
        this.BuildGrid();
    }

    public int getRows() {
        return Rows;
    }

    public void setRows(int Rows) {
        this.Rows = Rows;
        this.CalculateSize();
        this.BuildGrid();
    }

    public int getSpaceing() {
        return Spaceing;
    }

    public void setSpaceing(int Spaceing) {
        this.Spaceing = Spaceing;
    }

    public int getCellWidth() {
        return CellWidth;
    }

    public void setCellWidth(int CellWidth) {
        this.CellWidth = CellWidth;
        this.CalculateSize();
        this.BuildGrid();
    }

    public int getCellHeight() {
        return CellHeight;
    }

    public void setCellHeight(int CellHeight) {
        this.CellHeight = CellHeight;
        this.CalculateSize();
        this.BuildGrid();
    }

    public Vector<GridViewItem> getItems() {
        return Items;
    }
    
    public void AddItem(GridViewItem e) {
        this.Items.add(e);
    }

    public int getSelectedItemIndex() {
        return SelectedItemIndex;
    }

    public void setSelectedItemIndex(int SelectedItem) {
        this.SelectedItemIndex = SelectedItem;
    }

    public Color getHighliteColor() {
        return HighliteColor;
    }

    public void setHighliteColor(Color HighliteColor) {
        this.HighliteColor = HighliteColor;
    }

    public Color getHighliteBorderColor() {
        return HighliteBorderColor;
    }

    public void setHighliteBorderColor(Color HighliteBorderColor) {
        this.HighliteBorderColor = HighliteBorderColor;
    }

    public int getHighliteBorderWith() {
        return HighliteBorderWith;
    }

    public void setHighliteBorderWith(int HighliteBorderWith) {
        this.HighliteBorderWith = HighliteBorderWith;
    }

    public boolean isHighliteSelectedCell() {
        return HighliteSelectedCell;
    }

    public void setHighliteSelectedCell(boolean HighliteSelectedCell) {
        this.HighliteSelectedCell = HighliteSelectedCell;
    }
    
    public GridViewItem getSelectedItem() {
        return this.Items.get(this.SelectedItemIndex);
    }
    
}
