import java.io.File;

import javax.swing.filechooser.FileFilter;

public class GifFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		
		if (f.isDirectory())
			return false;
		
		String name = f.getName();
		
		return name.endsWith(".gif") || name.endsWith(".GIF");
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		
		return "GIF (.gif)";
	
	}

}
