package Model;

import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.ProductionCompany;
import info.movito.themoviedbapi.model.ProductionCountry;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of MovieModel
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class MovieModel extends MasterModel<Long, Movie> {

    //TODO: Joshua Henschel
    /*
    Alles implementieren und die Methode
        public MovieImpl parseTmdbMovie(MovieDb movie);
    erstellen und implementiren
     */

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    protected TmdbMovies movies;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    public MovieModel() {
        super();
        movies = this.getTmdbApi().getMovies();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * add a movie to our local database of movies
     *
     * @param movie
     * @return boolean success
     */
    boolean addMovie(Movie movie) {
        throw new NotImplementedException();
    }

    /**
     * delete the given movie from our local database
     *
     * @param id Long
     * @return boolean success
     */
    boolean deleteMovie(Long id) {
        throw new NotImplementedException();
    }

    /**
     * Parses the received MovieDb object to an MovieImpl object by copying all
     * necessary attribut values and creating a new instance
     *
     * @return movie object
     */

    //TODO: CHW @ Joshua: hab einfach mal angefangen
    public static MovieImpl parseTmdbMovie(MovieDb curMovieDb) {

        //set attributes
        Integer tmdbID = curMovieDb.getId();
        String title = curMovieDb.getTitle();
        Float popularity = curMovieDb.getPopularity();
        String posterUrl = curMovieDb.getPosterPath();
        String releaseDate = curMovieDb.getReleaseDate();
        boolean isAdult = curMovieDb.isAdult();
        String overview = curMovieDb.getOverview();
        String originalLanguage = curMovieDb.getOriginalLanguage();
        Integer runtime = curMovieDb.getRuntime();
        Float voteAverage = curMovieDb.getVoteAverage();
        String status = curMovieDb.getStatus();

        //set lists
        LinkedList<String> genres = new LinkedList<>();
        LinkedList<String> productionCompanies = new LinkedList<>();
        LinkedList<String> productionCountries = new LinkedList<>();

        List<Genre> tempGenreList = curMovieDb.getGenres();
        for (int i = 0; i < tempGenreList.size(); i++) {
            Genre genre = tempGenreList.get(i);
            genres.add(genre.getName());
        }

        List<ProductionCompany> tempProdCompList = curMovieDb.getProductionCompanies();
        for (int i = 0; i < tempProdCompList.size(); i++) {
            ProductionCompany curProdComp = tempProdCompList.get(i);
            productionCompanies.add(curProdComp.getName());
        }

        List<ProductionCountry> tempProdCounList = curMovieDb.getProductionCountries();
        for (int i = 0; i < tempProdCounList.size(); i++) {
            ProductionCountry curProdCountry = tempProdCounList.get(i);
            productionCountries.add(curProdCountry.getName());
        }

        MovieImpl movie = new MovieImpl(tmdbID, title, popularity, posterUrl, releaseDate,
                isAdult, genres, overview, originalLanguage,
                productionCompanies, productionCountries, runtime,
                voteAverage, status);

        return movie;

    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns a movie with the specific id from our database
     *
     * @param id
     * @return Movie
     */
    Movie getMovie(Long id) {
        throw new NotImplementedException();
    }

    /**
     * returns a movie from the tmdb database
     *
     * @param id
     * @return Movie
     */
    Movie getTmdbMovie(Integer id) {
        throw new NotImplementedException();
    }

    /**
     * returns movies that match the  query
     *
     * @param query
     * @return List
     */
    List<Movie> getMovies(String query) {
        throw new NotImplementedException();
    }

    /**
     * returns movies that are similar to this one
     *
     * @param movie
     * @return List
     */
    List<Movie> getSimilarMovies(Movie movie) {
        throw new NotImplementedException();
    }

}

