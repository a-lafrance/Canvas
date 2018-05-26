import java.awt.Color;
import java.awt.Graphics;

import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class Shape {

	private int x, y;
	private boolean isSelected, drawBorder;
	private Color fillColor, borderColor;
	public static final Color SELECTION_COLOR = new Color(0, 150, 255);
	
	public abstract void draw(PApplet surface, PGraphics graphics);
	public abstract int getArea();
	public abstract boolean isPointInShape(int x, int y);
	public abstract boolean isPointOnBorder(int x, int y);
	public abstract String getType();
	
	public Shape() {
		
		this.x = 0;
		this.y = 0;
		
		this.isSelected = false;
		this.drawBorder = true;
		
		this.fillColor = Color.LIGHT_GRAY;
		this.borderColor = Color.BLACK;
		
	}
	
	public Shape(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		this.isSelected = false;
		this.drawBorder = true;
		
		this.fillColor = Color.LIGHT_GRAY;
		this.borderColor = Color.BLACK;
		
	}
	
	public int getX() {
		
		return this.x;
		
	}
	
	public int getY() {
		
		return this.y;
		
	}
	
	public boolean isSelected() {
		
		return this.isSelected;
		
	}
	
	public Color getFillColor() {
		
		return this.fillColor;
		
	}
	
	public Color getBorderColor() {
		
		return this.borderColor;
		
	}
	
	public void setX(int newX) {
		
		this.x = newX;
		
	}
	
	public void setY(int newY) {
		
		this.y = newY;
		
	}
	
	public void setSelected(boolean isSelected) {
		
		this.isSelected = isSelected;
		
	}
	
	public void toggleState() {
		
		this.isSelected = !this.isSelected;
		
	}
	
	public boolean shouldDrawBorder() {
		
		return this.drawBorder;
		
	}
	
	public void setBorderState(boolean newState) {
		
		this.drawBorder = newState;
		
	}
	
	public void toggleBorderState() {
		
		this.drawBorder = !this.drawBorder;
		
	}
	
	public void setFillColor(Color newColor) {
		
		this.fillColor = newColor;
		
	}
	
	public void setBorderColor(Color newColor) {
		
		this.borderColor = newColor;
		
	}
	
}
