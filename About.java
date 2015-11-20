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
		
		JLabel label1 = new JLabel("����������");
		label1.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label1.setBounds(20, 0, 200, 30);
		mypanel.add(label1);
		
		JLabel label2 = new JLabel("�༶����������");
		label2.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label2.setBounds(20, 30, 200, 30);
		mypanel.add(label2);
		
		JLabel label3 = new JLabel("ѧ�ţ�320130936480 ");
		label3.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label3.setBounds(20, 60, 200, 30);
		mypanel.add(label3);
		
		JLabel label4 = new JLabel("�����飺ʵ�ֻ������ز��Ź��ܣ����߲���ֻ��ʵ�������������Ӳ��Ź�û�м���");
		label4.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label4.setBounds(20, 90, 800, 30);
		mypanel.add(label4);
		
		JLabel label5 = new JLabel("��ʵ�ֹ��ܣ���ȡ�����ļ����ļ��У���ӵ��б�");
		label5.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 23));
		label5.setBounds(20, 120, 700, 30);
		mypanel.add(label5);

		JLabel label6 = new JLabel("�б����������ȡ����˫�����ţ���Ӳ���ģʽѡ��Ĭ��ȫ��ѭ�����ţ�");
		label6.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label6.setBounds(40, 150, 700, 30);
		mypanel.add(label6);

		JLabel label7 = new JLabel("�Զ���JFrame��JPanel��JSCrollPane,JList,JButton,JProgressd...��ʵ������");
		label7.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label7.setBounds(40, 180, 700, 30);
		mypanel.add(label7);
		
		JLabel label8 = new JLabel("�������ţ������б���������ɾ����գ�����");
		label8.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label8.setBounds(40, 210, 700, 30);
		mypanel.add(label8);
		
		JLabel label9 = new JLabel("���ÿ�Դ���ȡ����������Ϣ");
		label9.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label9.setBounds(40, 240, 700, 30);
		mypanel.add(label9);
		
		JLabel label10 = new JLabel("���������δʵ�ֹ��ܣ�δ�ܴ���ô���ṹ���������");
		label10.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 23));
		label10.setBounds(20, 280, 700, 30);
		mypanel.add(label10);
		
		JLabel label11 = new JLabel("�˽⵽������jsoupץȡ������Ϣ������ʵ�ֶ�����������繦��");
		label11.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label11.setBounds(40, 310, 700, 30);
		mypanel.add(label11);
		
		JLabel label12 = new JLabel("��Ϊ���繦��δʵ�ֶ�������Ӹ����ʾ��������հ�");
		label12.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label12.setBounds(40, 340, 700, 30);
		mypanel.add(label12);
		
		JLabel label13 = new JLabel("����BUGδ����");
		label13.setFont(new Font("����ϸԲ_GBK", Font.PLAIN, 20));
		label13.setBounds(40, 370, 700, 30);
		mypanel.add(label13);
		
		setSize(800,500) ;
		setVisible(false) ;
	}
}
