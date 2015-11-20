import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.locks.LockSupport;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player implements AudioPlayer {
	private boolean isStop = true;// 控制播放线程
	private boolean isPause = false;// 暂停状态
	
	AudioInputStream audioInputStream;// 文件流
	AudioFormat audioFormat;// 文件格式
	SourceDataLine sourceDataLine;// 输出设备
	Thread playthread;// 播放线程
	Thread replaythread;


	public boolean isPause() {
		return isPause;
	}
	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}
	public boolean IsStop(){
		return isStop ;
	}
	

	// 播放

	public void init(InputStream input) {
		try {
//			isStop = true;// 停止播放线程

			// 取得文件输入流
			audioInputStream = AudioSystem.getAudioInputStream(input);
			// 获得文件输入流的音频格式类对象
			audioFormat = audioInputStream.getFormat();
			// 转换mp3文件编码
			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
						audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(),
						audioFormat.getChannels() * 2,
						audioFormat.getSampleRate(), false);
				audioInputStream = AudioSystem.getAudioInputStream(audioFormat,
						audioInputStream);
			}

			// 打开输出设备
			DataLine.Info dataLineInfo = new DataLine.Info(
					SourceDataLine.class, audioFormat,
					AudioSystem.NOT_SPECIFIED);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public class PlayThread extends Thread {
		byte tempBuffer[] = new byte[2048];

		public void run() {
			try {
				isStop = false ;
				int cnt;
				// 读取数据到缓存数据
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (cnt > 0) {
						// 写入缓存数据
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
					if (isPause == true) {
						LockSupport.park();

					}
				}
				System.out.println("播放完毕");
				// Block等待临时数据被输出为空
				sourceDataLine.drain();
//				sourceDataLine.stop();
//				如果打开则播放完毕后不能再次播放
				sourceDataLine.close();
//				sourceDataLine.
//				audioInputStream.close();
				isStop = true ;
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	@Override
	public void play(InputStream in) {
		init(in);
		play() ;
	}

	@Override
	public void play(File file) {
		try {
			play(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println("读取文件出错");
		}
	}

	@Override
	public void play(URL url) {
		try {
			play(url.openStream());
		} catch (IOException e) {
			System.out.println("网址有错");
		}

	}

	@Override
	public void play() {

		if (isPause == false) {

			if (playthread != null) {
				playthread.stop();
				playthread = new PlayThread();
				playthread.start();
				System.out.println("线程已存在，先销毁后新建");
			} else {
				playthread = new PlayThread();
				playthread.start();
				System.out.println("新建线程进行播放");
			}
		}
	}
	public void replay(){
		replaythread = new RePlayThread() ;
		replaythread.start();
	}

	public void stop() throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {
		if(playthread!=null){
		playthread.stop();
		playthread = new PlayThread() ;
		}
		if(!(replaythread==null)){
			replaythread.stop();
		}
	}

	public void pause() {
		if (!(replaythread == null)) {
			replaythread.stop();
		}
		isPause = true;
	}

	class RePlayThread extends Thread {
		@Override
		public void run() {

			while (true) {
				LockSupport.unpark(playthread);
			}
		}
	}
}