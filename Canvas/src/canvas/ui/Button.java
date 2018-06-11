package canvas.ui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton {

	public Button(String label) {
		
		super(label);
		
		Font font = new Font("Sans Serif", Font.PLAIN, 14);
		
		this.setBorderPainted(false);
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setFont(font);
		this.setSize(100, 50);
		
	}
	
}
