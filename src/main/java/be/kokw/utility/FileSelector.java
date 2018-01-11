package be.kokw.utility;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public interface FileSelector {

    static File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kies het Contract");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(new Stage());
        return file;
    }
}
