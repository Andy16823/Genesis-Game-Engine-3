package Genesis.Math;

import java.util.Vector;

public class Grid {
    private String name;
    private String tag;
    private int cellWidth;
    private int cellHeight;
    private Vector<Rectangle> rectangles;

    public Grid() {
        this.rectangles = new Vector<>();
    }

    public Grid(String name) {
        this.name = name;
        this.rectangles = new Vector<>();
    }

    public Grid(String name, String tag) {
        this.name = name;
        this.tag = tag;
        this.rectangles = new Vector<>();
    }

    public Grid(String name, int cellWidth, int cellHeight) {
        this.name = name;
        this.cellHeight = cellWidth;
        this.cellHeight = cellHeight;
        this.rectangles = new Vector<>();
    }

    public Grid(String name, String tag, int cellWidth, int cellHeight) {
        this.name = name;
        this.tag = tag;
        this.cellHeight = cellWidth;
        this.cellHeight = cellHeight;
        this.rectangles = new Vector<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public Vector<Rectangle> getRectangles() {
        return rectangles;
    }

    public void setRectangles(Vector<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }

    public void addRect(Rectangle rectangle)
    {
        this.rectangles.add(rectangle);
    }

    public boolean contains(Vector2 point) {
        for(Rectangle rect : this.rectangles) {
            if(rect.contains(point)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Vector2 point, Vector2 offset) {
        for(Rectangle rect : this.rectangles) {
            Rectangle oRect = new Rectangle(rect.getX() + offset.getX(), rect.getY() + offset.getY(), rect.getWidth(), rect.getHeight());
            if(oRect.contains(point)) {
                return true;
            }
        }
        return false;
    }

    public void transformGrid(int x, int y) {
        for(Rectangle rect : this.rectangles) {
            rect.addXY(x, y);
        }
    }

    public Rectangle getRectAtPosition(Vector2 pos) {
        for(Rectangle rect : this.rectangles) {
            if(rect.contains(pos)) {
                return rect;
            }
        }
        return null;
    }

    public Rectangle getRectAtPosition(Vector2 pos, Vector2 offset) {
        for(Rectangle rect : this.rectangles) {
            Rectangle oRect = new Rectangle(rect.getX() + offset.getX(), rect.getY() + offset.getY(), rect.getWidth(), rect.getHeight());
            if(oRect.contains(pos)) {
                return oRect;
            }
        }
        return null;
    }

}
