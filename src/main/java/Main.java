import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *Главный класс программы
 */
public class Main extends Application {

    @Override

    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("jfx/mainForm.fxml"));
        stage.setTitle("Работа моя");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
