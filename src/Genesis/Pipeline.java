package Genesis;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
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

    public void loadRessources(String path) {
        URL url = Toolkit.class.getResource(path);
        File files[] = new File(url.getPath()).listFiles();

        for(File f : files) {
            try {
                this.Images.add(new Ressource(f.getName(), ImageIO.read(f.toURI().toURL())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString(String vName, String basePath) {
        String out = "public void insertRessources() {";
        for(Ressource ressource : this.Images) {
            out += "\n" + vName + ".InsertRessource(\"" + ressource.getName() + "\", \"" + basePath + "/" + ressource.getName() + "\");";
        }
        out += "\n }";
        return out;
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
            this.Images.add(new Ressource(Name, ImageIO.read(url)));
            return  this.Images.size() -1;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Toolkit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  -1;
    }

    public void InsertTextureFromBase64(String name, String base64) {
        try {
            BufferedImage image = null;
            byte[] imageBytes;
            BASE64Decoder decoder = new BASE64Decoder();
            imageBytes = decoder.decodeBuffer(base64);
            ByteArrayInputStream is = new ByteArrayInputStream(imageBytes);
            image = ImageIO.read(is);
            this.Images.add(new Ressource(name, image));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
