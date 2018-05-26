import java.awt.Color;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Line {

	private int x1, y1, x2, y2, weight;
	private Color color;
	
	public Line() {
		
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		
		this.color = Color.BLACK;
		
		this.weight = 5;
		
	}
	
	public Line(int x1, int y1, int x2, int y2, Color color, int w) {
		
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
		this.color = color;
		
		this.weight = w;
		
	}
	
	public void draw(PApplet surface, PGraphics graphics) {
		
		surface.stroke(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
		graphics.stroke(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
		
		surface.strokeWeight(this.weight);
		graphics.strokeWeight(this.weight);

		surface.line(this.x1, this.y1, this.x2, this.y2);
		graphics.line(this.x1, this.y1, this.x2, this.y2);

	}
	
	public Color getColor() {
		
		return this.color;
		
	}
	
	public int getX1() {
		
		return this.x1;
		
	}
	
	public int getY1() {
		
		return this.y1;
		
	}
	
	public int getX2() {
		
		return this.x2;
		
	}
	
	public int getY2() {
		
		return this.y2;
		
	}
	
	public int getWeight() {
		
		return this.weight;
		
	}
	
	public void setWeight(int w) {
		
		this.weight = w;
		
	}
	
	public void setPoints(int x1, int y1, int x2, int y2) {
		
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
	}
	
	public void setColor(Color newColor) {
		
		this.color = newColor;
		
	}
	
}
