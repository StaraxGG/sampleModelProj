package ViewController;

import ViewController.Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

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

    //Controller and Views of controlled Screens
    private BaseManagedController baseManagedController;
    private Parent baseView;

    private HomeManagedController homeManagedController;
    private Parent homeView;

    private ListManagedController listManagedController;
    private Parent listView;

    private LoginManagedController loginManagedController;
    private Parent loginView;

    private StatsManagedController statsManagedController;
    private Parent statsView;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    private InstanceManager(){
        FXMLLoader loaderBase = new FXMLLoader(getClass().getResource("/fxml/baseWindow.fxml"));
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/fxml/homeWindow.fxml"));
        FXMLLoader loaderLists = new FXMLLoader(getClass().getResource("/fxml/listsWindow.fxml"));
        FXMLLoader loaderLogIn = new FXMLLoader(getClass().getResource("/fxml/logInWindow.fxml"));
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("/fxml/statsWindow.fxml"));

        try{
            //base setup
            this.baseView = loaderBase.load();
            this.baseManagedController = loaderBase.getController();

            //home setup
            this.homeView = loaderHome.load();
            this.homeManagedController = loaderHome.getController();

            //list setup
            this.listView = loaderLists.load();
            this.listManagedController = loaderLists.getController();

            //login setup
            this.loginView = loaderLogIn.load();
            this.loginManagedController = loaderLogIn.getController();

            //stats setup
            this.statsView = loaderStats.load();
            this.statsManagedController = loaderStats.getController();

        } catch (IOException exception){
            throw new RuntimeException(exception);
        }
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

    public BaseManagedController getBaseManagedController() {
        return baseManagedController;
    }

    public Parent getBaseView() {
        return baseView;
    }

    public HomeManagedController getHomeManagedController() {
        return homeManagedController;
    }

    public Parent getHomeView() {
        return homeView;
    }

    public ListManagedController getListManagedController() {
        return listManagedController;
    }

    public Parent getListView() {
        return listView;
    }

    public LoginManagedController getLoginManagedController() {
        return loginManagedController;
    }

    public Parent getLoginView() {
        return loginView;
    }

    public StatsManagedController getStatsManagedController() {
        return statsManagedController;
    }

    public Parent getStatsView() {
        return statsView;
    }
}
