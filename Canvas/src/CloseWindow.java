import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class CloseWindow extends JFrame implements ActionListener {

	private Button closeButton, cancelButton;
	private DrawingArea canvas;
	
	public CloseWindow(DrawingArea canvas) {
		
		super("File Not Saved");
		
		this.setLayout(null);
		this.setBounds(500, 300, 300, 400);

		this.canvas = canvas;
		
		JLabel label = null;
		
		if (this.canvas.getImageFile() != null)
			label = new JLabel("Are you sure you want to close '" + this.canvas.getImageFile().getName() + "'?\nUnsaved data will be lost.");
		else
			label = new JLabel("Are you sure you want to close 'Untitled'?\nUnsaved data will be lost.");
		
		label.setBounds(50, 75, 250, 50);
		this.add(label);
		
		this.closeButton = new Button("Close");
		this.closeButton.setBounds(100, 150, 100, 50);
		this.closeButton.addActionListener(this);
		this.add(this.closeButton);
		
		this.cancelButton = new Button("Cancel");
		this.cancelButton.setBounds(100, 250, 100, 50);
		this.cancelButton.addActionListener(this);
		this.add(this.cancelButton);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Button source = (Button)e.getSource();
		
		if (source == this.closeButton) {

			this.canvas.close();
			this.setVisible(false);
			
		}
		else {
			
			this.setVisible(false);
			
		}
		
	}

}
