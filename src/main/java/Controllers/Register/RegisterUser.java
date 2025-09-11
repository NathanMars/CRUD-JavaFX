package Controllers.Register;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterUser {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField roleField;

    @FXML
    private ComboBox<String> accessCbx;

    @FXML
    private CheckBox activeCheck;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton clearBtn;



}

