package Model;

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

    //TODO: Christian Warken
    /*
    Implement methods

    Du musst eigentlich nur so Setter Methoden und einen Konstruktor hinzufügen, der Joshua macht dann eine Methode
    mit
        public MovieImpl parseTmdbMovie(MovieDb movie);
    die dann diese Klasse hier quasi füllt
     */

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    private Long movieID;
    private Integer tmdbID;
    private String title;
    private Double popularity;
    private String posterURL;
    private String releaseDate;
    private boolean isAdult;
    private List<String> genres;
    private String overview;
    private String originalLanguage;
    private List<String> productionCompanies;
    private List<String> productionCountries;
    private Integer runtime;
    private Double voteAverage;
    private String status;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public MovieImpl(Long movieID, Integer tmdbID, String title, Double popularity, String posterURL, String releaseDate, boolean isAdult, List<String> genres, String overview, String originalLanguage, List<String> productionCompanies, List<String> productionCountries, Integer runtime, Double voteAverage, String status) {
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
    public Double getPopularity() {

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
    public Double getVoteAverage() {

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
}

