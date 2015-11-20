import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static MyPanel mypanel ;
	int mx , my , jfx , jfy ;
	private JLabel labTitle ;
	ChangeImage changeimage ;
	About about ;
	
	public static void setBack(String filepath){
		mypanel.setImage(filepath);
		mypanel.updateUI();
	}
	public static void setMainColor(int i ){
		mypanel.setColor(i);
		mypanel.updateUI();
	}
	public static void setColor(int i){
		BlankPanel.setColor(i);
	}
	public MyFrame() {
		setUndecorated(true);
		setBackground(new Color(0,0,0,0));
		mypanel = new MyPanel("drawable/icon/back.jpg") ;
		mypanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen()-mx +jfx, e.getYOnScreen()-my+jfy);
			}
		});
		mypanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx = e.getXOnScreen() ;
				my = e.getYOnScreen() ;
				jfx = getX() ;
				jfy = getY() ;
			}
		});
		mypanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode()==0){
					System.exit(0);
				}
			}
		});
		mypanel.setOpaque(false);
		setContentPane(mypanel);
		mypanel.setLayout(null);
		
		JButton btnClose = new ImageButton("drawable/icon/close.png");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClose.setBounds(950, 0, 50, 20);
		mypanel.add(btnClose);
		
		JButton btnMin = new ImageButton("drawable/icon/min.png");;
		btnMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnMin.setBounds(900, 0, 50, 20);
		mypanel.add(btnMin);
		
		JButton btnHide = new ImageButton("drawable/icon/hide.png");;
		btnHide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnHide.setBounds(850, 0, 50, 20);
		mypanel.add(btnHide);
		
		JButton btnAbout = new ImageButton("drawable/icon/about.png");;
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				about = new About() ;
				about.setVisible(true);
				about.setLocationRelativeTo(null);
			}
		});
		JButton btnChangeImage = new ImageButton("drawable/icon/image.png");
		btnChangeImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeimage = new ChangeImage() ;
				changeimage.setVisible(true);
				changeimage.setLocationRelativeTo(null);
			
			}
		});
		btnChangeImage.setBounds(800, 0, 50, 20);
		mypanel.add(btnChangeImage);
		btnAbout.setBounds(750, 0, 50, 20);
		mypanel.add(btnAbout);
		
		labTitle = new JLabel("\u7B80\u6613\u97F3\u4E50\u64AD\u653E\u5668");
		
		labTitle.setBounds(10, 0, 200, 30);
		labTitle.setFont(new Font("·½ÕýÏ¸Ô²¼òÌå", Font.PLAIN, 19));
		mypanel.add(labTitle);
	}

	public static void main(String[] args) {
		MyFrame myframe = new MyFrame();
		myframe.setVisible(true);
		myframe.setSize(1000, 600);
		myframe.setLocationRelativeTo(null);
	}
}
