package Application;

import atlantafx.base.theme.PrimerDark;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends javafx.application.Application {

    private static Stage stage;

    @Override
    public void start(Stage primarystage) throws IOException {
        Main.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        stage = primarystage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/General/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primarystage.setResizable(false);
        primarystage.setTitle("Login");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public void changeScene(String fxml, int width, int height, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}