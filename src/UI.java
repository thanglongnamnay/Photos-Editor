import java.awt.Dimension;

import javax.swing.*;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class UI extends JFrame{
	UI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Container container = new Container();
		addMenu();
		setContentPane(container);
		System.out.println(container.getWidth() + " " + container.getHeight());
		container.draw();
		pack();
		setVisible(true);
	}
	private void addMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("exit");
		JMenuItem colorAdjust = new JMenuItem("Color adjust");
		JMenuItem about = new JMenuItem("About");
		
		file.add(open);
		file.add(save);
		file.add(exit);
		edit.add(colorAdjust);
		help.add(about);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		setJMenuBar(menuBar);
	}
}
