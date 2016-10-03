package edu.byu.cs.superasteroids.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Model.Cannon;
import edu.byu.cs.superasteroids.Model.Engine;
import edu.byu.cs.superasteroids.Model.ExtraParts;
import edu.byu.cs.superasteroids.Model.MainBodyShip;
import edu.byu.cs.superasteroids.Model.MainGameClass;
import edu.byu.cs.superasteroids.Model.PowerCore;

/**
 * Created by raulbr on 2/12/16.
 * Version 1.0
 * Will implement database functions (CRUD) for the ShipParts tables
 */
public class ShipDao {
    /**
     * instance of db
     */
    private SQLiteDatabase _db;

    public ShipDao() {
        _db = MainGameClass.getDatabase();
    }

    public ShipDao(SQLiteDatabase db) {
        _db = db;
    }

    /**
     * Returns a set of MainBodyShip
     */
    public List<MainBodyShip> getMainBodyShips() {
        List<MainBodyShip> mainBodyShipList = new ArrayList<MainBodyShip>();
        final String SQL = "SELECT * FROM MainBodies ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                MainBodyShip mainBodyShip = new MainBodyShip();
                mainBodyShip.setId(cursor.getLong(0));
                mainBodyShip.setCannonAttach(cursor.getString(1));
                mainBodyShip.setEngineAttach(cursor.getString(2));
                mainBodyShip.setExtraAttach(cursor.getString(3));
                mainBodyShip.setImage(cursor.getString(4));
                mainBodyShip.setImageWidth(cursor.getInt(5));
                mainBodyShip.setImageHeight(cursor.getInt(6));
                mainBodyShipList.add(mainBodyShip);
                cursor.moveToNext();

            }
        }
        finally {
            cursor.close();
        }
        return mainBodyShipList;
    }

    /**
     * Adds an MainBodyShip
     */
    public boolean addMainBodyShip(JSONObject mainBodyShip) throws JSONException {
        if (mainBodyShip == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("cannon_attach", mainBodyShip.getString("cannonAttach"));
        values.put("engine_attach", mainBodyShip.getString("engineAttach"));
        values.put("extra_attach", mainBodyShip.getString("extraAttach"));
        values.put("image", mainBodyShip.getString("image"));
        values.put("image_width", mainBodyShip.getInt("imageWidth"));
        values.put("image_height", mainBodyShip.getInt("imageHeight"));
        long id = _db.insert("MainBodies", null, values);
        if (id >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns a set of Cannons
     */
    public List<Cannon> getCannons() {
        List<Cannon> cannonList = new ArrayList<Cannon>();
        final String SQL = "SELECT * FROM Cannons ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Cannon cannon = new Cannon();
                cannon.setId(cursor.getLong(0));
                cannon.setAttachPoint(cursor.getString(1));
                cannon.setEmitPoint(cursor.getString(2));
                cannon.setImage(cursor.getString(3));
                cannon.setImageWidth(cursor.getInt(4));
                cannon.setImageHeight(cursor.getInt(5));
                cannon.setAttackImage(cursor.getString(6));
                cannon.setAttackImageWidth(cursor.getInt(7));
                cannon.setAttackImageHeight(cursor.getInt(8));
                cannon.setAttackSounds(cursor.getString(9));
                cannon.setDamage(cursor.getInt(10));

                cannonList.add(cannon);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return cannonList;
    }

    /**
     * Adds an Cannon
     */
    public boolean addCannon(JSONObject cannon) throws JSONException {
        if (cannon == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("attach_point", cannon.getString("attachPoint"));
        values.put("emit_point", cannon.getString("emitPoint"));
        values.put("image", cannon.getString("image"));
        values.put("image_width", cannon.getInt("imageWidth"));
        values.put("image_height", cannon.getInt("imageHeight"));
        values.put("attack_image", cannon.getString("attackImage"));
        values.put("attack_image_width", cannon.getInt("attackImageWidth"));
        values.put("attack_image_height", cannon.getInt("attackImageHeight"));
        values.put("attack_sound", cannon.getString("attackSound"));
        values.put("damage", cannon.getInt("damage"));

        long id = _db.insert("Cannons", null, values);
        if (id >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns a set of ExtraParts
     */
    public List<ExtraParts> getExtraParts() {
        List<ExtraParts> extraPartsList = new ArrayList<ExtraParts>();
        final String SQL = "SELECT * FROM ExtraParts ";
        Cursor cursor = _db.rawQuery(SQL, new String[]{});
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                ExtraParts extraPart = new ExtraParts();
                extraPart.setId(cursor.getLong(0));
                extraPart.setAttachPoint(cursor.getString(1));
                extraPart.setImage(cursor.getString(2));
                extraPart.setImageWidth(cursor.getInt(3));
                extraPart.setImageHeight(cursor.getInt(4));

                extraPartsList.add(extraPart);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return extraPartsList;
    }

    /**
     * Adds an ExtraPart
     */
    public boolean addExtraParts(JSONObject extraParts) throws JSONException {
        if (extraParts == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("attach_point", extraParts.getString("attachPoint"));
        values.put("image", extraParts.getString("image"));
        values.put("image_width", extraParts.getInt("imageWidth"));
        values.put("image_height", extraParts.getInt("imageHeight"));

        long id = _db.insert("ExtraParts", null, values);
        if (id >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns a set of Engines
     */
    public List<Engine> getEngines() {
        List<Engine> engineList = new ArrayList<Engine>();
        final String SQL = "SELECT * FROM Engines ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Engine engine = new Engine();
                engine.setId(cursor.getLong(0));
                engine.setBaseSpeed(cursor.getInt(1));
                engine.setBaseTurnRate(cursor.getInt(2));
                engine.setAttachPoint(cursor.getString(3));
                engine.setImage(cursor.getString(4));
                engine.setImageWidth(cursor.getInt(5));
                engine.setImageHeight(cursor.getInt(6));

                engineList.add(engine);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return engineList;
    }

    /**
     * Adds an Engine
     */
    public boolean addEngine(JSONObject engine) throws JSONException {
        if (engine == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("base_speed", engine.getInt("baseSpeed"));
        values.put("base_turn_rate", engine.getInt("baseTurnRate"));
        values.put("attach_point", engine.getString("attachPoint"));
        values.put("image", engine.getString("image"));
        values.put("image_width", engine.getInt("imageWidth"));
        values.put("image_height", engine.getInt("imageHeight"));

        long id = _db.insert("Engines", null, values);
        if (id >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns a set of PowerCores
     */
    public List<PowerCore> getPowerCores() {
        List<PowerCore> powerCoreList = new ArrayList<PowerCore>();
        final String SQL = "SELECT * FROM PowerCores ";
        Cursor cursor = _db.rawQuery(SQL, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                PowerCore powerCore = new PowerCore();
                powerCore.setId(cursor.getLong(0));
                powerCore.setCannonBoost(cursor.getInt(1));
                powerCore.setEngineBoost(cursor.getInt(2));
                powerCore.setImage(cursor.getString(3));

                powerCoreList.add(powerCore);
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return powerCoreList;
    }

    /**
     * Adds an PowerCore
     */
    public boolean addPowerCore(JSONObject powerCore) throws JSONException {
        if (powerCore == null)
        {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("cannon_boost", powerCore.getInt("cannonBoost"));
        values.put("engine_boost", powerCore.getInt("engineBoost"));
        values.put("image", powerCore.getString("image"));

        long id = _db.insert("PowerCores", null, values);
        if (id >= 0)
        {
            return true;
        }
        return false;
    }

   public void deleteShipData() {
       _db.delete("MainBodies", null, null);
       _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "MainBodies" + "'");

       _db.delete("Cannons", null, null);
       _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "Cannons" + "'");

       _db.delete("ExtraParts", null, null);
       _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "ExtraParts" + "'");

       _db.delete("Engines", null, null);
       _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "Engines" + "'");

       _db.delete("PowerCores", null, null);
       _db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "PowerCores" + "'");
   }
}
