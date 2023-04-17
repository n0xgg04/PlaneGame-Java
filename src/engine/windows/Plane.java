package engine.windows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Plane {
    private static int UPDATE_PER_SECOND = 60;

    private Position position;

    private int healthPoint;
    private int damage;
    private int speed; // speed * 100 pixel per seconds
    private int type;
    private BufferedImage image;

    public Plane(Position position, int healthPoint, int damage, int speed, int type) {
        //this => current object, using this.property will differentiate name from the arguments
        this.position = position;
        this.healthPoint = healthPoint;
        this.damage = damage;
        this.speed = speed;
        this.type = type;

        try {
            switch (this.type) {
                case 1:
                    image = ImageIO.read(new File("Resources/PLANE1.png"));
                    break;
                case 2:
                    image = ImageIO.read(new File("Resources/PLANE2.png"));
                    break;
                case 3:
                    image = ImageIO.read(new File("Resources/PLANE3.png"));
                    break;
                case 4:
                    image = ImageIO.read(new File("Resources/PLANE4.png"));
                    break;
            }
        } catch (IOException ex){
            System.out.println("Error while loading plane image");
        }
    }

    public Position getPosition() {
        return position;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTickSpeed(){
        //A method that return the amount of change in position between ticks.
        return speed * 100 / UPDATE_PER_SECOND;
    }
}
