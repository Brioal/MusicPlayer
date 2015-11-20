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
	// 定义常量
	int s = 0, m = 0;// 当前分数秒数
	int S = 0, M = 0;// 总分数秒数
	int flag = 1;// 标志，用于控制是否是第一次点击播放按钮
	int index = 0;// 播放列表的下标
	private static boolean isAllCycle = true;
	private static boolean isSingleCycle = false;
	private static boolean isRandom = false;

	// 定义各种组件
	MediaPlayer player = null; // 定义播放器组件
	Timer timer; // Swing中的定时器组件，用于更新播放时间和进度条
	Timer flushtime ;
	GetSong getsong; // 选中文件或文件夹扫描获得播放列表
	private static List<File> list; // 用于存放播放列表
	DefaultListModel<String> model;
	private JFileChooser filechooser;
	// 面板定义
	private JPanel PlayPanel;
	private static JPanel ShowProgressPanel;
	private static JPanel ShowOnlinePanel;
	private static JPanel ShowPlayButtonPanel;
	private static JPanel ShowSongListPanel;
	private static JPanel PlayOptionPanel;
	private static JPanel panel_showMeeage;
	private static JPanel showSelectButtonPanel;
	private static JPanel showPicture;// 用于显示专辑图片
	// 按钮定义
	JButton btnStart, btnStop, btnPause, btnStartWeb, btnPrev, btnNext;
	private JButton btnSaveList;
	private JButton btnSelectFile;
	private JButton btnDelete;
	private JButton btnClear;
	// 标签定义
	private JLabel labelStart, labelEnd;
	private JLabel showMessage;
	private JLabel showTitle, showArtist, showAlbum, showYear, Picture;
	// 单选框组定义
	private JRadioButton btnAll;// 播放全部选择项
	private JRadioButton btnOnly;// 单曲播放选择项
	private JRadioButton btnRandom;// 随机播放选择项
	// 进度条定义
	private JProgressBar progressBar;
	// 列表定义
	private JList<String> songList;
	private getInfo getinfo;
	// 滚动面板定义
	private JScrollPane ps;
	// 输入框定义
	private JTextField textField;
	// 定义线程
	Thread playthread;  //用于播放音乐的线程
