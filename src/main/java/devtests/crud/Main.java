package devtests.crud;

import atlantafx.base.theme.PrimerDark;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {

    private static Stage stage;

    @Override
    public void start(Stage primarystage) throws IOException {
        Main.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        stage = primarystage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primarystage.setResizable(false);
        primarystage.setTitle("Login");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent scene = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}