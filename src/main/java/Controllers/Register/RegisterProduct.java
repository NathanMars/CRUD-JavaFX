package Controllers.Register;

import Model.DAO.ProductDAO;
import Model.Entity.Product;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterProduct {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private CheckBox activeCheck;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton clearBtn;

    private ProductDAO productDAO = new ProductDAO();

    @FXML
    public void initialize() {
        saveBtn.setOnAction(event -> saveProduct());
        clearBtn.setOnAction(event -> clearFields());
    }

    private void saveProduct() {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String description = descriptionField.getText();
            boolean active = activeCheck.isSelected();

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescription(description);
            product.setActive(active);

            if (productDAO.insertProduct(product)) {
                System.out.println("Produto salvo com sucesso!");
                clearFields();
                closeWindow();
            } else {
                System.err.println("Erro ao salvar produto.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameField.clear();
        priceField.clear();
        quantityField.clear();
        descriptionField.clear();
        activeCheck.setSelected(false);
    }

    private void closeWindow() {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
