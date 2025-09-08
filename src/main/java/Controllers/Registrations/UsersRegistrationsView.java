package Controllers.Registrations;

import Model.DAO.UserDAO;
import Model.Entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UsersRegistrationsView {

    @FXML private TableView<User> tableUsers;

    @FXML private TableColumn<User, Integer> colId;

    @FXML private TableColumn<User, String> colUsername;

    @FXML private TableColumn<User, String> colName;

    @FXML private TableColumn<User, String> colCpf;

    @FXML private TableColumn<User, String> colType;

    @FXML private TableColumn<User, Boolean> colActive;

    @FXML private TextField usernameField;

    @FXML private TextField nameField;

    @FXML private TextField cpfField;

    @FXML private TextField emailField;

    @FXML private DatePicker birthField;

    @FXML private ComboBox<String> typeField;

    @FXML private CheckBox activeField;

    @FXML private Button btnSave;

    @FXML
    private void initialize() {
        formatTableUsers();
        clearFields();
        typeField.getItems().addAll("BIBLIOTECARIO", "ESTAGIARIO", "ADMINISTRADOR");

        tableUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                UserSelected(newSelection);
            }
        });
    }

    private void formatTableUsers() {
        // Configura as colunas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        tableUsers.getColumns()
                .forEach(col -> {
                    col.setStyle("-fx-alignment: CENTER;");
                    col.setResizable(false);
                    col.setReorderable(false);
                });

        // Busca dados no Model.DAO
        UserDAO UserDAO = new UserDAO();
        List<User> Users = UserDAO.selectUsers();

        // Popula tabela
        populateUsers(Users);
    }

    private void populateUsers(List<User> Users) {
        tableUsers.getItems().clear();
        tableUsers.getItems().addAll(Users);
    }

    private void UserSelected(User user) {
        usernameField.setText(user.getUsername());
        //nameField.setText(user.getName());
        //cpfField.setText(user.getCpf());
        //emailField.setText(user.getEmail());
        //birthField.setValue(user.getBirthate()); // se birthDate for LocalDate
        //activeField.setSelected(user.getActive());

        // habilita os campos para edição
        usernameField.setDisable(false);
        nameField.setDisable(false);
        cpfField.setDisable(false);
        emailField.setDisable(false);
        birthField.setDisable(false);
        activeField.setDisable(false);
    }

    @FXML
    private void clearFields() {
        usernameField.clear();
        usernameField.setDisable(true);
        nameField.clear();
        nameField.setDisable(true);
        cpfField.clear();
        cpfField.setDisable(true);
        emailField.clear();
        emailField.setDisable(true);
        birthField.setValue(null);
        birthField.setDisable(true);
        typeField.getSelectionModel().clearSelection();
        typeField.setDisable(true);
        activeField.setSelected(false);
        activeField.setDisable(true);
        tableUsers.getSelectionModel().clearSelection();
    }
    }

