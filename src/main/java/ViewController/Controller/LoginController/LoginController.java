package ViewController.Controller.LoginController;

import Model.User.Exception.UserNotFoundException;
import Model.User.Exception.UserWrongPasswordException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Controller.Controller;
import ViewController.WINDOW_IDENTIFIER;
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
 * An implementation of LoginController
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class LoginController extends Controller implements Initializable {

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
            user = this.UMINstance.login(user);
        }catch (UserNotFoundException __){
            this.errorLabel.setText("Couldn't find a user with that name. Register?");
            this.playLoginFailAnimation();
            return;
        }catch (UserWrongPasswordException __){
            this.errorLabel.setText("Passwords do not match!");
            this.playLoginFailAnimation();
            return;
        }catch(IllegalArgumentException __){
            ;
        }

        Start.getManager().switchScreenTo(WINDOW_IDENTIFIER.HOMESCREEN);
    };

    private EventHandler<? super MouseEvent> signUpButtonClicked = mouseEvent -> {
        Start.getManager().switchScreenTo(WINDOW_IDENTIFIER.REGISTER);
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
