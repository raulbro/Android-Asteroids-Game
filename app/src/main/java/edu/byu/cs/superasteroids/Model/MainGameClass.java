package edu.byu.cs.superasteroids.Model;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Database.DbOpenHelper;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.game.ViewPort;
import edu.byu.cs.superasteroids.game.World;

/**
 * Created by raulbr on 2/21/16.
 */
public class MainGameClass {
    static private DbOpenHelper _dbOpenHelper;
    static private int _currentLevelId;
    static private ContentManager _content;
    static private AsteroidGame _asteroidGame;
    static private List<LevelObjects> _levelObjectsListByLevelId;
    static private List<Asteroid> _asteroidsListByLevelId;
    static private World _world = new World();
    static private ViewPort _viewPort = new ViewPort();
    static private PointF _newPoint;
    static private boolean _projectileFired;

    //Create a singleton
    static private MainGameClass singleton = new MainGameClass();

    public static MainGameClass getInstance()
    {
        return singleton;
    }

    private MainGameClass() {
    }

    public static void setDbOpenHelper(DbOpenHelper dbOpenHelper) {
        singleton._dbOpenHelper = dbOpenHelper;
        singleton._currentLevelId = 1;
    }

    public static SQLiteDatabase getDatabase() {
        return singleton._dbOpenHelper.getWritableDatabase();
    }

    public static void LoadGameData() {
        singleton._asteroidGame = new AsteroidGame(singleton._dbOpenHelper.getWritableDatabase());
        singleton._asteroidGame.LoadGameData();
        int t = 0;
    }

    public static Level get_currentLevel() {
        int levelsListSize = singleton._asteroidGame.get_levelsList().size();
        for (int i = 0; i < levelsListSize; i++) {
            if (singleton._asteroidGame.get_levelsList().get(i).get_number() == _currentLevelId)
            {
                return singleton._asteroidGame.get_levelsList().get(i);
            }
        }
        return singleton._asteroidGame.get_levelsList().get( (_currentLevelId-1) );
    }

    public static void addProjectile(Projectile projectile) {
        singleton._asteroidGame.get_projectileList().add(projectile);
    }

    private static List<LevelObjects> get_levelObjects() {
        int listSize = singleton._asteroidGame.get_levelObjectList().size();
        List<LevelObjects> levelObjectsList = new ArrayList<LevelObjects>();
        for (int i = 0; i < listSize; i++) {
            if (_currentLevelId == singleton._asteroidGame.get_levelObjectList().get(i).get_leveId())
            {
                levelObjectsList.add(singleton._asteroidGame.get_levelObjectList().get(i));
            }
        }

        List<BackGroundObject> backgroundList = singleton.get_asteroidGame().get_backGroundObjectList();
        for (int x = 0; x < levelObjectsList.size(); x++) {
            int objectId = levelObjectsList.get(x).get_objectId();
            levelObjectsList.get(x).set_path(backgroundList.get( (objectId - 1) ).get_path());
        }
        return levelObjectsList;
    }

    private static List<Asteroid> get_levelAsteroids() {
        List<LevelAsteroids> levelAsteroidList = singleton._asteroidGame.get_levelAsteroidList();
        List<LevelAsteroids> levelAsteroidsByLevelId = new ArrayList<LevelAsteroids>();
        for (int i = 0; i < levelAsteroidList.size(); i++) {
            if(levelAsteroidList.get(i).get_levelId() == singleton._currentLevelId)
            {
                levelAsteroidsByLevelId.add(levelAsteroidList.get(i));
            }
        }

        List<Asteroid> asteroidList = new ArrayList<Asteroid>();
        for (int i = 0; i < levelAsteroidsByLevelId.size(); i++) {
            LevelAsteroids levelAsteroidObj = levelAsteroidsByLevelId.get(i);
            for (int e = 0; e < 3; e++) {
                Asteroid asteroid = new Asteroid();
                if (levelAsteroidObj.get_asteroidId() == 1)
                {
                    asteroid = singleton._asteroidGame.get_asteroidList().get(0);
                }
                else if (levelAsteroidObj.get_asteroidId() == 2)
                {
                    asteroid = singleton._asteroidGame.get_asteroidList().get(1);
                }
                else if (levelAsteroidObj.get_asteroidId() == 3)
                {
                    asteroid = singleton._asteroidGame.get_asteroidList().get(2);
                }
                asteroid.set_asteroidVariables();
                asteroidList.add(asteroid);
            }
        }

        return asteroidList;
    }

    public static int get_currentLevelId() {
        return singleton._currentLevelId;
    }

    public static void set_currentLevelId(int _currentLevelId) {
        singleton._currentLevelId = _currentLevelId;
    }

    public static ContentManager get_content() {
        return singleton._content;
    }

    public static void set_content(ContentManager _content) {
        singleton._content = _content;
    }

    public static AsteroidGame get_asteroidGame() {
        return singleton._asteroidGame;
    }

    public static void set_asteroidGame(AsteroidGame _asteroidGame) {
        singleton._asteroidGame = _asteroidGame;
    }

    public static void set_gameLists() {
        singleton._levelObjectsListByLevelId = get_levelObjects();
        singleton._asteroidsListByLevelId = get_levelAsteroids();
    }

    public static List<LevelObjects> get_levelObjectsListByLevelId() {
        return singleton._levelObjectsListByLevelId;
    }

    public static void set_levelObjectsListByLevelId(List<LevelObjects> _levelObjectsListByLevelId) {
        MainGameClass.singleton._levelObjectsListByLevelId = _levelObjectsListByLevelId;
    }

    public static List<Asteroid> get_asteroidsListByLevelId() {
        return singleton._asteroidsListByLevelId;
    }

    public static void set_asteroidsListByLevelId(List<Asteroid> _asteroidsListByLevelId) {
        singleton._asteroidsListByLevelId = _asteroidsListByLevelId;
    }

    public static World get_world() {
        return _world;
    }

    public static void set_world(World _world) {
        MainGameClass._world = _world;
    }

    public static ViewPort get_viewPort() {
        return _viewPort;
    }

    public static void set_viewPort(ViewPort _viewPort) {
        MainGameClass._viewPort = _viewPort;
    }

    public static PointF get_newPoint() {
        return _newPoint;
    }

    public static void set_newPoint(PointF newPoint) {
        MainGameClass._newPoint = newPoint;
    }

    public static boolean is_projectileFired() {
        return _projectileFired;
    }

    public static void set_projectileFired(boolean fireProjectile) {
        MainGameClass._projectileFired = fireProjectile;
    }
}
