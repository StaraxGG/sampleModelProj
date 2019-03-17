package ViewController.Constructs;

import Model.Movie.Movie;
import Model.Movie.MoviePosterSize;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * An implementation of MovieOverviewConstruct
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-28
 */
public class MovieOverviewConstruct extends StackPane {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    @FXML
    private StackPane stackPaneMovieOverview;

    @FXML
    private Label lblLanguage;

    @FXML
    private Label lblDirector;

    @FXML
    private Label lblWriter;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblRuntime;

    @FXML
    private Label lblRelease;

    @FXML
    private Label lblGenres;

    @FXML
    private Label lblConclusion;

    @FXML
    private ImageView imgPoster;

    @FXML
    private JFXButton btnWatchlist;

    @FXML
    private JFXButton btnList;

    @FXML
    private JFXButton btnWatched;

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

        Image image = new Image(movie.getPosterUrl(MoviePosterSize.W342),342,513,true,true,false);
        imgPoster.setImage(image);

        Rectangle clip = new Rectangle();
        clip.setWidth(342);
        clip.setHeight(513);
        clip.setArcWidth(20);
        clip.setArcHeight(20);

        imgPoster.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image1 = imgPoster.snapshot(parameters,null);

        imgPoster.setClip(null);

        imgPoster.setEffect(new DropShadow(20, Color.BLACK));

        imgPoster.setImage(image1);

        setUpLables(movie);

        System.out.println(movie.getPosterUrl(MoviePosterSize.W342));
        BackgroundImage myBI= new BackgroundImage(new Image("/graphics/backdrop.jpg",
                1920,1080,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        //then you set to your node
        stackPaneMovieOverview.setBackground(new Background(myBI));



    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    private void setUpLables(Movie movie){
        lblConclusion.setText(movie.getOverview());
        lblRelease.setText(movie.getReleaseDate());
        lblTitle.setText(movie.getTitle());
        //lblGenres.setText(movie.getGenres().get(0));
        lblLanguage.setText(movie.getOriginalLanguage());
    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
