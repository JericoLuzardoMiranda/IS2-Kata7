package software.ulpgc.ImageViewer.view;

import software.ulpgc.ImageViewer.presenter.ImageDisplay;
import software.ulpgc.ImageViewer.model.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (bitmap != null) {
            Dimension panelSize = getSize();

            double widthRatio = (double) panelSize.width / bitmap.getWidth();
            double heightRatio = (double) panelSize.height / bitmap.getHeight();

            double scaleFactor = Math.max(widthRatio, heightRatio);

            int newWidth = (int) (bitmap.getWidth() * scaleFactor);
            int newHeight = (int) (bitmap.getHeight() * scaleFactor);

            int x = (panelSize.width - newWidth) / 2;
            int y = (panelSize.height - newHeight) / 2;

            g.drawImage(bitmap, x, y, newWidth, newHeight, null);
        }
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
