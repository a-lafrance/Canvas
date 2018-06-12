package canvas.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ToolBar extends JPanel implements ActionListener {

	
	private final Font buttonFont = new Font("Sans Serif", Font.PLAIN, 14);
	private DrawingArea canvas;
	
	public ToolBar(DrawingArea canvas) {
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);

		this.canvas = canvas;
		
		this.setupGui();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
	}
	
	private void setupGui() {
		
		
	}

	public void updateGui() {
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}
	
}
