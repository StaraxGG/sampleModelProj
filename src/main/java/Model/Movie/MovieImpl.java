package Model.Movie;

import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of MovieImpl
 * in samplemodelproject
 *
 * @author CHW
 * @version 1.0
 * @since 2019-Feb-10
 */
@Entity
@Table(name = "movie")
public class MovieImpl implements Movie {


    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    @Id
    @Column(name = "movie_ID", nullable = false)
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long movieID;

    @Column(name = "tmdb_ID", nullable = false)
    private Integer tmdbID;

    @Column(name = "title")
    private String title;

    @Column(name = "popularity")
    private Float popularity;

    @Column(name = "poster_URL")
    private String posterURL;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "is_adult")
    private boolean isAdult;

    @Column(name = "genres")
    private List<String> genres;

    @Column(name = "overview")
    private String overview;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "production_companies")
    private List<String> productionCompanies;

    @Column(name = "production_countries")
    private List<String> productionCountries;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "vote_average")
    private Float voteAverage;

    @Column(name = "movie_status")
    private String status;

    @Column(name = "movielist")
    private MovieList movieList;


    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */


    public MovieImpl(Integer tmdbID, String title, Float popularity, String posterURL, String releaseDate,
                     boolean isAdult, List<String> genres, String overview, String originalLanguage,
                     List<String> productionCompanies, List<String> productionCountries, Integer runtime,
                     Float voteAverage, String status) {
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
        this.genres = new LinkedList<>();
        this.productionCountries = new LinkedList<>();
        this.productionCompanies = new LinkedList<>();
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public boolean equals(Object obj) {

        // create MovieImpl object with cast
        MovieImpl movie = (MovieImpl) obj;

        // compare their tmdb id's and return true if they are the same
        return this.getTmdbId().equals(movie.getTmdbId());
    }

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
     * Sets the unique movie ID of the current movie object
     *
     * @param id UUID
     */
    protected void setId(Long id) {
        if (this.movieID == null) {
            this.movieID = id;
        }
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
     * Sets the unique TmdB ID of the current movie object
     *
     * @param tmdbID Tmdb-ID
     */
    protected void setTmdbId(Integer tmdbID) {

        this.tmdbID = tmdbID;
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
     * Sets the title of the current movie object
     *
     * @param title movie title
     */
    @Override
    public void setTitle(String title) {

        this.title = title;
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
     * Sets the popularity value of the current movie object
     *
     * @param popularity popularity value
     */
    public void setPopularity(Float popularity) {

        this.popularity = popularity;
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
     * Sets the URL of the poster of the current movie object
     *
     * @param posterURL URL
     */
    @Override
    public void setPosterUrl(String posterURL) {

        this.posterURL = posterURL;
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
     * Sets the release date of the current movie object
     *
     * @param releaseDate date as string
     */
    @Override
    public void setReleaseDate(String releaseDate) {

        this.releaseDate = releaseDate;
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
     * Sets adult status of the current movie object
     *
     * @param bool true/false adult status
     */
    @Override
    public void setIsAdult(boolean bool) {

        this.isAdult = bool;
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
     * Sets a list of genres the current movie object belongs to
     *
     * @param list of genre types
     */
    @Override
    public void setGenres(List<String> list) {

        this.genres = list;
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
     * Sets a short overview text of the current movie objekt
     *
     * @param overview overview text
     */
    @Override
    public void setOverview(String overview) {

        this.overview = overview;
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
     * Sets the original language of the current movie object
     *
     * @param originalLang original language
     */
    @Override
    public void setOriginalLanguage(String originalLang) {

        this.originalLanguage = originalLang;
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
     * Sets a list of production companies of the current movie object
     *
     * @param list list of production companies
     */
    @Override
    public void setProductionCompanies(List<String> list) {

        this.productionCompanies = list;
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
     * Sets a list of production countries of the current movie object
     *
     * @param list list of production countries
     */
    @Override
    public void setProductionCountries(List<String> list) {

        this.productionCountries = list;
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
     * Sets the runtime of the current movie object
     *
     * @param runtime runtime of the movie
     */
    @Override
    public void setRuntime(Integer runtime) {

        this.runtime = runtime;
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
     * Sets the average vote of the current movie object
     *
     * @param averageVote average vote
     */
    @Override
    public void setVoteAverage(Float averageVote) {

        this.voteAverage = averageVote;
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
     * Sets the status of the current movie object
     *
     * @param status status of the movie
     */
    @Override
    public void setStatus(String status) {

        this.status = status;
    }

    @Override
    public MovieListImpl getMovieList() {
        return (MovieListImpl) this.movieList;
    }

    @Override
    public void setMovieList(MovieList movieList) {
        this.movieList = movieList;
    }
}
