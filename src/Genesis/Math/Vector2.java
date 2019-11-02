/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis.Math;

import java.util.Vector;

/**
 *
 * @author Andy
 */
public class Vector2 {
    private int x;
    private int y;
    
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void addX(int Value)
    {
        this.x += Value;
    }
    
    public void addY(int Value)
    {
        this.y += Value;
    }

    public void addXY(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public boolean compare(Vector2 e)
    {
        if(e.getX() == x && e.getY() == y)
        {
            return true;
        }
        return false;
    }

    public void set(int X, int Y) {
        this.x = X;
        this.y = Y;
    }

}
