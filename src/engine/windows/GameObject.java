package engine.windows;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    protected Position position;
    protected BufferedImage image;

    public GameObject(Position position) {
        this.position = position;
    }

    public void update() {
    }

    public void draw(Graphics g) {
        g.drawImage(image, position.x, position.y, null);
    }

    public Position getPosition() {
        return position;
    }
}
