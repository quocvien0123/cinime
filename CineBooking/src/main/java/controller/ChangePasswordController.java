package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import dao.BaseRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChangePasswordController {

    @FXML
    private JFXButton changePassword_btn;

    @FXML
    private JFXButton close_btn;

    @FXML
    private Label error;

    @FXML
    private Label error_text;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField re_password;

    public void handleChangePasswordBtn(ActionEvent event) {
        String email = ForgotPasswordController.emailChangePass;
        if(password.getText().equals(re_password.getText())){
            BaseRepository.changePassword(password.getText(),email);
            close();
        }else {
            error.setVisible(true);
            error_text.setText("Error: Password do not match!");
        }

    }

    public void close() {
        Stage stage = (Stage)close_btn.getScene().getWindow();
        stage.close();
    }
}
