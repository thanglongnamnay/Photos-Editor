import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;

public class ColorAdjustment extends JFrame {

	JPanel contentPane;
	JLabel 	hueFigure = new JLabel("0"),
			satFigure = new JLabel("0");
	JButton okButton = new JButton("OK"),
			cancelButton = new JButton("Cancel");
	JSlider hueSlider = new JSlider(),
			satSlicer = new JSlider();
	Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorAdjustment frame = new ColorAdjustment();
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
	public ColorAdjustment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Change hue and saturation");
		setBounds(100, 100, 350, 180);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 20));
		
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
		JSlider[] js = {hueSlider, satSlicer};
		setSliderStyle(js);
		Component[] com = {
				lblNewLabel, 
				hueSlider, 
				hueFigure, 
				lblNewLabel_2, 
				satSlicer, 
				satFigure, 
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
