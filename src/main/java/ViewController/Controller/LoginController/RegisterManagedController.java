package ViewController.Controller.LoginController;

import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Screens;
import ViewController.Start;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterManagedController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.UMINstance = UserModel.getInstance();
        this.registerButton.setOnMouseClicked(this.registerButtonClicked);


    }

    private EventHandler<? super MouseEvent> registerButtonClicked = mouseEvent -> {
        if(this.emailField.getText().isEmpty()){
            this.errorLabel.setText("Email can't be empty");
            return;
        }

        if(!UserModel.verifyMailAdress(this.emailField.getText())){
            this.errorLabel.setText("Please enter a valid email!");
            return;
        }

        if(this.emailField.getText().isEmpty()){
            this.errorLabel.setText("Password can't be empty");
            return;
        }

        if(this.repeatPasswordField.getText().isEmpty()){
            this.errorLabel.setText("Please repeat your password");
            return;
        }



        if(!this.passwordField.getText().equals(this.repeatPasswordField.getText())){
            this.errorLabel.setText("Passwords do not match!");
            return;
        }

        User user = new UserImpl(this.emailField.getText(), this.passwordField.getText());
        if(!this.UMINstance.register(user)){
            this.errorLabel.setText("Invalid mail or user already exists");
        }else{
            this.errorLabel.setText("User successfully created! Please login with your new credentials");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Start.getManager().switchScreenTo(Screens.LOGIN);
        }
    };


}
