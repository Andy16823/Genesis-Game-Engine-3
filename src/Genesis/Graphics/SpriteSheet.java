package Genesis.Graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private String name;
    private BufferedImage spriteSheetImage;
    private int cellWidth;
    private int cellHeight;

    public SpriteSheet(String name, BufferedImage spriteSheetImage, int cellWidth, int cellHeight) {
        this.name = name;
        this.spriteSheetImage = spriteSheetImage;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    public SpriteSheet(String name, BufferedImage spriteSheetImage, int cellSize) {
        this.name = name;
        this.spriteSheetImage = spriteSheetImage;
        this.cellWidth = cellSize;
        this.cellHeight = cellSize;
    }

    public BufferedImage getSpriteSheetImage() {
        return spriteSheetImage;
    }

    public void setSpriteSheetImage(BufferedImage spriteSheetImage) {
        this.spriteSheetImage = spriteSheetImage;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellSize) {
        this.cellWidth = cellSize;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getSprite(int x, int y) {
        return this.spriteSheetImage.getSubimage(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
    }

}
