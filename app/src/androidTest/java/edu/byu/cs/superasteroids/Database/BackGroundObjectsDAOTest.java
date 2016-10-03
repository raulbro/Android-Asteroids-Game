package edu.byu.cs.superasteroids.Database;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.byu.cs.superasteroids.Model.Asteroid;
import edu.byu.cs.superasteroids.Model.BackGroundObject;

/**
 * Created by raulbr on 2/25/16.
 */
public class BackGroundObjectsDAOTest extends AndroidTestCase {

    private DbOpenHelper _dbOpenHelper;
    private SQLiteDatabase _db;
    private BackGroundObjectsDAO _dao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        _dbOpenHelper = new DbOpenHelper(getContext());
        _db = _dbOpenHelper.getWritableDatabase();
        _db.beginTransaction();
        _dao = new BackGroundObjectsDAO(_db);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        _db.endTransaction();
        _db = null;

        _dbOpenHelper.close();
        _dbOpenHelper = null;
    }

    public void testAddBackGroundObjectTrue() {
        assertTrue(_dao.addBackGroundObject("images/planet1.png"));
    }

    public void testAddBackGroundObjectFalse() {
        assertFalse(_dao.addBackGroundObject(null));
    }

    public void testGetBackGroundObjectList() {
        _dao.addBackGroundObject("images/planet0.png");
        List<BackGroundObject> backGroundObjectList = _dao.getBackGroundObjects();
        assertEquals(13, backGroundObjectList.size());
        _dao.addBackGroundObject("images/planet1.png");
        backGroundObjectList = _dao.getBackGroundObjects();
        assertEquals(14, backGroundObjectList.size());
        _dao.addBackGroundObject("images/planet112.png");
        _dao.addBackGroundObject("images/planet113.png");
        backGroundObjectList = _dao.getBackGroundObjects();
        assertEquals(16, backGroundObjectList.size());
    }
}
