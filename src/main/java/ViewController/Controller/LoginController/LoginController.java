package ViewController.Controller.LoginController;

import Model.Movie.MovieModel;
import Model.User.Exception.UserNotFoundException;
import Model.User.Exception.UserWrongPasswordException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Controller.Controller;
import ViewController.Controller.RootController;
import ViewController.InstanceManager;
import ViewController.WINDOW_IDENTIFIER;
import ViewController.Start;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.debug("Initializing LoginController");

        this.UMINstance = UserModel.getInstance();
        this.loginButton.setOnMouseClicked(this.loginButtonClicked);
        this.rememberMeCheckBox.setOnMouseClicked(this.rememberMeCheckboxClicked);
        this.signUpButton.setOnMouseClicked(this.signUpButtonClicked);
    }

    private EventHandler<? super MouseEvent> loginButtonClicked = mouseEvent -> {
        logger.debug("Login Button clicked!");
        String usertext = this.usernameField.getText();
        String pwtext = this.passwordField.getText();

        if(Objects.equals(usertext, "")){
            logger.debug("Login button was clicked without a username entered");
            this.errorLabel.setText("Please enter a Username");
            this.playLoginFailAnimation();
            return;
        }else if(!Objects.equals(usertext, "") && Objects.equals(pwtext, "")){
            logger.debug("Login button was clicked without a password entered");
            this.errorLabel.setText("Please enter a password");
            this.playLoginFailAnimation();
            return;
        }else{
            this.errorLabel.setText("");
        }

        User user = new UserImpl(usertext, pwtext);

        try{
            user = this.UMINstance.login(user);
        }catch (UserNotFoundException __){
            logger.debug("Login button clicked but no user found with name " + usertext);
            this.errorLabel.setText("Couldn't find a user with that name. Register?");
            this.playLoginFailAnimation();
            return;
        }catch (UserWrongPasswordException __){
            logger.debug("Login Attempt, but wrong password entered");
            this.errorLabel.setText("Password doesnt not match!");
            this.playLoginFailAnimation();
            return;
        }catch(IllegalArgumentException e){
            logger.error("Illegal Argument Exception in LoginController", e);
        }

        logger.debug("Login attempt successfull, switching to Homescreen");
        Start.getManager().switchScreenTo(WINDOW_IDENTIFIER.HOMESCREEN);
    };

    private EventHandler<? super MouseEvent> signUpButtonClicked = mouseEvent -> {
        logger.debug("Register Button clicked");
        Start.getManager().switchScreenTo(WINDOW_IDENTIFIER.REGISTER);
    };

    private EventHandler<? super MouseEvent> rememberMeCheckboxClicked = mouseEvent -> {
        logger.debug("Remember me checkbox checked");
        this.rememberMeCheckBox.setSelected(!this.rememberMeCheckBox.isSelected());
    };



    /* ---------------------------------------- Animation ----------------------------------------------------------- */
    private void playLoginFailAnimation(){
        logger.debug("Playing login fail animation");
        this.playAnimation(this.createLoginAnimation());

    }

    private Transition createLoginAnimation(){

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
        return sequentialTransition;
    }

    @Override
    protected void setComponentUp(){
        logger.debug("Setting up Component Login Controller");
        ((RootController)InstanceManager
                .getInstance()
                .getWindowControllerBridge(WINDOW_IDENTIFIER.Root)
                .getController()).disableTopBar();
    }

    @Override
    protected void tearComponentDown(){
        logger.debug("Tearing down Component Login Controller");
        ((RootController)InstanceManager
                .getInstance()
                .getWindowControllerBridge(WINDOW_IDENTIFIER.Root)
                .getController()).enableTopBar();
    }
    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
