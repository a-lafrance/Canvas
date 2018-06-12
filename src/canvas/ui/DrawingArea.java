package canvas.ui;
import java.awt.Color;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

import canvas.shapes.Line;
import canvas.shapes.Shape;

public class DrawingArea extends PApplet implements WindowListener {
	
	/*
	 * Fix FileHandler and opening/saving files, etc
	 */
	
	private Color currentColor, backgroundColor;
	private int currentThickness;
	public int drawingWidth, drawingHeight;
	private boolean drawModeSelected;
	private PGraphics graphics;
	private JFrame window;
	private ToolBar toolbar;
	private MenuBar menuBar;
	private boolean wasEditedSinceSave;
	private File file;
	private ArrayList<Line> drawing;
	private ArrayList<Shape> shapes;
	
	public DrawingArea(JFrame window, int w, int h) {
					
		this.currentColor = Color.BLACK;
		this.backgroundColor = Color.WHITE;
		
		this.currentThickness = 5;
		
		this.drawModeSelected = true;
				
		this.wasEditedSinceSave = false;
		
		this.file = null;
		
		this.drawingWidth = w;
		this.drawingHeight = h;
		
		this.drawing = new ArrayList<Line>();
		this.shapes = new ArrayList<Shape>();
				
		this.window = window;
		this.setupWindow();
				
	}
	
	public DrawingArea(File f, JFrame window, int w, int h) {
		
		this.currentColor = Color.BLACK;
		this.backgroundColor = Color.WHITE;

		this.currentThickness = 5;
		
		this.drawModeSelected = true;
				
		this.wasEditedSinceSave = false;

		this.file = f;
		
		this.drawingWidth = w;
		this.drawingHeight = h;
		
		this.drawing = new ArrayList<Line>();
		this.shapes = new ArrayList<Shape>();
		
		this.window = window;
		this.setupWindow();

	}
	
	public DrawingArea(Color bgColor, File f, ArrayList<Line> drawing, ArrayList<Shape> shapes, int w, int h) {
		
		this.currentColor = Color.BLACK;
		this.backgroundColor = bgColor;

		this.currentThickness = 5;
		
		this.drawModeSelected = true;
				
		this.wasEditedSinceSave = false;

		this.file = f;
		
		this.drawing = drawing;
		this.shapes = shapes;
		
		this.drawingWidth = w;
		this.drawingHeight = h;
		
		this.window = null;
		this.setupWindow();
		
	}
	
	public void settings() {
		
		this.size(500, 500);
		
	}
	
	public void setup() {
	
		this.graphics = this.createGraphics(this.width, this.height);

		this.background(this.backgroundColor.getRed(), this.backgroundColor.getGreen(), this.backgroundColor.getBlue());
		
		this.graphics.beginDraw();
		this.graphics.background(255);
		this.graphics.endDraw();
		
	}
	
	public void draw() {
		
		this.graphics.beginDraw();
		
		this.background(this.backgroundColor.getRed(), this.backgroundColor.getGreen(), this.backgroundColor.getBlue());
		
		for (int i = 0; i < this.drawing.size(); i++) {
			
			Line l = (Line)this.drawing.toArray()[i];
			l.draw(this, this.graphics);
			
		}
		
		for (int i = 0; i < this.shapes.size(); i++) {
			
			Shape s = (Shape)this.shapes.toArray()[i];
			s.draw(this, this.graphics);
			
		}
		
		this.graphics.endDraw();
		
	}
	
	public void mousePressed() {
		
		boolean shapeWasSelected = false;
		
		for (int i = 0; i < this.shapes.size(); i++) {
			
			Shape s = (Shape)this.shapes.toArray()[i];
			
			if (s.isPointInShape(this.mouseX, this.mouseY)) {
				
				s.setSelected(true);
				shapeWasSelected = true;
				
			}
			else {
				System.out.println("hi");
				if (s.isSelected()) {
					
					s.setSelected(false);
					return;
					
				}
				else {
					
					s.setSelected(false);
					
				}
				
			}
			
		}

		if (!shapeWasSelected) {
			
			Line l = new Line(this.mouseX, this.mouseY, this.pmouseX, this.pmouseY, this.currentColor, this.currentThickness);
			this.drawing.add(l);
			
			this.wasEdited();
			
		}

	}
	
