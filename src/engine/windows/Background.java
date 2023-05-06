package engine.windows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    BufferedImage background1;
    BufferedImage background2;
    BufferedImage island1;
    BufferedImage island2;
    BufferedImage island3;

    int windowsWidth;
    int windowsHeight;

    public Background(int windowsWidth, int windowsHeight) {
        try {
            background1 = ImageIO.read(new File("Resources/sea_bg1.png"));
            background2 = ImageIO.read(new File("Resources/sea_bg2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.windowsWidth = windowsWidth;
        this.windowsHeight = windowsHeight;

        try {
            BufferedImage assets = ImageIO.read(new File("Resources/1945assets.png"));
            island1 = assets.getSubimage(103, 499, 62, 62);
            island2 = assets.getSubimage(103 + 66, 499, 62, 62);
            island3 = assets.getSubimage(103 + 66 * 2, 499, 62, 62);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g) {
        int numberOfColumns = windowsWidth / 32 + 1;
        int numberOfRows = windowsHeight / 32 + 1;

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (i + j % 2 == 0) {
                    g.drawImage(background1, j * 32, i * 32, null);
                } else {
                    g.drawImage(background2, j * 32, i * 32, null);
                }
            }
        }

        g.drawImage(island1, 120, 100, null);
        g.drawImage(island1, 50, 600, null);
        g.drawImage(island1, 370, 300, null);
        g.drawImage(island2, 140, 200, null);
        g.drawImage(island3, 350, 500, null);
        g.drawImage(island3, 120, 360, null);
        g.drawImage(island3, 10, 50, null);

    }
}
