package ViewController.Controller;

import Model.Movie.Movie;
import Model.Movie.MovieModel;
import ViewController.Constructs.MovieConstruct;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * An implementation of HomeManagedController
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class HomeManagedController implements Initializable {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    @FXML
    private TilePane tilePanePopular;

    @FXML
    private TilePane tilePaneLatest;

    @FXML
    private ScrollPane spPopular;

    @FXML
    private ScrollPane spLatest;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXScrollPane.smoothScrolling(spPopular);
        JFXScrollPane.smoothScrolling(spLatest);

        //Retrieves lists with movies from TMDB
        MovieModel movieModel = MovieModel.getInstance();
        List<Movie> harry_potter = movieModel.getTmdbMovies("Harry Potter", 0);

        //Fills tilepane with corresponding list
        setupPopular(harry_potter);
        setupLatest(harry_potter);
    }

    /**
     * Fills TilePane Popular with a List of Movies
     * The movies are added to a MovieConstruct and these are put in the TilePane.
     * @param listToDisplay
     */
    private void setupPopular(List<Movie> listToDisplay ){
        listToDisplay.stream().forEach(e -> tilePanePopular.getChildren().add(new MovieConstruct(e)));
    }

    /**
     * Fills TilePane Popular with a List of Movies
     * The movies are added to a MovieConstruct and these are put in the TilePane.
     * @param listToDisplay
     */
    private void setupLatest(List<Movie> listToDisplay ){
        listToDisplay.stream().forEach(e -> tilePaneLatest.getChildren().add(new MovieConstruct(e)));
    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
