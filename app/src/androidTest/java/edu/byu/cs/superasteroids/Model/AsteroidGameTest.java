package edu.byu.cs.superasteroids.Model;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Database.AsteroidDAO;
import edu.byu.cs.superasteroids.Database.DbOpenHelper;

/**
 * Created by raulbr on 3/6/16.
 */
public class AsteroidGameTest extends AndroidTestCase {
    private DbOpenHelper _dbOpenHelper;
    private SQLiteDatabase _db;
    private AsteroidGame _asteroidGameClass;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        _dbOpenHelper = new DbOpenHelper(getContext());

        //_db = _dbOpenHelper.getWritableDatabase();
        //_db.beginTransaction();
        MainGameClass.setDbOpenHelper(_dbOpenHelper);
        MainGameClass.getDatabase().beginTransaction();
        _asteroidGameClass = new AsteroidGame(_db);
        _asteroidGameClass.LoadGameData();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        MainGameClass.getDatabase().endTransaction();
        _db = null;

        MainGameClass.getDatabase().close();
        _dbOpenHelper = null;
    }

    public void testGetMainBodiesPathList() throws JSONException {
        List<String> list = _asteroidGameClass.getMainBodiesPathList();
        assertEquals(2, list.size());
    }

    public void testGetEnginesPathList() throws JSONException {
        List<String> list = _asteroidGameClass.getEnginesPathList();
        assertEquals(2, list.size());
    }

    public void testGetPowerCoresPathList() throws JSONException {
        List<String> list = _asteroidGameClass.getPowerCoresPathList();
        assertEquals(2, list.size());
    }

    public void testGetExtraPartsPathList() throws JSONException {
        List<String> list = _asteroidGameClass.getExtraPartsPathList();
        assertEquals(2, list.size());
    }

    public void testGetCannonsPathList() throws JSONException {
        List<String> list = _asteroidGameClass.getCannonsPathList();
        assertEquals(2, list.size());
    }

    public void testGetAsteroidsList() throws JSONException {
        List<Asteroid> list = _asteroidGameClass.get_asteroidList();
        Asteroid compare = new Asteroid();
        compare.set_image("images/asteroids/asteroid.png");
        compare.set_name("regular");
        compare.set_type("regular");
        compare.set_imageWidth(169);
        compare.set_imageHeight(153);
        compare.set_id(1);
        assertEquals(compare.get_name(), list.get(0).get_name());
        assertEquals(compare.get_id(), list.get(0).get_id());
    }

    public void testGetBackGroundList() throws JSONException {
        List<BackGroundObject> list = _asteroidGameClass.get_backGroundObjectList();
        assertEquals("images/planet1.png", list.get(0).get_path());
        assertEquals("images/planet2.png", list.get(1).get_path());
        assertEquals("images/planet3.png", list.get(2).get_path());
        assertEquals("images/planet4.png", list.get(3).get_path());
        assertEquals("images/planet5.png", list.get(4).get_path());
    }

    public void testGetLevelsList() throws JSONException {
        List<Level> list = _asteroidGameClass.get_levelsList();
        Level compare = new Level();
        compare.set_id(1);
        compare.set_height(3000);
        compare.set_width(3000);
        compare.set_hint("Destroy 1 Asteroid");
        compare.set_music("sounds/SpyHunter.ogg");
        assertEquals(compare.get_height(), list.get(0).get_height());
        assertEquals(compare.get_width(), list.get(0).get_width());
    }

    public void testGetMainBodiesList() throws JSONException {
        List<MainBodyShip> list = _asteroidGameClass.get_mainBodyShipList();
        MainBodyShip compare = new MainBodyShip();
        compare.setId(1);
        compare.setImageHeight(400);
        compare.setImageWidth(200);
        compare.setEngineAttach("102,392");
        assertEquals(compare.getImageHeight(), list.get(0).getImageHeight());
        assertEquals(compare.getImageWidth(), list.get(0).getImageWidth());
        assertEquals(compare.getEngineAttach(), list.get(0).getEngineAttach());
    }

    public void testGetCannonsList() throws JSONException {
        List<Cannon> list = _asteroidGameClass.get_cannonList();
        Cannon compare = new Cannon();
        compare.setId(1);
        compare.setImageHeight(386);
        compare.setImageWidth(325);
        compare.setImage("images/parts/cannon2.png");
        assertEquals(compare.getImageHeight(), list.get(1).getImageHeight());
        assertEquals(compare.getImageWidth(), list.get(1).getImageWidth());

    }
}
