package sknerus.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by radek_000 on 16.04.2017.
 */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Sknerus");

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../gui/MainPane.fxml"));
        BorderPane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sknera");
        primaryStage.show();
    }


    public static void main(String[] args){ launch(args); }
}
