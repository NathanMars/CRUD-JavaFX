package Controllers;

import devtests.crud.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import java.io.IOException;

import DAO.UserDAO;
import Model.User;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Login {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Button loginButton;
    @FXML
    private Label warningLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void initialize() {

        Username.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                loginButton.fire();
            }
        });

        Password.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.ENTER){
                loginButton.fire();
            }
        });

        Platform.runLater(() -> Username.requestFocus());
    }

    @FXML
    private void authLogin() throws IOException {
        String username = Username.getText();
        String password = Password.getText();
        User user = userDAO.authenticate(username, password);

        if (user != null) {
            showWarning("Login feito com sucesso!", Color.WHITE);
            Main app = new Main();
            app.changeScene("/View/Menu.fxml", 1000, 700, "Biblioteca");
        } else {
            showWarning("Usuário não identificado!", Color.RED);
        }
    }

    private void showWarning(String text, Color color) {
        warningLabel.setText(text);
        warningLabel.setTextFill(color);
        warningLabel.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> warningLabel.setVisible(false));
        pause.play();
    }
}