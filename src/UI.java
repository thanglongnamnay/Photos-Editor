import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class UI extends JFrame{
	Container container;
	ColorAdjustment colorAdjustment = new ColorAdjustment();
	BrightnessAdjustment brightnessAdjustment = new BrightnessAdjustment();
	JMenuBar menuBar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu help = new JMenu("Help");
	
	JMenuItem open = new JMenuItem("Open");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem exit = new JMenuItem("exit");
	JMenuItem colorAdjust = new JMenuItem("Color adjust");
	JMenuItem brightAdjust = new JMenuItem("Brightness and contrast");
	JMenuItem about = new JMenuItem("About");
	UI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		container = new Container();
		setContentPane(container);
		System.out.println(container.getWidth() + " " + container.getHeight());
		container.draw();
		addMenu();
		pack();
		setVisible(true);
	}
	private void addMenu() {
		
		file.add(open);
		file.add(save);
		file.add(exit);
		edit.add(colorAdjust);
		edit.add(brightAdjust);
		help.add(about);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		setJMenuBar(menuBar);
		
		addEventListener();
	}
	void addEventListener() {
		ToolBar tb = container.getToolbar();
		open.addActionListener(tb.openAtion);
		save.addActionListener(tb.saveAtion);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		colorAdjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				colorAdjustment.setVisible(true);
			}
		});
		brightAdjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				brightnessAdjustment.setVisible(true);
			}
		});
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
