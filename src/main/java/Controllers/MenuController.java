package Controllers;

import devtests.crud.Main;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class MenuController {
    @FXML
    private JFXHamburger menuButton;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private VBox sidepane;

    @FXML
    private JFXButton logoutButton;

    @FXML
    void menuClick(MouseEvent event) throws IOException {

        loadSideMenu();

        if (menuDrawer.isOpened()) {
            menuDrawer.close();

            TranslateTransition transition = new TranslateTransition(Duration.millis(350), menuButton);
            transition.setToX(10);  // posição final em X
            transition.setToY(25);  // posição final em Y
            transition.setInterpolator(Interpolator.LINEAR); // movimento linear
            transition.play();
        } else {
            menuDrawer.open();

            TranslateTransition transition = new TranslateTransition(Duration.millis(400), menuButton);
            transition.setToX(210);
            transition.setToY(25);
            transition.setInterpolator(Interpolator.LINEAR);
            transition.play();
        }
    }

    public void loadSideMenu() throws IOException {
        sidepane = FXMLLoader.load(getClass().getResource("/View/SideMenu.fxml"));
        menuDrawer.setSidePane(sidepane);
    }

    public void userLogout(ActionEvent event) throws IOException {
        Main app = new Main();
        app.changeScene("/View/Login.fxml", 600, 400, "Login");
    }
}
