package sknerus.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sknerus.main.AppCore;

import java.io.IOException;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class MainPaneController {


    @FXML
    public void getDataFromUser(){

        Parent root;
        try {

            root = FXMLLoader.load(this.getClass().getResource("../gui/InputData.fxml"));

//            root = FXMLLoader.load(getClass().getClassLoader().getResource("/InputData.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (IOException e){
            e.printStackTrace();
        }


    }

    @FXML
    public void genStorePDF(){

    }

    @FXML
    public void exit(){
        Platform.exit();
    }
}
