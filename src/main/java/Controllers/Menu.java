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


public class Menu {
    @FXML
    private JFXHamburger menuButton;

    @FXML
    private VBox sideMenu;

    @FXML
    private JFXButton logoutButton;

    private Boolean menuisOpen = true;


    @FXML
    void menuClick(MouseEvent event) throws IOException {
        if (menuisOpen) {
            TranslateTransition menuClose = new TranslateTransition(Duration.millis(350), sideMenu);
            menuClose.setToX(-210);
            menuClose.setToY(0);
            menuClose.setInterpolator(Interpolator.LINEAR);
            menuClose.play();

            TranslateTransition menuButtonClose = new TranslateTransition(Duration.millis(350), menuButton);
            menuButtonClose.setToX(10);
            menuButtonClose.setToY(25);
            menuButtonClose.setInterpolator(Interpolator.LINEAR);
            menuButtonClose.play();

            menuisOpen = false;
        } else {
            TranslateTransition menuOpen = new TranslateTransition(Duration.millis(400), sideMenu);
            menuOpen.setToX(0);
            menuOpen.setToY(0);
            menuOpen.setInterpolator(Interpolator.LINEAR);
            menuOpen.play();

            TranslateTransition menuButtonOpen = new TranslateTransition(Duration.millis(400), menuButton);
            menuButtonOpen.setToX(215);
            menuButtonOpen.setToY(25);
            menuButtonOpen.setInterpolator(Interpolator.LINEAR);
            menuButtonOpen.play();

            menuisOpen = true;
        }
    }

    public void userLogout(ActionEvent event) throws IOException {
        Main app = new Main();
        app.changeScene("/View/Login.fxml", 600, 400, "Login");
    }
}
