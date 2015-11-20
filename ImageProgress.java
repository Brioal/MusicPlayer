import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JProgressBar;

public class ImageProgress extends JProgressBar {
	private static final long serialVersionUID = -1995799145083101425L;
	public  ImageProgress() {
		super();
		
	}
	@Override
	public void paint(Graphics g) {
		setBackground(new Color(0,0,0,0));
		setBorderPainted(false);//²»»æÖÆ±ß¿ò
		super.paint(g);
	}

	
}
