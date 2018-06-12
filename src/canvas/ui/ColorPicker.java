package canvas.ui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ColorPicker extends JFrame implements ActionListener {

	private Button confirm;
	private JTextField red, green, blue;
	private DrawingArea canvas;
	private ToolBar toolbar;
	private boolean shouldChangeBackground;
	
	public ColorPicker(DrawingArea canvas, ToolBar toolbar, boolean shouldChangeBackground) {
		
		super("Color Picker");
		
		this.canvas = canvas;
		this.toolbar = toolbar;
		
		this.shouldChangeBackground = shouldChangeBackground;
		
		this.setLayout(null);
		this.setupGui();
				
	}
	
	private void setupGui() {

		JLabel redLabel = new JLabel("Red: ");
		redLabel.setBounds(50, 50, 50, 25);
		this.add(redLabel);
		
		this.red = new JTextField(10);
		this.red.setEditable(true);
		this.red.setBounds(100, 50, 35, 25);
		this.add(this.red);
		
		JLabel greenLabel = new JLabel("Green: ");
		greenLabel.setBounds(50, 100, 50, 25);
		this.add(greenLabel);
		
		this.green = new JTextField(10);
		this.green.setEditable(true);
		this.green.setBounds(100, 100, 35, 25);
		this.add(this.green);
		
		JLabel blueLabel = new JLabel("Blue: ");
		blueLabel.setBounds(50, 150, 50, 25);
		this.add(blueLabel);
		
		this.blue = new JTextField(10);
		this.blue.setEditable(true);
		this.blue.setBounds(100, 150, 35, 25);
		this.add(this.blue);
		
		this.confirm = new Button("Confirm");
		this.confirm.setLocation(25, 200);
		this.confirm.addActionListener(this);
		this.add(this.confirm);
				
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		int red, green, blue;
		
		if (this.red.getText().equals("")) {
			
			red = 0;

		}
		else {
			
			try {
				
				red = Integer.parseInt(this.red.getText());

			}
			catch (NumberFormatException e) {
				
				
			}
			
			try {
				
				red = (int)Double.parseDouble(this.red.getText());

			}
			catch (NumberFormatException e) {
				
				red = 0;
				
			}

		}
		
		if (this.green.getText().equals("")) {
			
			green = 0;

		}
		else {
			
			try {
				
				green = Integer.parseInt(this.green.getText());

			}
			catch (NumberFormatException e) {
				
				
			}
			
			try {
				
				green = (int)Double.parseDouble(this.green.getText());

			}
			catch (NumberFormatException e) {
				
				green = 0;
				
			}

		}
		
		if (this.blue.getText().equals("")) {
			
			blue = 0;

		}
		else {
			
			try {
				
				blue = Integer.parseInt(this.blue.getText());

			}
			catch (NumberFormatException e) {
				
				
			}
			
			try {
				
				blue = (int)Double.parseDouble(this.blue.getText());

			}
			catch (NumberFormatException e) {
				
				blue = 0;
				
			}

		}
		
		if (red > 255)
			red = 255;
		else if (red < 0)
			red = 0;
		
		if (green > 255)
			green = 255;
		else if (green < 0)
			green = 0;
		
		if (blue > 255)
			blue = 255;
		else if (blue < 0)
			blue = 0;
		
		if (this.shouldChangeBackground) {
			
			this.canvas.setBackgroundColor(red, green, blue);
			
		}
		else {
			
			this.canvas.setColor(new Color(red, green, blue));
			
		}
		
		this.setVisible(false);
		
	}
	
}
