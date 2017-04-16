package sknerus.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class MainPaneController {

    @FXML
    public void exit(){
        Platform.exit();
    }
}
