package edu.byu.cs.superasteroids.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Model.Asteroid;
import edu.byu.cs.superasteroids.Model.MainGameClass;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will implement database functions (CRUD) for the Asteroids table
 */
public class AsteroidDAO {
    /**
     * instance of db
     */
    private SQLiteDatabase _db;

    /**
     * Constructor, pass db and initialize db instance on the class
     */
    public AsteroidDAO() {
        _db = MainGameClass.getDatabase();
    }

    public AsteroidDAO(SQLiteDatabase db) {
        this._db = db;
    }

    /**
     * Returns a set of Asteroids
     */
    public List<Asteroid> getAsteroids() {
        List<Asteroid> asteroidList = new ArrayList<Asteroid>();
        final String SQL = "SELECT * FROM Asteroids ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Asteroid asteroid = new Asteroid();
                asteroid.set_id(cursor.getLong(0));
                asteroid.set_name(cursor.getString(1));
                asteroid.set_image(cursor.getString(2));
                asteroid.set_imageWidth(cursor.getInt(3));
                asteroid.set_imageHeight(cursor.getInt(4));
                asteroid.set_type(cursor.getString(5));

                asteroidList.add(asteroid);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return asteroidList;
    }

    /**
     * Adds an asteroid
     */
    public boolean addAsteroid(JSONObject asteroid) throws JSONException {
        if (asteroid == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("name", asteroid.getString("name"));
        values.put("image", asteroid.getString("image"));
        values.put("image_width", asteroid.getInt("imageWidth"));
        values.put("image_height", asteroid.getInt("imageHeight"));
        values.put("type", asteroid.getString("type"));
        long id = _db.insert("Asteroids", null, values);
        if (id >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Deletes asteroid data
     */
    public void deleteAsteroidData()
    {
        _db.delete("Asteroids", null, null);
        _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "Asteroids" + "'");
    }
}