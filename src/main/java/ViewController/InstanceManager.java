package ViewController;

import javafx.fxml.FXMLLoader;

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
    private HashMap<WINDOW_IDENTIFIER, WindowControllerBridge> WindowIdentifierMap = new HashMap();

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    private InstanceManager(){
        FXMLLoader root = new FXMLLoader(getClass().getResource("/fxml/rootWindow.fxml"));
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/fxml/homeWindow.fxml"));
        FXMLLoader loaderLists = new FXMLLoader(getClass().getResource("/fxml/listsWindow.fxml"));
        FXMLLoader loaderLogIn = new FXMLLoader(getClass().getResource("/fxml/logInWindow.fxml"));
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("/fxml/statsWindow.fxml"));
        FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/fxml/registerWindow.fxml"));
        //todo load other views


        this.WindowIdentifierMap.put(WINDOW_IDENTIFIER.Root, new WindowControllerBridge(root));
        this.WindowIdentifierMap.put(WINDOW_IDENTIFIER.HOMESCREEN, new WindowControllerBridge(loaderHome));
        this.WindowIdentifierMap.put(WINDOW_IDENTIFIER.LISTS, new WindowControllerBridge(loaderLists));
        this.WindowIdentifierMap.put(WINDOW_IDENTIFIER.LOGIN, new WindowControllerBridge(loaderLogIn));
        this.WindowIdentifierMap.put(WINDOW_IDENTIFIER.STATS, new WindowControllerBridge(loaderStats));
        this.WindowIdentifierMap.put(WINDOW_IDENTIFIER.REGISTER, new WindowControllerBridge(loaderRegister));
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public static InstanceManager getInstance(WindowManager windowManagerTopLevel){
        if(instanceManager == null){
            instanceManager = new InstanceManager();
            windowManager = windowManagerTopLevel;
        }
        return instanceManager;
    }

    public static InstanceManager getInstance(){
        if(instanceManager == null){
            instanceManager = new InstanceManager();
        }
        return instanceManager;
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public WindowControllerBridge getWindowControllerBridge(WINDOW_IDENTIFIER screen){
        return this.WindowIdentifierMap.get(screen);
    }
}
