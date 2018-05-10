import java.awt.*;

import javax.swing.*;

public class ColorAdjustmentWindow extends JFrame {
	ColorAdjustmentWindow(){
		
	}
	public Color saturationChange(Color color, float saturation) {
		float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
		float hue = hsb[0];
		float sat = hsb[1];
		float bri = hsb[2];
		sat *= saturation;
		
		color = Color.getHSBColor(hue, sat, bri);
		return color;
	}
}
