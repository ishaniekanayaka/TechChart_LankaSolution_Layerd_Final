package lk.ijse.dep.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.model.ItemDTO;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.ItemBO;
import lk.ijse.dep.model.tm.ItemTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemManagementFormController {

    public AnchorPane ParentItemRootNode;
    public AnchorPane ChildItemRootNode;
    public TableView tblItems;
    public TableColumn colItemId;
    public TableColumn colName;
    public TableColumn colQty;
    public TableColumn colPrice;


    ItemBO itemBO = (ItemBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.ITEM);

    public void btnPosOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/POS_Item_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildItemRootNode.getChildren().clear();
        ChildItemRootNode.getChildren().add(PerenetRootNode);

    }

    public void btnComputerOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ComputerItem_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildItemRootNode.getChildren().clear();
        ChildItemRootNode.getChildren().add(PerenetRootNode);
    }

    public void btnBeautyPartsOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BeautyItem_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildItemRootNode.getChildren().clear();
        ChildItemRootNode.getChildren().add(PerenetRootNode);
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<ItemTm> objects = FXCollections.observableArrayList();

        try {

            List<ItemDTO> itemList = itemBO.getAllItem();
            for (ItemDTO item : itemList) {
                ItemTm tm = new ItemTm(
                        item.getItemId(),
                        item.getI_name(),
                        item.getQtyAvailable(),
                        item.getItemPrice()
                );
                objects.add(tm);
            }
            tblItems.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("i_name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyAvailable"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

    }
}
