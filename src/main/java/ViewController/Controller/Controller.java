package ViewController.Controller;

import javafx.animation.Transition;
import javafx.application.Platform;

public class Controller implements IController {

    /**
     * setUp function, which wraps a components setComponentUp function in a Platform.runLater call,
     * to ensure that the aforementioned function is called on the JavaFX GUI thread.
     *
     * final, because this function must not be overwritten. If you want your component to use a
     * setUp function, use setComponentUp instead
     */
    @Override
    public final void setUp() {
        Platform.runLater(this::setComponentUp);
    }

    /**
     * teardown function, which wraps a components tearComponentDown function in a Platform.runLater call,
     * to ensure that the aforementioned function is called on the JavaFX GUI thread.
     *
     * final, because this function must not be overwritten. If you want your component to use a
     * setUp function, use tearComponentDown instead
     */
    @Override
    public final void teardown() {
        Platform.runLater(this::tearComponentDown);
    }

    /**
     * Play a transition for a component on the JavaFX GUI thread.
     * @param transition the transition to be played
     */
    public final void playAnimation(Transition transition){
        Platform.runLater(transition::play);
    }

    /**
     * set your component up. It's wise to make this as short as possible, to minimize application
     * freeze because of runtime overhead
     */
    protected void setComponentUp(){
        return;
    }

    /**
     * tear your component down. It's wise to make this as short as possible, to minimize application
     * freeze because of runtime overhead
     */
    protected void tearComponentDown(){
        return;
    }

}
