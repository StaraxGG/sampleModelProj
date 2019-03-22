package ViewController.Controller;

import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Screens;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * An implementation of LoginController
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class LoginController implements Initializable {

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



    private WindowManager windowManager;
    
    private UserModel UMINstance;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        this.windowManager = WindowManager.getInstance(null);


        this.UMINstance = UserModel.getInstance();
        this.loginButton.setOnMouseClicked(this.loginButtonClicked);
        this.rememberMeCheckBox.setOnMouseClicked(this.rememberMeCheckboxClicked);
        this.signUpButton.setOnMouseClicked(this.signUpButtonClicked);
        */
    }

    
    private EventHandler<? super MouseEvent> loginButtonClicked = mouseEvent -> {
        String usertext = this.usernameField.getText();
        String pwtext = this.passwordField.getText();
        
        User user = null;
        
        try{
            user = new UserImpl(usertext, pwtext);
        }catch (NullPointerException e){
            //play error animation
        }

        try{
            if(this.UMINstance.login(user) != null){
                this.windowManager.switchScreenTo(Screens.HOMESCREEN);
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
    
    



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
