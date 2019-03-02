package ViewController.Constructs;

import Model.Movie.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * An implementation of MovieOverviewConstruct
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-28
 */
public class MovieOverviewConstruct extends BorderPane {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    @FXML
    private ImageView imgPoster;

    @FXML
    private Label lblInfo;

    private Movie movie;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public MovieOverviewConstruct(Movie movie) {
        super();

        this.movie = movie;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/movieOverviewConstruct.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //todo change back when getPosterURL returns url and not key
        imgPoster.setImage(new Image("https://image.tmdb.org/t/p/w300"+
                movie.getPosterUrl(),200,350,true,true));
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
