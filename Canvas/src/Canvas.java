import javax.swing.JFrame;

public class Canvas {

	public static void main(String[] args) {
		
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		StartWindow window = new StartWindow();
		window.setBounds(500, 300, 300, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
	
}
