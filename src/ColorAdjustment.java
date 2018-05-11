import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ColorAdjustment extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField hueFigure;
	private JTextField satFigure;
	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	JSlider hueSlider = new JSlider();
	JSlider satSlicer = new JSlider();
	{
		hueFigure = new JTextField("50");
		satFigure = new JTextField("50");
	}

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
		setBounds(100, 100, 353, 211);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 20));
		
		JLabel lblNewLabel = new JLabel("Hue");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);
		
		hueSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hueSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(hueSlider);
		
		hueFigure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(hueFigure);
		hueFigure.setColumns(3);
		
		JLabel lblNewLabel_2 = new JLabel("Saturation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2);
		
		satSlicer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		satSlicer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(satSlicer);
		
		satFigure.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(satFigure);
		satFigure.setColumns(3);
		
		okButton.setIconTextGap(5);
		okButton.setFocusPainted(false);
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(okButton);
		
		cancelButton.setIconTextGap(5);
		cancelButton.setFocusPainted(false);
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(cancelButton);
	}
	void showWindow() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		hueFigure.getText();
	}
}
