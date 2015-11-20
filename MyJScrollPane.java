import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JScrollPane;

public class MyJScrollPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public MyJScrollPane() {
		super();
	}

	public MyJScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
	}

	public MyJScrollPane(Component view) {
		super(view);
	}

	public MyJScrollPane(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
	}

	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	protected void paintChildren(Graphics g) {
		setForeground(new Color(0,0,0,0));
		setBackground(new Color(0,0,0,0));
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintChildren(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
//		super.paintBorder(g);
	}

//	@Override
//	public void paint(Graphics g) {
////		super.paint(g);
//	}
	

}
