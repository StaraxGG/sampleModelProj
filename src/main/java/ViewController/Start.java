package ViewController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An implementation of Start
 * in sample-model-project
 *
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class Start extends Application {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private static WindowManager windowManagerSave;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * Starts the application.
     * Windowmanager is created and saved here.
     * For later is it is now accessible through the static getManager Method.
     * The baseView is added to a scene and the scene is added to the primaryStage.
     *
     * @param primaryStage  the stage of our application
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        WindowManager windowManager = WindowManager.getInstance(primaryStage);
        windowManagerSave = windowManager;
        primaryStage.setScene(new Scene(windowManager.getBaseView()));
        windowManager.switchScreenTo(Screens.LOGIN);
        primaryStage.show();
    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public static WindowManager getManager(){
        return windowManagerSave;
    }
}
