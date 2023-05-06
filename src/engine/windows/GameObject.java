package engine.windows;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    protected Position position;
    protected BufferedImage image;

    private boolean isDestroyed = false;

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

    public void destroyGameObject() {
        this.isDestroyed = true;
    }

    public boolean isDestroy() {
        return isDestroyed;
    }

    public boolean isCollide(GameObject gameObject) {
        int x1 = this.position.x;
        int y1 = this.position.y;
        int width1 = this.image.getWidth();
        int height1 = this.image.getHeight();

        int x2 = gameObject.position.x;
        int y2 = gameObject.position.y;
        int width2 = gameObject.image.getWidth();
        int height2 = gameObject.image.getHeight();

        return !(x1 + width1 < x2 ||
                y1 + height1 < y2 ||
                x1 > x2 + width2 ||
                y1 > y2 + height2
        );

    }
}
