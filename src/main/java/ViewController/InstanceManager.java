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
    private BaseController baseController;
    private Parent baseView;

    private HomeController homeController;
    private Parent homeView;

    private MovieListController movieListController;
    private Parent listView;

    private LoginController loginController;
    private Parent loginView;

    private StatsController statsController;
    private Parent statsView;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    private InstanceManager(){
        FXMLLoader loaderBase = new FXMLLoader(getClass().getResource("/fxml/baseWindow.fxml"));
        FXMLLoader loaderHome = new FXMLLoader(getClass().getResource("/fxml/homeWindow.fxml"));
        FXMLLoader loaderLists = new FXMLLoader(getClass().getResource("/fxml/listsWindow.fxml"));
        FXMLLoader loaderLogIn = new FXMLLoader(getClass().getResource("/fxml/logInWindow.fxml"));
        FXMLLoader loaderStats = new FXMLLoader(getClass().getResource("/fxml/statsWindow.fxml"));
        //todo load other views

        try{
            //base setup
            this.baseView = loaderBase.load();
            this.baseController = loaderBase.getController();

            //home setup
            this.homeView = loaderHome.load();
            this.homeController = loaderHome.getController();

            //list setup
            this.listView = loaderLists.load();
            this.movieListController = loaderLists.getController();

            //login setup
            this.loginView = loaderLogIn.load();
            this.loginController = loaderLogIn.getController();

            //stats setup
            this.statsView = loaderStats.load();
            this.statsController = loaderStats.getController();

            //todo add other setups if needed
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

    public BaseController getBaseController() {
        return baseController;
    }

    public Parent getBaseView() {
        return baseView;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public Parent getHomeView() {
        return homeView;
    }

    public MovieListController getMovieListController() {
        return movieListController;
    }

    public Parent getListView() {
        return listView;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public Parent getLoginView() {
        return loginView;
    }

    public StatsController getStatsController() {
        return statsController;
    }

    public Parent getStatsView() {
        return statsView;
    }
}
