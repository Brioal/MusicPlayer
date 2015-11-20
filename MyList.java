import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class MyList extends JList {

	private static final long serialVersionUID = 1L;

	public MyList() {
		super();
	}

	public MyList(ListModel dataModel) {
		super(dataModel);
	}

	public MyList(Object[] listData) {
		super(listData);
	}

	public MyList(Vector listData) {
		super(listData);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
	}
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}
