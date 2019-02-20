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
     * Sets the title of the current movie object
     *
     * @param title movie title
     */
    void setTitle(String title);

    /**
     * returns this tmdbMovies
     *
     * @return Double
     */
    Float getPopularity();

    void setPopularity(Float popularity);

    /**
     * returns the url for the poster of this movie
     *
     * @return String
     */
    String getPosterUrl();

    /**
     * Sets the URL of the poster of the current movie object
     *
     * @param posterURL URL
     */
    void setPosterUrl(String posterURL);

    /**
     * returns the release date of this movie
     *
     * @return String release date
     */
    String getReleaseDate();

    /**
     * Sets the release date of the current movie object
     *
     * @param releaseDate date as string
     */
    void setReleaseDate(String releaseDate);

    /**
     * returns a Boolean determining if this movie is adult rated
     *
     * @return Boolean
     */
    Boolean getIsAdult();

    /**
     * Sets adult status of the current movie object
     *
     * @param bool true/false adult status
     */
    void setIsAdult(boolean bool);

    /**
     * returns a list of strings for this movie
     * that contain the genres in this tmdbMovies specified language
     *
     * @return {@link List}
     */
    List<String> getGenres();

    /**
     * Sets a list of genres the current movie object belongs to
     *
     * @param list of genre types
     */
    void setGenres(List<String> list);

    /**
     * returns this tmdbMovies overview (the short explanation of this tmdbMovies content)
     * in this tmdbMovies language
     *
     * @return String
     */
    String getOverview();


    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    /**
     * Sets a short overview text of the current movie objekt
     *
     * @param overview overview text
     */
    void setOverview(String overview);

    /**
     * returns this tmdbMovies original language
     *
     * @return String
     */
    String getOriginalLanguage();

    /**
     * Sets the original language of the current movie object
     *
     * @param originalLang original language
     */
    void setOriginalLanguage(String originalLang);

    /**
     * returns a list of this tmdbMovies production companies names
     *
     * @return {@link List}
     */
    List<String> getProductionCompanies();

    /**
     * Sets a list of production companies of the current movie object
     *
     * @param list list of production companies
     */
    void setProductionCompanies(List<String> list);

    /**
     * returns a list of production countries
     *
     * @return {@link List}
     */
    List<String> getProductionCountries();

    /**
     * Sets a list of production countries of the current movie object
     *
     * @param list list of production countries
     */
    void setProductionCountries(List<String> list);

    /**
     * returns the Runtime of this movie in mins
     *
     * @return Integer
     */
    Integer getRuntime();

    /**
     * Sets the runtime of the current movie object
     *
     * @param runtime runtime of the movie
     */
    void setRuntime(Integer runtime);

    /**
     * returns the average rating for this movie
     *
     * @return Double
     */
    Float getVoteAverage();

    /**
     * Sets the average vote of the current movie object
     *
     * @param averageVote average vote
     */
    void setVoteAverage(Float averageVote);

    /**
     * returns the status
     * this is information like released, coming soon, etc.
     *
     * @return String
     */
    String getStatus();

    /**
     * Sets the status of the current movie object
     *
     * @param status status of the movie
     */
    void setStatus(String status);
}

