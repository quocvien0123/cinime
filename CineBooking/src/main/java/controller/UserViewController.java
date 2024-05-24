package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import run.Run;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {

    @FXML
    private JFXButton support_btn;

    @FXML
    private JFXButton film_btn;

    @FXML
    private JFXButton food_btn;

    @FXML
    private JFXButton close;

    @FXML
    private JFXButton home_btn;

    @FXML
    private JFXButton minimize;

    @FXML
    private JFXRadioButton radio1;

    @FXML
    private JFXRadioButton radio2;

    @FXML
    private JFXRadioButton radio3;

    @FXML
    private JFXRadioButton radio4;

    @FXML
    private JFXRadioButton radio5;

    @FXML
    private JFXRadioButton radio6;

    @FXML
    private JFXRadioButton radio7;


    @FXML
    private MediaView mediaView;

    private Media media;

    private MediaPlayer mediaPlayer;

    private File file;
    static boolean logout = false;

    ToggleGroup buttonGrounp = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        home_btn.getStyleClass().clear();
        home_btn.getStyleClass().add("nav_set_btn");

        radio1.setToggleGroup(buttonGrounp);
        radio2.setToggleGroup(buttonGrounp);
        radio3.setToggleGroup(buttonGrounp);
        radio4.setToggleGroup(buttonGrounp);
        radio5.setToggleGroup(buttonGrounp);
        radio6.setToggleGroup(buttonGrounp);
        radio7.setToggleGroup(buttonGrounp);

        radio1.setSelected(true);
        String path = "src/main/resources/video/video1.mp4";
        play_vd(path);
    }
    public void play_vd(String path){
        file = new File(path);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(mediaPlayer.getStartTime());
            mediaPlayer.play();
        });
    }

    public void onClickVideo1() {
        mediaPlayer.stop();
        String path ="src/main/resources/video/video1.mp4";
        play_vd(path);
    }

    public void onClickVideo2() {
        mediaPlayer.stop();
        String path ="src/main/resources/video/video2.mp4";
        play_vd(path);
    }

    public void onClickVideo3() {
        mediaPlayer.stop();
        String path ="src/main/resources/video/video3.mp4";
        play_vd(path);
    }
    public void onClickVideo4() {
        mediaPlayer.stop();
        String path ="src/main/resources/video/video4.mp4";
        play_vd(path);
    }

    public void onClickVideo5() {
        mediaPlayer.stop();
        String path ="src/main/resources/video/video5.mp4";
        play_vd(path);
    }

    public void onClickVideo6() {
        mediaPlayer.stop();
        String path ="src/main/resources/video/video6.mp4";
        play_vd(path);
    }
    
    public void onClickVideo7() {
        mediaPlayer.stop();
        String path ="src/main/resources/video/video7.mp4";
        play_vd(path);
    }

    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void click_home_btn() {
        home_btn.getStyleClass().clear();
        film_btn.getStyleClass().clear();
        food_btn.getStyleClass().clear();
        support_btn.getStyleClass().clear();

        home_btn.getStyleClass().add("nav_set_btn");
        film_btn.getStyleClass().add("nav_btn");
        food_btn.getStyleClass().add("nav_btn");
        support_btn.getStyleClass().add("nav_btn");
    }

    public void click_film_btn() {
        home_btn.getStyleClass().clear();
        film_btn.getStyleClass().clear();
        food_btn.getStyleClass().clear();
        support_btn.getStyleClass().clear();

        film_btn.getStyleClass().add("nav_set_btn");
        home_btn.getStyleClass().add("nav_btn");
        food_btn.getStyleClass().add("nav_btn");
        support_btn.getStyleClass().add("nav_btn");
    }

    public void click_food_btn() {
        home_btn.getStyleClass().clear();
        film_btn.getStyleClass().clear();
        food_btn.getStyleClass().clear();
        support_btn.getStyleClass().clear();

        food_btn.getStyleClass().add("nav_set_btn");
        home_btn.getStyleClass().add("nav_btn");
        film_btn.getStyleClass().add("nav_btn");
        support_btn.getStyleClass().add("nav_btn");
    }

    public void click_support_btn() {
        home_btn.getStyleClass().clear();
        film_btn.getStyleClass().clear();
        food_btn.getStyleClass().clear();
        support_btn.getStyleClass().clear();

        support_btn.getStyleClass().add("nav_set_btn");
        home_btn.getStyleClass().add("nav_btn");
        film_btn.getStyleClass().add("nav_btn");
        food_btn.getStyleClass().add("nav_btn");
    }

    public void handleLogoutBtn(ActionEvent event) throws IOException {

        if(logout){
            Stage stage = (Stage)close.getScene().getWindow();
            stage.close();
            LoginController.resume();
        }

    }
}
