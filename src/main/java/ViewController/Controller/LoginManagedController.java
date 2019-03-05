package ViewController.Controller;

import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Screens;
import ViewController.Start;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * An implementation of LoginManagedController
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class LoginManagedController implements Initializable {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXCheckBox rememberMeCheckBox;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private Label errorLabel;



    private WindowManager windowManager;
    
    private UserModel UMINstance;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.windowManager = Start.getManager();


        this.UMINstance = UserModel.getInstance();
        this.loginButton.setOnMouseClicked(this.loginButtonClicked);
        this.rememberMeCheckBox.setOnMouseClicked(this.rememberMeCheckboxClicked);
        this.signUpButton.setOnMouseClicked(this.signUpButtonClicked);
    }

    private EventHandler<? super MouseEvent> loginButtonClicked = mouseEvent -> {
        String usertext = this.usernameField.getText();
        String pwtext = this.passwordField.getText();

        if(Objects.equals(usertext, "")){
            this.errorLabel.setText("Please enter a Username");
            return;
        }else if(!Objects.equals(usertext, "") && Objects.equals(pwtext, "")){
            this.errorLabel.setText("Please enter a password");
            return;
        }else{
            this.errorLabel.setText("");
        }

        User user = new UserImpl(usertext, pwtext);

        try{
            if(this.UMINstance.login(user) != null){
                this.windowManager.switchScreenTo(Screens.HOMESCREEN);
            }else{
                this.playLoginFailAnimation();
                this.errorLabel.setText("Couldn't find a user with matching credentials");
            }
        }catch(Exception e ){
            //log
        }
    };

    private EventHandler<? super MouseEvent> signUpButtonClicked = mouseEvent -> {
        //todo switch window manager to display register form
    };

    private EventHandler<? super MouseEvent> rememberMeCheckboxClicked = mouseEvent -> {
        this.rememberMeCheckBox.setSelected(!this.rememberMeCheckBox.isSelected());
    };



    /* ---------------------------------------- Animation ----------------------------------------------------------- */
    private void playLoginFailAnimation(){

        TranslateTransition translateRight = new TranslateTransition(Duration.millis(25), this.loginButton);
        translateRight.setFromX(0);
        translateRight.setToX(5);
        translateRight.setCycleCount(2);
        translateRight.setAutoReverse(true);

        TranslateTransition translateLeft = new TranslateTransition(Duration.millis(25), this.loginButton);
        translateLeft.setFromX(0);
        translateLeft.setToX(-5);
        translateLeft.setCycleCount(2);
        translateLeft.setAutoReverse(true);

        SequentialTransition sequentialTransition= new SequentialTransition();
        sequentialTransition.getChildren().addAll(translateRight, translateLeft);
        sequentialTransition.play();

    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
