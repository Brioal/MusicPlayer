import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ChangeImage extends JFrame {
	private static final long serialVersionUID = 1884442421748571599L;
	JLabel labImage1, labImage2, labImage3, labImage4, labImage5, labImage6,
			labImage7, labImage8;
	private JSlider slider;
	private JSlider slider_1;

	public ChangeImage() {
		// …Ë÷√±≥æ∞‘§¿¿
		Image image_1 = new ImageIcon("drawable/image/back_1.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		Image image_2 = new ImageIcon("drawable/image/back_2.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		Image image_3 = new ImageIcon("drawable/image/back_3.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		Image image_4 = new ImageIcon("drawable/image/back_4.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		Image image_5 = new ImageIcon("drawable/image/back_5.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		Image image_6 = new ImageIcon("drawable/image/back_6.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		Image image_7 = new ImageIcon("drawable/image/back_7.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		Image image_8 = new ImageIcon("drawable/image/back_8.jpg").getImage()
				.getScaledInstance(200, 100, 0);
		JButton btnNewButton = new ImageButton("drawable/icon/close.png");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		MyClichListener myclickListener = new MyClichListener();
		btnNewButton.setBounds(750, 0, 50, 20);
		getContentPane().setLayout(null);
		getContentPane().add(btnNewButton);
		labImage1 = new JLabel(new ImageIcon(image_1));
		labImage1.setBounds(0, 0, 200, 100);
		getContentPane().add(labImage1);
		labImage1.addMouseListener(myclickListener);
		labImage2 = new JLabel(new ImageIcon(image_2));
		labImage2.setBounds(200, 0, 200, 100);
		getContentPane().add(labImage2);
		labImage2.addMouseListener(myclickListener);
		labImage3 = new JLabel(new ImageIcon(image_3));
		labImage3.setBounds(400, 0, 200, 100);
		getContentPane().add(labImage3);
		labImage3.addMouseListener(myclickListener);
		labImage4 = new JLabel(new ImageIcon(image_4));
		labImage4.setBounds(600, 0, 200, 100);
		getContentPane().add(labImage4);
		labImage4.addMouseListener(myclickListener);
		labImage5 = new JLabel(new ImageIcon(image_5));
		labImage5.setBounds(0, 100, 200, 100);
		getContentPane().add(labImage5);
		labImage5.addMouseListener(myclickListener);
		labImage6 = new JLabel(new ImageIcon(image_6));
		labImage6.setBounds(200, 100, 200, 100);
		getContentPane().add(labImage6);
		labImage6.addMouseListener(myclickListener);
		labImage7 = new JLabel(new ImageIcon(image_7));
		labImage7.setBounds(400, 100, 200, 100);
		getContentPane().add(labImage7);
		labImage7.addMouseListener(myclickListener);
		labImage8 = new JLabel(new ImageIcon(image_8));
		labImage8.setBounds(600, 100, 200, 100);
		getContentPane().add(labImage8);
		slider = new JSlider();
		slider.setMaximum(225);
		slider.setBounds(0, 200, 800, 25);
		getContentPane().add(slider);
		slider_1 = new JSlider();
		slider_1.setMaximum(225);
		slider_1.setBounds(0, 225, 800, 25);
		slider_1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Test.setColor(slider_1.getValue());
			}
		});
		getContentPane().add(slider_1);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				MyFrame.setMainColor(slider.getValue());

			}
		});
		labImage8.addMouseListener(myclickListener);
		setUndecorated(true);

		setVisible(false);
		setSize(800, 250);
	}

	class MyClichListener implements MouseListener {

		public void copy(String path) throws IOException {
			File reFile = new File("drawable/icon/back.jpg");
			reFile.delete();
			File newFile = new File(path);
			FileInputStream in = new FileInputStream(newFile);
			FileOutputStream out = new FileOutputStream(reFile);
			byte[] buffer = new byte[4096];
			int ch = 0;
			while ((ch = in.read(buffer)) != -1) {
				out.write(buffer, 0, ch);
			}
			in.close();
			out.close();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == labImage1) {
				MyFrame.setBack("drawable/image/back_1.jpg");
				try {
					copy("drawable/image/back_1.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == labImage2) {
				MyFrame.setBack("drawable/image/back_2.jpg");
				try {
					copy("drawable/image/back_2.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == labImage3) {
				MyFrame.setBack("drawable/image/back_3.jpg");
				try {
					copy("drawable/image/back_3.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == labImage4) {
				MyFrame.setBack("drawable/image/back_4.jpg");
				try {
					copy("drawable/image/back_4.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == labImage5) {
				MyFrame.setBack("drawable/image/back_5.jpg");
				try {
					copy("drawable/image/back_5.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == labImage6) {
				MyFrame.setBack("drawable/image/back_6.jpg");
				try {
					copy("drawable/image/back_6.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == labImage7) {
				MyFrame.setBack("drawable/image/back_7.jpg");
				try {
					copy("drawable/image/back_7.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == labImage8) {
				MyFrame.setBack("drawable/image/back_8.jpg");
				try {
					copy("drawable/image/back_8.jpg");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {		}
		@Override
		public void mouseReleased(MouseEvent e) {		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {		}
	}
}
