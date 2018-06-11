package canvas.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class NewFileDialog extends JFrame implements ActionListener {

	private JTextField width, height;
	private Button confirm;
	private StartWindow parent;
	
	public NewFileDialog(StartWindow parent) {
		
		super("New Drawing");
		
		this.setLayout(null);
		this.setBounds(400, 400, 300, 250);
		
		this.parent = parent;
		
		this.setupGui();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int w = 0, h = 0;
		
		try {
			
			w = Integer.parseInt(this.width.getText());
			h = Integer.parseInt(this.height.getText());
			
			while (w % 5 != 0) {
				
				w++;
				
			}
			
			while (h % 5 != 0) {
				
				h++;
				
			}
			
		}
		catch (NumberFormatException error) {
			
			this.setVisible(false);
			
		}
		
		if (this.isVisible()) {

			DrawingArea drawing = new DrawingArea(null, w, h);
			PApplet.runSketch(new String[]{""}, drawing);
			PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
			PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
			
			JFrame window = (JFrame)canvas.getFrame();
			window.setBounds(500, 300, w, h);
			window.setMinimumSize(new Dimension(w, h));
			window.setResizable(true);
			window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			drawing.setWindow(window);

			window.setVisible(true);
			
			this.setVisible(false);
			
			if (this.parent != null) {
				
				this.parent.setVisible(false);

			}
			
		}
		
	}

	private void setupGui() {
		
		JLabel wLabel = new JLabel("Image width: ");
		wLabel.setBounds(87, 50, 100, 25);
		this.add(wLabel);
		
		this.width = new JTextField(10);
		this.width.setBounds(175, 50, 50, 25);
		this.add(this.width);
		
		JLabel hLabel = new JLabel("Image height: ");
		hLabel.setBounds(87, 100, 100, 25);
		this.add(hLabel);
		
		this.height = new JTextField(10);
		this.height.setBounds(175, 100, 50, 25);
		this.add(this.height);
		
		this.confirm = new Button("Confirm");
		this.confirm.setLocation(100, 150);
		this.confirm.addActionListener(this);
		this.add(this.confirm);
		
	}
	
}
