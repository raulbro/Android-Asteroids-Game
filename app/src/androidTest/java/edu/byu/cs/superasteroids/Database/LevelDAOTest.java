package edu.byu.cs.superasteroids.Database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Model.Cannon;
import edu.byu.cs.superasteroids.Model.Engine;
import edu.byu.cs.superasteroids.Model.ExtraParts;
import edu.byu.cs.superasteroids.Model.Level;
import edu.byu.cs.superasteroids.Model.LevelAsteroids;
import edu.byu.cs.superasteroids.Model.LevelObjects;
import edu.byu.cs.superasteroids.Model.MainBodyShip;
import edu.byu.cs.superasteroids.Model.PowerCore;

/**
 * Created by raulbr on 2/25/16.
 */
public class LevelDAOTest extends AndroidTestCase {
    private DbOpenHelper _dbOpenHelper;
    private SQLiteDatabase _db;
    private LevelDAO _dao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        _dbOpenHelper = new DbOpenHelper(getContext());

        _db = _dbOpenHelper.getWritableDatabase();
        _db.beginTransaction();
        _dao = new LevelDAO(_db);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        _db.endTransaction();
        _db = null;

        _dbOpenHelper.close();
        _dbOpenHelper = null;
    }

    public void testAddLevelObjectTrue() throws JSONException {
        JSONObject mainBody = new JSONObject();
        assertTrue(_dao.addLevel(createLevelJsonObject(), createLevelObjectsList(), createLevelAsteroidsList()));
    }

    public void testAddLevelObjectFalse() throws JSONException {
        JSONObject mainBody = new JSONObject();
        assertFalse(_dao.addLevel(null, null, null));
    }

    public void testGetLevelObjectsList() throws JSONException {
        List<LevelObjects> levelObjects = _dao.getLevelObjects();
        assertEquals(1, levelObjects.size());
    }

    public void testGetLevelAsteroidsList() throws JSONException {
        List<LevelAsteroids> levelAsteroidsList = _dao.getLevelAsteroids();
        assertEquals(6, levelAsteroidsList.size());
    }

    public void testGetLevelsList() throws JSONException {
        List<Level> levels = _dao.getLevels();
        assertEquals(5, levels.size());
    }

    private JSONObject createLevelJsonObject() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("number", 1);
        obj.put("title", "Level 1");
        obj.put("hint", "Destroy 1 Asteroid");
        obj.put("width", 3000);
        obj.put("height", 3000);
        obj.put("music", "sounds/SpyHunter.ogg");

        return obj;
    }

    private List<JSONObject> createLevelObjectsList() throws JSONException {
        List<JSONObject> levelObjectsList = new ArrayList<>();
        JSONObject obj = new JSONObject();
        obj.put("level_id", 1);
        obj.put("position", "1000,1000");
        obj.put("objectId", 1);
        obj.put("scale", 1.5);
        levelObjectsList.add(obj);
        return levelObjectsList;
    }

    private List<JSONObject> createLevelAsteroidsList() throws JSONException {
        List<JSONObject> levelAsteroidList = new ArrayList<>();
        JSONObject obj = new JSONObject();
        obj.put("level_id", 1);
        obj.put("number", 4);
        obj.put("asteroidId", 1);
        obj.put("scale", 1.5);
        levelAsteroidList.add(obj);
        return levelAsteroidList;
    }
}
