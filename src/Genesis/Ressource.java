package Genesis;

import java.awt.image.BufferedImage;

public class Ressource {
    private String Name;
    private BufferedImage Texture;

    public Ressource(String name, BufferedImage texture) {
        Name = name;
        Texture = texture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BufferedImage getTexture() {
        return Texture;
    }

    public void setTexture(BufferedImage texture) {
        Texture = texture;
    }
}
