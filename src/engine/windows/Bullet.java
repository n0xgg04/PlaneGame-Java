package engine.windows;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameObject {
    private static final int UPDATE_PER_SECOND = 60;

    private final int speed; //pixel per seconds

    public Bullet(Position anchorPosition, int speed) {
        super(anchorPosition);
        //Anchor Position: center of the bullet
        try {
            image = ImageIO.read(new File("Resources/BULLET.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.position.x -= image.getWidth() / 2;
        this.position.y -= image.getHeight();
        this.speed = speed;
    }

    @Override
    public void update() {
        this.position.y -= speed / UPDATE_PER_SECOND;
    }

}
