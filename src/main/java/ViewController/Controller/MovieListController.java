package ViewController.Controller;

import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
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


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.addListButton.setOnMouseClicked(this.addListButtonClicked);

    }

    @Override
    protected void setComponentUp(){
        User user = UserModel.getInstance().getCurrentUser();
        setUpListView((UserImpl) user);

        setupDeleteButton();
        setUpRefreshButton();
    }


    private EventHandler<? super MouseEvent> addListButtonClicked = me -> {
        User currentUser = UserModel.getInstance().getCurrentUser();
        try {
            currentUser.getMovieLists()
                    .add(new MovieListImpl("test", currentUser.getUsername()));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        if(currentUser instanceof UserImpl){
            UserModel.getInstance().update((UserImpl)currentUser);
            setUpListView((UserImpl)currentUser);
        }
    };
    /**
     * Registers clicks on list view but not on label and
     * may doesn't work anymore when movieLists get deleted.
     * @param user
     */
    private void setUpListView(UserImpl user){
        List<MovieListImpl> movieLists = user.getMovieLists();
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
    private void delteMovieList(){
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
                delteMovieList();
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
