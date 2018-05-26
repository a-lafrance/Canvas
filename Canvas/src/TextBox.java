import java.awt.Color;
import java.awt.Graphics;

import processing.core.PApplet;
import processing.core.PGraphics;

public class TextBox extends Rectangle {

	private String text;
	private int align;
	private Color textColor;
	
	public TextBox() {
		
		super();
		
		this.text = "";
		this.textColor = Color.BLACK;
		this.align = PApplet.CENTER;
		
	}
	
	public TextBox(int x, int y) {
		// TODO Auto-generated constructor stub
		
		super(x, y, 250, 50);

		this.text = "";
		this.textColor = Color.BLACK;
		this.align = PApplet.CENTER;

	}

	public String getType() {
		
		return "TEXT";
		
	}
	
	public Color getTextColor() {
		
		return this.textColor;
		
	}
	
	@Override
	public void draw(PApplet surface, PGraphics graphics) {
		// TODO Auto-generated method stub

		super.draw(surface, graphics);
		
		surface.fill(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());
		graphics.fill(this.textColor.getRed(), this.textColor.getGreen(), this.textColor.getBlue());

		surface.textAlign(this.align);
		graphics.textAlign(this.align);

		surface.text(this.text, this.getX() - this.getWidth() / 2, this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());
		graphics.text(this.text, this.getX() - this.getWidth() / 2, this.getY() - this.getHeight() / 2, this.getWidth(), this.getHeight());

	}
	
	public String getText() {
		
		return this.text;
		
	}
	
	public int getAlignment() {
		
		return this.align;
		
	}
	
	public void setAlignment(int a) {
		
		this.align = a;
		
	}
	
	public void setText(String newText) {
		
		this.text = newText;
		
	}
	
	public void append(String str) {
		
		this.text += str;
		
	}
	
	public void setTextColor(Color newColor) {
		
		this.textColor = newColor;
		
	}

}
