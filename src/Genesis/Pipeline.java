package Genesis;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pipeline {
    private Vector<Ressource> Images;

    public Pipeline() {
        this.Images = new Vector<Ressource>();
    }

    /**
     * Adds a new image to the ressources
     * @param Name the name of the image
     * @param Image the image to insert
     * @return the index of the images, whats got insert
     */
    public int InsertRessource(String Name, BufferedImage Image) {
        this.Images.add(new Ressource(Name, Image));
        return  this.Images.size() -1;
    }

    /**
     * Adds a new image to the ressources
     * @param Name the name of the image
     * @param Path the path of the image
     * @return the index of the images, whats got insert
     */
    public int InsertRessource(String Name, String Path) {
        try {
            URL url = Toolkit.class.getResource(Path);
            this.Images.add(new Ressource(Name,ImageIO.read(url)));
            return  this.Images.size() -1;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  -1;
    }

    /**
     * Returns the Image at the position n
     * @param index the position of the Image
     */
    public BufferedImage GetRessource(int index) {
        return this.Images.get(index).getTexture();
    }

    /**
     * Returns the Image with the name n
     * @param Name the name of the image you want load
     */
    public BufferedImage GetRessource(String Name)    {
        for(Ressource e : this.Images)
        {
            if(e.getName().equals(Name))
            {
                return  e.getTexture();
            }
        }
        return null;
    }

}
