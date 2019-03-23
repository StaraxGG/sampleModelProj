package ViewController;

import ViewController.Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class WindowControllerBridge {

    private Controller controller;
    private Parent window;

    public WindowControllerBridge(FXMLLoader loader){
        try {
            this.window = loader.load();
            this.controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Controller getController() {
        return this.controller;
    }

    public Parent getWindow(){
        return this.window;
    }
}
