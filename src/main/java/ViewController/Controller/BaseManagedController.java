package ViewController.Controller;

import ViewController.Screens;
import ViewController.Start;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * An implementation of BaseManagedController
 * in sample-model-project cool
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class BaseManagedController implements Initializable {

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

    @FXML
    private JFXTextField searchField;

    @FXML
    private HBox menuBox;

    @FXML
    private HBox motherBox;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */




    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLists.setOnAction(event -> Start.getManager().switchScreenTo(Screens.LISTS));
        btnHome.setOnAction(event -> Start.getManager().switchScreenTo(Screens.HOMESCREEN));
        btnStats.setOnAction(event -> Start.getManager().switchScreenTo(Screens.STATS));

        masterStackpane.setPickOnBounds(false);
        //HBox.setHgrow(menuBox, Priority.ALWAYS);
        //HBox.setHgrow(searchField, Priority.NEVER);
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public StackPane getMasterStackpane() {
        return masterStackpane;
    }


}
