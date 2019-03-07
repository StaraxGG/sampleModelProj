package ViewController;

import Model.Movie.Movie;
import ViewController.Constructs.MovieConstruct;
import ViewController.Constructs.MovieOverviewConstruct;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTabPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

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

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /**
     * Private WindowManager constructor, because the singleton pattern is used here.
     */
    private WindowManager(Stage stage){
        //initialise managed windows
        instanceManager = InstanceManager.getInstance(this);

        //initialise masterStackPane
        masterStackPane = instanceManager.getBaseManagedController().getMasterStackpane();

        //set StackCount to zero
        stackCount = 0;

        //set current Stage
        currentStage = stage;

        //switchScreenTo(Screens.HOMESCREEN);
        switchScreenTo(Screens.LISTS);
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
     * Switches Screens to the chosen screen.
     * The center stackpane, which holds all the screens, gets cleared
     * and the chosen screen is added on top.
     *
     * The instances are retrieved from the InstanceManager,
     * which assures that no window is loaded multiple times.
     *
     * This method doesn't allow stacking of screens.
     * For that see putMovieOverviewOnStack() method.
     * @param screen
     */
    public void switchScreenTo(Screens screen){
        switch (screen){
            case HOMESCREEN:
                switchScreen(instanceManager.getHomeView());
                break;
            case STATS:
                switchScreen(instanceManager.getStatsView());
                break;
            case LISTS:
                switchScreen(instanceManager.getListView());
                break;
            case SETTINGS:
                //todo switch Screen on Stackpane to Settings
                break;
            case LOGIN:
                switchScreen(instanceManager.getLoginView());
                break;
            case REGISTER:
                switchScreen(instanceManager.getRegisterView());
        }
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
    public Parent getBaseView(){
        return instanceManager.getBaseView();
    }

    /**
     * Returns current Stage
     * @return
     */
    public static Stage getCurrentStage() {
        return currentStage;
    }

}
