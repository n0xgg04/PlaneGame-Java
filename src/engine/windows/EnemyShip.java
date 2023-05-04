package engine.windows;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class EnemyShip extends GameObject {
    public EnemyShip(Position position) {
        super(position);
        try {
            image = ImageIO.read(new File("Resources/ship.png"));
            this.position = position;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
