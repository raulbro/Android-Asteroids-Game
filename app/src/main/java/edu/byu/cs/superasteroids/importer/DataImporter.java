package edu.byu.cs.superasteroids.importer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

import edu.byu.cs.superasteroids.Database.AsteroidDAO;
import edu.byu.cs.superasteroids.Database.BackGroundObjectsDAO;
import edu.byu.cs.superasteroids.Database.DbOpenHelper;
import edu.byu.cs.superasteroids.Database.LevelDAO;
import edu.byu.cs.superasteroids.Database.ShipDao;
import edu.byu.cs.superasteroids.Model.MainGameClass;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will handle data import functions
 */
public class DataImporter implements IGameDataImporter {
    private static final String TAG_OBJECTS = "objects";
    private static final String TAG_ASTEROIDSGAME = "asteroidsGame";
    private static final String TAG_ASTEROIDS = "asteroids";
    private static final String TAG_LEVELS = "levels";
    private static final String TAG_MAINBODIES = "mainBodies";
    private static final String TAG_CANNONS = "cannons";
    private static final String TAG_EXTRAPARTS = "extraParts";
    private static final String TAG_ENGINES = "engines";
    private static final String TAG_POWERCORES = "powerCores";

    public DataImporter() {

    }

    public boolean importData(InputStreamReader dataInputReader) {
        String jSonString = new String();
        try
        {
            BufferedReader reader = new BufferedReader(dataInputReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = new String();
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            jSonString = stringBuilder.toString();

            reader.close();
        }
        catch (IOException e)
        {
            return false;
        }
        if (jSonString == null || jSonString == "")
        {
            return false;
        }
        try {
            JSONObject jsonObject = new JSONObject(jSonString);
            JSONObject asteroidGameData = jsonObject.getJSONObject(TAG_ASTEROIDSGAME);
            parseBackgroundData(asteroidGameData);
            parseAsteroidsData(asteroidGameData);
            parseLevelsData(asteroidGameData);
            parseMainBodiesData(asteroidGameData);
            parseCannonsData(asteroidGameData);
            parseExtraPartsData(asteroidGameData);
            parseEnginesData(asteroidGameData);
            parsePowerCoresData(asteroidGameData);
        }
        catch (JSONException e)
        {
            return false;
        }
        MainGameClass.LoadGameData();
        return true;
    }

    private void parseBackgroundData(JSONObject asteroidGameData) throws JSONException {
        JSONArray objects = asteroidGameData.getJSONArray(TAG_OBJECTS);
        BackGroundObjectsDAO dao = new BackGroundObjectsDAO();
        dao.deleteBackGroundData();
        for (int i = 0; i < objects.length(); i++)
        {
            String backgroundImageLocation = objects.getString(i);
            dao.addBackGroundObject(backgroundImageLocation);
        }
    }

    private void parseAsteroidsData(JSONObject asteroidGameData) throws JSONException {
        JSONArray asteroids = asteroidGameData.getJSONArray(TAG_ASTEROIDS);
        AsteroidDAO dao = new AsteroidDAO();
        dao.deleteAsteroidData();
        for(int i = 0; i < asteroids.length(); i++)
        {
            JSONObject asteroid = asteroids.getJSONObject(i);
            dao.addAsteroid(asteroid);
        }
    }

    private void parseLevelsData(JSONObject asteroidGameData) throws JSONException {
        JSONArray levels = asteroidGameData.getJSONArray(TAG_LEVELS);
        LevelDAO dao = new LevelDAO();
        dao.deleteLevelData();
        List<JSONObject> levelObjectsList = new ArrayList<JSONObject>();
        List<JSONObject> levelAsteroidsList = new ArrayList<JSONObject>();
        for (int i = 0; i < levels.length(); i++)
        {
            JSONObject level = levels.getJSONObject(i);
            JSONArray levelObjects = level.getJSONArray("levelObjects");
            for (int x = 0; x < levelObjects.length(); x++)
            {
                JSONObject levelObject = levelObjects.getJSONObject(x);
                levelObjectsList.add(levelObject);
            }

            JSONArray levelAsteroids = level.getJSONArray("levelAsteroids");
            for (int z = 0; z < levelAsteroids.length(); z++)
            {
                JSONObject levelAsteroid = levelAsteroids.getJSONObject(z);
                levelAsteroidsList.add(levelAsteroid);
            }
            dao.addLevel(level, levelObjectsList, levelAsteroidsList);
            levelObjectsList.clear();
            levelAsteroidsList.clear();
        }
    }

    private void parseMainBodiesData(JSONObject asteroidGameData) throws JSONException {
        JSONArray mainBodies = asteroidGameData.getJSONArray(TAG_MAINBODIES);
        ShipDao dao = new ShipDao();
        dao.deleteShipData();
        for(int i = 0; i < mainBodies.length(); i++)
        {
            JSONObject mainBody = mainBodies.getJSONObject(i);
            dao.addMainBodyShip(mainBody);
        }
    }

    private void parseCannonsData(JSONObject asteroidGameData) throws JSONException {
        JSONArray cannons = asteroidGameData.getJSONArray(TAG_CANNONS);
        ShipDao dao = new ShipDao();
        for(int i = 0; i < cannons.length(); i++)
        {
            JSONObject cannon = cannons.getJSONObject(i);
            dao.addCannon(cannon);
        }
    }

    private void parseExtraPartsData(JSONObject asteroidGameData) throws JSONException {
        JSONArray extraParts = asteroidGameData.getJSONArray(TAG_EXTRAPARTS);
        ShipDao dao = new ShipDao();
        for(int i = 0; i < extraParts.length(); i++)
        {
            JSONObject extraPart = extraParts.getJSONObject(i);
            dao.addExtraParts(extraPart);
        }
    }

    private void parseEnginesData(JSONObject asteroidGameData) throws JSONException {
        JSONArray engines = asteroidGameData.getJSONArray(TAG_ENGINES);
        ShipDao dao = new ShipDao();
        for(int i = 0; i < engines.length(); i++)
        {
            JSONObject engine = engines.getJSONObject(i);
            dao.addEngine(engine);
        }
    }

    private void parsePowerCoresData(JSONObject asteroidGameData) throws JSONException {
        JSONArray powerCores = asteroidGameData.getJSONArray(TAG_POWERCORES);
        ShipDao dao = new ShipDao();
        for(int i = 0; i < powerCores.length(); i++)
        {
            JSONObject powerCore = powerCores.getJSONObject(i);
            dao.addPowerCore(powerCore);
        }
    }
}

