import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.text.JTextComponent;
public class ToolBar extends JPanel  {
	public ImageContainer imageContainer;
	JLabel separate = new JLabel("|");
	JButton select = new JButton();
	JButton move = new JButton();
	JButton undo = new JButton();
	JButton redo = new JButton();
	JButton zoomIn = new JButton();
	JButton zoomOut = new JButton();
	JButton save = new JButton();
	JButton open = new JButton();
	
	JTextField zoomRate = new JTextField("100%");
	
	public ActionListener openAtion = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imageContainer.resetImagePosition();
			File f = getOpenedFile();
			if(f != null) {
				imageContainer.importImage(new ImageIcon(f.toString()).getImage());
				imageContainer.repaint();
			}
		}
	};
	public ActionListener saveAtion = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				if(imageContainer.getBufferedImage() != null)
					ImageIO.write(imageContainer.getBufferedImage(), "jpg", getSavedFile());
			} catch (IOException e1) {
				e1.toString();
			}
		}
	};
	public ActionListener zoomInAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imageContainer.zoom += 0.1f;
			zoomRate.setText(Integer.toString(Math.round(imageContainer.zoom * 100)));
			zoomRate.setText(Math.round(imageContainer.zoom * 100)+"%");
			imageContainer.resetImagePosition();
			imageContainer.repaint();
		}
	};
	public ActionListener zoomOutAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imageContainer.zoom -= 0.1f;
			zoomRate.setText(Math.round(imageContainer.zoom * 100)+"%");
			imageContainer.resetImagePosition();
			imageContainer.repaint();
		}
	};
	public ActionListener moveAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imageContainer.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			imageContainer.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent arg0) {
				}
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					imageContainer.pressedX = arg0.getX() - imageContainer.x;
					imageContainer.pressedY = arg0.getY() - imageContainer.y;
				}
				public void mouseExited(MouseEvent arg0) {
				}
				public void mouseEntered(MouseEvent arg0) {
				}
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			imageContainer.addMouseMotionListener(new MouseMotionListener() {
				public void mouseMoved(MouseEvent e) {
				}
				public void mouseDragged(MouseEvent e) {
					imageContainer.x = e.getX() - imageContainer.pressedX;
					imageContainer.y = e.getY() - imageContainer.pressedY;
					imageContainer.repaint();
				}
			});
		}
	};
	public ActionListener selectAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imageContainer.setCursor(Cursor.getDefaultCursor());
			imageContainer.removeMouseListener(imageContainer.getMouseListeners()[0]);
			imageContainer.removeMouseMotionListener(imageContainer.getMouseMotionListeners()[0]);
		}
	};
	public ActionListener undoAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(imageContainer.currentImage-1 >= 0) {
				imageContainer.currentImage--;
				imageContainer.repaint();
				redo.setEnabled(true);
				if(imageContainer.currentImage == 0) undo.setEnabled(false);
			}
		}
	};
	public ActionListener redoAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imageContainer.currentImage++;
			imageContainer.repaint();
			undo.setEnabled(true);
			if(imageContainer.img[imageContainer.currentImage + 1] == null) redo.setEnabled(false);
		}
	};

	ToolBar(ImageContainer imageContainer){
		this.imageContainer = imageContainer;
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		separate.setFont(new Font("arial",Font.PLAIN,30));
		addIcons();
		undo.setEnabled(false);
		redo.setEnabled(false);
		JButton[] buttons = {move,undo,redo,zoomIn,zoomOut,select,save,open};
		setOutlook(buttons);
		
		zoomRate.setMaximumSize(new Dimension(40, 25));
		zoomRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zoomNumber = textFilter(zoomRate.getText());
				imageContainer.zoom = Float.parseFloat(zoomNumber) / 100;
				zoomRate.setText(Math.round(imageContainer.zoom * 100)+"%");
				imageContainer.resetImagePosition();
				imageContainer.repaint();
			}
		});
		addButton();
		setSize(150,450);
		setBorder(BorderFactory.createLineBorder(Color.black, 2));
		addEvents();
	}
	String textFilter(String s) {
		int i = 0;
		String filtedString = "";
		while((i < s.length())&& ("0123456789".contains(new String(new char[] {s.charAt(i)})))) {
			filtedString += s.charAt(i);
			i++;
		}
		return filtedString;
	}
	private void addButton() {
		add(open);
		add(save);
		add(separate);
		add(select);
		add(move);
		add(undo);
		add(redo);
		add(zoomIn);
		add(zoomOut);
		add(zoomRate);
	}
	private void addEvents()
	{
		select.addActionListener(selectAction);
		move.addActionListener(moveAction);
		undo.addActionListener(undoAction);
		redo.addActionListener(redoAction);
		zoomIn.addActionListener(zoomInAction);
		zoomOut.addActionListener(zoomOutAction);
		open.addActionListener(openAtion);
		save.addActionListener(saveAtion);
	}
	private void addIcons() {
		move.setIcon(new ImageIcon("src\\icons\\hand.png"));
		zoomIn.setIcon(new ImageIcon("src\\icons\\zoomIn.png"));
		zoomOut.setIcon(new ImageIcon("src\\icons\\zoomOut.png"));
		select.setIcon(new ImageIcon("src\\icons\\select.png"));
		save.setIcon(new ImageIcon("src\\icons\\save.png"));
		open.setIcon(new ImageIcon("src\\icons\\open.png"));
		undo.setIcon(new ImageIcon("src\\icons\\undo.png"));
		redo.setIcon(new ImageIcon("src\\icons\\redo.png"));
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
