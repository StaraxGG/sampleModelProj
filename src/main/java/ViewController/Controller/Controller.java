package ViewController.Controller;

import javafx.animation.Transition;
import javafx.application.Platform;

public class Controller implements IController {
    public final void setUp() {
        Platform.runLater(this::setComponentUp);
    }

    public final void teardown() {
        Platform.runLater(this::tearComponentDown);
    }

    public final void playAnimation(Transition transition){
        Platform.runLater(transition::play);
    }

    protected void setComponentUp(){
        return;
    }

    protected void tearComponentDown(){
        return;
    }

}
