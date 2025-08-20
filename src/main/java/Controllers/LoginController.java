package Controllers;

import devtests.crud.Main;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label warningLabel;

    @FXML
    protected void userLogin() throws IOException {
        authLogin();
    }

    private void authLogin() throws IOException {
        if (username.getText().equals("nmarques") && password.getText().equals("Teste")) {
            showWarning("Login feito com sucesso!", Color.WHITE);
            Main app = new Main();
            app.changeScene("/View/Menu.fxml");
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