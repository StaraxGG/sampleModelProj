package ViewController.Controller.LoginController;

import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Screens;
import ViewController.Start;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterManagedController implements Initializable {

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXTextField repeatPasswordField;

    @FXML
    private Label errorLabel;

    private UserModel UMINstance;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.UMINstance = UserModel.getInstance();

    }

    private EventHandler<? super MouseEvent> registerButtonClicked = mouseEvent -> {
        if(this.emailField.getText().isEmpty()){
            this.errorLabel.setText("Username can't be empty");
        }else if(this.emailField.getText().isEmpty()){
            this.errorLabel.setText("Password can't be empty");
        }else if(this.repeatPasswordField.getText().isEmpty()){
            this.errorLabel.setText("Please repeat your password");
        }

        if(!this.emailField.getText().equals(this.repeatPasswordField.getText())){
            this.errorLabel.setText("Passwords do not match!");
        }

        User user = new UserImpl(this.emailField.getText(), this.passwordField.getText());
        if(this.UMINstance.register(user)){
            this.errorLabel.setText("Invalid mail or user already exists");
        }else{
            Start.getManager().switchScreenTo(Screens.LOGIN);
        }
    };


}
