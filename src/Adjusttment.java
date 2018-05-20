import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Adjusttment extends JFrame {
	
	Container container;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JPanel 	BriConPanel = new JPanel(),
			HueSatPanel = new JPanel();

	JLabel 	brightnessFigure = new JLabel("0"),
			contrastFigure = new JLabel("0"),
			hueFigure = new JLabel("0"),
			satFigure = new JLabel("0");
	final JButton okButton1 = new JButton("OK"),
			okButton2 = new JButton("OK"),
			cancelButton1 = new JButton("Cancel"),
			cancelButton2 = new JButton("Cancel");
	JSlider brightnessSlider = new JSlider(),
			contrastSlicer = new JSlider(),
			hueSlider = new JSlider(),
			satSlicer = new JSlider();
	Component[] valuedComponents = {	
			brightnessFigure,
			contrastFigure,
			hueFigure,
			satFigure,
			brightnessSlider,
			contrastSlicer,
			hueSlider,
			satSlicer
	};
	Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
	
	ActionListener OKAction;
	ActionListener cancelAction;
	private JPanel contentPane;
	private final JButton btnNewButton = new JButton("New button");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Adjusttment frame = new Adjusttment(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Adjusttment(Container con) {
		container = con;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 210);
		setTitle("Image adjustment");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		contentPane.add(tabbedPane);

		BriConPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 20));
		HueSatPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 20));
		tabbedPane.addTab("Brightness and Constract", null, BriConPanel, "Fuck");
		tabbedPane.addTab("Hue and Saturation", null, HueSatPanel, "off");
		tabbedPane.setFocusable(false);
		OKAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ImageContainer ic = container.imageContainer;
				BufferedImage bi = ic.getBufferedImage();
				if (bi != null) {
					bi = container.imageProcessor.getAdjustedBufferedImage(
							bi, 
							brightnessSlider.getValue(), 
							contrastSlicer.getValue(), 
							hueSlider.getValue(), 
							satSlicer.getValue());
					container.imageContainer.newImage(bi);
					container.imageContainer.repaint();
					container.imageContainer.clearUnnecessaryImages();
					container.toolbar.undo.setEnabled(true);
				}
				resetValue(valuedComponents);
			}
		};
		cancelAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				resetValue(valuedComponents);
			}
		};
		initBriCon();
		initHueSat();
	}
	void initHueSat() {
		JSlider slider = new JSlider();
		slider.setMinorTickSpacing(2);
		contentPane.add(slider);
		
		final JLabel lblNewLabel = new JLabel("Hue");
		lblNewLabel.setFont(tahoma14);
		
		hueSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hueSlider.setFont(tahoma14);
		hueSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				hueFigure.setText(Integer.toString(hueSlider.getValue()));
			}
		});
		
		hueFigure.setFont(tahoma14);
		hueFigure.setPreferredSize(new Dimension(40, hueFigure.getPreferredSize().height));
		
		final JLabel lblNewLabel_2 = new JLabel("Saturation");
		lblNewLabel_2.setFont(tahoma14);
		
		satSlicer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		satSlicer.setFont(tahoma14);
		satSlicer.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				satFigure.setText(Integer.toString(satSlicer.getValue()));
			}
		});
		
		satFigure.setFont(tahoma14);
		satFigure.setPreferredSize(new Dimension(40, satFigure.getPreferredSize().height));
		
		okButton1.setIconTextGap(5);
		okButton1.setFocusPainted(false);
		okButton1.addActionListener(OKAction);
		
		cancelButton1.setIconTextGap(5);
		cancelButton1.setFocusPainted(false);
		cancelButton1.addActionListener(cancelAction);
		JSlider[] js = {hueSlider, satSlicer};
		setSliderStyle(js);
		Component[] com = {
				lblNewLabel, 
				hueSlider, 
				hueFigure, 
				lblNewLabel_2, 
				satSlicer, 
				satFigure, 
				okButton1, 
				cancelButton1};
		addCom(HueSatPanel,com);
	}
	void initBriCon() {
		final JLabel lblNewLabel = new JLabel("Brightness");
		lblNewLabel.setFont(tahoma14);
		
		brightnessSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		brightnessSlider.setFont(tahoma14);
		brightnessSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				brightnessFigure.setText(Integer.toString(brightnessSlider.getValue()));
			}
		});
		
		brightnessFigure.setFont(tahoma14);
		brightnessFigure.setPreferredSize(new Dimension(40, brightnessFigure.getPreferredSize().height));
		
		final JLabel lblNewLabel_2 = new JLabel("Contrast");
		lblNewLabel_2.setFont(tahoma14);
		
		contrastSlicer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contrastSlicer.setFont(tahoma14);
		contrastSlicer.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				contrastFigure.setText(Integer.toString(contrastSlicer.getValue()));
			}
		});
		
		contrastFigure.setFont(tahoma14);
		contrastFigure.setPreferredSize(new Dimension(40, contrastFigure.getPreferredSize().height));
		
		okButton2.setIconTextGap(5);
		okButton2.setFocusPainted(false);
		okButton2.addActionListener(OKAction);
		
		cancelButton2.setIconTextGap(5);
		cancelButton2.setFocusPainted(false);
		cancelButton2.addActionListener(cancelAction);
		

		JSlider[] js = {brightnessSlider, contrastSlicer};
		setSliderStyle(js);
		Component[] com = {
				lblNewLabel, 
				brightnessSlider, 
				brightnessFigure, 
				lblNewLabel_2, 
				contrastSlicer, 
				contrastFigure, 
				okButton2, 
				cancelButton2};
		addCom(BriConPanel, com);
	}
	void setSliderStyle(JSlider[] js) {
		for (JSlider s : js) {
			s.setValue(0);
			s.setMaximum(255);
			s.setMinimum(-255);
		}
	}
	void resetValue(Component[] com) {
		for(Component c : com)
			try {
				((JSlider) c).setValue(0);
				((JLabel) c).setText("0");
			} catch(Exception e) {
				e.toString();
			}
	}
	void addCom(JPanel container, Component[] oject) {
		for (Component o : oject) {
			container.add(o);
		}
	}
}
