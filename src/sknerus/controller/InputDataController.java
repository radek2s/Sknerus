package sknerus.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sknerus.main.AppCore;
import sknerus.main.Main;

import java.sql.Timestamp;
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

    private Timestamp timestamp;

    @FXML
    public void save(){

        timestamp = new Timestamp(System.currentTimeMillis());
        String time = String.valueOf(timestamp.getTime());
        String id   = String.valueOf(AppCore.getInstance().data.size()+1);
        String docType = "income";
        String tax = "11%";

        String data[] = {
                time,
                id,
                docType,
                tf1.getText(),
                tf3.getText(),
                tf5.getText(),
                tf4.getText(),
                tax,
                tf2.getText()
        };

        AppCore.getInstance().addData(data);

        tf1.setText("");
        tf2.setText("");
        tf3.setText("");
        tf4.setText("");
        tf5.setText("");

    }
    @FXML
    public void close(){
        AppCore.getInstance().currentStage.close();
    }
}
