package Model;

import info.movito.themoviedbapi.model.MovieDb;

import java.util.List;

/**
 * An implementation of MovieImpl
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public class MovieImpl implements Movie {


    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    private Long movieID;
    private Integer tmdbID;
    private String title;
    private Float popularity;
    private String posterURL;
    private String releaseDate;
    private boolean isAdult;
    private List<String> genres;
    private String overview;
    private String originalLanguage;
    private List<String> productionCompanies;
    private List<String> productionCountries;
    private Integer runtime;
    private Float voteAverage;
    private String status;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public MovieImpl(Integer tmdbID, String title, Float popularity, String posterURL, String releaseDate, boolean isAdult, List<String> genres, String overview, String originalLanguage, List<String> productionCompanies, List<String> productionCountries, Integer runtime, Float voteAverage, String status) {
        //TODO CHW: woher kommt die UUID und wird die hier gesetzt?
        //this.movieID = movieID;
        this.tmdbID = tmdbID;
        this.title = title;
        this.popularity = popularity;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
        this.isAdult = isAdult;
        this.genres = genres;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.status = status;
    }

    public MovieImpl() {
        //TODO CHW: woher kommt die UUID und wird die hier gesetzt?
        //this.movieID =
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */


    //TODO: CHW @ Joshua: hab einfach mal angefangen
    public MovieImpl parseTmdbMovie(MovieDb curMovieDb){
        MovieImpl movie = new MovieImpl();

        //set attributes
        movie.setTmdbId(curMovieDb.getId());
        movie.setTitle(curMovieDb.getTitle());
        movie.setPopularity(curMovieDb.getPopularity());
        movie.setPosterUrl(curMovieDb.getPosterPath());
        movie.setReleaseDate(curMovieDb.getReleaseDate());
        movie.setIsAdult(curMovieDb.isAdult());
        //movie.setGenres(curMovieDb.getGenres()); //TODO: other type
        movie.setOverview(curMovieDb.getOverview());
        movie.setOriginalLanguage(curMovieDb.getOriginalLanguage());
        //movie.setProductionCompanies(curMovieDb.getProductionCompanies()); //TODO: other type
        //movie.setProductionCountries(curMovieDb.getProductionCountries());
        movie.setRuntime(curMovieDb.getRuntime());
        movie.setVoteAverage(curMovieDb.getVoteAverage());
        movie.setStatus(curMovieDb.getStatus());

        return movie;
    };



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * Returns the unique movie ID of the current movie object
     *
     * @return unique movieID
     */
    @Override
    public Long getId() {

        return this.movieID;
    }

    /**
     * Returns the unique TmdB ID of the current movie object
     *
     * @return unique TmdbID
     */
    @Override
    public Integer getTmdbId() {

        return this.tmdbID;
    }

    /**
     * Returns the title of the current movie object
     *
     * @return movie title
     */
    @Override
    public String getTitle() {

        return this.title;
    }

    /**
     * Returns the popularity value of the current movie object
     *
     * @return popularity value
     */
    @Override
    public Float getPopularity() {

        return this.popularity;
    }

    /**
     * Returns the URL of the poster of the current movie object
     *
     * @return poster URL
     */
    @Override
    public String getPosterUrl() {

        return this.posterURL;
    }

    /**
     * Returns the release date of the current movie object
     *
     * @return release date as string
     */
    @Override
    public String getReleaseDate() {

        return this.releaseDate;
    }

    /**
     * Returns adult status of the current movie object
     *
     * @return true/false adult status
     */
    @Override
    public Boolean getIsAdult() {

        return this.isAdult;
    }

    /**
     * Returns a list of genres the current movie object belongs to
     *
     * @return list of genre types
     */
    @Override
    public List<String> getGenres() {

        return this.genres;
    }

    /**
     * Returns a short overview text of the current movie objekt
     *
     * @return overview text
     */
    @Override
    public String getOverview() {

        return this.overview;
    }

    /**
     * Returns the original language of the current movie object
     *
     * @return original language
     */
    @Override
    public String getOriginalLanguage() {

        return this.originalLanguage;
    }

    /**
     * Returns a list of production companies of the current movie object
     *
     * @return list of production companies
     */
    @Override
    public List<String> getProductionCompanies() {

        return this.productionCompanies;
    }

    /**
     * Returns a list of production countries of the current movie object
     *
     * @return list of production countries
     */
    @Override
    public List<String> getProductionCountries() {

        return this.productionCountries;
    }

    /**
     * Returns the runtime of the current movie object
     *
     * @return runtime of the movie
     */
    @Override
    public Integer getRuntime() {

        return this.runtime;
    }

    /**
     * Returns the average vote of the current movie object
     *
     * @return average vote
     */
    @Override
    public Float getVoteAverage() {

        return this.voteAverage;
    }

    /**
     * Returns the status of the current movie object
     *
     * @return status of the movie
     */
    @Override
    public String getStatus() {

        return this.status;
    }

    /**
     * Sets the unique movie ID of the current movie object
     *
     * @param id UUID
     */
    private void setId(Long id) {
        if(this.movieID == null) {
            this.movieID = id;
        }
    }

    /**
     * Sets the unique TmdB ID of the current movie object
     *
     * @param tmdbID Tmdb-ID
     */
    private void setTmdbId(Integer tmdbID) {

        this.tmdbID = tmdbID;
    }

    /**
     * Sets the title of the current movie object
     *
     * @param title movie title
     */
    private void setTitle(String title) {

        this.title = title;
    }

    /**
     * Sets the popularity value of the current movie object
     *
     * @param popularity popularity value
     */
    private void setPopularity(Float popularity) {

        this.popularity = popularity;
    }

    /**
     * Sets the URL of the poster of the current movie object
     *
     * @param posterURL URL
     */
    private void setPosterUrl(String posterURL) {

        this.posterURL = posterURL;
    }

    /**
     * Sets the release date of the current movie object
     *
     * @param releaseDate date as string
     */
    private void setReleaseDate(String releaseDate) {

        this.releaseDate = releaseDate;
    }

    /**
     * Sets adult status of the current movie object
     *
     * @param bool true/false adult status
     */
    private void setIsAdult(boolean bool) {

        this.isAdult = bool;
    }

    /**
     * Sets a list of genres the current movie object belongs to
     *
     * @param list of genre types
     */
    private void setGenres(List<String> list) {

        this.genres = list;
    }

    /**
     * Sets a short overview text of the current movie objekt
     *
     * @param overview overview text
     */
    private void setOverview(String overview) {

        this.overview = overview;
    }

    /**
     * Sets the original language of the current movie object
     *
     * @param originalLang original language
     */
    private void setOriginalLanguage(String originalLang) {

        this.originalLanguage = originalLang;
    }

    /**
     * Sets a list of production companies of the current movie object
     *
     * @param list list of production companies
     */
    private List<String> setProductionCompanies(List<String> list) {

        return this.productionCompanies;
    }

    /**
     * Sets a list of production countries of the current movie object
     *
     * @param list list of production countries
     */
    private void setProductionCountries(List<String> list) {

        this.productionCountries = list;
    }

    /**
     * Sets the runtime of the current movie object
     *
     * @param runtime runtime of the movie
     */
    private void setRuntime(Integer runtime) {

        this.runtime = runtime;
    }

    /**
     * Sets the average vote of the current movie object
     *
     * @param averageVote average vote
     */
    private void setVoteAverage(Float averageVote) {

        this.voteAverage = averageVote;
    }

    /**
     * Sets the status of the current movie object
     *
     * @param status status of the movie
     */
    private void setStatus(String status) {

        this.status = status;
    }



}

