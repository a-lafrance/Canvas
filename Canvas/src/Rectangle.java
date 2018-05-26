import java.awt.Color;
import java.awt.Graphics;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Rectangle extends Shape {

	private int width, height;
	
	public Rectangle() {
		
		super();
		
		this.width = 50;
		this.height = 50;
		
	}
	
	public Rectangle(int x, int y, int width, int height) {
		
		super(x, y);
		
		this.width = width;
		this.height = height;
		
	}
	
	public String getType() {
		
		return "RECT";
		
	}
	
	@Override
	public void draw(PApplet surface, PGraphics graphics) {
		// TODO Auto-generated method stub

		surface.fill(this.getFillColor().getRed(), this.getFillColor().getGreen(), this.getFillColor().getBlue());
		graphics.fill(this.getFillColor().getRed(), this.getFillColor().getGreen(), this.getFillColor().getBlue());

		surface.stroke(this.getBorderColor().getRed(), this.getBorderColor().getGreen(), this.getBorderColor().getBlue());
		graphics.stroke(this.getBorderColor().getRed(), this.getBorderColor().getGreen(), this.getBorderColor().getBlue());

		surface.strokeWeight(1);
		graphics.strokeWeight(1);
		
		surface.rectMode(PApplet.CENTER);
		surface.rect(this.getX(), this.getY(), this.width, this.height);
		
		graphics.rectMode(PApplet.CENTER);
		graphics.rect(this.getX(), this.getY(), this.width, this.height);
		
		if (this.isSelected()) {
			
			surface.fill(Shape.SELECTION_COLOR.getRed(), Shape.SELECTION_COLOR.getGreen(), Shape.SELECTION_COLOR.getBlue());
			graphics.fill(Shape.SELECTION_COLOR.getRed(), Shape.SELECTION_COLOR.getGreen(), Shape.SELECTION_COLOR.getBlue());

			surface.rect(this.getX() - 3 - this.width / 2, this.getY() - 3 - this.height / 2, 6, 6);
			surface.rect(this.getX() + this.width / 2 + 3, this.getY() - 3 - this.height / 2, 6, 6);
			surface.rect(this.getX() - 3 - this.width / 2, this.getY() + this.height / 2 + 3, 6, 6);
			surface.rect(this.getX() + this.width / 2 + 3, this.getY() + this.height / 2 + 3, 6, 6);
			
			graphics.rect(this.getX() - 3 - this.width / 2, this.getY() - 3 - this.height / 2, 6, 6);
			graphics.rect(this.getX() + this.width / 2 + 3, this.getY() - 3 - this.height / 2, 6, 6);
			graphics.rect(this.getX() - 3 - this.width / 2, this.getY() + this.height / 2 + 3, 6, 6);
			graphics.rect(this.getX() + this.width / 2 + 3, this.getY() + this.height / 2 + 3, 6, 6);
			
		}
		
	}

	@Override
	public int getArea() {
		// TODO Auto-generated method stub
		
		return this.width * this.height;
		
	}

	@Override
	public boolean isPointInShape(int x, int y) {
		// TODO Auto-generated method stub
		
		if (x > this.getX() - this.width / 2 && x < this.getX() + this.width / 2) {
			
			if (y > this.getY() - this.height / 2 && y < this.getY() + this.height / 2) {
				
				return true;
				
			}
			else {
				
				return false;
				
			}
			
		}
		else {
			
			return false;
			
		}	
		
	}
	
	public boolean isPointOnBorder(int x, int y) {
		
		if (x >= this.getX() && x <= this.getX() + this.width) {
			
			if (y == this.getY() || y == this.getY() + this.height) {
				
				return true;
				
			}
			else {
				
				return false;
				
			}
			
		}
		else if (y >= this.getY() && y <= this.getY() + this.height) {
			
			if (x == this.getX() || x == this.getX() + this.width) {
				
				return true;
				
			}
			else {
				
				return false;
				
			}

		}
		else {
			
			return false;
			
		}
		
	}
	
	public int getWidth() {
		
		return this.width;
		
	}
	
	public void setWidth(int w) {
		
		this.width = w;
		
	}
	
	public int getHeight() {
		
		return this.height;
		
	}
	
	public void setHeight(int h) {
		
		this.height = h;
		
	}

}
