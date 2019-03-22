package ViewController;

import Model.Movie.Movie;
import ViewController.Constructs.MovieOverviewConstruct;
import ViewController.Controller.RootController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * An implementation of WindowManager
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class WindowManager {


    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    //holds all views
    private static InstanceManager instanceManager;

    //center Stackpane
    private static StackPane masterStackPane;

    //counts how many elements are on the stack
    private int stackCount;

    //singleton instance of windowmanager
    private static WindowManager windowManager;

    //currentStage
    private static Stage currentStage;

    private WINDOW_IDENTIFIER current_window_identifier;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /**
     * Private WindowManager constructor, because the singleton pattern is used here.
     */
    private WindowManager(Stage stage){
        //initialise managed windows
        instanceManager = InstanceManager.getInstance(this);

        //initialise masterStackPane
        masterStackPane = ((RootController) instanceManager.getWindowControllerBridge(WINDOW_IDENTIFIER.Root)
                .getController()).getMasterStackpane();

        //set StackCount to zero
        stackCount = 0;

        //set current Stage
        currentStage = stage;

        //switchScreenTo(WINDOW_IDENTIFIER.HOMESCREEN);
        switchScreenTo(WINDOW_IDENTIFIER.LOGIN);
    }

    /**
     * Returns the only instance of WindowManager which shall be created.
     * Either the attribute windowManager gets initialised if not happened already
     * or the attribute is returned.
     * @return
     */
    public static WindowManager getInstance(Stage stage){
        if (windowManager == null){
            windowManager = new WindowManager(stage);
        }
        return windowManager;
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * Switches WINDOW_IDENTIFIER to the chosen window_identifier.
     * The center stackpane, which holds all the screens, gets cleared
     * and the chosen window_identifier is added on top.
     *
     * The instances are retrieved from the InstanceManager,
     * which assures that no window_identifier is loaded multiple times.
     *
     * This method doesn't allow stacking of screens.
     * For that see putMovieOverviewOnStack() method.
     * @param window_identifier
     */
    public void switchScreenTo(WINDOW_IDENTIFIER window_identifier){
        WindowControllerBridge bridge = instanceManager.getWindowControllerBridge(window_identifier);
        bridge.getController().setUp();
        switchScreen(bridge.getWindow());
        if(this.current_window_identifier != null){
            instanceManager.getWindowControllerBridge(this.current_window_identifier).getController().teardown();
        }
        this.current_window_identifier = window_identifier;
    }

    /**
     * Clears stackpane and adds the new screen.
     * @param parent
     */
    private void switchScreen(Parent parent){
        masterStackPane.getChildren().clear();
        masterStackPane.getChildren().add(parent);
    }

    /**
     * Puts the movie overview on the Stack.
     * The views below are still in the scene graph for later use.
     * @param movie contains the information which is used to fill the movieOverview
     */
    public void putMovieOverviewOnStack(Movie movie){
        MovieOverviewConstruct movieOverviewConstruct = new MovieOverviewConstruct(movie);
        putOnStack(movieOverviewConstruct);

    }

    /**
     * Puts a node on the stack.
     * Elements on the scene graph which are below are kept there.
     * @param node  node that gets added to the top of the stackpane
     */
    private void putOnStack(Node node){
        masterStackPane.getChildren().add(node);
        stackCount++;
    }

    /**
     * Drops an element from the stackpane.
     *
     * @return  true if an element could be dropped
     *          false if there wasn't an element to drop on the stack.
     */
    public boolean dropFromStack(){
        if(stackCount > 0){
            masterStackPane.getChildren().remove(masterStackPane.getChildren().size()-1);
            stackCount--;
            return true;
        }
        return false;
    }



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * Returns the baseView, which contains the masterStackpane.
     * This view is added to the Scene.
     * @return  baseView of the Application.
     */
    public Parent getRoot(){
        return instanceManager.getWindowControllerBridge(WINDOW_IDENTIFIER.Root).getWindow();
    }

    /**
     * Returns current Stage
     * @return
     */
    public static Stage getCurrentStage() {
        return currentStage;
    }

}
