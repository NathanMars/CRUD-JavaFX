package Controllers.Registrations;

import DAO.ClientDAO;
import Model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.List;

public class ClientsRegistrationsView {

    @FXML private TableView<Client> tableClients;

    @FXML private TableColumn<Client, String> colName;

    @FXML private TableColumn<Client, String> colCpf;

    @FXML private TextField nameField;

    @FXML private TextField cpfField;

    @FXML private DatePicker birthField;

    @FXML private TextField emailField;

    @FXML private TextField phoneField;

    @FXML private TextField addressField;

    @FXML private CheckBox activeField;

    @FXML private Button btnSave;

    @FXML
    private void initialize() {
        formatTableClients();
        clearFields();

        // Listener para seleção na tabela
        tableClients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ClientSelected(newSelection);
            }
        });
    }

    private void formatTableClients() {
        // Configuração das colunas
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        tableClients.getColumns().forEach(col -> {
            col.setStyle("-fx-alignment: CENTER;");
            col.setResizable(false);
            col.setReorderable(false);
        });

        // Busca no DAO
        ClientDAO ClientDAO = new ClientDAO();
        List<Client> Clients = ClientDAO.selectClients();

        populateClients(Clients);
    }

    private void populateClients(List<Client> Clients) {
        tableClients.getItems().clear();
        tableClients.getItems().addAll(Clients);
    }

    private void ClientSelected(Client Client) {
        nameField.setText(Client.getName());
        cpfField.setText(Client.getCpf());
        birthField.setValue(Client.getBirthdate());
        emailField.setText(Client.getEmail());
        phoneField.setText(Client.getPhone());
        addressField.setText(Client.getAddress());
        activeField.setSelected(Client.isActive());

        // Habilita campos para edição
        enableFields(true);
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        cpfField.clear();
        emailField.clear();
        phoneField.clear();
        birthField.setValue(null);
        addressField.clear();
        activeField.setSelected(false);

        enableFields(false);
        tableClients.getSelectionModel().clearSelection();
    }

    private void enableFields(boolean enable) {
        nameField.setDisable(!enable);
        cpfField.setDisable(!enable);
        emailField.setDisable(!enable);
        phoneField.setDisable(!enable);
        birthField.setDisable(!enable);
        addressField.setDisable(!enable);
        activeField.setDisable(!enable);
    }
}
