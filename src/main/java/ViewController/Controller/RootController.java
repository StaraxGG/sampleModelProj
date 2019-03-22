package ViewController.Controller;

import ViewController.E_Windows;
import ViewController.Start;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * An implementation of RootController
 * in sample-model-project cool
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class RootController extends Controller implements Initializable {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */


    @FXML
    private JFXButton btnLists;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnStats;

    @FXML
    private StackPane masterStackpane;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */




    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLists.setOnAction(event -> Start.getManager().switchScreenTo(E_Windows.LISTS));
        btnHome.setOnAction(event -> Start.getManager().switchScreenTo(E_Windows.HOMESCREEN));
        btnStats.setOnAction(event -> Start.getManager().switchScreenTo(E_Windows.STATS));

        masterStackpane.setPickOnBounds(false);
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public StackPane getMasterStackpane() {
        return masterStackpane;
    }


}
