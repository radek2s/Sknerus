package sknerus.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sknerus.main.AppCore;
import sknerus.main.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lgrzegorek
 * @version 1.0 created on 2017-04-25.
 *          Description:
 */
public class InputDataController {

    @FXML
    private
    TextField tf1;
    @FXML
    private
    TextField tf2;
    @FXML
    private
    TextField tf3;
    @FXML
    private
    TextField tf4;
    @FXML
    private
    TextField tf5;

    //display todays date
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String dateNow = sdf.format(new Date());

    @FXML
    public void save(){
        //AppCore.getInstance().addData(tf1.getText(),dateNow , tf2.getText(),tf3.getText(),Float.parseFloat(tf4.getText()), Integer.parseInt(tf5.getText()) );
    }
    @FXML
    public void close(){
        AppCore.getInstance().currentStage.close();
    }
}
