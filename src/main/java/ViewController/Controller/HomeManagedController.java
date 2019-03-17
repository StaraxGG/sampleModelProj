package ViewController.Controller;

import Model.Movie.Movie;
import Model.Movie.MovieModel;
import ViewController.Constructs.MovieConstruct;
import ViewController.WindowManager;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private JFXMasonryPane masonryPopular;

    @FXML
    private JFXMasonryPane masonryTop;

    @FXML
    private ScrollPane spPopular;

    @FXML
    private ScrollPane spTop;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //Sets some settings for the scrollPanes
        setupScrollpanes();

        //Retrieves lists with movies from TMDB
        MovieModel movieModel = MovieModel.getInstance();
        List<Movie> popularMovies = movieModel.getPopularMovies(0);
        popularMovies.addAll(movieModel.getPopularMovies(2));

        List<Movie> topRatedMovies = movieModel.getPopularMovies(0);
        topRatedMovies.addAll(movieModel.getPopularMovies(2));

        //Fills masonryPane with corresponding list
        setupPopular(popularMovies);
        setupLatest(topRatedMovies);

    }

    /**
     * Smooths scrolling and fixes some refreshing issue.
     */
    private void setupScrollpanes(){
        //Fixes scrollPane issue, where window is only scrollable if resized
        //manually before once. //TODO issues still there
        Platform.runLater(spPopular::requestLayout);

        //scrolles slow as hell otherwise with JavafX standard.
        JFXScrollPane.smoothScrolling(spPopular);
        JFXScrollPane.smoothScrolling(spTop);
    }

    /**
     * Fills TilePane Popular with a List of Movies
     * The movies are added to a MovieConstruct and these are put in the TilePane.
     * @param listToDisplay
     */
    private void setupPopular(List<Movie> listToDisplay ){
        listToDisplay.stream().forEach(e -> masonryPopular.getChildren().add(new MovieConstruct(e)));
    }

    /**
     * Fills TilePane Popular with a List of Movies
     * The movies are added to a MovieConstruct and these are put in the TilePane.
     * @param listToDisplay
     */
    private void setupLatest(List<Movie> listToDisplay ){
        listToDisplay.stream().forEach(e -> masonryTop.getChildren().add(new MovieConstruct(e)));
    }




    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
