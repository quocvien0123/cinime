package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static controller.UserViewController.logout;

public class LogoutController {

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton yesbtn;


    public void handleClose(MouseEvent mouseEvent) {
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }



    public void handleNoBtn(ActionEvent event) {
        Stage stage = (Stage)close.getScene().getWindow();
        stage.close();
    }
    public void handleYesBtn(ActionEvent event) {
        logout = true;
    }
}
