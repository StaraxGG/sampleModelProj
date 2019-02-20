package Model;

import Tools.ConfigTools;
import com.google.common.collect.Lists;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.ProductionCompany;
import info.movito.themoviedbapi.model.ProductionCountry;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
     */
    void addMovie(Movie movie) {
        doInTransaction((em) -> {
            em.persist(movie);
        });
    }

    /**
     * delete the given movie from our local database
     *
     * @param id Long
     */
    void deleteMovie(Long id) {
        doInTransaction((em -> {
            Movie movie = em.find(Movie.class, id);
            em.remove(movie);
        }));
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
        return this.findById(id);
    }

    /**
     * returns a movie from the tmdb database
     *
     * @param id
     * @return Movie
     */
    Movie getTmdbMovie(Integer id) {
        MovieDb movie = movies.getMovie(id, tmdbLang);
        return parseTmdbMovie(movie);
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
     * @param page the search result page that will be fetched
     *             e.g. 0 returns the first page, for the next page you have to call this method again but with a 1
     * @return List
     */
    List<Movie> getSimilarMovies(Movie movie, Integer page) {


        List<MovieDb> results = movies.getSimilarMovies(movie.getTmdbId(), ConfigTools.getVal("lang"), page).getResults()
        return parseTmdbMovieList(results);
    }

    /**
     * turns a list of MovieDb objects from the Tmdb Api to a List of our Movie objects
     * @param tmdbMoviesList
     * @return List
     */
    private static List<Movie> parseTmdbMovieList(List<MovieDb> tmdbMoviesList){

        // this is hands down the best line of code in this project
        // thanks to jetbrains for doing everything
        return tmdbMoviesList.stream().map(MovieModel::parseTmdbMovie).collect(Collectors.toList());

    }

}

