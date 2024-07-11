package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.model.ItemDTO;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.ItemBO;

import java.sql.SQLException;

public class ComputerItemManagementFormController {
    
    public AnchorPane ComItemRootNode;
    public TextField txtItemId;
    public TextField txtItemName;

    public TextField txtQTY;
    public TextField txtPrice;

    ItemBO itemBO = (ItemBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.ITEM);

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtItemId.getText();

        ItemDTO item = itemBO.searchByItemId(id);
        if (item != null) {
            txtItemId.setText(item.getItemId());
            txtItemName.setText(item.getI_name());
            txtQTY.setText(String.valueOf(item.getQtyAvailable()));
            txtPrice.setText(String.valueOf(item.getItemPrice()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "item not found!").show();

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String itemId = txtItemId.getText();
        String i_name = txtItemName.getText();
        int qtyAvailable= Integer.parseInt(txtQTY.getText());
        double itemPrice= Double.parseDouble(txtPrice.getText());

    if (isValid()){
        try {
            boolean isUpdated = itemBO.updateItem(new ItemDTO(itemId, i_name,  qtyAvailable,itemPrice));

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "item didnt updated!").show();
            }

        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String itemId= txtItemId.getText();


        try {
            boolean isDelete = itemBO.deleteItem(itemId);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtItemId.setText("");
        txtItemName.setText("");
        txtQTY.setText("");
        txtPrice.setText("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String itemId = txtItemId.getText();
        String i_name = txtItemName.getText();
        int qtyAvailable = Integer.parseInt(txtQTY.getText());
        double itemPrice = Double.parseDouble(txtPrice.getText());

        if (isValid()){
        ItemDTO item = new ItemDTO(itemId, i_name,  qtyAvailable, itemPrice);

        try {
            boolean isSaved = itemBO.saveItem(item);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }
    public void txtItemIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDI, txtItemId);

    }

    public void txtItemNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtItemId);
    }

    public void txtQTYOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.QTY, txtQTY);
    }

    public void txtPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.DOUBLE, txtPrice);

    }
    public boolean isValid(){

        boolean nameValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtItemName);
        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDI, txtItemId);
        boolean qtyValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.QTY, txtQTY);
        boolean priceValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.DOUBLE, txtPrice);

        return nameValid && idValid && qtyValid && priceValid  ;
    }

}
