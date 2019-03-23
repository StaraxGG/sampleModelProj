package ViewController.Controller;

import javafx.animation.Transition;

public interface IController {
    public void setUp();
    public void teardown();
    public void playAnimation(Transition transition);
}
