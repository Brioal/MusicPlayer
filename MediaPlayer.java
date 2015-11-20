import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//����Player������չ����,����һ���������һ��ʵ�ֲ��ţ���ͣ����һ������һ����ѭ�������������
//��Ҫ����
//1.���� ��ʵ��
//2.��ͣ	��ʵ��
//3.ֹͣ	��ʵ��
//4.�ָ����� ��ʵ��

public class MediaPlayer {
	private Player player;

	public MediaPlayer() {
		player = new Player();
	}

	// �����Ƿ�����ͣ������һ����ûָ����Ź���
	public boolean getPause() {
		return player.isPause();
	}

	// �����Ƿ񲥷���ϣ�����һ��ʵ��ѭ��������һ���ȵȷ���
	public boolean isStop() {
		return player.IsStop();
	}

	// �����ַ����ж���URL����File�����õײ㲥��������ʱ�ײ㲥��������play��inputstream���е���play����
	public void Play(String string) {
		if (string.contains("http")) {
			try {
				player.play(new URL(string));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			player.play(new File(string));
		}
	}

	// �ָ�����
	public void Play() {
		player.replay();
	}

	// ��ͣ
	public void Pause() {
		player.pause();
	}

	// ֹͣ����
	public void Stop() {
		try {
			player.stop();
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}
