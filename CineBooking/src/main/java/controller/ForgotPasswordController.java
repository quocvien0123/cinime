package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.BaseRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Account;

import java.sql.SQLException;

public class ForgotPasswordController {
    public static String emailChangePass;
    @FXML
    private Label error;

    @FXML
    private Label error_text;

    @FXML
    private JFXButton close_btn;

    @FXML
    private JFXButton confirm_btn;

    @FXML
    private JFXTextField email;

    public void close() {
        Stage stage = (Stage)close_btn.getScene().getWindow();
        stage.close();
    }


    public void handleConfirmBtn(ActionEvent event) throws SQLException {
        if(email.getText().isEmpty()){
            error.setVisible(true);
            error_text.setText("Error: Please enter email!");
        }
        if(!email.getText().isEmpty() && !RegisterController.isEmailFormat(email.getText())){
            error.setVisible(true);
            error_text.setText("Error: Email is not valid!");
        }
        if(BaseRepository.checkEmail(email.getText()) == 1 ){
            emailChangePass = email.getText();
            close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChangePassword.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Register");
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((((Node)event.getSource()).getScene().getWindow())));
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.setX(370);
                stage.setY(175);
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            error.setVisible(true);
            error_text.setText("Error: Email is incorrect!");
        }
    }
}
