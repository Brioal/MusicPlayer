import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

public class getInfo {
	MP3File mp3file;
	String title, artist, album, year;
	long time = 0  ;

	public MP3File getMp3file() {
		return mp3file;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getYear() {
		return year;
	}
	public long getTime(){
		return time ;
	}

	public getInfo(File file) throws IOException, TagException,
			ReadOnlyFileException, InvalidAudioFrameException {
		mp3file = new MP3File(file);
		Tag tag = mp3file.getTag();
		artist = tag.getFirst(FieldKey.ARTIST);
		album = tag.getFirst(FieldKey.ALBUM);
		year = tag.getFirst(FieldKey.YEAR);
		title = tag.getFirst(FieldKey.TITLE);
		MP3AudioHeader audioHeader = (MP3AudioHeader)mp3file.getAudioHeader();  
        time = audioHeader.getTrackLength();
		AbstractID3v2Tag tagid3v2 = mp3file.getID3v2Tag();
		AbstractID3v2Frame frame = (AbstractID3v2Frame) tagid3v2.getFrame("APIC");
		FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
		byte[] imageData = body.getImageData();
		FileOutputStream fos = new FileOutputStream("drawable/picture/"+title+".jpg");
		fos.write(imageData);
		fos.close();
	}
//	public static void main(String[] args) throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
//		getInfo getinfo = new getInfo(new File("2.mp3")) ;
//		System.out.println(getinfo.getTitle());
//		System.out.println(getinfo.getArtist());
//		System.out.println(getinfo.getAlbum());
//		System.out.println(getinfo.getYear());
//	}

}
