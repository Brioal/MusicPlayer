import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class BlankPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Color mycolor;
	
	public  static void setColor(int i){
		mycolor = new Color(255,255,255,i) ;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;// ×ª»¯Îª2d
		// ¿¹¾â³Ý
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(mycolor!=null){
			g2d.setColor(Color.DARK_GRAY);
		g2d.setColor(new Color(0,0,0,255));
		g2d.setStroke(new BasicStroke(3));
		g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
	}else{
		g2d.setColor(Color.DARK_GRAY);
		g2d.setColor(new Color(255,255,255,40));
		g2d.setStroke(new BasicStroke(3));
		g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
	}
	}
}
