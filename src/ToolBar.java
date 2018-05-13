import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;
public class ToolBar extends JPanel  {
	public ImageContainer imageContainer;
	JLabel separate = new JLabel("|");
	JButton select = new JButton();
	JButton move = new JButton();
	JButton zoom = new JButton();
	JButton save = new JButton();
	JButton open = new JButton();
	
	JTextField zoomRate = new JTextField("100%");
	
	public ActionListener openAtion = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imageContainer.importImage(new ImageIcon(getOpenedFile().toString()).getImage());
			imageContainer.repaint();
		}
	};
	public ActionListener saveAtion = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				if(imageContainer.getBufferedImage() != null)
					ImageIO.write(imageContainer.getBufferedImage(), "jpg", getSavedFile());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	};
	public ActionListener zoomAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	public ActionListener moveAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	public ActionListener selectAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
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
		
		zoomRate.setMaximumSize(new Dimension(40, 25));
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
		add(zoomRate);
	}
	private void addEvent()
	{
		select.addActionListener(selectAction);
		move.addActionListener(moveAction);
		zoom.addActionListener(zoomAction);
		open.addActionListener(openAtion);
		save.addActionListener(saveAtion);
	}
	void setOutlook(JButton[] button){
		// định dạng nút
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
	public ActionListener open() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageContainer.importImage(new ImageIcon(getOpenedFile().toString()).getImage());
				imageContainer.repaint();
			}
		};
	}
}
