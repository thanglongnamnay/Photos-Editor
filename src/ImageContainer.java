import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class ImageContainer extends JPanel{
	Image img;
	public ImageContainer(Image img) {
		this.img = img;
		setSize(300,300);
		setBorder(BorderFactory.createLineBorder(Color.black,1));
	}
	public void importImage(Image img) {
		this.img = img;
		repaint();
	}
	public Image exportImage() {
		return img;
	}
	public BufferedImage getBufferedImage() {
		try {
			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D buffGrap = bi.createGraphics();
			buffGrap.drawImage(img, 0, 0, null);
			buffGrap.dispose();
			return bi;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No picture file yet!");
			return null;
		}
	}
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.drawImage(img, 0, 0, null);
    }
}
