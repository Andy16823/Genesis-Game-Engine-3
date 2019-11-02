/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.Graphics;

import Genesis.Math.Vector2;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Andy
 */
public abstract class ILight {
    private String Name;
    private String Tag;
    private Vector2 Location;
    private int Intensity;
    private Color DiffuseColor;
    private boolean Enabled;
    private Lightmap Lightmap;
    
    
    public ILight(Vector2 Location, int Intensity)
    {
        this.Location = Location;
        this.Intensity = Intensity;
        this.DiffuseColor = Color.WHITE;
        this.Enabled = true;
    }

    public void RenderLight(Graphics2D g2d) {
        
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

    public Vector2 getLocation() {
        return Location;
    }

    public void setLocation(Vector2 Location) {
        this.Location = Location;
    }

    public int getIntensity() {
        return Intensity;
    }

    public void setIntensity(int Intensity) {
        this.Intensity = Intensity;
    }

    public Color getDiffuseColor() {
        return DiffuseColor;
    }

    public void setDiffuseColor(Color DiffuseColor) {
        this.DiffuseColor = DiffuseColor;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }

    public Lightmap getLightmap() {
        return Lightmap;
    }

    public void setLightmap(Lightmap Lightmap) {
        this.Lightmap = Lightmap;
    }        
}
