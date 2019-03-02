package Model.Movie;

import Model.Movie.MovieImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * An implementation of Model.Movie.MovieImplTest
 * in samplemodelproject
 *
 * @author CHW
 * @version 1.0
 * @since 2019-Feb-14
 */

public class MovieImplTest {

    private MovieImpl movie = new MovieImpl();
    private static final String genreOne = "Horror";
    private static final String genreTwo = "Comedy";
    private static final String prodCom = "Test productions";
    private static final String proCoun = "Test country";
    private static final int tmdbID = 12345;
    private static final String testTitle = "Test Movie Title";
    private static final String testPosterURL = "http://image.tmdb.org/t/p/w92/1234abcd.jpg";
    private static final String testPosterPath = "/1234abcd.jpg";
    private static final String testReleaseDate = "2017-10-08";
    private static final String testOverview = "This is a overview";
    private static final String testLanguage = "de";
    private static final String testStatus = "released";

    @Before
    public void setUp() throws Exception {
        this.movie.setTmdbId(tmdbID);
        this.movie.setTitle(testTitle);
        this.movie.setPopularity(5.5f);
        this.movie.setPosterUrl(testPosterPath);
        this.movie.setReleaseDate(testReleaseDate);
        this.movie.setIsAdult(true);
        this.movie.setOverview(testOverview);
        this.movie.setOriginalLanguage(testLanguage);
        this.movie.setRuntime(120);
        this.movie.setVoteAverage(3.5f);
        this.movie.setStatus(testStatus);
        this.movie.setId(123456L);

        LinkedList<String> genres = new LinkedList<>();
        genres.add(genreOne);
        genres.add(genreTwo);
        this.movie.setGenres(genres);

        LinkedList<String> productionCompanies = new LinkedList<>();
        productionCompanies.add(prodCom);
        this.movie.setProductionCompanies(productionCompanies);

        LinkedList<String> productionCountries = new LinkedList<>();
        productionCountries.add(proCoun);
        this.movie.setProductionCountries(productionCountries);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetTmdbId() {
        assertTrue(this.movie.getTmdbId() == tmdbID);
    }

    @Test
    public void testGetTitle() {
        assertTrue(this.movie.getTitle().equals(testTitle));
    }

    @Test
    public void testGetPopularity() {
        assertTrue(this.movie.getPopularity() == 5.5f);
    }

    @Test
    public void testGetPosterUrl() {
        assertTrue(this.movie.getPosterUrl(MoviePosterSize.W92).equals(testPosterURL));
    }

    @Test
    public void testGetReleaseDate() {
        assertTrue(this.movie.getReleaseDate().equals(testReleaseDate));
    }

    @Test
    public void testGetIsAdult() {
        assertTrue(this.movie.getIsAdult() == true);
    }

    @Test
    public void testGetGenres() {
        assertTrue(this.movie.getGenres().contains(genreOne) && this.movie.getGenres().contains(genreTwo));
    }

    @Test
    public void testGetOverview() {
        assertTrue(this.movie.getOverview().equals(testOverview));
    }

    @Test
    public void testGetOriginalLanguage() {
        assertTrue(this.movie.getOriginalLanguage().equals(testLanguage));
    }

    @Test
    public void testGetProductionCompanies() {
        assertTrue(this.movie.getProductionCompanies().contains(prodCom));
    }

    @Test
    public void testGetProductionCountries() {
        assertTrue(this.movie.getProductionCountries().contains(proCoun));
    }

    @Test
    public void testGetRuntime() {
        assertTrue(this.movie.getRuntime() == 120);
    }

    @Test
    public void testGetVoteAverage() {
        assertTrue(this.movie.getVoteAverage() == 3.5f);
    }

    @Test
    public void testGetStatus() {
        assertTrue(this.movie.getStatus().equals(testStatus));
    }

    @Test
    public void testGetID() {
        assertTrue(this.movie.getId() == 123456L);
    }

    @Test
    public void testSetTmdbId() {
        this.movie.setTmdbId(552233);
        assertTrue(this.movie.getTmdbId() == 552233);
    }

    @Test
    public void testSetTitle() {
        this.movie.setTitle(testTitle);
        assertTrue(this.movie.getTitle().equals(testTitle));
    }

    @Test
    public void testSetPopularity() {
        this.movie.setPopularity(9.9f);
        assertTrue(this.movie.getPopularity() == 9.9f);

    }

    @Test
    public void testSetPosterUrl() {
        this.movie.setPosterUrl(testPosterPath);
        assertTrue(this.movie.getPosterUrl(MoviePosterSize.W92).equals(testPosterURL));
    }

    @Test
    public void testSetReleaseDate() {
        this.movie.setReleaseDate("2018-10-01");
        assertTrue(this.movie.getReleaseDate().equals("2018-10-01"));
    }

    @Test
    public void testSetIsAdult() {
        this.movie.setIsAdult(true);
        assertTrue(this.movie.getIsAdult());
    }

    @Test
    public void testSetGenresAdd() {
        String genreThree = "Thriller";
        this.movie.getGenres().add(genreThree);

        assertTrue(this.movie.getGenres().contains(genreThree));
    }

    @Test
    public void testSetGenresSize() {
        String genreThree = "Thriller";
        this.movie.getGenres().add(genreThree);
        assertTrue(this.movie.getGenres().size() == 3);
    }

    @Test
    public void testSetOverview() {
        this.movie.setOverview(testOverview);
        assertTrue(this.movie.getOverview().equals(testOverview));
    }

    @Test
    public void testSetOriginalLanguage() {
        this.movie.setOriginalLanguage("es");
        assertTrue(this.movie.getOriginalLanguage().equals("es"));
    }

    @Test
    public void testSetProductionCompaniesAdd() {
        String curProdComp = "Test Productions";
        this.movie.getProductionCompanies().add(curProdComp);
        assertTrue(this.movie.getProductionCompanies().contains(curProdComp));
    }

    @Test
    public void testSetProductionCompaniesSize() {
        String curProdComp = "Test Productions";
        this.movie.getProductionCompanies().add(curProdComp);
        assertTrue(this.movie.getProductionCompanies().size() == 2);
    }

    @Test
    public void testSetProductionCountriesAdd() {
        String curProdCoun = "Testland";
        this.movie.getProductionCountries().add(curProdCoun);
        assertTrue(this.movie.getProductionCountries().contains(curProdCoun));
    }

    @Test
    public void testSetProductionCountriesSize() {
        String curProdCoun = "Testland";
        this.movie.getProductionCountries().add(curProdCoun);
        assertTrue(this.movie.getProductionCountries().size() == 2);
    }

    @Test
    public void testSetRuntime() {
        this.movie.setRuntime(150);
        assertTrue(this.movie.getRuntime() == 150);

    }

    @Test
    public void testSetVoteAverage() {
        this.movie.setVoteAverage(7.7f);
        assertTrue(this.movie.getVoteAverage() == 7.7f);

    }

    @Test
    public void testSetStatus() {
        this.movie.setStatus("teststatus");
        assertTrue(this.movie.getStatus().equals("teststatus"));
    }

    @Test
    public void testSetIDalreadySet() {
        this.movie.setId(456789L);
        assertTrue(this.movie.getId() == 123456L);
    }

    @Test
    public void testEquals() {
        MovieImpl testMovieOne = movie;
        MovieImpl testMovieTwo = movie;
        assertTrue(testMovieOne.equals(testMovieTwo) && testMovieTwo.equals(testMovieOne));
    }

    @Test
    public void testHashcode() {
        MovieImpl testMovieOne = movie;
        MovieImpl testMovieTwo = movie;
        assertTrue(testMovieOne.hashCode() == testMovieTwo.hashCode());
    }
}