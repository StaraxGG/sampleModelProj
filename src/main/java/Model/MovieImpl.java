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
    private String OriginalLanguage;
    private List<String> ProductionCompanies;
    private List<String> ProductionCountries;
    private Integer Runtime;
    private Double VoteAverage;
    private String Status;

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Integer getTmdbId() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public Double getPopularity() {
        return null;
    }

    @Override
    public String getPosterUrl() {
        return null;
    }

    @Override
    public String getReleaseDate() {
        return null;
    }

    @Override
    public Boolean getIsAdult() {
        return null;
    }

    @Override
    public List<String> getGenres() {
        return null;
    }

    @Override
    public String getOverview() {
        return null;
    }

    @Override
    public String getOriginalLanguage() {
        return null;
    }

    @Override
    public List<String> getProductionCompanies() {
        return null;
    }

    @Override
    public List<String> getProductionCountries() {
        return null;
    }

    @Override
    public Integer getRuntime() {
        return null;
    }

    @Override
    public Double getVoteAverage() {
        return null;
    }

    @Override
    public String getStatus() {
        return null;
    }
}

