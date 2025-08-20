package devtests.crud;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;


public class MenuController {
    @FXML
    private JFXButton logoutButton;

    public void userLogout(ActionEvent event) throws IOException {
        Main app = new Main();
        app.changeScene("/View/Login.fxml");
    }
}
