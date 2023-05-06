package engine.windows.session4;

import engine.windows.GameObject;
import engine.windows.MovementVector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Session4 extends Frame implements Runnable{

    BufferedImage bufferedImage;
    int frameNumber = 8;

    Image image;
    Graphics second;
    ArrayList<BufferedImage> bufferedImageList = new ArrayList<BufferedImage>();
    public Session4(){
        this.setTitle("Session4");
        this.setFocusable(true);
        this.setSize(1920, 1080);
        this.setVisible(true);
        this.setResizable(false);

        try {
            bufferedImage = ImageIO.read(new File("Resources/cat.png"));
            int maxColumn = 5;
            int maxRow = 2;
            int frameIndex = 0;
            for (int i = 0; i < maxRow; i++) {
                for (int j = 0; j < maxColumn; j++) {
                    frameIndex++;
                    if (frameIndex == 8) {
                        break;
                    }
                    bufferedImageList.add(
                            bufferedImage.getSubimage(
                                    221 * j,
                                    154 * i,
                                    221,
                                    154
                            )
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / 18);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        image = createImage(this.getWidth(), this.getHeight());
        //Tạo một 1 graphics ẩn
        second = image.getGraphics();
        paint(second);

        //Vẽ trên graphics ẩn
        g.drawImage(image, 0, 0, null);
    }

    int drawingFrameIndex = 0;
    @Override
    public void paint(Graphics g) {
        g.drawImage(
                bufferedImageList.get(drawingFrameIndex),
                100,
                100,
                null
        );
        drawingFrameIndex++;
        if (drawingFrameIndex == bufferedImageList.size()) {
            drawingFrameIndex = 0;
        }
    }

    public void start() {
        Thread mainThread = new Thread(this);
        mainThread.start();
    }
}
