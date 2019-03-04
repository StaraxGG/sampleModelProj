package ViewController.Controller;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.Movie.MovieModel;
import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.WindowManager;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * An implementation of ListManagedController
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class ListManagedController implements Initializable {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private WindowManager windowManager;

    @FXML
    private JFXToggleButton jfxAdultToggle;

    @FXML
    private JFXListView<Label> jfxList;

    @FXML
    private JFXMasonryPane jfxMasonry;

    @FXML
    private JFXButton buttonCollapse;

    @FXML
    private JFXButton but3d;

    @FXML
    private JFXButton buttonExp;


    private List<Label> stringList     = new ArrayList<>(5);
    private ObservableList observableList = FXCollections.observableArrayList();

    private int counter = 0;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }




    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
