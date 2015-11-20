import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;



public class Test extends MyFrame {

	private static final long serialVersionUID = 1146016338150340486L;
	// ���峣��
	int s = 0, m = 0;// ��ǰ��������
	int S = 0, M = 0;// �ܷ�������
	int flag = 1;// ��־�����ڿ����Ƿ��ǵ�һ�ε�����Ű�ť
	int index = 0;// �����б���±�
	private static boolean isAllCycle = true;
	private static boolean isSingleCycle = false;
	private static boolean isRandom = false;

	// ����������
	MediaPlayer player = null; // ���岥�������
	Timer timer; // Swing�еĶ�ʱ����������ڸ��²���ʱ��ͽ�����
	Timer flushtime ;
	GetSong getsong; // ѡ���ļ����ļ���ɨ���ò����б�
	private static List<File> list; // ���ڴ�Ų����б�
	DefaultListModel<String> model;
	private JFileChooser filechooser;
	// ��嶨��
	private JPanel PlayPanel;
	private static JPanel ShowProgressPanel;
	private static JPanel ShowOnlinePanel;
	private static JPanel ShowPlayButtonPanel;
	private static JPanel ShowSongListPanel;
	private static JPanel PlayOptionPanel;
	private static JPanel panel_showMeeage;
	private static JPanel showSelectButtonPanel;
	private static JPanel showPicture;// ������ʾר��ͼƬ
	// ��ť����
	JButton btnStart, btnStop, btnPause, btnStartWeb, btnPrev, btnNext;
	private JButton btnSaveList;
	private JButton btnSelectFile;
	private JButton btnDelete;
	private JButton btnClear;
	// ��ǩ����
	private JLabel labelStart, labelEnd;
	private JLabel showMessage;
	private JLabel showTitle, showArtist, showAlbum, showYear, Picture;
	// ��ѡ���鶨��
	private JRadioButton btnAll;// ����ȫ��ѡ����
	private JRadioButton btnOnly;// ��������ѡ����
	private JRadioButton btnRandom;// �������ѡ����
	// ����������
	private JProgressBar progressBar;
	// �б���
	private JList<String> songList;
	private getInfo getinfo;
	// ������嶨��
	private JScrollPane ps;
	// �������
	private JTextField textField;
	// �����߳�
	Thread playthread;  //���ڲ������ֵ��߳�
//	������ּ�����
	MouseListener mouseclicklistener ;		//�����б�ĵ���¼�
	ActionListener fileoperatorlistener ;	//�ļ��������ĵ���¼�
	ActionListener playoperatorlistener;	//���Ž���ĵ���¼�
	TimerActioner timeraction;				//Timer��ʱ�������
	Flush flushaction ;
	MyItemListener itemlistener;			//����ģʽ��ʱ�������

	// ���췽��
	public Test() throws IOException {
//		super();
		init();
		timeraction = new TimerActioner();	//ʵ����Timer����������
		timer = new Timer(1000, timeraction);	//ʵ����Timer����
		flushaction = new Flush() ;
		flushtime = new Timer(100, flushaction) ;
		flushtime.start();
//		ʵ�������ַ�������
//		ʵ�����¼�������
		mouseclicklistener = new MouseClichListener();
		fileoperatorlistener = new FileOperatorListener();
		playoperatorlistener = new PlayOperatorListener();
		itemlistener = new MyItemListener() ;
		
		player = new MediaPlayer(); //ʵ�������ֲ�����
		JudgeLocationPlayList() ; //�жϱ����Ƿ���ڲ����б��ļ�
		list = new ArrayList<File>(); // ʵ����list
		
		// �����ļ�������
		filechooser = new JFileChooser("music");
		filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Allowed File", "mp3", "wav");
		filechooser.setFileFilter(filter);
		model = new DefaultListModel<String>();
		songList = new MyJList(model);
		songList.setFont(new Font("����ϸԲ����", Font.PLAIN, 19));
		ps = new MyJScrollPane(songList);
		ps.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		ShowSongListPanel.add(ps);
		AddActionListener() ;
	}

	

