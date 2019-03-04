package ViewController.Controller;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.Movie.MovieModel;
import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Model.MovieList.MovieListModel;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Constructs.MovieConstruct;
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

import static com.sun.java.accessibility.util.EventID.ITEM;

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


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserModel userModel = UserModel.getInstance();

        User user = userModel.login(new UserImpl("test3@test.de", "test3"));

        MovieModel instanceMovieModel = MovieModel.getInstance();

        try{
            MovieListImpl movieList = new MovieListImpl("caviar", user.getUsername());
            movieList.addMovies(instanceMovieModel.getPopularMovies(0));
            user.addMovieList(movieList);
            MovieListImpl movieList2 = new MovieListImpl("watchlist", user.getUsername());
            movieList2.addMovies(instanceMovieModel.getPopularMovies(2));
            user.addMovieList(movieList2);

        }catch (UserNotFoundException e){
            System.out.println("Böse");
        }

        userModel.logout();

        List<MovieListImpl> movieLists = user.getMovieLists();
        setUpListView2(movieLists);

    }

    /**
     * Only registres clicks on label not on listView
     * @param movieLists
     */
    private void setUpListView(List<MovieListImpl> movieLists){
        movieLists.stream().forEach(e -> {
            Label label = new Label(e.getName());
            label.setOnMouseClicked(event -> e.getMovies().stream().forEach(e1 -> jfxMasonry.getChildren().add(new MovieConstruct(e1))));
            jfxList.getItems().add(label);
        });
    }

    /**
     * Registers clicks on list view but not on label and
     * may doesn't work anymore when movieLists get deleted.
     * //TODO how to implement with deletion of movieLists
     * @param movieLists
     */
    private void setUpListView2(List<MovieListImpl> movieLists){
        jfxList.getItems().clear();
        
        movieLists.stream().forEach(e -> {
            jfxList.getItems().add(new Label(e.getName()));
        });

        jfxList.setOnMouseClicked(event -> {
            int selectedIndex = jfxList.getSelectionModel().getSelectedIndex();
            MovieListImpl movieList = movieLists.get(selectedIndex);
            jfxMasonry.getChildren().clear();
            movieList.getMovies().stream().forEach(e -> jfxMasonry.getChildren().add(new MovieConstruct(e)));
        });
    }


        /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    }
