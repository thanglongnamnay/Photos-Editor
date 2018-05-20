import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class ImageContainer extends JPanel{
	Image[] img = new Image[999];
	int currentImage = 0, lastImage = 0;
	public int x = 0, y = 0, pressedX, pressedY;
	public float zoom = 1f;
	public ImageContainer(Image img) {
		this.img[currentImage] = img;
		setSize(300,300);
		setBorder(BorderFactory.createLineBorder(Color.black,1));
	}
	public void importImage(Image img) {
		this.img[currentImage] = img;
		repaint();
	}
	public BufferedImage getBufferedImage() {
		try {
			BufferedImage bi = new BufferedImage(
					img[currentImage].getWidth(null), 
					img[currentImage].getHeight(null), 
					BufferedImage.TYPE_INT_RGB);
			Graphics2D buffGrap = bi.createGraphics();
			//bi is fuking empty, draw something on that
			buffGrap.drawImage(img[currentImage], 0, 0, null);
			buffGrap.dispose();
			return bi;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No picture file yet!");
			return null;
		}
	}
	void newImage(BufferedImage bi) {
		currentImage++;
		img[currentImage] = bi;
		if (currentImage > lastImage) lastImage = currentImage;
	}
	void clearUnnecessaryImages() {
		for (int i = currentImage + 1; i <= lastImage; i++) {
			img[i] = null;
		}
	}
	void resetImagePosition() {
		y = 0;
		x = 0;
		pressedX = 0;
		pressedY = 0;
	}
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, getWidth(), getHeight());
        if (zoom < 0.1f) zoom = 0.1f;
        if (img[currentImage] != null) {
            int xx = x + (getWidth() - (int) (img[currentImage].getWidth(null) * zoom)) / 2,
    		yy = y + (getHeight() - (int) (img[currentImage].getHeight(null) * zoom)) / 2;
        	g2d.drawImage(
        			img[currentImage], 
        			xx, 
        			yy, 
        			(int) (img[currentImage].getWidth(null) * zoom), 
        			(int) (img[currentImage].getHeight(null) * zoom), null);
        }
    }
}
