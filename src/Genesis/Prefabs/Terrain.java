package Genesis.Prefabs;

import Genesis.GameElement;
import Genesis.Graphics.RenderMode;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Terrain extends GameElement {
    private BufferedImage texture;
    private Vector2 tileSize;
    private Vector2 tiles;

    public Terrain(String Name, Vector2 TileSize, Vector2 Tiles, BufferedImage Texture) {
        super(Name, new Vector2(0,0), new Vector2(0, 0), Texture, RenderMode.DYNAMIC);
        this.setSprite(this.fillBackground(Texture, TileSize, Tiles));
        this.texture = Texture;
        this.tiles = Tiles;
        this.tileSize = TileSize;
        this.setSize(new Vector2(TileSize.getX() * Tiles.getX(), TileSize.getY() * Tiles.getY()));
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public Vector2 getTileSize() {
        return tileSize;
    }

    public void setTileSize(Vector2 tileSize) {
        this.tileSize = tileSize;
    }

    public Vector2 getTiles() {
        return tiles;
    }

    public void setTiles(Vector2 tiles) {
        this.tiles = tiles;
    }

    public BufferedImage fillBackground(BufferedImage texture, Vector2 TileSize, Vector2 Tiles) {
        int width = TileSize.getX() * Tiles.getX();
        int height = TileSize.getY() * Tiles.getY();
        BufferedImage renderTarget = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = renderTarget.createGraphics();

        for(int iy = 0; iy < Tiles.getY(); iy++)
        {
            for(int ix = 0; ix < Tiles.getX(); ix++)
            {
                int x = ix * TileSize.getX();
                int y = iy * TileSize.getY();
                g2d.drawImage(texture, x, y, TileSize.getX(), TileSize.getY(), null);
            }
        }

        return renderTarget;
    }

}