	public void addShape(Shape shape) {
		
		this.shapes.add(shape);
		
	}
	
	public void mouseDragged() {
		
		boolean shapeWasMoved = false;
		
		for (int i = 0; i < this.shapes.size(); i++) {
			
			Shape s = (Shape)this.shapes.toArray()[i];
			
			if (s.isSelected()) {
				
				s.moveTo(this.mouseX, this.mouseY);
				shapeWasMoved = true;
				
			}
			
		}
		
		if (!shapeWasMoved) {
			
			Line l = new Line(this.mouseX, this.mouseY, this.pmouseX, this.pmouseY, this.currentColor, this.currentThickness);
			this.drawing.add(l);
						
		}
		
		this.wasEdited();

	}
	
	public BufferedImage getImage() {
		
		return (BufferedImage)this.graphics.image;
		
	}
	
	public void setImageFile(File f) {
		
		this.file = f;
		
	}
	
	public Color getColor() {
		
		return this.currentColor;
		
	}
	
	public File getImageFile() {
		
		return this.file;
		
	}
	
	public Color getBackgroundColor() {
		
		return this.backgroundColor;
		
	}
	
	public Line[] getDrawing() {
		
		Line[] drawing = new Line[this.drawing.size()];
		
		for (int i = 0; i < drawing.length; i++) {
			
			Line l = (Line)this.drawing.toArray()[i];
			drawing[i] = l;
			
		}
		
		return drawing;
		
	}
	
	public Shape[] getShapes() {
		
		Shape[] shapes = new Shape[this.shapes.size()];
		
		for (int i = 0; i < shapes.length; i++) {
			
			Shape l = (Shape)this.shapes.toArray()[i];
			shapes[i] = l;
			
		}
		
		return shapes;
	
	}
	
	public void setColor(Color c) {
		
		this.currentColor = c;
		
	}
	
	public void setWeight(int w) {
		
		this.currentThickness = w;
		
	}
	
	public void setWindow(JFrame window) {
		
		this.window = window;
		this.setupWindow();
		
	}
	
	public void setDrawingMode(boolean drawModeSelected) {
		
		this.drawModeSelected = drawModeSelected;
		
	}
	
	public void setTitle(String str) {
		
		this.window.setTitle(str);
		
	}
	
	public void setBackgroundColor(int r, int g, int b) {
		
		this.backgroundColor = new Color(r, g, b);
		
	}
	
	public void wasEdited() {
		
		this.wasEditedSinceSave = true;
		
		if (this.file != null)
			this.window.setTitle(this.file.getName() + "* - " + this.drawingWidth + " x " + this.drawingHeight);
		else
			this.window.setTitle("Untitled* - " + this.drawingWidth + " x " + this.drawingHeight);
	
	}
	
	public void wasSaved() {
		
		this.wasEditedSinceSave = false;
		
		if (this.file != null)
			this.window.setTitle(this.file.getName() + " - " + this.drawingWidth + " x " + this.drawingHeight);
		else
			this.window.setTitle("Untitled - " + this.drawingWidth + " x " + this.drawingHeight);

	}
	
	public void close() {
		System.out.println("closing");
		this.window.dispose();
		
	}
	
	private void setupWindow() {
		
		String name = "";
		
		if (this.file != null)
			name = this.file.getName();
		else
			name = "Untitled";
		
		if (this.window != null) {
			
			this.window.setTitle(name + " - " + this.drawingWidth + " x " + this.drawingHeight);
			
			this.menuBar = new MenuBar(this, this.toolbar);
			this.window.setJMenuBar(this.menuBar);
			
			this.window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			
			this.window.addWindowListener(this);
			
		}
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

		if (this.wasEditedSinceSave) {
			
			CloseWindow window = new CloseWindow(this);
			window.setVisible(true);
			
		}
		else {
			
			this.window.setVisible(false);
			
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