	public void init() throws IOException {
		// ���������岼��
		getContentPane().setLayout(null);
		// ���ʵ����
		PlayPanel = new BlankPanel();
		PlayPanel.setSize(1000, 150);
		ShowPlayButtonPanel = new BlankPanel();
		ShowProgressPanel = new BlankPanel();
		panel_showMeeage = new BlankPanel();
		ShowSongListPanel = new BlankPanel();
		ShowSongListPanel.setLocation(700, 20);
		ShowSongListPanel.setSize(300, 430);
		ShowOnlinePanel = new BlankPanel();
		PlayOptionPanel = new BlankPanel();
		showSelectButtonPanel = new BlankPanel();
		showPicture = new BlankPanel();
		showPicture.setSize(400, 400);
		showPicture.setLocation(300, 50);
		// ���ʵ����
		btnSelectFile = new ImageButton("drawable/icon/add.png");
		btnSaveList = new ImageButton("drawable/icon/save.png");
		btnPause = new ImageButton("drawable/icon/pause.png");
		btnStart = new ImageButton("drawable/icon/play.png");
		btnPrev = new ImageButton("drawable/icon/prev.png");
		btnNext = new ImageButton("drawable/icon/next.png");
		progressBar = new ImageProgress();
		labelEnd = new JLabel("00:00");
		labelStart = new JLabel("00:00");
		showMessage = new JLabel();
		showMessage.setBounds(150, 85, 150, 27);
		showMessage.setFont(new Font("����ϸԲ����", Font.PLAIN, 19));
		showYear = new JLabel();
		showYear.setFont(new Font("����ϸԲ����", Font.PLAIN, 19));
		Picture = new JLabel();
		Picture.setHorizontalAlignment(SwingConstants.CENTER);
		btnAll = new MyJRadioButton("��������");
		btnOnly = new MyJRadioButton("��������");
		btnRandom = new MyJRadioButton("���ѭ��");
		btnDelete = new ImageButton("drawable/icon/delete.png");
		btnClear = new ImageButton("drawable/icon/clear.png");
		// �������
		PlayPanel.setLayout(new BorderLayout());
		ShowPlayButtonPanel.setLayout(new FlowLayout());
		ShowProgressPanel.setLayout(new BorderLayout());
		ShowSongListPanel.setLayout(new BorderLayout(0, 0));
		ShowOnlinePanel.setLayout(new BorderLayout(0, 0));
		PlayOptionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		panel_showMeeage.setPreferredSize(new Dimension(300, 100));
		ShowPlayButtonPanel.setPreferredSize(new Dimension(400, 100));
		PlayOptionPanel.setPreferredSize(new Dimension(300, 100));
		showPicture.setPreferredSize(new Dimension(400, 400));
		// �������
		btnPause.setVisible(false);
		btnAll.setSelected(true);
		// ������
		ShowPlayButtonPanel.add(btnPrev);
		ShowPlayButtonPanel.add(btnPause);
		ShowPlayButtonPanel.add(btnStart);
		ShowPlayButtonPanel.add(btnNext);
		ShowProgressPanel.add(labelStart, BorderLayout.WEST);
		ShowProgressPanel.add(progressBar, BorderLayout.CENTER);
		ShowProgressPanel.add(labelEnd, BorderLayout.EAST);
		panel_showMeeage.setLayout(null);
		showArtist = new JLabel();
		showArtist.setBounds(0, 0, 150, 30);
		showArtist.setFont(new Font("����ϸԲ����", Font.PLAIN, 19));
		panel_showMeeage.add(showArtist); // ��ʾ������Ϣ
		showTitle = new JLabel();
		showTitle.setBounds(0, 40, 300, 50);
		showTitle.setFont(new Font("����ϸԲ����", Font.PLAIN, 39));
		panel_showMeeage.add(showTitle); // ��ʾ����
		showAlbum = new JLabel();
		showAlbum.setBounds(150, 0, 150, 30);
		showAlbum.setFont(new Font("����ϸԲ����", Font.PLAIN, 19));
		panel_showMeeage.add(showAlbum);
		panel_showMeeage.add(showMessage); // ���������ʾ��Ϣ�ı�ǩ
		showPicture.setLayout(new BorderLayout(0, 0));
		showPicture.add(Picture);
		ButtonGroup group = new ButtonGroup();
		group.add(btnAll);
		group.add(btnOnly);
		group.add(btnRandom);
		PlayOptionPanel.add(btnAll);
		PlayOptionPanel.add(btnOnly);
		PlayOptionPanel.add(btnRandom);
		
		showSelectButtonPanel.add(btnSelectFile);
		showSelectButtonPanel.add(btnSaveList);
		showSelectButtonPanel.add(btnDelete);
		showSelectButtonPanel.add(btnClear);
		// �����ӵ����
		getContentPane().add(PlayPanel);
		PlayPanel.setLocation(0, 450);
		PlayPanel.add(ShowPlayButtonPanel, BorderLayout.CENTER);
		PlayPanel.add(ShowProgressPanel, BorderLayout.SOUTH);
		PlayPanel.add(PlayOptionPanel, BorderLayout.EAST);
		PlayPanel.add(panel_showMeeage, BorderLayout.WEST);
		getContentPane().add(ShowSongListPanel, BorderLayout.EAST);
		
		getContentPane().add(showPicture, BorderLayout.CENTER);
		ShowSongListPanel.add(showSelectButtonPanel, BorderLayout.SOUTH);
	
	}
//	�����������¼�������
	public void AddActionListener(){
		btnPrev.addActionListener(playoperatorlistener);
		btnNext.addActionListener(playoperatorlistener);
		btnStart.addActionListener(playoperatorlistener);
		btnPause.addActionListener(playoperatorlistener);
		btnSaveList.addActionListener(fileoperatorlistener);
		btnSelectFile.addActionListener(fileoperatorlistener);
		btnDelete.addActionListener(fileoperatorlistener);
		btnClear.addActionListener(fileoperatorlistener);
		btnAll.addItemListener(itemlistener);
		btnOnly.addItemListener(itemlistener);
		btnRandom.addItemListener(itemlistener);
	}
	//�жϱ����Ƿ񱣴��в����б��ļ�
	public void JudgeLocationPlayList() throws IOException{
		// �ж��Ƿ���ڱ���Ĳ����б�
		if (new File("playList/playList.txt").exists()) {
			// �������ʱ����ǩ����Ϊ��ǰ���Ÿ���
			showMessage.setText("�ҵ�����Ĳ����б����ڶ�ȡ");
			// ���ж���ʵ����list������������ļ���ʵ����
			list = new ArrayList<File>();
			// ��ȡ�ļ�
			@SuppressWarnings("resource")
			BufferedReader fr = new BufferedReader(new FileReader(new File(
					"1.txt")));
			String ch = null;
			while ((ch = fr.readLine()) != null) {
				// ��ӵ�list��
				list.add(new File(ch));
			}
			// �������ʱ����ǩ����Ϊ��ǰ���Ÿ���
			showMessage.setText("�����б��ȡ�ɹ�");
			model = new DefaultListModel<String>();
			for (int i = 0; i < list.size(); i++) {
				model.addElement(list.get(i).getName().split("[.]")[0]);
			}
			songList = new MyJList(model);

		}
		// ������ڲ����б��ļ�������ӵ�songList
		if (songList != null) {
			// �����춯���
			ps = new JScrollPane(songList);
			ps.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			ShowSongListPanel.add(ps);
			songList.setSelectedIndex(0);
			songList.addMouseListener(mouseclicklistener);
		}
	}
	public static void setColor(int i) {
		BlankPanel.setColor(i);
		ShowPlayButtonPanel.updateUI();
		ShowProgressPanel.updateUI();
		panel_showMeeage.updateUI();

		ShowSongListPanel.updateUI();
		ShowOnlinePanel.updateUI();
		PlayOptionPanel.updateUI();
		showSelectButtonPanel.updateUI();
	}

