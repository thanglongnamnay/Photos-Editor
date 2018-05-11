import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class ToolBar extends JPanel  {
	ImageContainer imageContainer;
	JLabel separate = new JLabel("|");
	JButton select = new JButton();
	JButton move = new JButton();
	JButton zoom = new JButton();
	JButton save = new JButton();
	JButton open = new JButton();
	ToolBar(ImageContainer imageContainer){
		this.imageContainer = imageContainer;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		separate.setFont(new Font("arial",Font.PLAIN,30));
		move.setIcon(new ImageIcon("src\\icons\\hand.png"));
		zoom.setIcon(new ImageIcon("src\\icons\\zoom.png"));
		select.setIcon(new ImageIcon("src\\icons\\select.png"));
		save.setIcon(new ImageIcon("src\\icons\\save.png"));
		open.setIcon(new ImageIcon("src\\icons\\open.png"));
		JButton[] buttons = {move,zoom,select,save,open};
		setOutlook(buttons);
		addButton();
		setSize(150,450);
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
		addEvent();
	}
	private void addButton() {
		add(open);
		add(save);
		add(separate);
		add(select);
		add(move);
		add(zoom);
	}
	private void addEvent()
	{
		select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		move.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		zoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageContainer.importImage(new ImageIcon(getOpenedFile().toString()).getImage());
				imageContainer.repaint();
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(imageContainer.getBufferedImage(), "jpg", getSavedFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	void setOutlook(JButton[] button){
		for(JButton b:button) {
			b.setPreferredSize(new Dimension(25,25));
			b.setFocusPainted(false);
			
			b.setMargin(new Insets(0,0,0,0));
			b.setHorizontalAlignment(SwingConstants.CENTER);
			b.setVerticalAlignment(SwingConstants.CENTER);
			
			//bug màu nút.
			b.setContentAreaFilled(false);
	//		b.setBorderPainted(false);
		}
	}
	File getOpenedFile() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Jpeg images", "jpg"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnValue = fc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} 
		return null;
	}
	File getSavedFile() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Jpeg images", "jpg"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnValue = fc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			f = new File(f.toString()+".jpg");
			return f;
		}
		return null;
	}
}
