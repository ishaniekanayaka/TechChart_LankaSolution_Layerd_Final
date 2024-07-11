package lk.ijse.dep.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";

        switch (textField) {
            case IDA:
                filed = "^A\\d+$";
                break;
            case NAME:
                filed = "^[a-zA-Z ]+$";
                break;
            case PASSWORD:
                filed=".*";
                break;
            case EMAIL:
                filed = ".*";
               // "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$"
                break;
            case ADDRESS:
                filed = "^[A-Za-z0-9\\s\\-_.,'\"/&@!?():;%+=#]*$";
                break;
            case IDC:
                filed = "^C\\d+$";
                break;
            case CONTACT:
                filed=".*";
                break;
            case IDCS:
                filed = ".*"/*"^CS\\d+$"*/;
                break;
            case DESCRIPTION:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case DATE:
                filed = ".*"/*"^\\d{4}-\\d{2}-\\d{2}$"*/;
                break;
            case STATUS:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case IDD:
                filed = "^D\\d+$";
                break;
            case METHOD:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case  AMOUNT:
                filed="^\\d+$";
                break;
            case IDP:
                filed = "^P\\d+$";
                break;
            case PRODUCT:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case IDS:
                filed = "^S\\d+$";
                break;
            case IDE:
                filed = "^E\\d+$";
                break;
            case POSITION:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case DOUBLE:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case QTY:
                filed = ".*";
                break;
            case IDI:
                filed = "^I\\d+$";
                break;

        }
        Pattern pattern = Pattern.compile(filed);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean setTextColor(TextField textField, javafx.scene.control.TextField fxTextField){
        boolean isValid = isTextFieldValid(textField, fxTextField.getText());
        if (isValid) {
            fxTextField.setStyle("-fx-border-color: green; -fx-unfocus-color: green;");
        } else {
            fxTextField.setStyle("-fx-border-color: red; -fx-unfocus-color: red;");
        }
        return isValid;
    }
}
