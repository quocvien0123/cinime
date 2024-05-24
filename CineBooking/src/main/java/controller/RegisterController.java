package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.BaseRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    @FXML
    private Label error;

    @FXML
    private Label error_text;

    @FXML
    private JFXButton close_btn;

    @FXML
    private JFXButton register_btn;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField username;



    public void close() {
        Stage stage = (Stage)close_btn.getScene().getWindow();
        stage.close();
    }

    public static boolean isEmailFormat(String email){
        return email.matches(EMAIL_PATTERN);
    }

    public boolean checkNull(){
        return username.getText().isEmpty() || password.getText().isEmpty() || email.getText().isEmpty();
    }

    public void handleRegisterBtn(ActionEvent event) throws SQLException {
        if(checkNull()){
            error.setVisible(true);
            error_text.setText("Error: Please enter complete information!");
        }
        else{
            if(BaseRepository.checkUsername(username.getText()) == 0){
                if(isEmailFormat(email.getText()) && !checkNull()){
                    if(BaseRepository.checkEmail(email.getText()) == 0){
                        BaseRepository.registerAccount(username.getText(),password.getText(),email.getText());
                        close();
                    }else {
                        error.setVisible(true);
                        error_text.setText("Error: Email is used!");
                    }
                }
                else {
                    error.setVisible(true);
                    error_text.setText("Error: Email is not valid!");
                }
            }
            else {
                error.setVisible(true);
                error_text.setText("Error: Username is used!");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
