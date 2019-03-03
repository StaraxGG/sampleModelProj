package Model.Movie;

import Model.MasterModel;
import Tools.ConfigTools;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.ProductionCompany;
import info.movito.themoviedbapi.model.ProductionCountry;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

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
public class MovieModel extends MasterModel<Long, MovieImpl> {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    protected TmdbMovies tmdbMovies;
    protected TmdbSearch tmdbSearch;

    private static MovieModel movieModel = null;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */
    private MovieModel() {
        super();
        tmdbMovies = this.getTmdbApi().getMovies();
        tmdbSearch = this.getTmdbApi().getSearch();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /**
     * returns THE instance of this MovieModel
     * @return MovieModel
     */
    public static MovieModel getInstance(){
        if (movieModel == null)
            movieModel = new MovieModel();

        return movieModel;
    }

    /**
     * Parses the received MovieDb object to an MovieImpl object by copying all
     * necessary attribut values and creating a new instance
     *
     * @return movie object
     */
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

        /*

        this is also a possible solution
        the attribute genres has to be set to null before though

        if (curMovieDb.getGenres() != null){
            genres = curMovieDb.getGenres().stream().map(Genre::getName).collect(Collectors.toCollection(LinkedList::new));
        }
        */
        List<Genre> tempGenreList = curMovieDb.getGenres();
        if(tempGenreList!=null && tempGenreList.size()>0) {
            for (int i = 0; i < tempGenreList.size(); i++) {
                Genre genre = tempGenreList.get(i);
                genres.add(genre.getName());
            }
        } else {
            genres = null;
        }

        List<ProductionCompany> tempProdCompList = curMovieDb.getProductionCompanies();
        if(tempProdCompList!=null && tempProdCompList.size()>0) {
            for (int i = 0; i < tempProdCompList.size(); i++) {
                ProductionCompany curProdComp = tempProdCompList.get(i);
                productionCompanies.add(curProdComp.getName());
            }
        } else{
            productionCompanies = null;
        }

        List<ProductionCountry> tempProdCounList = curMovieDb.getProductionCountries();
        if(tempProdCounList!=null && tempProdCounList.size()>0) {
            for (int i = 0; i < tempProdCounList.size(); i++) {
                ProductionCountry curProdCountry = tempProdCounList.get(i);
                productionCountries.add(curProdCountry.getName());
            }
        } else {
            productionCountries = null;
        }

        return new MovieImpl(tmdbID, title, popularity, posterUrl, releaseDate,
                isAdult, genres, overview, originalLanguage,
                productionCompanies, productionCountries, runtime,
                voteAverage, status);

    }

    /**
     * turns a list of MovieDb objects from the Tmdb Api to a List of our Movie objects
     *
     * @param tmdbMoviesList
     * @return List
     */
    private static List<Movie> parseTmdbMovieList(List<MovieDb> tmdbMoviesList) {

        // this is hands down the best line of code in this project
        // thanks to jetbrains for doing everything
        return tmdbMoviesList.stream().map(MovieModel::parseTmdbMovie).collect(Collectors.toList());

    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * returns a movie from the tmdb database
     *
     * @param id
     * @return Movie
     */
    public Movie getTmdbMovie(Integer id) {
        MovieDb movie = tmdbMovies.getMovie(id, tmdbLang);
        return parseTmdbMovie(movie);
    }

    /**
     * returns Movies that match the query
     * you have to run the query again for the next pages of search results
     * please notice: this method does use lazy-loading, that means that the returned MovieImpl objects
     * have only attributes like title, posterpath, etc. filled.
     *
     * to get more details about the movie from this list, a call to {@link #getTmdbMovie(Integer)} is advised.
     * this method will try to load as many attributes as possible.
     *
     * @param query
     * @param page
     * @return List
     */
    public List<Movie> getTmdbMovies(String query, Integer page) {
        MovieResultsPage movieResultsPage =
                tmdbSearch.searchMovie(
                        query,
                        null,
                        tmdbLang,
                        Boolean.valueOf(ConfigTools.getVal("tmdb.adult")),
                        page
                );

        // parses all the tmdb movies to our movie objects
        return parseTmdbMovieList(movieResultsPage.getResults());
    }

    /**
     * returns tmdbMovies that are similar to this one
     *
     * @param movie
     * @param page  the search result page that will be fetched
     *              e.g. 0 returns the first page, for the next page you have to call this method again but with a 1
     * @return List
     */
    public List<Movie> getSimilarMovies(Movie movie, Integer page) {

        List<MovieDb> results =
                tmdbMovies.getSimilarMovies(movie.getTmdbId(), ConfigTools.getVal("lang"), page).getResults();
        return parseTmdbMovieList(results);
    }

    /**
     * returns the daily movie popularity list from the TMDB website
     *
     * @param page  the search result page that will be fetched
     *              e.g. 0 returns the first page, for the next page you have to call this method again but with a 1
     * @return List
     */
    public List<Movie> getPopularMovies(Integer page) {

        List<MovieDb> results =
                tmdbMovies.getPopularMovies(ConfigTools.getVal("lang"), page).getResults();
        return parseTmdbMovieList(results);
    }
}

