import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class MenuBar extends JMenuBar implements ActionListener {

	private DrawingArea canvas;
	private ToolBar toolbar;
	private JMenuItem newFile, open, save, saveAs, export, 
	  selectAll, cut, copy, paste,
	  square, rect, circle, line, text, image,
	  draw, fill, select, erase;
	
	public MenuBar(DrawingArea canvas, ToolBar toolbar) {
		
		super();
		
		this.canvas = canvas;
		this.toolbar = toolbar;
		
		this.initializeMenuBar();
		
	}
	
	private void initializeMenuBar() {
		
		// File menu
		JMenu fileMenu = new JMenu("File");
        this.add(fileMenu);

        this.newFile = new JMenuItem("New", KeyEvent.VK_N);
        this.newFile.addActionListener(this);
        fileMenu.add(newFile);
        
        this.open = new JMenuItem("Open", KeyEvent.VK_O);
        this.open.addActionListener(this);
        fileMenu.add(open);
        
        this.save = new JMenuItem("Save", KeyEvent.VK_S);
        this.save.addActionListener(this);
        fileMenu.add(save);
        
        this.saveAs = new JMenuItem("Save as");
        this.saveAs.addActionListener(this);
        fileMenu.add(saveAs);
        
        this.export = new JMenuItem("Export");
        this.export.addActionListener(this);
        fileMenu.add(this.export);
        
        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        this.add(editMenu);
        
        this.selectAll = new JMenuItem("Select All", KeyEvent.VK_A);
        editMenu.add(selectAll);
        
        this.cut = new JMenuItem("Cut", KeyEvent.VK_X);
        editMenu.add(cut);
        
        this.copy = new JMenuItem("Copy", KeyEvent.VK_C);
        editMenu.add(copy);
        
        this.paste = new JMenuItem("Paste", KeyEvent.VK_V);
        editMenu.add(paste);
        
        // Insert menu
        JMenu insertMenu = new JMenu("Insert");
        this.add(insertMenu);
        
        JMenu shapeMenu = new JMenu("Shape");
        insertMenu.add(shapeMenu);
        
        this.square = new JMenuItem("Square");
        this.square.addActionListener(this);
        shapeMenu.add(square);
        
        this.rect = new JMenuItem("Rectangle");
        this.rect.addActionListener(this);
        shapeMenu.add(rect);
        
        this.circle = new JMenuItem("Circle");
        this.circle.addActionListener(this);
        shapeMenu.add(circle);
        
        this.line = new JMenuItem("Line", KeyEvent.VK_L);
        insertMenu.add(line);
        
        this.text = new JMenuItem("Text Box", KeyEvent.VK_T);
        insertMenu.add(text);
        
        this.image = new JMenuItem("Image", KeyEvent.VK_I);
        insertMenu.add(image);
        
        // Tools menu
        JMenu toolsMenu = new JMenu("Tools");
        this.add(toolsMenu);
        
        JMenu drawingMode = new JMenu("Select Drawing Mode");
        toolsMenu.add(drawingMode);
        
        ButtonGroup group = new ButtonGroup();
        
        this.draw = new JRadioButtonMenuItem("Draw");
        this.draw.setSelected(true);
        this.draw.addActionListener(this);
        group.add(this.draw);
        drawingMode.add(draw);
        
        this.erase = new JRadioButtonMenuItem("Erase");
        this.erase.addActionListener(this);
        group.add(this.erase);
        drawingMode.add(erase);
        
        this.fill = new JRadioButtonMenuItem("Fill");
        this.fill.addActionListener(this);
        group.add(this.fill);
        drawingMode.add(fill);
        
        this.select = new JRadioButtonMenuItem("Select");
        this.select.addActionListener(this);
        group.add(this.select);
        drawingMode.add(select);
        
//        this.changeColor = new JMenuItem("Change Color");
//        this.changeColor.addActionListener(this);
//        toolsMenu.add(changeColor);
//        
//        this.changeBackground = new JMenuItem("Change Background Color");
//        this.changeBackground.addActionListener(this);
//        toolsMenu.add(this.changeBackground);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		JMenuItem source = (JMenuItem)e.getSource();
		
//		if (source == this.changeColor) {
//			
//			ColorPicker c = new ColorPicker(this.canvas, this.toolbar, false);
//			c.setBounds(400, 400, 150, 300);
//			c.setVisible(true);
//			
//		}
		 if (source == this.export) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new PngFilter());
			fileChooser.addChoosableFileFilter(new GifFilter());
			fileChooser.addChoosableFileFilter(new JpgFilter());

			int result = fileChooser.showSaveDialog(this);
			
			String ext = "";
			
			if (result == JFileChooser.APPROVE_OPTION) {
				
				String extension = fileChooser.getFileFilter().getDescription();
				
				if (extension.equals("PNG (.png)")) {
					
					ext = ".png";
					
				}
				else if (extension.equals("GIF (.gif)")) {
					
					ext = ".gif";
					
				}
				else if (extension.equals("JPG (.jpg)")) {
					
					ext = ".jpg";
					
				}
				
			}
			
			try {
				
				File file = fileChooser.getSelectedFile();
				
				if (file != null)
					ImageIO.write(this.canvas.getImage(), ext.substring(1), file);
			
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
			
		}
		else if (source == this.newFile) {

			NewFileDialog panel = new NewFileDialog(null);
			panel.setVisible(true);
			
		}
		else if (source == this.saveAs) {
			
			JFileChooser fc = new JFileChooser();
			fc.addChoosableFileFilter(new CifFilter());
			
			int result = fc.showSaveDialog(this);
			
			String ext = "";
			
			if (result == JFileChooser.APPROVE_OPTION) {
				
				ext = ".txt";
				
			}
			
			File f = fc.getSelectedFile();
			
			if (f != null) {
				
				this.canvas.setImageFile(f);
				
				this.canvas.wasSaved();
				
				try {
					
					FileWriter fWriter = new FileWriter(f);
					FileHandler fHandler = new FileHandler(fWriter);

					fHandler.parseDrawing(this.canvas);

					fc.setVisible(false);
					
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
				}
				
			}
			
		}
		else if (source == this.open) {
			
			JFileChooser fc = new JFileChooser();
			int result = fc.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				
				File f = fc.getSelectedFile();
				
				if (f != null) {
					
					try {
						
						FileReader reader = new FileReader(f);
						FileHandler fHandler = new FileHandler(reader);
						
						DrawingArea canvas = fHandler.parseFile(f);
						
						PApplet.runSketch(new String[]{"Canvas"}, canvas);
						PSurfaceAWT surf = (PSurfaceAWT) canvas.getSurface();
						PSurfaceAWT.SmoothCanvas sc = (PSurfaceAWT.SmoothCanvas) surf.getNative();
						
						JFrame window = (JFrame)sc.getFrame();
						window.setBounds(500, 300, canvas.drawingWidth, canvas.drawingHeight);
						window.setMinimumSize(new Dimension(canvas.drawingWidth, canvas.drawingHeight));
						window.setResizable(false);
						canvas.setWindow(window);

						window.setVisible(true);
							
						fc.setVisible(false);

					}
					catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
					
						e1.printStackTrace();
					
					}
					
				}
				
			}
			
		}
		else if (source == this.save) {
			
			try {
				
				FileWriter fWriter = new FileWriter(this.canvas.getImageFile());
				FileHandler fHandler = new FileHandler(fWriter);

				fHandler.parseDrawing(this.canvas);
				
				this.canvas.wasSaved();
				
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
			
		}
		else if (source == this.draw) {
			
			this.canvas.setDrawingMode(true);
			
		}
		else if (source == this.erase) {
			
			this.canvas.setDrawingMode(false);
			
		}
//		else if (source == this.changeBackground) {
//			
//			ColorPicker c = new ColorPicker(this.canvas, this.toolbar, true);
//			c.setBounds(400, 400, 150, 300);
//			c.setVisible(true);
//			
//		}
		else if (source == this.square) {
			
			//this.canvas.getDrawing().addShape(new Rectangle(this.canvas.getX() + this.canvas.getDrawing().getImageWidth() / 3, 
			//											 this.canvas.getY() + this.canvas.getDrawing().getImageHeight() / 2,
			//											 60, 60));
			
		}
		
	}

}
