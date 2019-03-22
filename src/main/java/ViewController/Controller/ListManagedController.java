package ViewController.Controller;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.Movie.MovieModel;
import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Model.MovieList.MovieListModel;
import Model.User.Exception.UserNotFoundException;
import Model.User.Exception.UserWrongPasswordException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import ViewController.Constructs.MovieConstruct;
import ViewController.WindowManager;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnRefresh;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public void initialize(URL location, ResourceBundle resources){

        try {

            UserImpl user = new UserImpl("ytatar11@googlemail.com", "MyPass2019");
            UserModel userModel = UserModel.getInstance();

            //register the user
            if (!userModel.register(user)){
                // the user is already in the database so retrieve him
                user = userModel.findById(user.getUsername());
                user = (UserImpl) userModel.login(user);
            }

            setUpListView2(user);

        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (UserWrongPasswordException e){
            e.printStackTrace();
        }



        //btnDelete.setOnAction(event -> delteMovieList());
        setupDeleteButton();
        setUpRefreshButton();


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
     * @param user
     */
    private void setUpListView2(UserImpl user){
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
        UserModel.getInstance().update((UserImpl)currentUser);
        setUpListView2((UserImpl)currentUser);
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
            UserModel.getInstance().update((UserImpl)currentUser);
            setUpListView2((UserImpl)currentUser);
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
