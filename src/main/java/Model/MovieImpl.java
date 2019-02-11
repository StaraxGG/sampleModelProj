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

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    @Override
    public Long getId() {

        return this.movieID;
    }

    @Override
    public Integer getTmdbId() {

        return this.tmdbID;
    }

    @Override
    public String getTitle() {

        return this.title;
    }

    @Override
    public Double getPopularity() {

        return this.popularity;
    }

    @Override
    public String getPosterUrl() {

        return this.posterURL;
    }

    @Override
    public String getReleaseDate() {

        return this.releaseDate;
    }

    @Override
    public Boolean getIsAdult() {

        return this.isAdult;
    }

    @Override
    public List<String> getGenres() {

        return this.genres;
    }

    @Override
    public String getOverview() {

        return this.overview;
    }

    @Override
    public String getOriginalLanguage() {

        return this.originalLanguage;
    }

    @Override
    public List<String> getProductionCompanies() {

        return this.productionCompanies;
    }

    @Override
    public List<String> getProductionCountries() {

        return this.productionCountries;
    }

    @Override
    public Integer getRuntime() {

        return this.runtime;
    }

    @Override
    public Double getVoteAverage() {

        return this.voteAverage;
    }

    @Override
    public String getStatus() {

        return this.status;
    }
}

