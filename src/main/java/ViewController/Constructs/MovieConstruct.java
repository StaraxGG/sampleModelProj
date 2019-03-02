package ViewController.Constructs;

import Model.Movie.Movie;
import ViewController.Start;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * An implementation of MovieConstruct
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class MovieConstruct extends BorderPane {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    @FXML
    private ImageView imgPoster;

    private Movie movie;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public MovieConstruct (Movie movie){
        super();

        this.movie = movie;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/movieConstruct.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        imgPoster.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Start.getManager().putMovieOverviewOnStack(movie);
            }
        });

        //todo change back when getPosterURL returns url and not key
        imgPoster.setFitHeight(278);
        imgPoster.setFitWidth(185);
        imgPoster.setPreserveRatio(true);
        imgPoster.setImage(new Image("https://image.tmdb.org/t/p/w185"+
                movie.getPosterUrl(),185,278,true,true));
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
