package testing;

import static org.junit.jupiter.api.Assertions.fail;

import javax.swing.JFrame;
import src.FileView;

import org.junit.jupiter.api.Test;


class FileViewTest {
	
	JFrame f = new JFrame();
	FileView tester = new FileView(f,"Bob_Keener");
	
	@Test
	void test() {
		f.setVisible(true);
		fail("Not yet implemented");
	}

}
