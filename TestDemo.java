import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;


public class TestDemo {
	public static void main(String[] args) throws IOException  {
		Test t = new Test();
		t.setVisible(true);
		t.setSize(1000, 600);
		// 设置窗口居中显示
		t.setLocationRelativeTo(null);
		// 设置窗口图标
		Toolkit tool = t.getToolkit();
		Image myimage = tool.getImage("drawable/icon/titeImage.png");
		t.setIconImage(myimage);
	}
}
