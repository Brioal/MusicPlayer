import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class GetSong extends JFrame{
	private static final long serialVersionUID = -8957970342008470159L;
	JFileChooser filechooser;
	static int n = 0;
	static List<File> list = new ArrayList<File>();

	public GetSong()  {
		filechooser = new JFileChooser("music");
		getContentPane().add(filechooser);
		filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}

	// 保存文件方法，传入List
	public void Save(List<?> list) throws IOException {
		int value = filechooser.showSaveDialog(null);
		if (value == JFileChooser.APPROVE_OPTION) {
			FileWriter fw =  new FileWriter(new File("playList/playList.txt"));
			for (int i = 0; i < list.size(); i++) {
				fw.write(((File) list.get(i)).getAbsolutePath() + "\n");
			}
			fw.close();
		}
	}

	// 添加文件方法
	public List<File> Open() {
		int value = filechooser.showOpenDialog(null);
		if (value == JFileChooser.APPROVE_OPTION) {
			get(filechooser.getSelectedFile());
		}
		return list;
	}

	public static void get(File file) {

		try {
			// 判断文件是否是文件，如果是文件，获取路径，并计数
			if (file.isFile()) {
				n++;
				list.add(file);
				System.out.println(file.getAbsolutePath());

			} else {
				// 如果是文件夹，声明一个数组放文件夹和他的子文件
				File[] f = file.listFiles();
				// 遍历文件件下的文件，并获取路径
				for (File file2 : f) {
					get(file2);
				}

			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args)  {
		GetSong getsong = new GetSong() ;
		getsong.setVisible(true);
		
		
	}

}