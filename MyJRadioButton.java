import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JRadioButton;

public class MyJRadioButton extends JRadioButton {

	private static final long serialVersionUID = 1L;

	public MyJRadioButton(String text, Icon icon) {
		super(text, icon);
	}

	public MyJRadioButton(String text) {
		super(text);
	}
	
	@Override
	public void setBackground(Color bg) {
		super.setBackground(new Color(0,0,0,0));
	}

}
