import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageProcessor {

	public BufferedImage getAdjustedBufferedImage(BufferedImage bi, int briDelta, int conDelta, int hueDelta, int satDelta) {
		int[][] pixels = new int[bi.getWidth()][bi.getHeight()];
		for (int i = 0; i < bi.getWidth(); i++) 
			for (int j = 0; j < bi.getHeight(); j++) {
				pixels[i][j] = bi.getRGB(i, j);
				Color c = new Color(pixels[i][j]);
				c = briConChange(c, briDelta, conDelta);
				c = hueSatChange(c, hueDelta, satDelta);
				bi.setRGB(i, j, c.getRGB());
			}
		return bi;
	}
	
	public Color briConChange(Color color, int briDelta, int conDelta) {
		int 	r = color.getRed(),
				g = color.getGreen(),
				b = color.getBlue();
		r = trunc(r + briDelta);
		g = trunc(g + briDelta);
		b = trunc(b + briDelta);
		
		float contrastFactor = 259 * ((float)conDelta + 255) / (255 * (259 - (float)conDelta));
		r = trunc((int)(contrastFactor * (r - 128) + 128));
		g = trunc((int)(contrastFactor * (g - 128) + 128));
		b = trunc((int)(contrastFactor * (b - 128) + 128));
		return new Color(r, g, b);
	}
	Color hueSatChange(Color c, int hueDelta, int satDelta) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb);
		hsb[0] = trunc(hsb[0] + (float)hueDelta / 255);
		hsb[1] = trunc(hsb[1] + (float)satDelta / 255);
		return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	int trunc(int value) {
		if(value > 255) return 255;
		if(value < 0) return 0;
		return value;
	}
	float trunc(float value) {
		if(value > 1) return 1;
		if(value < 0) return 0;
		return value;
	}
	public static void main(String[] s) {
		ImageProcessor ip = new ImageProcessor();
		System.out.println(ip.trunc((int)(0.99f * (150 - 128) + 128)));;
	  }
}
