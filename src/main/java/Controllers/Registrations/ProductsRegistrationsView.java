package Controllers.Registrations;

import Model.DAO.ProductDAO;
import Model.Entity.Product;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ProductsRegistrationsView {

    @FXML private TableView<Product> tableProducts;

    @FXML private TableColumn<Product, Integer> colId;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, Double> colPrice;
    @FXML private TableColumn<Product, Integer> colQuantity;
    @FXML private TableColumn<Product, String> colDescription;
    @FXML private TableColumn<Product, Boolean> colActive;

    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private TextArea descriptionField;
    @FXML private CheckBox activeField;

    @FXML
    private void initialize() {
        formatTableProducts();
        clearFields();

        tableProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ProductSelected(newSelection);
            }
        });
    }

    private void formatTableProducts() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        tableProducts.getColumns().forEach(col -> {
            col.setStyle("-fx-alignment: CENTER;");
            col.setResizable(false);
            col.setReorderable(false);
        });

        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.selectProducts();
        populateProducts(products);
    }

    private void populateProducts(List<Product> products) {
        tableProducts.getItems().clear();
        tableProducts.getItems().addAll(products);
    }

    private void ProductSelected(Product product) {
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getQuantity()));
        descriptionField.setText(product.getDescription());
        activeField.setSelected(product.isActive());

        nameField.setDisable(false);
        priceField.setDisable(false);
        quantityField.setDisable(false);
        descriptionField.setDisable(false);
        activeField.setDisable(false);
    }

    @FXML
    private void clearFields() {
        nameField.clear();
        nameField.setDisable(true);
        priceField.clear();
        priceField.setDisable(true);
        quantityField.clear();
        quantityField.setDisable(true);
        descriptionField.clear();
        descriptionField.setDisable(true);
        activeField.setSelected(false);
        activeField.setDisable(true);
        tableProducts.getSelectionModel().clearSelection();
    }
}
