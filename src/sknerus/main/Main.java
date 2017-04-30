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


    //TODO: 1.Utworzyć plik kontrolera InputData - 2.dodać do widoku pole ilość - 3. Utowrzyc metode '@FXML public void zapisz' w
    // dzięki której dane z pól zostaną zapisane do AppCore.getInstance poprzez metodę addData([...parametry...])


    public static void main(String[] args){ launch(args); }
}
