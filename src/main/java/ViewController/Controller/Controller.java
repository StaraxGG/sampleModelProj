package ViewController.Controller;

import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class Controller implements IController {

    private final int MAX_THREADS = 4;

    private Executor executor = Executors.newFixedThreadPool(MAX_THREADS, runnable -> {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t ;
    });

    private void exec(Runnable runnable){
        executor.execute(runnable);
    }

    public <T> Task await(Supplier<T> supplier){
        return new Task() {
            @Override
            protected Object call() throws Exception {
                return supplier.get();
            }
        };
    }

    public Task async(Runnable runnable){
        return new Task() {
            @Override
            protected Object call() throws Exception {
                runnable.run();
                return null;
            }
        };
    }

    /**
     * Use this ONLY for SHORT UPDATES to the UI
     * @param runnable
     */
    public void updateComponent(Runnable runnable){
        Platform.runLater(runnable);
    }

    /**
     * setUp function, which wraps a components setComponentUp function in a Platform.runLater call,
     * to ensure that the aforementioned function is called on the JavaFX GUI thread.
     *
     * final, because this function must not be overwritten. If you want your component to use a
     * setUp function, use setComponentUp instead
     */
    @Override
    public final void setUp() {
        exec(async(this::setComponentUp));
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
        exec(async(this::tearComponentDown));
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
