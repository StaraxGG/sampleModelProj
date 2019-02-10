import Model.Movie;
import Model.MovieModel;

import java.util.ArrayList;

/**
 * An implementation of Main
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class Main {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    public static void main(String[] args) {

        MovieModel movieModel = new MovieModel();
        ArrayList<Movie> movies = (ArrayList<Movie>) movieModel.getMovies();

        for (Movie movie : movies){
            movie.getMovieId();
        }

        UserModel userModel = new UserModel();
        User user = userModel.getCurrentUser();

        String name = user.getName();


    }


    /* ---------------------------------------- Attributes ---------------------------------------------------------- */



    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

