import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;

public class BrightnessAdjustment extends JFrame {

	private JPanel contentPane;
	private JTextField brightnessFigure;
	private JTextField contrastFigure;
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	JSlider brightnessSlider = new JSlider();
	JSlider contrastSlicer = new JSlider();
	Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
	{
		brightnessFigure = new JTextField("50");
		contrastFigure = new JTextField("50");
	}

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
		contentPane.add(lblNewLabel);
		
		brightnessSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		brightnessSlider.setFont(tahoma14);
		contentPane.add(brightnessSlider);
		brightnessSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				brightnessFigure.setText(Integer.toString(brightnessSlider.getValue()));
			}
		});
		
		brightnessFigure.setFont(tahoma14);
		contentPane.add(brightnessFigure);
		brightnessFigure.setColumns(3);
		
		final JLabel lblNewLabel_2 = new JLabel("Contrast");
		lblNewLabel_2.setFont(tahoma14);
		contentPane.add(lblNewLabel_2);
		
		contrastSlicer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contrastSlicer.setFont(tahoma14);
		contentPane.add(contrastSlicer);
		contrastSlicer.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				contrastFigure.setText(Integer.toString(contrastSlicer.getValue()));
			}
		});
		
		contrastFigure.setFont(tahoma14);
		contentPane.add(contrastFigure);
		contrastFigure.setColumns(3);
		
		okButton.setIconTextGap(5);
		okButton.setFocusPainted(false);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		cancelButton.setIconTextGap(5);
		cancelButton.setFocusPainted(false);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
	void showWindow() {
		this.setVisible(true);
	}

}
