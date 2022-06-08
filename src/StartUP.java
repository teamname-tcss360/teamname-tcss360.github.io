package src;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.ProtectionDomain;

public class StartUP{


    public String folderLocation = "";
    public String parentFolder = "";
    private boolean start = true;


    String fileHubInitializer() throws IOException {

        try {
            File file = new File(StartUP.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            parentFolder = file.getParent();
            System.out.println(parentFolder);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File deskTop = new File(parentFolder);
        File[] parentFiles = deskTop.listFiles();
        for (File f : parentFiles) {
            if ((f.isDirectory() && f.getName().contains("FileViewer"))) {
                if (f.isDirectory()) {
                    start = false;
                } else {
                    //  Haven't Found dir yet
                }
            }
        }
        System.out.println(System.getProperty("user.dir"));
        if(parentFolder.contains("TEAMNAME")){
            folderLocation = parentFolder+"\\FileViewer";
            System.out.println(folderLocation);


        }else{
            folderLocation =parentFolder;
            System.out.println(folderLocation);


        }



        while (start) {
      //          System.out.println(System.getProperty("user.dir"));

                File fileViewer = new File(folderLocation);
                fileViewer.mkdir();
            System.out.println(fileViewer);

                File fileHub = new File(folderLocation+"\\FileHub");
                fileHub.mkdir();
                System.out.println(fileHub);

                File Users = new File(folderLocation + "\\Users");
                System.out.println("Users folder");
                Users.mkdir();

                File UsersTXT = new File(folderLocation + "\\Users" + "\\UserFile.txt");
                System.out.println(" Users Text file :  "+folderLocation + "\\Users" + "\\UserFile.txt");
                UsersTXT.createNewFile();

            System.out.println(folderLocation);

                start = false;
        }
        return folderLocation;
    }




}
