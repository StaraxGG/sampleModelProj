package ViewController.Controller;

import Model.MovieList.MovieListImpl;
import Model.MovieList.MovieListModel;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Constructs.MovieConstruct;
import ViewController.WindowManager;
import com.jfoenix.controls.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * An implementation of MovieListController
 * in sample-model-project
 *
 * @author Nicolas
 * @version 1.0
 * @since 2019-Feb-27
 */
public class MovieListController extends Controller implements Initializable {

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
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private JFXButton addListButton;

    final static Logger logger = LoggerFactory.getLogger(MovieListController.class);


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources){
        logger.debug("Initialised MovieListController Component");
        this.addListButton.setOnMouseClicked(this.addListButtonClicked);

    }

    @Override
    protected void setComponentUp(){
        logger.debug("Setting up MovieListController Component");
        User user = UserModel.getInstance().getCurrentUser();
        setUpListView((UserImpl) user);

        setupDeleteButton();
        setUpRefreshButton();
    }


    private EventHandler<? super MouseEvent> addListButtonClicked = me -> {
        logger.debug("User clicked addListButton");
        User currentUser = UserModel.getInstance().getCurrentUser();

        MovieListImpl newlist = null;
        try {
            logger.debug("Creating new list for user " + currentUser.getUsername());
            newlist = new MovieListImpl("test", currentUser.getUsername());
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e.getClass().getSimpleName());
        }

        logger.debug("Persisting Movie List");
        MovieListModel.getInstance().persist(newlist);

        currentUser.getMovieLists().add(newlist);
        currentUser = UserModel.getInstance().update((UserImpl)currentUser);
        try {
            logger.debug("Adding user to list " + newlist.getName());
            newlist.addUser(currentUser);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage(), e.getClass().getSimpleName());
        }

        logger.debug("Persisting User");
        UserModel.getInstance().update((UserImpl) currentUser);

        setUpListView((UserImpl)currentUser);
    };
    /**
     * Registers clicks on list view but not on label and
     * may doesn't work anymore when movieLists get deleted.
     * @param user
     */
    private void setUpListView(UserImpl user){
        logger.debug("setUpListView was called for user with name " + user.getUsername());
        List<MovieListImpl> movieLists = user.getMovieLists();

        logger.debug("User with name " + user.getUsername()
                + " has " + movieLists.size() +" Lists");
        jfxList.getItems().clear();
        jfxMasonry.getChildren().clear();

        movieLists.stream().forEach(e -> {
            jfxList.getItems().add(new Label(e.getName()));
        });

        jfxList.setOnMouseClicked(event -> {
            int selectedIndex = jfxList.getSelectionModel().getSelectedIndex();
            if(selectedIndex >= 0){
                MovieListImpl movieList = movieLists.get(selectedIndex);
                jfxMasonry.getChildren().clear();
                movieList.getMovies().stream().forEach(e -> jfxMasonry.getChildren().add(new MovieConstruct(e)));
            }
        });
    }



    public void refreshContent(){
        logger.debug("refresh Content was called");
        User currentUser = UserModel.getInstance().getCurrentUser();
        if(currentUser instanceof UserImpl) {
            UserModel.getInstance().update((UserImpl) currentUser);
            setUpListView((UserImpl)currentUser);
        }
    }

    /**
     * Deletes currently shown movieList
     * from the Users Lists and stops displaying it.
     */
    //TODO Maybe forgot to update the List? Only the user is updated
    private void deleteMovieList(){
        logger.debug("deleteMovieList was called");
        int index = jfxList.getSelectionModel().getSelectedIndex();
        if(index != -1){
            User currentUser = UserModel.getInstance().getCurrentUser();
            currentUser.getMovieLists().remove(index);
            if(currentUser instanceof UserImpl){
                UserModel.getInstance().update((UserImpl)currentUser);
                setUpListView((UserImpl)currentUser);
            }

        }
    }

    private void setupDeleteButton(){
        JFXListView<Label> list = new JFXListView<>();
        list.getItems().add(new Label("yes"));
        list.getItems().add(new Label("naa, bring me back"));

        JFXPopup popup = new JFXPopup(list);
        btnDelete.setOnAction(e -> popup.show(btnDelete, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT));

        list.setOnMouseClicked(e -> {
            int selectedIndex = list.getSelectionModel().getSelectedIndex();

            if(selectedIndex == 0){
                deleteMovieList();
                popup.hide();
            }
            else {
                popup.hide();
            }
        });
    }

    private void setUpRefreshButton(){
        btnRefresh.setOnAction(event -> refreshContent());
    }


        /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    }
