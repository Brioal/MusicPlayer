import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//创建Player对象并扩展功能,由上一层操作，上一层实现播放，暂停，上一曲，下一曲，循环。随机，单曲
//主要功能
//1.播放 已实现
//2.暂停	已实现
//3.停止	已实现
//4.恢复播放 已实现

public class MediaPlayer {
	private Player player;

	public MediaPlayer() {
		player = new Player();
	}

	// 返回是否是暂停，供上一层调用恢复播放功能
	public boolean getPause() {
		return player.isPause();
	}

	// 返回是否播放完毕，供上一层实现循环单曲下一曲等等方法
	public boolean isStop() {
		return player.IsStop();
	}

	// 传入字符串判断是URL或者File并调用底层播放器，此时底层播放器已在play（inputstream）中调用play方法
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

	// 恢复播放
	public void Play() {
		player.replay();
	}

	// 暂停
	public void Pause() {
		player.pause();
	}

	// 停止播放
	public void Stop() {
		try {
			player.stop();
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}
