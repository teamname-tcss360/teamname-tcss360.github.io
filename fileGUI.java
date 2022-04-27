
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class fileGUI{

    void guiBuilder(){
        JFrame frame = new JFrame();
        JPanel j = new JPanel();
        JButton about = new JButton();
        j.add(about);
        frame.setVisible(true);
    }
}
class main{
    public static void main(String[] args) {
        new fileGUI().guiBuilder();
        System.out.println("Help");
    }


}