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
		setMargin(new Insets(0,0,0,0));//���ñ߾�
		setContentAreaFilled(false);//�����ư�ť����
		setBorderPainted(false);//�����Ʊ߿�
		setIcon(new ImageIcon(path));//����Ĭ��ͼƬ
		}
		}
	


