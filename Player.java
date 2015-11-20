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
	private boolean isStop = true;// ���Ʋ����߳�
	private boolean isPause = false;// ��ͣ״̬
	
	AudioInputStream audioInputStream;// �ļ���
	AudioFormat audioFormat;// �ļ���ʽ
	SourceDataLine sourceDataLine;// ����豸
	Thread playthread;// �����߳�
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
	

	// ����

	public void init(InputStream input) {
		try {
//			isStop = true;// ֹͣ�����߳�

			// ȡ���ļ�������
			audioInputStream = AudioSystem.getAudioInputStream(input);
			// ����ļ�����������Ƶ��ʽ�����
			audioFormat = audioInputStream.getFormat();
			// ת��mp3�ļ�����
			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
						audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(),
						audioFormat.getChannels() * 2,
						audioFormat.getSampleRate(), false);
				audioInputStream = AudioSystem.getAudioInputStream(audioFormat,
						audioInputStream);
			}

			// ������豸
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
				// ��ȡ���ݵ���������
				while ((cnt = audioInputStream.read(tempBuffer, 0,
						tempBuffer.length)) != -1) {
					if (cnt > 0) {
						// д�뻺������
						sourceDataLine.write(tempBuffer, 0, cnt);
					}
					if (isPause == true) {
						LockSupport.park();

					}
				}
				System.out.println("�������");
				// Block�ȴ���ʱ���ݱ����Ϊ��
				sourceDataLine.drain();
//				sourceDataLine.stop();
//				������򲥷���Ϻ����ٴβ���
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
			System.out.println("��ȡ�ļ�����");
		}
	}

	@Override
	public void play(URL url) {
		try {
			play(url.openStream());
		} catch (IOException e) {
			System.out.println("��ַ�д�");
		}

	}

	@Override
	public void play() {

		if (isPause == false) {

			if (playthread != null) {
				playthread.stop();
				playthread = new PlayThread();
				playthread.start();
				System.out.println("�߳��Ѵ��ڣ������ٺ��½�");
			} else {
				playthread = new PlayThread();
				playthread.start();
				System.out.println("�½��߳̽��в���");
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