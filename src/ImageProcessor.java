import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class ImageProcessor {

	BufferedImage getAdjustedBufferedImage(BufferedImage bufferedImage, int briDelta, int conDelta, int hueDelta, int satDelta) {
		BufferedImage bi = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		int[][] pixels = new int[bi.getWidth()][bi.getHeight()];
		for (int i = 0; i < bi.getWidth(); i++)
			for (int j = 0; j < bi.getHeight(); j++) {
				pixels[i][j] = bufferedImage.getRGB(i, j);
				Color c = new Color(pixels[i][j]);
				c = briConChange(c, briDelta, conDelta);
				c = hueSatChange(c, hueDelta, satDelta);
				bi.setRGB(i, j, c.getRGB());
			}
		return bi;

//		return (new GrayScaleMap(bi)).getBalancedImage();
	}

	BufferedImage getBalancedBufferedImage(BufferedImage bufferedImage) {
		return (new GrayScaleMap(bufferedImage)).getBalancedImage();
	}

	BufferedImage getMeanBufferedImage(BufferedImage bufferedImage)  {
		return new GrayScaleMap(bufferedImage).getMeanImage();
	}
	
	private Color briConChange(Color color, int briDelta, int conDelta) {
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
	private Color hueSatChange(Color c, int hueDelta, int satDelta) {
		float[] hsb = new float[3];
		Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb);
		hsb[0] = trunc(hsb[0] + (float)hueDelta / 255);
		hsb[1] = trunc(hsb[1] + (float)satDelta / 255);
		return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}

	private int trunc(int value) {
		if(value > 255) return 255;
		return Math.max(value, 0);
	}
	private float trunc(float value) {
		if(value > 1) return 1;
		if(value < 0) return 0;
		return value;
	}
	private float trunc(double value) {
		if(value > 1) return 1;
		if(value < 0) return 0;
		return (float)value;
	}
}

class Utils {
	public static int trunc(int value) {
		if(value > 255) return 255;
		return Math.max(value, 0);
	}
	public static float trunc(float value) {
		if(value > 1) return 1;
		if(value < 0) return 0;
		return value;
	}
	public static float trunc(double value) {
		if(value > 1) return 1;
		if(value < 0) return 0;
		return (float)value;
	}

	public static int max(int[] a) {
		if (a.length == 0) return -1;
		return Arrays.stream(a).max().getAsInt();
	}
}

class GrayScaleMap {
	private final int[][][] pixels;
	private long[][] before = new long[3][256];
	private BufferedImage bufferedImage;

	long[] sum = new long[3];

	GrayScaleMap(BufferedImage bi) {
		System.out.println("started");
		bufferedImage = bi;
		pixels = new int[3][bi.getWidth()][bi.getHeight()];
		for (int i = 0; i < bi.getWidth(); i++) {
			for (int j = 0; j < bi.getHeight(); j++) {
				Color c = new Color(bi.getRGB(i, j));
				pixels[0][i][j] = c.getRed();
				pixels[1][i][j] = c.getGreen();
				pixels[2][i][j] = c.getBlue();
				before[0][c.getRed()] += 1;
				before[1][c.getGreen()] += 1;
				before[2][c.getBlue()] += 1;
			}
		}
		for (int i = 0; i < 3; ++i) {
			sum[i] = Arrays.stream(before[i]).sum();
		}
	}

	BufferedImage getMeanImage() {
		int[][][] pixelsMean = calPixelsMod(3);
		BufferedImage bi = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				bi.setRGB(i, j, new Color(pixelsMean[0][i][j], pixelsMean[1][i][j], pixelsMean[2][i][j]).getRGB());
			}
		}

		System.out.println("finished");
		return bi;
	}

	BufferedImage getBalancedImage() {
		long[][] after = calcAfterBalance();
		int[][] map = calcMap(after);
		BufferedImage bi = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bufferedImage.getWidth(); i++) {
			for (int j = 0; j < bufferedImage.getHeight(); j++) {
				bi.setRGB(i, j, new Color(map[0][pixels[0][i][j]], map[1][pixels[1][i][j]], map[2][pixels[2][i][j]]).getRGB());

			}
		}
		System.out.println("finished");
		return bi;
	}

	int scale(int c, double scale) {
		return Utils.trunc((int)Math.floor(c * scale));
	}

	int[][][] calPixelsMod(int kmean) {
		int left = kmean / 2;
		int height = pixels.length;
		int width = pixels[0].length;
		int[][][] after = new int[3][height][width];
		for (int channel = 0; channel < 3; ++channel) {
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					if (i < left || j < left || i >= height - left || j >= width - left) {
						after[channel][i][j] = pixels[channel][i][j];
					} else {
						ArrayList<Integer> arr = new ArrayList<>();
						for (int k = -left; k <= left; ++k) {
							for (int l = -left; l <= left; ++l) {
								int t = pixels[channel][i + k][j + l];
								if (arr.stream().noneMatch(x -> x == t)) {
									arr.add(t);
								}
							}
						}
						after[channel][i][j] = (int) arr.stream().sorted().toArray()[arr.size() / 2];
					}
//				System.out.println(pixels[i][j] + ", " + after[i][j]);
				}
			}
		}

		return after;
	}

	int[][][] calPixelsMean(int kmean) {
		double mean = 1d / kmean / kmean;
		int left = kmean / 2;
		int height = pixels.length;
		int width = pixels[0].length;
		int[][][] after = new int[3][height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				for (int k = 0; k < 3; ++k) {
					if (i < left || j < left || i >= height - left || j >= width - left) {
						after[k][i][j] = pixels[k][i][j];
					} else {
						after[k][i][j] = 0;
						for (int h = -left; h <= left; ++h) {
							for (int l = -left; l <= left; ++l) {
								after[k][i][j] += (int) (mean * pixels[k][i + h][j + l]);
							}
						}
					}
//				System.out.println(pixels[i][j] + ", " + after[i][j]);
				}
			}
		}

		return after;
	}

	long[][] calcAfterBalance() {
		long[][] after = new long[3][256];
		for (int j = 0; j < 3; ++j) {
			after[j][0] = before[j][0];
			for (int i = 1; i < 256; ++i) {
				after[j][i] = after[j][i - 1] + before[j][i];
			}
		}

		return after;
	}

	int[][] calcMap(long[][] after) {
		int[][] map = new int[3][256];
		for (int j = 0; j < 3; ++j) {
			for (int i = 0; i < 256; ++i) {
				map[j][i] = (int) (after[j][i] * 255 / sum[j]);
//				System.out.println(map[j][i]);
			}
		}

		return map;
	}
}