import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

public class MyJList extends JList{

	

	private static final long serialVersionUID = 1L;

	public MyJList() {
		super();
	}

	public MyJList(ListModel dataModel) {
		super(dataModel);
	}

	public MyJList(Object[] listData) {
		super(listData);
	}

	public MyJList(Vector listData) {
		super(listData);
	}

	@Override
	protected void paintComponent(Graphics g) {
		setBackground(new Color(0,0,0,0));
		g.setColor(new Color(0,0,0,100));
		super.paintComponent(g);
		setSelectionBackground(getBackground());
	}

	@Override
	protected void paintChildren(Graphics g) {
	}

	@Override
	protected void paintBorder(Graphics g) {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}
