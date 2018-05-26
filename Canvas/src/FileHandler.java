import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
	
	public static final int UNKNOWN_FIELD_ERROR = 0;
	public static final int UNKNOWN_ENTRY_ERROR = 1;
	public static final int TYPE_MISMATCH_ERROR = 2;
	
	private BufferedWriter writer;
	private FileWriter fWriter;
	
	private BufferedReader reader;
	private FileReader fReader;
		
	public FileHandler(FileWriter fw) {
		
		this.fWriter = fw;
		this.writer = new BufferedWriter(fw);

	}
	
	public FileHandler(FileReader fr) {
		
		this.fReader = fr;
		this.reader = new BufferedReader(fr);
		
	}
	
	public void parseDrawing(DrawingArea canvas) {
		
		int width = canvas.drawingWidth;
		int height = canvas.drawingHeight;
		
		try {

			this.writer.write("CANVAS IMAGE FILE");
			this.writer.newLine();
			this.writer.write("SIZE:" + width + "x" + height);
			this.writer.newLine();
			this.writer.write("BG COLOR:{" + canvas.getBackgroundColor().getRed() + ","
					 			  + canvas.getBackgroundColor().getGreen() + ","
					 			  + canvas.getBackgroundColor().getBlue() + "}");
			this.writer.newLine();
			
			for (Line l : canvas.getDrawing()) {

				this.writer.write("NEW LINE");
				this.writer.newLine();
				this.writer.write("COLOR:{" + l.getColor().getRed() + ","
			 			  + l.getColor().getGreen() + ","
			 			  + l.getColor().getBlue() + "}");
				this.writer.newLine();
				
				this.writer.write("COORDS:(" + l.getX1() + "," + l.getY1() + "),(" + l.getX2() + "," + l.getY2() + ")");
				this.writer.newLine();
				
				this.writer.write("WEIGHT:" + l.getWeight());
				this.writer.newLine();
				
				this.writer.write("END");
				this.writer.newLine();
				
			}
			
			for (Shape s : canvas.getShapes()) {
				
				this.writer.write("NEW SHAPE");
				this.writer.newLine();
				this.writer.write("TYPE:" + s.getType());
				this.writer.newLine();
				
				this.writer.write("COORDS:(" + s.getX() + "," + s.getY() + ")");
				this.writer.newLine();

				String selected = "";
				
				if (s.isSelected())
					selected = "T";
				else
					selected = "F";
				
				this.writer.write("IS SELECTED:" + selected);
				
				String drawBorder;
				
				if (s.shouldDrawBorder())
					drawBorder = "T";
				else
					drawBorder = "F";
				
				this.writer.write("DRAW BORDER:" + drawBorder);
				this.writer.newLine();
				
				Color fillColor = s.getFillColor();
				
				this.writer.write("FILL COLOR:{" + fillColor.getRed() + ","
												 + fillColor.getGreen() + ","
												 + fillColor.getBlue() + "}");
				this.writer.newLine();
				
				Color borderColor = s.getBorderColor();
				
				this.writer.write("BORDER COLOR:{" + borderColor.getRed() + ","
											   + borderColor.getGreen() + ","
											   + borderColor.getBlue() + "}");
				this.writer.newLine();
				
				String type = s.getType();
				
				if (type.equals("RECT")) {
					
					Rectangle rect = (Rectangle)s;
					
					int rWidth = rect.getWidth();
					this.writer.write("WIDTH:" + rWidth);
					this.writer.newLine();
					
					int rHeight = rect.getHeight();
					this.writer.write("HEIGHT:" + rHeight);
					this.writer.newLine();
					
				}
				else if (type.equals("TEXT")) {
					
					TextBox textBox = (TextBox)s;
					
					int rWidth = textBox.getWidth();
					this.writer.write("WIDTH:" + rWidth);
					this.writer.newLine();
					
					int rHeight = textBox.getHeight();
					this.writer.write("HEIGHT:" + rHeight);
					this.writer.newLine();
					
					String text = textBox.getText();
					this.writer.write("TEXT:'" + text + "'");
					this.writer.newLine();
					
					Color textColor = textBox.getTextColor();
					this.writer.write("TEXT COLOR:{" + textColor.getRed() + ","
													 + textColor.getGreen() + ","
													 + textColor.getBlue() + "}");
					this.writer.newLine();
					
				}
				
				this.writer.write("END");
				this.writer.newLine();
				
			}
		
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		finally {
			
				try {
					
					if (this.writer != null)
						this.writer.flush();
						this.writer.close();
				
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			
		}
		
	}
	
	public DrawingArea parseFile(File f) {
		
		try {
			
			String firstLine = this.reader.readLine();
			
			if (firstLine != null && !firstLine.equals("CANVAS IMAGE FILE")) {
				
				return null;
				
			}
			
			String secondLine = this.reader.readLine();
			
			int width = 0, height = 0;
			
			if (secondLine != null && secondLine.startsWith("SIZE")) {
				
				int separatorIndex = secondLine.indexOf("x");
				int beginIndex = secondLine.indexOf(":") + 1;
				
				width = Integer.parseInt(secondLine.substring(beginIndex, separatorIndex));
				height = Integer.parseInt(secondLine.substring(separatorIndex + 1));
				
			}
			
			String thirdLine = this.reader.readLine();
			
			Color bgColor = null;
			
			if (thirdLine != null && thirdLine.startsWith("BG COLOR")) {
				
				int startIndex = thirdLine.indexOf("{") + 1;
				
				int firstIndex = thirdLine.indexOf(",");
				int secondIndex = thirdLine.lastIndexOf(",");
				
				int r = Integer.parseInt(thirdLine.substring(startIndex, firstIndex));
				int g = Integer.parseInt(thirdLine.substring(firstIndex + 1, secondIndex));
				int b = Integer.parseInt(thirdLine.substring(secondIndex + 1, thirdLine.length() - 1));
				
				bgColor = new Color(r, g, b);
				
			}
			
			String nextLine = this.reader.readLine();
			
			ArrayList<Line> drawing = new ArrayList<Line>();
			Line l = null;
			
			while (nextLine != null) {
				
				if (nextLine.equalsIgnoreCase("NEW LINE")) {
					
					l = new Line();
					
				}
				else if (nextLine.startsWith("COLOR")) {
					
					int startIndex = nextLine.indexOf("{") + 1;
					int s1Index = nextLine.indexOf(",");
					int s2Index = nextLine.lastIndexOf(",");
					int endIndex = nextLine.indexOf("}");

					int r = Integer.parseInt(nextLine.substring(startIndex, s1Index));
					int g = Integer.parseInt(nextLine.substring(s1Index + 1, s2Index));
					int b = Integer.parseInt(nextLine.substring(s2Index + 1, endIndex));
					
					Color color = new Color(r, g, b);
					
					l.setColor(color);
					
				}
				else if (nextLine.startsWith("COORDS")) {
					
					int x1Index = nextLine.indexOf("(") + 1;
					int separator1Index = nextLine.indexOf(",");
					int y1Index = nextLine.indexOf(")");
					
					int x2Index = nextLine.lastIndexOf("(") + 1;
					int separator2Index = nextLine.lastIndexOf(",");
					int y2Index = nextLine.lastIndexOf(")");
					
					int x1 = Integer.parseInt(nextLine.substring(x1Index, separator1Index));
					int y1 = Integer.parseInt(nextLine.substring(separator1Index + 1, y1Index));
								
					int x2 = Integer.parseInt(nextLine.substring(x2Index, separator2Index));
					int y2 = Integer.parseInt(nextLine.substring(separator2Index + 1, y2Index));

					l.setPoints(x1, y1, x2, y2);
					
				}
				else if (nextLine.startsWith("WEIGHT")) {
					
					int i = nextLine.indexOf(":") + 1;
					
					String wStr = nextLine.substring(i);
					
					int w = Integer.parseInt(wStr);
					
					l.setWeight(w);
					
				}
				else if (nextLine.equalsIgnoreCase("END")) {
					
					drawing.add(l);
					
				}
				else if (nextLine.equalsIgnoreCase("NEW SHAPE")) {
					
					break;
					
				}
				else {
					
					this.throwFileError(FileHandler.UNKNOWN_FIELD_ERROR);
					
				}
				
				nextLine = this.reader.readLine();
				
			}
						
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			Shape shape = null;
			Rectangle rect = null;
			TextBox textBox = null;
			
			while (nextLine != null) {
				
				if (nextLine.equalsIgnoreCase("NEW SHAPE")) {
					
					rect = null;
					textBox = null;
					
				}
				else if (nextLine.startsWith("TYPE")) {
					
					if (nextLine.indexOf("RECT") != -1) {
						
						rect = new Rectangle();
						
					}
					else if (nextLine.indexOf("TEXT") != -1) {
						
						textBox = new TextBox();
						
					}
					
				}
				else if (nextLine.startsWith("COORDS")) {
					
					int startIndex = nextLine.indexOf("{") + 1;
					int middleIndex = nextLine.indexOf(",");
					int endIndex = nextLine.indexOf("}");
					
					int x = Integer.parseInt(nextLine.substring(startIndex, middleIndex));
					int y = Integer.parseInt(nextLine.substring(middleIndex + 1, endIndex));
					
					shape.setX(x);
					shape.setY(y);
					
				}
				else if (nextLine.startsWith("IS SELECTED")) {
					
					int i = nextLine.indexOf(":") + 1;
					
					String bool = nextLine.substring(i);
					
					if (bool.equalsIgnoreCase("T")) {
						
						shape.setSelected(true);
						
					}
					else if (bool.equalsIgnoreCase("F")) {
						
						shape.setSelected(false);
						
					}
					else {
						
						this.throwFileError(FileHandler.UNKNOWN_ENTRY_ERROR);
						
					}
					
				}
				else if (nextLine.startsWith("DRAW BORDER")) {
					
					int i = nextLine.indexOf(":") + 1;
					
					String bool = nextLine.substring(i);
					
					if (bool.equalsIgnoreCase("T")) {
						
						shape.setBorderState(true);
						
					}
					else if (bool.equalsIgnoreCase("F")) {
						
						shape.setBorderState(false);
						
					}
					else {
						
						this.throwFileError(FileHandler.UNKNOWN_ENTRY_ERROR);
						
					}
					
				}
				else if (nextLine.startsWith("FILL COLOR")) {
					
					int startIndex = nextLine.indexOf("{" + 1);
					int s1Index = nextLine.indexOf(",");
					int s2Index = nextLine.lastIndexOf(",");
					int endIndex = nextLine.indexOf("}");
					
					int r = Integer.parseInt(nextLine.substring(startIndex, s1Index));
					int g = Integer.parseInt(nextLine.substring(s1Index + 1, s2Index));
					int b = Integer.parseInt(nextLine.substring(s2Index + 1, endIndex));
					
					Color color = new Color(r, g, b);
					
					shape.setFillColor(color);
					
				}
				else if (nextLine.startsWith("BORDER COLOR")) {
					
					int startIndex = nextLine.indexOf("{" + 1);
					int s1Index = nextLine.indexOf(",");
					int s2Index = nextLine.lastIndexOf(",");
					int endIndex = nextLine.indexOf("}");
					
					int r = Integer.parseInt(nextLine.substring(startIndex, s1Index));
					int g = Integer.parseInt(nextLine.substring(s1Index + 1, s2Index));
					int b = Integer.parseInt(nextLine.substring(s2Index + 1, endIndex));
					
					Color color = new Color(r, g, b);
					
					shape.setBorderColor(color);
					
				}
				else if (nextLine.startsWith("WIDTH")) {
					
					int i = nextLine.indexOf(":") + 1;
					
					int w = Integer.parseInt(nextLine.substring(i));
					
					if (shape.getType().equals("RECT")) {
												
						rect.setWidth(w);
						
					}
					else if (shape.getType().equals("TEXT")) {
												
						textBox.setWidth(w);
						
					}
					else {
						
						this.throwFileError(FileHandler.TYPE_MISMATCH_ERROR);
						
					}
					
				}
				else if (nextLine.startsWith("HEIGHT")) {
					
					int i = nextLine.indexOf(":") + 1;
					
					int h = Integer.parseInt(nextLine.substring(i));
					
					if (shape.getType().equals("RECT")) {
						
						rect.setHeight(h);
						
					}
					else if (shape.getType().equals("TEXT")) {
						
						textBox.setHeight(h);
						
					}
					else {
						
						this.throwFileError(FileHandler.TYPE_MISMATCH_ERROR);
						
					}
					
				}
				else if (nextLine.startsWith("TEXT")) {
					
					if (shape.getType().equals("TEXT")) {
						
						int startIndex = nextLine.indexOf("'") + 1;
						int endIndex = nextLine.lastIndexOf("'");
						
						String text = nextLine.substring(startIndex, endIndex);
												
						textBox.setText(text);
						
					}
					else {
						
						this.throwFileError(FileHandler.TYPE_MISMATCH_ERROR);
						
					}
					
				}
				else if (nextLine.startsWith("TEXT COLOR")) {
					
					int startIndex = nextLine.indexOf("{" + 1);
					int s1Index = nextLine.indexOf(",");
					int s2Index = nextLine.lastIndexOf(",");
					int endIndex = nextLine.indexOf("}");
					
					int r = Integer.parseInt(nextLine.substring(startIndex, s1Index));
					int g = Integer.parseInt(nextLine.substring(s1Index + 1, s2Index));
					int b = Integer.parseInt(nextLine.substring(s2Index + 1, endIndex));
					
					Color color = new Color(r, g, b);
					
					textBox.setBorderColor(color);
					
				}
				else if (nextLine.startsWith("END")) {
					
					if (rect != null) {
						
						shapes.add(rect);
						
					}
					else if (textBox != null) {
						
						shapes.add(textBox);
						
					}
				
				}
				
			}
			
			DrawingArea canvas = new DrawingArea(bgColor, f, drawing, shapes, width, height);
			
			return canvas;
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public void throwFileError(int errorCode) {
		
		// Trigger window as warning
	}
	
}