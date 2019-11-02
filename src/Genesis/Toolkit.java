/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Andy
 */
public class Toolkit {
    public static Map<String, GameElement> GameElementMap;
    
    public static BufferedImage LoadAsset(String e){
        try {
            URL url = Toolkit.class.getResource(e);
            return ImageIO.read(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean Contains(Vector2 e1, Vector2 e2, Vector2 size)
    {
        if(e2.getX() > e1.getX() && e2.getX() < e1.getX() + size.getX() && e2.getY() > e1.getY()&& e2.getY() < e1.getY() + size.getY())
        {
            return true;
        }
        return false;
    }

    public static boolean Contains2(Vector2 e1, Vector2 e2, Vector2 size)
    {
        int refXmin = e1.getX() - (size.getX() / 2);
        int refXmax = e1.getX() + (size.getX() / 2);
        int refYmin = e1.getY()  - (size.getY() / 2);
        int refYmax = e1.getY() + (size.getY() / 2);

        if( e2.getX() > refXmin && e2.getX() < refXmax && e2.getY() > refYmin && e2.getY() < refYmax )
        {
            return true;
        }
        return false;
    }

    public static boolean ContainsCoord(int c1, int c2, int span)
    {
        if(c2 > (c1 - (span / 2)) && c2 < (c1  + (span / 2)))
        {
            return  true;
        }
        return false;
    }

    public boolean isNumberBetween(float n1, float n2, float value)
    {
        if(value > n1 && value < n2)
        {
            return  true;
        }
        return  false;
    }


    public static BufferedImage Base64Decode(String base) {
        try {
            byte[] iBytes = Base64.getDecoder().decode(base);
            ByteArrayInputStream is = new ByteArrayInputStream(iBytes);
            BufferedImage img = ImageIO.read(is);
            is.close();
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
