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

    @Before
    public void setUp() throws Exception {
        this.movie.setTmdbId(12345);
        this.movie.setTitle("Test Movie Title");
        this.movie.setPopularity(5.5f);
        this.movie.setPosterUrl("http://test.de");
        this.movie.setReleaseDate("2017-10-08");
        this.movie.setIsAdult(true);
        this.movie.setOverview("This is a overview");
        this.movie.setOriginalLanguage("de");
        this.movie.setRuntime(120);
        this.movie.setVoteAverage(3.5f);
        this.movie.setStatus("released");
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
        assertTrue(this.movie.getTmdbId() == 12345);
    }

    @Test
    public void testGetTitle() {
        assertTrue(this.movie.getTitle().equals("Test Movie Title"));
    }

    @Test
    public void testGetPopularity() {
        assertTrue(this.movie.getPopularity() == 5.5f);
    }

    @Test
    public void testGetPosterUrl() {
        // the method is new, please change the test TODO: CHW
        //assertTrue(this.movie.getPosterUrl().equals("http://test.de"));
        fail();
    }

    @Test
    public void testGetReleaseDate() {
        assertTrue(this.movie.getReleaseDate().equals("2017-10-08"));
    }

    @Test
    public void testGetIsAdult() {
        assertTrue(this.movie.getIsAdult()==true);
    }

    @Test
    public void testGetGenres() {
        assertTrue(this.movie.getGenres().contains(genreOne) && this.movie.getGenres().contains(genreTwo));
    }

    @Test
    public void testGetOverview() {
        assertTrue(this.movie.getOverview().equals("This is a overview"));
    }

    @Test
    public void testGetOriginalLanguage() {
        assertTrue(this.movie.getOriginalLanguage().equals("de"));
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
        assertTrue(this.movie.getRuntime()==120);
    }

    @Test
    public void testGetVoteAverage() {
        assertTrue(this.movie.getVoteAverage()==3.5f);
    }

    @Test
    public void testGetStatus() {
        assertTrue(this.movie.getStatus().equals("released"));
    }

    @Test
    public void testGetID() {
        assertTrue(this.movie.getId()==123456L);
    }

    @Test
    public void testSetTmdbId() {
        this.movie.setTmdbId(552233);
        assertTrue(this.movie.getTmdbId() == 552233);
    }

    @Test
    public void testSetTitle() {
        this.movie.setTitle("Setter Test Title");
        assertTrue(this.movie.getTitle().equals("Setter Test Title"));    }

    @Test
    public void testSetPopularity() {
        this.movie.setPopularity(9.9f);
        assertTrue(this.movie.getPopularity()==9.9f);

    }

    @Test
    public void testSetPosterUrl() {
        // TODO CHW
        //this.movie.setPosterUrl("http://setter_test.org");
        //assertTrue(this.movie.getPosterUrl().equals("http://setter_test.org"));
        fail();
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
        assertTrue(this.movie.getGenres().size()==3);
    }

    @Test
    public void testSetOverview() {
        this.movie.setOverview("This is a test overview");
        assertTrue(this.movie.getOverview().equals("This is a test overview"));
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
        assertTrue(this.movie.getProductionCompanies().size()==2);
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
        assertTrue(this.movie.getProductionCountries().size()==2);
    }

    @Test
    public void testSetRuntime() {
        this.movie.setRuntime(150);
        assertTrue(this.movie.getRuntime()==150);

    }

    @Test
    public void testSetVoteAverage() {
        this.movie.setVoteAverage(7.7f);
        assertTrue(this.movie.getVoteAverage()==7.7f);

    }

    @Test
    public void testSetStatus() {
        this.movie.setStatus("teststatus");
        assertTrue(this.movie.getStatus().equals("teststatus"));
    }

    @Test
    public void testSetIDalreadySet() {
        this.movie.setId(456789L);
        assertTrue(this.movie.getId()==123456L);
    }
}