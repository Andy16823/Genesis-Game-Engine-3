/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.Graphics;

import static Genesis.Graphics.RenderMode.STATIC;
import Genesis.Math.Vector2;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Lightmap {
    private Vector<ILight> Lights;
    private String Name;
    private String Tag;
    private Color LightmapColor;
    private Vector2 Location;
    private Vector2 Size;
    private BufferedImage Lightmap;
    private boolean Enabled;
    private  RenderMode RenderMode;

    public Lightmap(Color LightmapColor, Vector2 Location, Vector2 Size) {
        this.Lights = new Vector<ILight>();
        this.LightmapColor = LightmapColor;
        this.Location = Location;
        this.Size = Size;
        this.RenderMode = RenderMode.STATIC;
    }
    
    
    
    public void AddLight(ILight light)
    {
        light.setLightmap(this);
        this.Lights.add(light);
    }
    
    public void RenderLightmap()
    {
        this.Lightmap = new BufferedImage(this.Size.getX(), this.Size.getY(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = this.Lightmap.createGraphics();
        g2d.setColor(LightmapColor);
        g2d.fillRect(0, 0, this.Size.getX(), this.Size.getY());
                
        for(ILight light : this.Lights)
        {
            if(light.isEnabled())
            {
                light.RenderLight(g2d);
            }
        }        
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

    public Color getLightmapColor() {
        return LightmapColor;
    }

    public void setLightmapColor(Color LightmapColor) {
        this.LightmapColor = LightmapColor;
    }

    public Vector2 getLocation() {
        return Location;
    }

    public void setLocation(Vector2 Location) {
        this.Location = Location;
    }

    public Vector2 getSize() {
        return Size;
    }

    public void setSize(Vector2 Size) {
        this.Size = Size;
    }

    public BufferedImage getLightmap() {
        return Lightmap;
    }

    public void setLightmap(BufferedImage Lightmap) {
        this.Lightmap = Lightmap;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }

    public RenderMode getRenderMode() {
        return RenderMode;
    }

    public void setRenderMode(RenderMode RenderMode) {
        this.RenderMode = RenderMode;
    }
}
