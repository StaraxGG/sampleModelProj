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

    /**
     * Sets the title of the current movie object
     *
     * @param title movie title
     */
    void setTitle(String title);

    void setPopularity(Float popularity);

    /**
     * Sets the URL of the poster of the current movie object
     *
     * @param posterURL URL
     */
    void setPosterUrl(String posterURL);

    /**
     * Sets the release date of the current movie object
     *
     * @param releaseDate date as string
     */
    void setReleaseDate(String releaseDate);

    /**
     * Sets adult status of the current movie object
     *
     * @param bool true/false adult status
     */
    void setIsAdult(boolean bool);

    /**
     * Sets a list of genres the current movie object belongs to
     *
     * @param list of genre types
     */
    void setGenres(List<String> list);

    /**
     * Sets a short overview text of the current movie objekt
     *
     * @param overview overview text
     */
    void setOverview(String overview);

    /**
     * Sets the original language of the current movie object
     *
     * @param originalLang original language
     */
    void setOriginalLanguage(String originalLang);

    /**
     * Sets a list of production companies of the current movie object
     *
     * @param list list of production companies
     */
    void setProductionCompanies(List<String> list);

    /**
     * Sets a list of production countries of the current movie object
     *
     * @param list list of production countries
     */
    void setProductionCountries(List<String> list);

    /**
     * Sets the runtime of the current movie object
     *
     * @param runtime runtime of the movie
     */
    void setRuntime(Integer runtime);

    /**
     * Sets the average vote of the current movie object
     *
     * @param averageVote average vote
     */
    void setVoteAverage(Float averageVote);

    /**
     * Sets the status of the current movie object
     *
     * @param status status of the movie
     */
    void setStatus(String status);
}

