import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        PizzaOrderPage orderPage = new PizzaOrderPage();
        Scene scene = new Scene(orderPage.getView(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Pizza Kiosk - Sakshi");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}