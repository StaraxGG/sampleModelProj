package ViewController;

import ViewController.Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.HashMap;

/**
 * An implementation of InstanceManager
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class InstanceManager {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private static InstanceManager instanceManager;

    private static WindowManager windowManager;
    private HashMap<E_Windows, WindowControllerBridge> WindowIdentifier = new HashMap();

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    private InstanceManager(){
        FXMLLoader root = new FXMLLoader(getClass().getResource("/fxml/rootWindow.fxml"));
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/fxml/homeWindow.fxml"));
        FXMLLoader loaderLists = new FXMLLoader(getClass().getResource("/fxml/listsWindow.fxml"));
        FXMLLoader loaderLogIn = new FXMLLoader(getClass().getResource("/fxml/logInWindow.fxml"));
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("/fxml/statsWindow.fxml"));
        //todo load other views


        this.WindowIdentifier.put(E_Windows.Root, new WindowControllerBridge(root));
        this.WindowIdentifier.put(E_Windows.HOMESCREEN, new WindowControllerBridge(loaderHome));
        this.WindowIdentifier.put(E_Windows.LISTS, new WindowControllerBridge(loaderLists));
        this.WindowIdentifier.put(E_Windows.LOGIN, new WindowControllerBridge(loaderLogIn));
        this.WindowIdentifier.put(E_Windows.STATS, new WindowControllerBridge(loaderStats));
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public static InstanceManager getInstance(WindowManager windowManagerTopLevel){
        if(instanceManager == null){
            instanceManager = new InstanceManager();
            windowManager = windowManagerTopLevel;
        }
        return instanceManager;
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public WindowControllerBridge getWindowUnionController(E_Windows screen){
        return this.WindowIdentifier.get(screen);
    }
}
