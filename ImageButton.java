import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
		/**
	 * 
	 */
	private static final long serialVersionUID = -559410900146662427L;

		public ImageButton(String path){
		super();
		setMargin(new Insets(0,0,0,0));//设置边距
		setContentAreaFilled(false);//不绘制按钮区域
		setBorderPainted(false);//不绘制边框
		setIcon(new ImageIcon(path));//设置默认图片
		}
		}
	


