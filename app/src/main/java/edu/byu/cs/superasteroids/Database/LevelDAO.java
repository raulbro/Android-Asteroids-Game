package edu.byu.cs.superasteroids.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Model.Level;
import edu.byu.cs.superasteroids.Model.LevelAsteroids;
import edu.byu.cs.superasteroids.Model.LevelObjects;
import edu.byu.cs.superasteroids.Model.MainGameClass;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will implement database functions (CRUD) for the Levels, LevelObjects, LevelAsteroids table
 */
public class LevelDAO {
    /** instance of db */
    private SQLiteDatabase _db;

    public LevelDAO() {
        _db = MainGameClass.getDatabase();
    }

    public LevelDAO(SQLiteDatabase db) {
        _db = db;
    }

    /**
     * Returns a list of Levels
     */
    public List<Level> getLevels() {
        List<Level> levelsList = new ArrayList<Level>();
        final String SQL = "SELECT * FROM Levels ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Level level = new Level();
                level.set_id(cursor.getLong(0));
                level.set_number(cursor.getInt(1));
                level.set_title(cursor.getString(2));
                level.set_hint(cursor.getString(3));
                level.set_width(cursor.getInt(4));
                level.set_height(cursor.getInt(5));
                level.set_music(cursor.getString(6));

                levelsList.add(level);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return levelsList;
    }

    /**
     * Adds a level
     */
    public boolean addLevel(JSONObject level, List<JSONObject> levelObjects,
                            List<JSONObject> levelAsteroids) throws JSONException {
        if (level == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("number", level.getInt("number"));
        values.put("title", level.getString("title"));
        values.put("hint", level.getString("hint"));
        values.put("width", level.getInt("width"));
        values.put("height", level.getInt("height"));
        values.put("music", level.getString("music"));
        long id = _db.insert("Levels", null, values);

        addLevelObjects(levelObjects, (int)id);
        addLevelAsteroids(levelAsteroids, (int)id);

        if (id >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns a list of BackGroundObjects for a level
     */
    public List<LevelObjects> getLevelObjects() {
        List<LevelObjects> backGroundObjectList = new ArrayList<LevelObjects>();
        final String SQL = "SELECT * FROM LevelObjects ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                LevelObjects LevelObject = new LevelObjects();
                LevelObject.set_id(cursor.getLong(0));
                LevelObject.set_leveId(cursor.getInt(1));
                LevelObject.set_position(cursor.getString(2));
                LevelObject.set_objectId(cursor.getInt(3));
                LevelObject.set_scale(cursor.getInt(4));
                backGroundObjectList.add(LevelObject);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return backGroundObjectList;
    }

    /**
     * Adds level objects for a level
     */
    public boolean addLevelObjects(List<JSONObject> levelObjects, int levelId) throws JSONException {
        for(int i = 0; i < levelObjects.size(); i++)
        {
            JSONObject levelObj = levelObjects.get(i);
            ContentValues levelObjValues = new ContentValues();
            levelObjValues.put("level_id", levelId);
            levelObjValues.put("position", levelObj.getString("position"));
            levelObjValues.put("object_id", levelObj.getInt("objectId"));
            levelObjValues.put("scale", levelObj.getDouble("scale"));
            long levelObjId = _db.insert("LevelObjects", null, levelObjValues);
            if (levelObjId < 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a set of asteroids for a level
     */
    public List<LevelAsteroids> getLevelAsteroids() {
        //NEEDS AN WHERE ON THE SQL
        List<LevelAsteroids> levelAsteroidsList = new ArrayList<LevelAsteroids>();
        final String SQL = "SELECT * FROM LevelAsteroids ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                LevelAsteroids levelAsteroids = new LevelAsteroids();
                levelAsteroids.set_id(cursor.getLong(0));
                levelAsteroids.set_levelId(cursor.getInt(1));
                levelAsteroids.set_number(cursor.getInt(2));
                levelAsteroids.set_asteroidId(cursor.getInt(3));
                levelAsteroidsList.add(levelAsteroids);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return levelAsteroidsList;
    }

    /**
     * Adds level asteroids for a level
     */
    public boolean addLevelAsteroids(List<JSONObject> levelAsteroids, int levelId)
            throws JSONException {
        for(int i = 0; i < levelAsteroids.size(); i++) {
            JSONObject levelAsteroidObj = levelAsteroids.get(i);
            ContentValues levelAsteroidObjValues = new ContentValues();
            levelAsteroidObjValues.put("level_id", levelId);
            levelAsteroidObjValues.put("number", levelAsteroidObj.getInt("number"));
            levelAsteroidObjValues.put("asteroid_id", levelAsteroidObj.getInt("asteroidId"));
            long levelAsteroidObjId = _db.insert("LevelAsteroids", null, levelAsteroidObjValues);
            if (levelAsteroidObjId < 0)
            {
                return false;
            }
        }
        return true;
    }

    public void deleteLevelData() {
        _db.delete("Levels", null, null);
        _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "Levels" + "'");
        _db.delete("LevelObjects", null, null);
        _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "LevelObjects" + "'");
        _db.delete("LevelAsteroids", null, null);
        _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "LevelAsteroids" + "'");
    }
}
