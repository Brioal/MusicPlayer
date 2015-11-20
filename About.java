import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;


public class About extends MyFrame {
	private static final long serialVersionUID = 1L;
	MyPanel mypanel ;
	public About(){
		setUndecorated(true);
		mypanel = new MyPanel("drawable/image/back_7.jpg") ;
		mypanel.setBackground(Color.WHITE);
		setContentPane(mypanel);
		mypanel.setLayout(null);
		JButton btnClose = new ImageButton("drawable/icon/close.png");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false) ;
			}
		});
		btnClose.setBounds(760, 0, 50, 20);
		mypanel.add(btnClose);
		
		JLabel label1 = new JLabel("姓名：梁谨");
		label1.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label1.setBounds(20, 0, 200, 30);
		mypanel.add(label1);
		
		JLabel label2 = new JLabel("班级：电子商务");
		label2.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label2.setBounds(20, 30, 200, 30);
		mypanel.add(label2);
		
		JLabel label3 = new JLabel("学号：320130936480 ");
		label3.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label3.setBounds(20, 60, 200, 30);
		mypanel.add(label3);
		
		JLabel label4 = new JLabel("程序简介：实现基本本地播放功能，在线播放只能实现输入下载链接播放故没有加入");
		label4.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label4.setBounds(20, 90, 800, 30);
		mypanel.add(label4);
		
		JLabel label5 = new JLabel("已实现功能：读取本地文件或文件夹，添加到列表");
		label5.setFont(new Font("方正细圆_GBK", Font.PLAIN, 23));
		label5.setBounds(20, 120, 700, 30);
		mypanel.add(label5);

		JLabel label6 = new JLabel("列表滚动单击获取焦点双击播放，添加播放模式选择（默认全部循环播放）");
		label6.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label6.setBounds(40, 150, 700, 30);
		mypanel.add(label6);

		JLabel label7 = new JLabel("自定义JFrame，JPanel，JSCrollPane,JList,JButton,JProgressd...等实现美化");
		label7.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label7.setBounds(40, 180, 700, 30);
		mypanel.add(label7);
		
		JLabel label8 = new JLabel("基本播放，播放列表操作，添加删除清空，保存");
		label8.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label8.setBounds(40, 210, 700, 30);
		mypanel.add(label8);
		
		JLabel label9 = new JLabel("调用开源库获取歌曲各种信息");
		label9.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label9.setBounds(40, 240, 700, 30);
		mypanel.add(label9);
		
		JLabel label10 = new JLabel("存在问题和未实现功能：未能处理好代码结构，代码较乱");
		label10.setFont(new Font("方正细圆_GBK", Font.PLAIN, 23));
		label10.setBounds(20, 280, 700, 30);
		mypanel.add(label10);
		
		JLabel label11 = new JLabel("了解到可以用jsoup抓取网络信息但难以实现而放弃添加网络功能");
		label11.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label11.setBounds(40, 310, 700, 30);
		mypanel.add(label11);
		
		JLabel label12 = new JLabel("因为网络功能未实现而放弃添加歌词显示，左边面板空白");
		label12.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label12.setBounds(40, 340, 700, 30);
		mypanel.add(label12);
		
		JLabel label13 = new JLabel("部分BUG未处理");
		label13.setFont(new Font("方正细圆_GBK", Font.PLAIN, 20));
		label13.setBounds(40, 370, 700, 30);
		mypanel.add(label13);
		
		setSize(800,500) ;
		setVisible(false) ;
	}
}
