package Controllers.Registrations;

import Model.DAO.PurchaseDAO;
import Model.Entity.Purchase;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PurchasesRegistrationsView {

    @FXML private TableView<Purchase> tablePurchases;

    @FXML private TableColumn<Purchase, Integer> colId;
    @FXML private TableColumn<Purchase, String> colUser;
    @FXML private TableColumn<Purchase, String> colProduct;
    @FXML private TableColumn<Purchase, String> colDate;
    @FXML private TableColumn<Purchase, Integer> colQuantity;
    @FXML private TableColumn<Purchase, Double> colTotalPrice;

    @FXML
    private void initialize() {
        formatTablePurchases();
    }

    private void formatTablePurchases() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        colUser.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
            
        colProduct.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getProduct().getName()));
            
        colDate.setCellValueFactory(cellData -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return new SimpleStringProperty(cellData.getValue().getDate().format(formatter));
        });
        
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        tablePurchases.getColumns().forEach(col -> {
            col.setStyle("-fx-alignment: CENTER;");
            col.setResizable(false);
            col.setReorderable(false);
        });

        PurchaseDAO purchaseDAO = new PurchaseDAO();
        List<Purchase> purchases = purchaseDAO.selectPurchases();
        populatePurchases(purchases);
    }

    private void populatePurchases(List<Purchase> purchases) {
        tablePurchases.getItems().clear();
        tablePurchases.getItems().addAll(purchases);
    }
}
