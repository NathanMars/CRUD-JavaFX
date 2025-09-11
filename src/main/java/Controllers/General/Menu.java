package Controllers.General;

import Application.Main;
import javafx.animation.*;
import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Menu {
    @FXML
    private JFXHamburger menuButton;

    @FXML
    private Circle menuButtonBackdrop;

    @FXML
    private VBox sideMenu;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private VBox submenuRegister;

    @FXML
    private VBox submenuRegistrations;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton registrationsButton;

    private Boolean menuIsOpen = true;

    @FXML
    private void initialize() {
        setupMenuHoverEffects();

        submenuRegister.setVisible(false);
        submenuRegister.setManaged(false);
        submenuRegister.setPrefHeight(0);

        registerButton.setOnAction(e -> toggleSubmenu(submenuRegister, 126));
        registrationsButton.setOnAction(e -> toggleSubmenu(submenuRegistrations, 126));

        javafx.application.Platform.runLater(() -> {
            contentPane.requestFocus();
        });
    }

    // Comportamento do menu lateral
    @FXML
    void menuClick(MouseEvent event) throws IOException {
        if (menuIsOpen) {
            TranslateTransition menuClose = new TranslateTransition(Duration.millis(350), sideMenu);
            menuClose.setToX(-210);
            menuClose.setToY(0);
            menuClose.setInterpolator(Interpolator.LINEAR);
            menuClose.play();

            TranslateTransition ButtonBackdropClose = new TranslateTransition(Duration.millis(350), menuButtonBackdrop);
            ButtonBackdropClose.setToX(20);
            ButtonBackdropClose.setToY(20);
            ButtonBackdropClose.setInterpolator(Interpolator.LINEAR);
            ButtonBackdropClose.play();

            TranslateTransition menuButtonClose = new TranslateTransition(Duration.millis(350), menuButton);
            menuButtonClose.setToX(10);
            menuButtonClose.setToY(15);
            menuButtonClose.setInterpolator(Interpolator.LINEAR);
            menuButtonClose.play();

            menuIsOpen = false;
        } else {
            TranslateTransition menuOpen = new TranslateTransition(Duration.millis(350), sideMenu);
            menuOpen.setToX(0);
            menuOpen.setToY(0);
            menuOpen.setInterpolator(Interpolator.LINEAR);
            menuOpen.play();

            TranslateTransition ButtonBackdropOpen = new TranslateTransition(Duration.millis(350), menuButtonBackdrop);
            ButtonBackdropOpen.setToX(225);
            ButtonBackdropOpen.setToY(20);
            ButtonBackdropOpen.setInterpolator(Interpolator.LINEAR);
            ButtonBackdropOpen.play();

            TranslateTransition menuButtonOpen = new TranslateTransition(Duration.millis(350), menuButton);
            menuButtonOpen.setToX(215);
            menuButtonOpen.setToY(15);
            menuButtonOpen.setInterpolator(Interpolator.LINEAR);
            menuButtonOpen.play();

            menuIsOpen = true;
        }
    }

    private void setupMenuHoverEffects() {
        Color originalColor = Color.web("#162636");
        Color hoverColor = Color.web("#2b3d4e");

        EventHandler<MouseEvent> onEnter = e -> {
            menuButtonBackdrop.setScaleX(1.1);
            menuButtonBackdrop.setScaleY(1.1);
            menuButton.setScaleX(1.1);
            menuButton.setScaleY(1.1);
            menuButtonBackdrop.setFill(hoverColor);
            menuButtonBackdrop.setCursor(Cursor.HAND);
            menuButton.setCursor(Cursor.HAND);
        };

        EventHandler<MouseEvent> onExit = e -> {
            menuButtonBackdrop.setScaleX(1.0);
            menuButtonBackdrop.setScaleY(1.0);
            menuButton.setScaleX(1.0);
            menuButton.setScaleY(1.0);
            menuButtonBackdrop.setFill(originalColor);
            menuButtonBackdrop.setCursor(Cursor.DEFAULT);
            menuButton.setCursor(Cursor.DEFAULT);
        };

        menuButtonBackdrop.setOnMouseEntered(onEnter);
        menuButton.setOnMouseEntered(onEnter);

        menuButtonBackdrop.setOnMouseExited(onExit);
        menuButton.setOnMouseExited(onExit);
    }

    // Comportamento dos submenus colapsaveis
    private void toggleSubmenu(VBox submenu, double targetHeight) {
        if (submenu.isVisible()) {
            collapseSubmenu(submenu);
        } else {
            expandSubmenu(submenu, targetHeight);
        }
    }

    private void expandSubmenu(VBox submenu, double targetHeight) {
        submenu.setVisible(true);
        submenu.setManaged(true);

        Timeline expand = new Timeline(
                new KeyFrame(Duration.millis(350),
                        new KeyValue(submenu.prefHeightProperty(), targetHeight, Interpolator.LINEAR))
        );
        expand.play();
    }

    private void collapseSubmenu(VBox submenu) {
        Timeline collapse = new Timeline(
                new KeyFrame(Duration.millis(350),
                        new KeyValue(submenu.prefHeightProperty(), 0, Interpolator.LINEAR))
        );

        collapse.setOnFinished(e -> {
            submenu.setVisible(false);
            submenu.setManaged(false);
        });

        collapse.play();
    }

    // Comportamento dos botões
    @FXML
    void registerClientClick(ActionEvent event) {
        loadContent("/View/Register/registerClient.fxml");
    }

    @FXML
    void registerUserClick(ActionEvent event) {
        loadContent("/View/Register/registerUser.fxml");
    }

    @FXML
    void clientRegistrationsClick(ActionEvent event) {
        loadContent("/View/Register/registerClient.fxml");
    }

    @FXML
    void userRegistrationsClick(ActionEvent event) {
        loadContent("/View/Register/registerUser.fxml");
    }

    public void userLogout(ActionEvent event) throws IOException {
        Main app = new Main();
        app.changeScene("/View/General/Login.fxml", 600, 400, "Login");
    }

    private void loadContent(String fxmlPath) {
        try {

            Node node = FXMLLoader.load(getClass().getResource(fxmlPath));
            FadeTransition ft = new FadeTransition(Duration.millis(200), node);
            ft.setFromValue(0);
            ft.setToValue(1);
            contentPane.getChildren().clear();
            contentPane.getChildren().add(node);
            ft.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
