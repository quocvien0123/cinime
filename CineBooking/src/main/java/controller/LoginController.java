package controller;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import dao.BaseRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginController implements Initializable{

    public JFXTextField showPass;
    private File file;
    private Media media;
    private static MediaPlayer mediaPlayer;

    @FXML
    private JFXButton forgot_btn;

    @FXML
    private JFXButton close_btn;

    @FXML
    private JFXButton minus_btn;

    @FXML
    private JFXButton register;

    @FXML
    private Label error;

    @FXML
    private Label error_text;

    @FXML
    private MediaView mediaView;

    @FXML
    private JFXButton login_btn;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXCheckBox show_pass;

    @FXML
    private JFXTextField username;


    public static void resume(){
        mediaPlayer.setMute(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String path ="src/main/resources/video/video_login.mp4";
        file = new File(path);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        });
        showPass.setVisible(false);

    }

//    login faild
    public void loginFaild(){
        error.setVisible(true);
        error_text.setText("Error: Incorrect username or password!");
    }

//    register
    public void handleRegister(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Register.fxml"));
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

//    login
    public void handleLoginButton(ActionEvent event) throws IOException, SQLException {
        if (BaseRepository.checkAccount(username.getText(), password.getText())) {
            System.out.println("Đăng nhập thành công!");
            mediaPlayer.setMute(true);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UserView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("CineBooking");
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((((Node)event.getSource()).getScene().getWindow())));
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Thông tin đăng nhập không hợp lệ!");
            loginFaild();
        }
    }

//    show pass
    public void checkedShow_pass() {
        if(show_pass.isSelected()){
            showPass.setText(password.getText());
            showPass.setVisible(true);
            password.setVisible(false);
        }
        else{
            showPass.setVisible(false);
            password.setText(showPass.getText());
            password.setVisible(true);
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minus() {
        Stage stage = (Stage)minus_btn.getScene().getWindow();
        stage.setIconified(true);
    }
//  forgot password
    public void handleForgotPassword(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ForgotPassword.fxml"));
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

}
