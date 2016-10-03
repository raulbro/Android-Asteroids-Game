package edu.byu.cs.superasteroids.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Model.BackGroundObject;
import edu.byu.cs.superasteroids.Model.MainGameClass;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will implement database functions (CRUD) for the BackGroundObjects table
 */
public class BackGroundObjectsDAO {
    /**
     * instance of db
     */
    private SQLiteDatabase _db;

    public BackGroundObjectsDAO() {
        _db = MainGameClass.getDatabase();
    }

    public BackGroundObjectsDAO(SQLiteDatabase db) {
        _db = db;
    }

    /**
     * Returns a set of BackGroundObjects
     */
    public List<BackGroundObject> getBackGroundObjects() {
        List<BackGroundObject> backGroundObjectList = new ArrayList<BackGroundObject>();
        final String SQL = "SELECT * FROM BackgroundObjects ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                BackGroundObject backGroundObject = new BackGroundObject();
                backGroundObject.set_id(cursor.getLong(0));
                backGroundObject.set_path(cursor.getString(1));
                backGroundObjectList.add(backGroundObject);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return backGroundObjectList;
    }

    /**
     * Adds a BackGroundObject
     */
    public boolean addBackGroundObject(String backgroundImageLocation) {
        if (backgroundImageLocation == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("path", backgroundImageLocation);
        long id = _db.insert("BackgroundObjects", null, values);
        if (id >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Delete BackGroundObject data
     */
    public void deleteBackGroundData()
    {
        //final String deleteSQL = "DELETE FROM BackgroundObjects; ";
        //MainGameClass.getDatabase().execSQL("delete from " + "BackgroundObjects"); THIS ONE WORKS
        _db.delete("BackgroundObjects", null, null);
        _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "BackgroundObjects" + "'");
    }
}
