package ViewController.Constructs;

import Model.Movie.Movie;
import Model.Movie.MovieModel;
import Model.Movie.MoviePosterSize;
import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Model.MovieList.MovieListModel;
import Model.User.Exception.UserNotFoundException;
import Model.User.Exception.UserWrongPasswordException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    private JFXButton btnDeleteFromList;

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

        setUpLables(movie);
        setUpPoster(movie);
        setUpBackground(movie);

        //TODO uncomment when loginwindow exists and user is logged in by the time this method is called
        //User userUserModel.getInstance().getCurrentUser();
        //setUpAddToListButton(User user)
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    private void setUpAddToListButton(User user){


        List<MovieListImpl> movieLists = user.getMovieLists();
        System.out.println(movieLists.size());


        JFXListView<CheckboxConstruct> list = new JFXListView<>();
        movieLists.stream().forEach( c -> {
            CheckboxConstruct checkboxConstruct = new CheckboxConstruct(c,movie);
            list.getItems().add(checkboxConstruct);
        });


        JFXPopup popup = new JFXPopup(list);
        btnList.setOnAction(e -> popup.show(btnList, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT));
    }



    private void setUpBackground(Movie movie){
        BackgroundImage myBI= new BackgroundImage(new Image("/graphics/backdrop.jpg",
                1920,1080,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        stackPaneMovieOverview.setBackground(new Background(myBI));
    }

    private void setUpPoster(Movie movie){
        Image image = new Image(movie.getPosterUrl(MoviePosterSize.W342),342,513,
                true,true,true);
        imgPoster.setImage(image);
    }

    private void setUpLables(Movie movie){
        lblConclusion.setText(movie.getOverview());
        lblRelease.setText(movie.getReleaseDate());
        lblTitle.setText(movie.getTitle());
        //lblGenres.setText(movie.getGenres().get(0));
        lblLanguage.setText(movie.getOriginalLanguage());
    }


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
