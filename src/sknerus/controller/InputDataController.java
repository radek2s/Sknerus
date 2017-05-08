package sknerus.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sknerus.main.AppCore;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Dell on 2017-04-25.
 */
public class InputDataController {

    private MainPaneController mainPaneController;

    @FXML
    TextField tf1;
    @FXML
    TextField tf2;
    @FXML
    TextField tf3;
    @FXML
    TextField tf4;
    @FXML
    TextField tf5;

    //display todays date
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dateNow = sdf.format(new Date());


    @FXML
    public void save(){
        AppCore.getInstance().addData(tf1.getText(),dateNow , tf2.getText(),tf3.getText(),Float.parseFloat(tf4.getText()), Integer.parseInt(tf5.getText()) );
    }
    @FXML
    public void close(){
      Platform.exit();
    }
}
