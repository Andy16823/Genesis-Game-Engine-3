/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Genesis;

import Genesis.Graphics.Camera;
import Genesis.Math.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static void CenterPlayerElement(GameElement PlayerElement, Vector2 ScreenDimension, Scene SceneToTransform) {
        int newPlayerX = (ScreenDimension.getX() /2) - (PlayerElement.getSize().getX() / 2);
        int newPlayerY = (ScreenDimension.getY() / 2) - (PlayerElement.getSize().getY() / 2);
        int playerDiffX = PlayerElement.getLocation().getX() - newPlayerX;
        int playerDiffY = PlayerElement.getLocation().getY() - newPlayerY;

        SceneToTransform.transformScene(-playerDiffX, -playerDiffY);
    }

    public static double getDistance(Vector2 e1, Vector2 e2) {
        double distX = e2.getX() - e1.getX();
        double distY = e2.getY() - e1.getY();

        return Math.sqrt((distX * distX) + (distY * distY));
    }

    public static Color getColorAlpha(Color base, int alpha) {
        return new Color(base.getRed(), base.getGreen(), base.getBlue(), alpha);
    }

    public static void snapCamtoGameElement(GameElement element, Camera cam)
    {
        cam.setX(element.getCenterLocation().getX() - cam.getScene().getLocation().getX());
        cam.setY(element.getCenterLocation().getY() - cam.getScene().getLocation().getY());
        cam.lookAtViewport();
    }

    public static Vector2 getScreenResulution() {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Vector2 vector2 = new Vector2(dimension.width, dimension.height);
        return vector2;
    }

    public static float getAngle(GameElement element, GameElement ref) {
        int elementX = element.getCenterLocation().getX();
        int elementY = element.getCenterLocation().getY();
        int refX = ref.getCenterLocation().getX();
        int refY = ref.getCenterLocation().getY();
        float radians = (float) Math.atan2(refY - elementY, refX - elementX);
        float deg = (float) Math.toDegrees(radians) - (float) element.getRotation();
        return deg;
    }

    public static File getFileFromRessources(String path) {
        URL url = Toolkit.class.getResource(path);
        return new File(url.getPath());
    }

    public static Color getColor(Color base, int alpha) {
        return new Color(base.getRed(), base.getGreen(), base.getBlue(), alpha);
    }

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static Font getFont(String name, int size) {
        InputStream is = Toolkit.class.getResourceAsStream("/Assets/Font/Font.ttf");
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = baseFont.deriveFont(size);
            return font;

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