	private void selectedActionPerformed(ActionEvent e) {
		int value = filechooser.showOpenDialog(null);
		if (value == JFileChooser.APPROVE_OPTION) {
			getList(filechooser.getSelectedFile());
			showMessage.setText(list.size() - model.size() + "�׸��Ѿ����");
			for (int i = model.size(); i < list.size(); i++) {
				model.addElement(list.get(i).getName().split("[.]")[0]);
			}
			songList.addMouseListener(mouseclicklistener);
		}
	}

	public void playAll() throws IOException {
		while (true) {
			if (player.isStop()) {
				try {
					if (isAllCycle) {
						System.out.println("����ѭ��ȫ��");
						System.out.println(index);
						player.Play(list.get(index).getAbsolutePath());
						// �߳���ͣ����ʹ��isStop���false
						Thread.sleep(1000);
						index = (index + 1) % list.size();
					} else if (isSingleCycle) {
						System.out.println("���뵥��ѭ��");
						player.Play(list.get(index).getAbsolutePath());
						// �߳���ͣ����ʹ��isStop���false
						Thread.sleep(1000);
					} else if (isRandom) {
						System.out.println("�������ѭ��");
						player.Play(list.get(index).getAbsolutePath());
						// �߳���ͣ����ʹ��isStop���false
						Thread.sleep(1000);
						index = new Random().nextInt(list.size());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					if(timer!=null){
						timer.stop();
					}
					timer.start();
					getinfo = new getInfo(list.get(index));
					// ��ʾ������Ϣ������
					showTitle.setText(getinfo.getTitle());
					showArtist.setText(getinfo.getArtist());
					showAlbum.setText(getinfo.getAlbum());
					showYear.setText(getinfo.getYear());
					Picture.setIcon(new ImageIcon(new ImageIcon(
							"drawable/picture/" + getinfo.getTitle() + ".jpg")
							.getImage().getScaledInstance(330, 330, 0)));
				} catch (TagException e) {
					e.printStackTrace();
				} catch (ReadOnlyFileException e) {
					e.printStackTrace();
				} catch (InvalidAudioFrameException e) {
					e.printStackTrace();
				}
				// �����������ʼ��ʱ�䣬������
				labelStart.setText("00:00");
				labelEnd.setText("00:00");
				progressBar.setValue(0);
			}

			// ����ʱ����ǩ����Ϊ��ǰ���Ÿ���

		}
	}

	public static void getList(File file) {
		try {
			// �ж��ļ��Ƿ����ļ���������ļ�����ȡ·����������
			if (file.isFile()) {
				list.add(file);
				System.out.println(file.getAbsolutePath());

			} else {
				// ������ļ��У�����һ��������ļ��к��������ļ�
				File[] f = file.listFiles();
				// �����ļ����µ��ļ�������ȡ·��
				for (File file2 : f) {
					getList(file2);
				}

			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}

	class MouseClichListener implements MouseListener {
		@SuppressWarnings("deprecation")
		@Override
		public void mouseClicked(MouseEvent e) {
			// �����б���ʱ�������
			if (e.getClickCount() == 2) {
				if (flag == 1) {

					index = songList.locationToIndex(e.getPoint());
					playthread = new PlayThread();
					playthread.start();
					// �������ʱ����ǩ����Ϊ��ǰ���Ÿ���
					timer.start();
					btnStart.setVisible(false);
					btnPause.setVisible(true);
					labelStart.setText(m + " : " + s);
					labelEnd.setText(M + " : " + S);
					progressBar.setValue((int) ((m * 60 + s) / 300));
					showMessage.setText("���ڲ��ţ�");
					flag++;
				} else {
					playthread.stop();
					player.Play(list.get(index).getAbsolutePath());
					System.out.println("s");
					playthread = new PlayThread();
					playthread.start();
					int in = songList.locationToIndex(e.getPoint());
					index = in;
				}
			} else {
				int in = songList.locationToIndex(e.getPoint());
				index = in;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

	// �����ļ������¼�������
	class FileOperatorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSelectFile) {
				selectedActionPerformed(e);
			}
			if (e.getSource() == btnSaveList) {
				try {
					getsong.Save(list);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getSource() == btnDelete) {
				if (model != null) {
					model.remove(index);
					list.remove(index);
					songList = new JList<String>(model);
					// ��ʾɾ���ļ���Ϣ
					showMessage.setText(list.get(index - 1).getName()
							.split("[.]")[0]
							+ "��ɾ��");
					// ����б���������
					songList.addMouseListener(mouseclicklistener);

				}

			}
			// ���list��songList
			if (e.getSource() == btnClear) {
				if (model != null) {
					model.removeAllElements();
					list.removeAll(list);
					// songList = new JList<String>(model);
					// ��ʾɾ���ļ���Ϣ
					showMessage.setText("��ɾ��");
					repaint();
					// ͬʱɾ������Ĳ����б��ļ�
					if (new File("playList.txt").exists()) {
						new File("playList.txt").delete();
					}
				} else {
					showMessage.setText("��ǰ�����б���Ϊ��");
				}
			}
		};
	}

	// ��������¼�
	class PlayOperatorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnStart) {
				// �������ʱ����ǩ����Ϊ��ǰ���Ÿ���
				showMessage.setText("���ڲ��ţ�");
				if (flag == 1) {
					// Ĭ��ѭ����������
					btnStart.setVisible(false);
					btnPause.setVisible(true);
					flag++;
				} else {
					timer.start();
					player.Play();
					btnStart.setVisible(false);
					btnPause.setVisible(true);
					labelStart.setText(m + " : " + s);
					labelEnd.setText(M + " : " + S);
					progressBar.setValue((int) ((m * 60 + s) / 300));
				}
			}

			if (e.getSource() == btnStop) {
				// ���ֹͣʱ����ǩ����Ϊ��ǰ���Ÿ���
				showMessage.setText("ֹͣ");
				player.Stop();
				timer.stop();
				btnPause.setVisible(false);
				btnStart.setVisible(true);
				s = 0;
				m = 0;
				labelStart.setText("00:00");
				progressBar.setValue(0);

			}
			if (e.getSource() == btnPause) {
				// �����ͣʱ����ǩ����Ϊ��ǰ���Ÿ���
				showMessage.setText("��ͣ");
				player.Pause();
				timer.stop();
				btnPause.setVisible(false);
				btnStart.setVisible(true);
				flag++;

			}
			if (e.getSource() == btnPrev) {
				// �����һ��ʱ����ǩ����Ϊ��ǰ���Ÿ���
				showMessage.setText("��һ��");
				if (index != 0) {
					// ֹͣ��һ���߳�
					// Ĭ��ѭ����������
					index--;
					// ��һ����ʼ����ʱ����ǩ����Ϊ��ǰ���Ÿ���
					player.Stop();
					labelStart.setText("00:00");
					progressBar.setValue(0);
					try {
						getinfo = new getInfo(list.get(index));
						// ��ʾ������Ϣ������
						showTitle.setText(getinfo.getTitle());
						showArtist.setText(getinfo.getArtist());
						showAlbum.setText(getinfo.getAlbum());
						showYear.setText(getinfo.getYear());
						Picture.setIcon(new ImageIcon(new ImageIcon(
								"drawable/picture/" + getinfo.getTitle()
										+ ".jpg").getImage()));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (TagException e1) {
						e1.printStackTrace();
					} catch (ReadOnlyFileException e1) {
						e1.printStackTrace();
					} catch (InvalidAudioFrameException e1) {
						e1.printStackTrace();
					}

					flag++;
				} else {
					System.out.println("�Ѿ��ǵ�һ��");
				}
			}
			if (e.getSource() == btnNext) {
				// �����һ��ʱ����ǩ����Ϊ��ǰ���Ÿ���

				// ֹͣ��һ���߳�
				// Ĭ��ѭ����������
				index++;
				// �����һ������ʱ����ǩ����Ϊ��ǰ���Ÿ���
				showMessage.setText("��һ��");
				labelStart.setText("00:00");
				progressBar.setValue(0);
				if (index == list.size()) {
					labelStart.setText("00:00");
					progressBar.setValue(0);
					labelEnd.setText("00:00");
					System.out.println("�Ѿ������һ��");
					timer.stop();
				}
				player.Stop();
				try {
					getinfo = new getInfo(list.get(index));
					// ��ʾ������Ϣ������
					showTitle.setText(getinfo.getTitle());
					showArtist.setText(getinfo.getArtist());
					showAlbum.setText(getinfo.getAlbum());
					showYear.setText(getinfo.getYear());
					Picture.setIcon(new ImageIcon(new ImageIcon(
							"drawable/picture/" + getinfo.getTitle() + ".jpg")
							.getImage()));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (TagException e1) {
					e1.printStackTrace();
				} catch (ReadOnlyFileException e1) {
					e1.printStackTrace();
				} catch (InvalidAudioFrameException e1) {
					e1.printStackTrace();
				}

				flag++;
			}
			labelStart.setText(m + " : " + s);
			labelEnd.setText(M + ":" + S);
		};

	}

	public static void setAllCycle(boolean isallCycle) {
		isAllCycle = isallCycle;
	}

	public static void setSingleCycle(boolean issingleCycle) {
		isSingleCycle = issingleCycle;
	}

	public static void setRandom(boolean israndom) {
		isRandom = israndom;
	}

	public class PlayThread extends Thread {
		@Override
		public void run() {
			try {
				playAll();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	public class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == btnAll) {
				setAllCycle(true);
				setSingleCycle(false);
				setRandom(false);
			}
			if (e.getSource() == btnOnly) {
				setAllCycle(false);
				setSingleCycle(true);
				setRandom(false);
			}
			if (e.getSource() == btnRandom) {
				setAllCycle(false);
				setSingleCycle(false);
				setRandom(true);
			}
		}

	}

	// ����ʵ��ʵʱˢ�½��Ⱥ�ʱ��
	public class TimerActioner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			s++;
			if (s > 60) {
				m++;
				s = s - 60;
			}
			progressBar.setValue(((m * 60 + s) * 100 / 300));
		
			labelStart.setText(m + " : " + s);
			getContentPane().update(getGraphics());
		}
	}
	public class Flush implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			getContentPane().update(getGraphics());
			
		}
		
	}

}
