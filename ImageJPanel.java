import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1870181926901919317L;

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon icon = new ImageIcon("back.jpg");
	    g.drawImage(icon.getImage(), 0, 0, 400,200,null);
//	    ImageIcon img = new ImageIcon(ImgJframeTest.class.getResource("\\img\\2.jpg"));
//	    icon.paintIcon(this, g, 0, 0);

	}
}
