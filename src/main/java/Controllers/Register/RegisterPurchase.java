package Controllers.Register;

import Model.DAO.ProductDAO;
import Model.DAO.PurchaseDAO;
import Model.DAO.UserDAO;
import Model.Entity.Product;
import Model.Entity.Purchase;
import Model.Entity.User;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDateTime;
import java.util.List;

public class RegisterPurchase {

    @FXML
    private ComboBox<User> userCbx;

    @FXML
    private ComboBox<Product> productCbx;

    @FXML
    private TextField quantityField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton clearBtn;

    private UserDAO userDAO = new UserDAO();
    private ProductDAO productDAO = new ProductDAO();
    private PurchaseDAO purchaseDAO = new PurchaseDAO();

    @FXML
    public void initialize() {
        loadUsers();
        loadProducts();

        saveBtn.setOnAction(event -> savePurchase());
        clearBtn.setOnAction(event -> clearFields());

        quantityField.textProperty().addListener((observable, oldValue, newValue) -> calculateTotal());
        productCbx.valueProperty().addListener((observable, oldValue, newValue) -> calculateTotal());
    }

    private void loadUsers() {
        List<User> users = userDAO.selectUsers();
        userCbx.getItems().addAll(users);
        userCbx.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                return user != null ? user.getUsername() : "";
            }

            @Override
            public User fromString(String string) {
                return null;
            }
        });
    }

    private void loadProducts() {
        List<Product> products = productDAO.selectProducts();
        productCbx.getItems().addAll(products);
        productCbx.setConverter(new StringConverter<Product>() {
            @Override
            public String toString(Product product) {
                return product != null ? product.getName() : "";
            }

            @Override
            public Product fromString(String string) {
                return null;
            }
        });
    }

    private void calculateTotal() {
        try {
            if (productCbx.getValue() != null && !quantityField.getText().isEmpty()) {
                double price = productCbx.getValue().getPrice();
                int quantity = Integer.parseInt(quantityField.getText());
                double total = price * quantity;
                totalPriceLabel.setText(String.format("R$ %.2f", total));
            } else {
                totalPriceLabel.setText("R$ 0.00");
            }
        } catch (NumberFormatException e) {
            totalPriceLabel.setText("R$ 0.00");
        }
    }

    private void savePurchase() {
        try {
            User user = userCbx.getValue();
            Product product = productCbx.getValue();
            int quantity = Integer.parseInt(quantityField.getText());

            if (user == null || product == null) {
                System.err.println("Selecione usuário e produto.");
                return;
            }

            Purchase purchase = new Purchase();
            purchase.setUser(user);
            purchase.setProduct(product);
            purchase.setQuantity(quantity);
            purchase.setTotalPrice(product.getPrice() * quantity);
            purchase.setDate(LocalDateTime.now());

            if (purchaseDAO.insertPurchase(purchase)) {
                System.out.println("Compra salva com sucesso!");
                clearFields();
                closeWindow();
            } else {
                System.err.println("Erro ao salvar compra.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato numérico: " + e.getMessage());
        }
    }

    private void clearFields() {
        userCbx.getSelectionModel().clearSelection();
        productCbx.getSelectionModel().clearSelection();
        quantityField.clear();
        totalPriceLabel.setText("R$ 0.00");
    }

    private void closeWindow() {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
}
