package Model;

import java.util.List;

/**
 * An implementation of Movie
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Feb-10
 */
public interface Movie {

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */


    /**
     * returns the id for this movie for our database
     *
     * @return Long
     */
    Long getId();

    /**
     * returns the TmdbId
     *
     * @return Integer
     */
    Integer getTmdbId();

    /**
     * returns the title that was set at object construction
     *
     * @return String
     */
    String getTitle();

    /**
     * returns this movies
     *
     * @return Double
     */
    Float getPopularity();

    /**
     * returns the url for the poster of this movie
     *
     * @return String
     */
    String getPosterUrl();

    /**
     * returns the release date of this movie
     *
     * @return String release date
     */
    String getReleaseDate();

    /**
     * returns a Boolean determining if this movie is adult rated
     *
     * @return Boolean
     */
    Boolean getIsAdult();

    /**
     * returns a list of strings for this movie
     * that contain the genres in this movies specified language
     *
     * @return {@link List}
     */
    List<String> getGenres();

    /**
     * returns this movies overview (the short explanation of this movies content)
     * in this movies language
     *
     * @return String
     */
    String getOverview();

    /**
     * returns this movies original language
     *
     * @return String
     */
    String getOriginalLanguage();

    /**
     * returns a list of this movies production companies names
     *
     * @return {@link List}
     */
    List<String> getProductionCompanies();

    /**
     * returns a list of production countries
     *
     * @return {@link List}
     */
    List<String> getProductionCountries();

    /**
     * returns the Runtime of this movie in mins
     *
     * @return Integer
     */
    Integer getRuntime();

    /**
     * returns the average rating for this movie
     *
     * @return Double
     */
    Float getVoteAverage();

    /**
     * returns the status
     * this is information like released, coming soon, etc.
     *
     * @return String
     */
    String getStatus();


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}

