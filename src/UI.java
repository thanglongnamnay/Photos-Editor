import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;


public class UI extends JFrame {
	Container container;
	Adjusttment adjusttment;
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
		adjusttment = new Adjusttment(container);
		addMenu();
		pack();
		setVisible(true);
		getRootPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                System.out.println("componentResized");
                container.setPreferredSize(new Dimension(getWidth(), getHeight()));
        		container.imageContainer.setBounds(0, 30, container.getPreferredSize().width, container.getPreferredSize().height);
        		container.toolbar.setSize(container.getPreferredSize().width-16, 25);
        		System.out.println(container.imageContainer.getPreferredSize());
        		repaint();
            }
		});
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
				adjusttment.setVisible(true);
			}
		});
		brightAdjust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adjusttment.setVisible(true);
			}
		});
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
