package src;

import java.io.File;
import java.io.IOException;

public class StartUP{

    private boolean start = true;


    void fileHubInitializer() throws IOException {
        try{
            File deskTop = new File(System.getProperty("user.home") + "\\Desktop");
            File[] desktopFiles = deskTop.listFiles();
            for (File f : desktopFiles) {
                if(f.isDirectory() && f.getName().contains("FileViewer")){
                    start = false;
                }else {
                    //  Haven't Found dir yet
                }
            }
            while (start) {
                File fileViewer = new File(System.getProperty("user.home") + "\\Desktop\\TEAMNAME-File Explorer\\" + "FileViewer");
                fileViewer.mkdir();

                File fileHub = new File(System.getProperty("user.home") + "\\Desktop\\TEAMNAME-File Explorer\\" + "FileViewer\\" + "FileHub");
                fileHub.mkdir();

                File Users = new File(System.getProperty("user.home") + "\\Desktop\\TEAMNAME-File Explorer\\" + "FileViewer\\" + "Users");
                Users.mkdir();

                File UsersTXT = new File(System.getProperty("user.home") + "\\Desktop\\TEAMNAME-File Explorer\\" + "FileViewer\\" + "Users" + "\\UserFile.txt");
                UsersTXT.createNewFile();

                start = false;
            }

        }catch (IOException exception){
            exception.printStackTrace();
        }
    }


}
