package ViewController.Controller.LoginController;

import Model.Movie.MovieModel;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Controller.Controller;
import ViewController.WINDOW_IDENTIFIER;
import ViewController.Start;
import ViewController.WINDOW_IDENTIFIER;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class RegisterController extends Controller implements Initializable {

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField repeatPasswordField;

    @FXML
    private Label errorLabel;

    private UserModel UMINstance;

    @FXML
    private JFXButton registerButton;

    final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.debug("Initializing Component RegisterController");
        this.UMINstance = UserModel.getInstance();
        this.registerButton.setOnMouseClicked(this.registerButtonClicked);


    }

    private EventHandler<? super MouseEvent> registerButtonClicked = mouseEvent -> {
        logger.debug("Register button clicked");
        if(this.emailField.getText().isEmpty()){
            logger.debug("Email Field was empty");
            this.errorLabel.setText("Email can't be empty");
            return;
        }

        if(!UserModel.verifyMailAdress(this.emailField.getText())){
            logger.debug("Invalid email entered. mail: " + this.emailField.getText());
            this.errorLabel.setText("Please enter a valid email!");
            return;
        }

        if(this.emailField.getText().isEmpty()){
            logger.debug("Password field empty");
            this.errorLabel.setText("Password can't be empty");
            return;
        }

        if(this.repeatPasswordField.getText().isEmpty()){
            logger.debug("Password repeat field empty");
            this.errorLabel.setText("Please repeat your password");
            return;
        }



        if(!this.passwordField.getText().equals(this.repeatPasswordField.getText())){
            logger.debug("Passwords in passwordField and repeatPasswordField do not match");
            this.errorLabel.setText("Passwords do not match!");
            return;
        }

        User user = new UserImpl(this.emailField.getText(), this.passwordField.getText());

        if(!this.UMINstance.register(user)){
            logger.debug("User already exists with name " + user.getUsername());
            this.errorLabel.setText("User already exists");
        }else{
            logger.debug("Successfully created User with name " + user.getUsername());
            this.errorLabel.setText("User successfully created! Please login with your new credentials");
            try {
                logger.debug("Sleeping for 5 Seconds");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error(e.getClass().getSimpleName(), e.getMessage());
            }
            logger.debug("Switching to Login Screen");
            Start.getManager().switchScreenTo(WINDOW_IDENTIFIER.LOGIN);
        }
    };


}
