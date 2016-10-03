package edu.byu.cs.superasteroids.Database;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.byu.cs.superasteroids.Model.Asteroid;
import edu.byu.cs.superasteroids.Model.MainGameClass;

/**
 * Created by raulbr on 2/25/16.
 */
public class AsteroidDAOTest extends AndroidTestCase {

    private DbOpenHelper _dbOpenHelper;
    private SQLiteDatabase _db;
    private AsteroidDAO _dao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        _dbOpenHelper = new DbOpenHelper(getContext());

        _db = _dbOpenHelper.getWritableDatabase();
        _db.beginTransaction();
        _dao = new AsteroidDAO(_db);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        _db.endTransaction();
        _db = null;

        _dbOpenHelper.close();
        _dbOpenHelper = null;
    }

    public void testIdZero() {
        Asteroid asteroid = createAsteroid();
        assertEquals(0, asteroid.get_id());
    }

    public void testAddAsteroidTrue() throws JSONException{
        JSONObject asteroid = new JSONObject();
        asteroid = createAsteroidJsonObj();
        assertTrue(_dao.addAsteroid(asteroid));
    }

    public void testAddAsteroidFalse() throws JSONException{
        JSONObject asteroid = new JSONObject();
        asteroid = null;
        assertFalse(_dao.addAsteroid(asteroid));
    }


    public void testGetAsteroidsList() throws JSONException{
        JSONObject asteroid = createAsteroidJsonObj();
        List<Asteroid> asteroidList = _dao.getAsteroids();
        assertEquals(3, asteroidList.size());
        _dao.addAsteroid(asteroid);
        asteroidList = _dao.getAsteroids();
        assertEquals(4, asteroidList.size());
    }

    private Asteroid createAsteroid()
    {
        Asteroid asteroid = new Asteroid();
        asteroid.set_name("Regular");
        asteroid.set_imageHeight(145);
        asteroid.set_imageWidth(100);
        asteroid.set_type("regular");
        asteroid.set_image("images/asteroids/regular");
        return asteroid;
    }

    private JSONObject createAsteroidJsonObj() throws JSONException
    {
        JSONObject asteroid = new JSONObject();
        asteroid.put("name", "regular");
        asteroid.put("image", "images/asteroids/asteroid.png");
        asteroid.put("imageWidth", 169);
        asteroid.put("imageHeight", 153);
        asteroid.put("type", "regular");
        return asteroid;
    }
}
