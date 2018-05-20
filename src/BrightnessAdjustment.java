import java.awt.*;
import java.awt.event.*;
import java.io.Console;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;

public class BrightnessAdjustment extends JFrame {

	private JPanel contentPane;
	JLabel 	brightnessFigure = new JLabel("0"),
			contrastFigure = new JLabel("0");
	JButton okButton = new JButton("OK"),
			cancelButton = new JButton("Cancel");
	JSlider brightnessSlider = new JSlider(),
			contrastSlicer = new JSlider();
	Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrightnessAdjustment frame = new BrightnessAdjustment();
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
	public BrightnessAdjustment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Change brightness and contrasturation");
		setBounds(100, 100, 350, 180);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 20));
		
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
		
		okButton.setIconTextGap(5);
		okButton.setFocusPainted(false);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		cancelButton.setIconTextGap(5);
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		

		JSlider[] js = {brightnessSlider, contrastSlicer};
		setSliderStyle(js);
		Component[] com = {
				lblNewLabel, 
				brightnessSlider, 
				brightnessFigure, 
				lblNewLabel_2, 
				contrastSlicer, 
				contrastFigure, 
				okButton, 
				cancelButton};
		addCom(com);
	}
	void setSliderStyle(JSlider[] js) {
		for (JSlider s : js) {
			s.setValue(0);
			s.setMaximum(255);
			s.setMinimum(-255);
		}
	}
	void addCom(Component[] oject) {
		for (Component o : oject) {
			contentPane.add(o);
		}
	}
	void showWindow() {
		this.setVisible(true);
	}

}
