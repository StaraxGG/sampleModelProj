package ViewController.Constructs;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Model.MovieList.MovieListModel;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Start;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * An implementation of CheckboxConstruct
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Mär-21
 */
public class CheckboxConstruct extends JFXCheckBox {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private MovieListImpl movieList;
    private Movie movie;

    private UserImpl currentUser;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public CheckboxConstruct(MovieListImpl movieList, Movie movie) {
        super();
        this.movieList = movieList;
        this.movie = movie;

        this.setText(movieList.getName());
        setup();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    public void setup() {
        this.setText(movieList.getName());

        if (movieList.contains(movie)) {
            this.setSelected(true);
        } else {
            this.setSelected(false);
        }

        ChangeListener listCheckChange = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {

                currentUser = (UserImpl) UserModel.getInstance().getCurrentUser();
                MovieListModel movieListModel = MovieListModel.getInstance();

                //movie gets added to list
                if (new_val) {
                    if (movie instanceof MovieImpl) {
                        boolean add = movieList.getMovies().add((MovieImpl) movie);
                        if (!add) {
                            System.out.println("Movie couldnt be added to list");
                        } else {
                            System.out.println(movie.getTitle() + "added to " + movieList.getName());
                        }
                        movieList = movieListModel.update(movieList);
                        currentUser = UserModel.getInstance().update((UserImpl) UserModel.getInstance().getCurrentUser());
                        refreshCheckBox();
                        Start.getManager().refreshListView();
                    }
                }
                //movie gets deleted from list
                else {
                    if (movie instanceof MovieImpl) {
                        boolean remove = movieList.getMovies().remove((MovieImpl) movie);
                        if (!remove) {
                            System.out.println("movie isnt in list");
                        } else {
                            System.out.println(movie.getTitle() + "deleted from " + movieList.getName());
                        }
                        movieList = movieListModel.update(movieList);
                        currentUser = UserModel.getInstance().update((UserImpl) UserModel.getInstance().getCurrentUser());
                        refreshCheckBox();
                        Start.getManager().refreshListView();
                    }
                }
            }
        };

        this.selectedProperty().addListener(listCheckChange);
    }

    /**
     * TODO test this shit
     */
    private void refreshCheckBox() {
        if (movieList.contains(movie)) {
            this.setSelected(true);
        } else {
            this.setSelected(false);
        }
    }

}