//	定义各种监听器
	MouseListener mouseclicklistener ;		//播放列表的点击事件
	ActionListener fileoperatorlistener ;	//文件操作面板的点击事件
	ActionListener playoperatorlistener;	//播放界面的点击事件
	TimerActioner timeraction;				//Timer的时间监听器
	Flush flushaction ;
	MyItemListener itemlistener;			//播放模式的时间监听器

	// 构造方法
	public Test() throws IOException {
//		super();
		init();
		timeraction = new TimerActioner();	//实例化Timer监听器工具
		timer = new Timer(1000, timeraction);	//实例化Timer工具
		flushaction = new Flush() ;
		flushtime = new Timer(100, flushaction) ;
		flushtime.start();
//		实例化各种非面板组件
//		实例化事件监听器
		mouseclicklistener = new MouseClichListener();
		fileoperatorlistener = new FileOperatorListener();
		playoperatorlistener = new PlayOperatorListener();
		itemlistener = new MyItemListener() ;
		
		player = new MediaPlayer(); //实例化音乐播放器
		JudgeLocationPlayList() ; //判断本地是否存在播放列表文件
		list = new ArrayList<File>(); // 实例化list
		
		// 设置文件过滤名
		filechooser = new JFileChooser("music");
		filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Allowed File", "mp3", "wav");
		filechooser.setFileFilter(filter);
		model = new DefaultListModel<String>();
		songList = new MyJList(model);
		songList.setFont(new Font("方正细圆简体", Font.PLAIN, 19));
		ps = new MyJScrollPane(songList);
		ps.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		ShowSongListPanel.add(ps);
		AddActionListener() ;
	}

	

	public void init() throws IOException {
		// 设置主窗体布局
		getContentPane().setLayout(null);
		// 面板实例化
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
		// 组件实例化
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
		showMessage.setFont(new Font("方正细圆简体", Font.PLAIN, 19));
		showYear = new JLabel();
		showYear.setFont(new Font("方正细圆简体", Font.PLAIN, 19));
		Picture = new JLabel();
		Picture.setHorizontalAlignment(SwingConstants.CENTER);
		btnAll = new MyJRadioButton("连续播放");
		btnOnly = new MyJRadioButton("单曲播放");
		btnRandom = new MyJRadioButton("随机循环");
		btnDelete = new ImageButton("drawable/icon/delete.png");
		btnClear = new ImageButton("drawable/icon/clear.png");
		// 面板设置
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
		// 组件设置
		btnPause.setVisible(false);
		btnAll.setSelected(true);
		// 添加组件
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
		showArtist.setFont(new Font("方正细圆简体", Font.PLAIN, 19));
		panel_showMeeage.add(showArtist); // 显示歌曲信息
		showTitle = new JLabel();
		showTitle.setBounds(0, 40, 300, 50);
		showTitle.setFont(new Font("方正细圆简体", Font.PLAIN, 39));
		panel_showMeeage.add(showTitle); // 显示歌名
		showAlbum = new JLabel();
		showAlbum.setBounds(150, 0, 150, 30);
		showAlbum.setFont(new Font("方正细圆简体", Font.PLAIN, 19));
		panel_showMeeage.add(showAlbum);
		panel_showMeeage.add(showMessage); // 添加用于显示信息的标签
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
		// 面板添加到面板
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
//	各个组件添加事件监听器
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
	//判断本地是否保存有播放列表文件
	public void JudgeLocationPlayList() throws IOException{
		// 判断是否存在保存的播放列表
		if (new File("playList/playList.txt").exists()) {
			// 点击播放时将标签更换为当前播放歌曲
			showMessage.setText("找到保存的播放列表，正在读取");
			// 在判断中实例化list，如果不存在文件则不实例化
			list = new ArrayList<File>();
			// 读取文件
			@SuppressWarnings("resource")
			BufferedReader fr = new BufferedReader(new FileReader(new File(
					"1.txt")));
			String ch = null;
			while ((ch = fr.readLine()) != null) {
				// 添加到list中
				list.add(new File(ch));
			}
			// 点击播放时将标签更换为当前播放歌曲
			showMessage.setText("播放列表读取成功");
			model = new DefaultListModel<String>();
			for (int i = 0; i < list.size(); i++) {
				model.addElement(list.get(i).getName().split("[.]")[0]);
			}
			songList = new MyJList(model);

		}
		// 如果存在播放列表文件并已添加到songList
		if (songList != null) {
			// 创建混动面板
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
			showMessage.setText(list.size() - model.size() + "首歌已经添加");
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
						System.out.println("进入循环全部");
						System.out.println(index);
						player.Play(list.get(index).getAbsolutePath());
						// 线程暂停用于使得isStop变成false
						Thread.sleep(1000);
						index = (index + 1) % list.size();
					} else if (isSingleCycle) {
						System.out.println("进入单曲循环");
						player.Play(list.get(index).getAbsolutePath());
						// 线程暂停用于使得isStop变成false
						Thread.sleep(1000);
					} else if (isRandom) {
						System.out.println("进入随机循环");
						player.Play(list.get(index).getAbsolutePath());
						// 线程暂停用于使得isStop变成false
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
					// 显示歌曲信息到界面
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
				// 歌曲结束后初始化时间，进度条
				labelStart.setText("00:00");
				labelEnd.setText("00:00");
				progressBar.setValue(0);
			}

			// 播放时将标签更换为当前播放歌曲

		}
	}

	public static void getList(File file) {
		try {
			// 判断文件是否是文件，如果是文件，获取路径，并计数
			if (file.isFile()) {
				list.add(file);
				System.out.println(file.getAbsolutePath());

			} else {
				// 如果是文件夹，声明一个数组放文件夹和他的子文件
				File[] f = file.listFiles();
				// 遍历文件件下的文件，并获取路径
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
			// 设置列表点击时间监听器
			if (e.getClickCount() == 2) {
				if (flag == 1) {

					index = songList.locationToIndex(e.getPoint());
					playthread = new PlayThread();
					playthread.start();
					// 点击播放时将标签更换为当前播放歌曲
					timer.start();
					btnStart.setVisible(false);
					btnPause.setVisible(true);
					labelStart.setText(m + " : " + s);
					labelEnd.setText(M + " : " + S);
					progressBar.setValue((int) ((m * 60 + s) / 300));
					showMessage.setText("正在播放：");
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

	// 设置文件操作事件监听器
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
					// 显示删除文件信息
					showMessage.setText(list.get(index - 1).getName()
							.split("[.]")[0]
							+ "已删除");
					// 添加列表点击监听器
					songList.addMouseListener(mouseclicklistener);

				}

			}
			// 清空list，songList
			if (e.getSource() == btnClear) {
				if (model != null) {
					model.removeAllElements();
					list.removeAll(list);
					// songList = new JList<String>(model);
					// 显示删除文件信息
					showMessage.setText("已删除");
					repaint();
					// 同时删除保存的播放列表文件
					if (new File("playList.txt").exists()) {
						new File("playList.txt").delete();
					}
				} else {
					showMessage.setText("当前播放列表已为空");
				}
			}
		};
	}

	// 播放组件事件
	class PlayOperatorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnStart) {
				// 点击播放时将标签更换为当前播放歌曲
				showMessage.setText("正在播放：");
				if (flag == 1) {
					// 默认循环播放所有
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
				// 点击停止时将标签更换为当前播放歌曲
				showMessage.setText("停止");
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
				// 点击暂停时将标签更换为当前播放歌曲
				showMessage.setText("暂停");
				player.Pause();
				timer.stop();
				btnPause.setVisible(false);
				btnStart.setVisible(true);
				flag++;

			}
			if (e.getSource() == btnPrev) {
				// 点击上一曲时将标签更换为当前播放歌曲
				showMessage.setText("上一曲");
				if (index != 0) {
					// 停止上一个线程
					// 默认循环播放所有
					index--;
					// 上一曲开始播放时将标签更换为当前播放歌曲
					player.Stop();
					labelStart.setText("00:00");
					progressBar.setValue(0);
					try {
						getinfo = new getInfo(list.get(index));
						// 显示歌曲信息到界面
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
					System.out.println("已经是第一首");
				}
			}
			if (e.getSource() == btnNext) {
				// 点击下一曲时将标签更换为当前播放歌曲

				// 停止上一个线程
				// 默认循环播放所有
				index++;
				// 点击下一曲播放时将标签更换为当前播放歌曲
				showMessage.setText("下一曲");
				labelStart.setText("00:00");
				progressBar.setValue(0);
				if (index == list.size()) {
					labelStart.setText("00:00");
					progressBar.setValue(0);
					labelEnd.setText("00:00");
					System.out.println("已经是最后一首");
					timer.stop();
				}
				player.Stop();
				try {
					getinfo = new getInfo(list.get(index));
					// 显示歌曲信息到界面
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

	// 用于实现实时刷新进度和时间
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
