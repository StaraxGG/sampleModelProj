package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class MovieImplTest {

    private MovieImpl movie = new MovieImpl();
    private String genreOne = "Horror";
    private String genreTwo = "Comedy";
    private String prodCom = "Test productions";
    private String proCoun = "Test country";

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
        assertTrue(this.movie.getPosterUrl().equals("http://test.de"));
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
    public void getRuntime() {
        assertTrue(false);

    }

    @Test
    public void getVoteAverage() {
        assertTrue(false);

    }

    @Test
    public void getStatus() {
        assertTrue(false);

    }

    @Test
    public void setTmdbId() {
        assertTrue(false);

    }

    @Test
    public void setTitle() {
        assertTrue(false);

    }

    @Test
    public void setPopularity() {
        assertTrue(false);

    }

    @Test
    public void setPosterUrl() {
        assertTrue(false);

    }

    @Test
    public void setReleaseDate() {
        assertTrue(false);

    }

    @Test
    public void setIsAdult() {
        assertTrue(false);

    }

    @Test
    public void setGenres() {
        assertTrue(false);

    }

    @Test
    public void setOverview() {
        assertTrue(false);

    }

    @Test
    public void setOriginalLanguage() {
        assertTrue(false);

    }

    @Test
    public void setProductionCompanies() {
        assertTrue(false);

    }

    @Test
    public void setProductionCountries() {
        assertTrue(false);

    }

    @Test
    public void setRuntime() {
        assertTrue(false);

    }

    @Test
    public void setVoteAverage() {
        assertTrue(false);

    }

    @Test
    public void setStatus() {
        assertTrue(false);

    }
}