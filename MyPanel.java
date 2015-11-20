import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	ImageIcon image  ;
	Color mycolor ;

	public void setImage(String filepath) {
		image = new ImageIcon(filepath) ;
	}
	public void setColor(int i){
		mycolor = new Color(255,255,255,i) ;
	}

	public MyPanel(String filepath) {
		super();
		image = new ImageIcon(filepath) ;
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;// ×ª»¯Îª2d
		// ¿¹¾â³Ý
		g2d.drawImage(image.getImage(), 0, 0, 1000, 600, null);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(mycolor!=null){
			g2d.setColor(Color.DARK_GRAY);
		g2d.setColor(mycolor);
		g2d.setStroke(new BasicStroke(3));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	}

}
