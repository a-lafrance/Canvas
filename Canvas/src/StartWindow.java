import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StartWindow extends JFrame implements ActionListener {

	private Button newButton, openButton;
	
	public StartWindow() {
		
		super("Canvas");
		
		this.setLayout(null);
		
		this.newButton = new Button("Create New Drawing");
		this.newButton.setBounds(50, 100, 200, 50);
		this.newButton.addActionListener(this);
		this.add(this.newButton);
		
		this.openButton = new Button("Open Existing Drawing");
		this.openButton.setBounds(40, 200, 220, 50);
		this.openButton.addActionListener(this);
		this.add(this.openButton);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Button source = (Button)e.getSource();
		
		if (source == this.newButton) {

			NewFileDialog panel = new NewFileDialog(this);
			panel.setVisible(true);
			
		}
		else {
			
			JFileChooser fc = new JFileChooser();
			int result = fc.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				
				File f = fc.getSelectedFile();
				
				try {
					
					FileReader reader = new FileReader(f);
					FileHandler fHandler = new FileHandler(reader);

					DrawingArea canvas = fHandler.parseFile(f);

					PApplet.runSketch(new String[]{"Canvas"}, canvas);
					PSurfaceAWT surf = (PSurfaceAWT) canvas.getSurface();
					PSurfaceAWT.SmoothCanvas sc = (PSurfaceAWT.SmoothCanvas) surf.getNative();
					
					JFrame window = (JFrame)sc.getFrame();
					window.setBounds(500, 300, canvas.width, canvas.height);
					window.setMinimumSize(new Dimension(canvas.width, canvas.height));
					window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					window.setResizable(true);
					canvas.setWindow(window);

					window.setVisible(true);
					
					this.setVisible(false);
					
				}
				catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
				
					e1.printStackTrace();
				
				}
				
			}
			
		}
		
	}

}
