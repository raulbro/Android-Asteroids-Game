package edu.byu.cs.superasteroids.Model;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Database.AsteroidDAO;
import edu.byu.cs.superasteroids.Database.BackGroundObjectsDAO;
import edu.byu.cs.superasteroids.Database.LevelDAO;
import edu.byu.cs.superasteroids.Database.ShipDao;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Represents an instance of a game and related game functions
 */
public class AsteroidGame {
    private SQLiteDatabase _db;
    private Ship _ship;
    private ShipDao _shipDao;
    private LevelDAO _levelDao;
    private List<Asteroid> _asteroidList;
    private List<BackGroundObject> _backGroundObjectList;
    private List<Engine> _engineList;
    private List<MainBodyShip> _mainBodyShipList;
    private List<PowerCore> _powerCoreList;
    private List<ExtraParts> _extraPartList;
    private List<Cannon> _cannonList;
    private List<LevelAsteroids> _levelAsteroidList;
    private List<LevelObjects> _levelObjectList;
    private List<Level> _levelsList;
    private List<Projectile> _projectileList;

    public AsteroidGame(SQLiteDatabase db) {
       _db = db;
    }

    public void LoadGameData() {
        _ship = new Ship();
        _shipDao = new ShipDao();
        _levelDao = new LevelDAO();
        _backGroundObjectList = new ArrayList<>();
        _projectileList = new ArrayList<>();

        loadBackGroundObjectList();
        loadAsteroidList();
        loadEngineList();
        loadMainBodyShipList();
        loadPowerCoreList();
        loadExtraPartList();
        loadCannonList();
        loadLevelAsteroidList();
        loadLevelObjectList();
        loadLevelList();
    }

    public List<String> getMainBodiesPathList() {
        List<String> mainBodiesPathList = new ArrayList<String>();
        for(int i = 0; i < _mainBodyShipList.size(); i++) {
            mainBodiesPathList.add(_mainBodyShipList.get(i).getImage());
        }
        return mainBodiesPathList;
    }

    public List<String> getEnginesPathList() {
        List<String> enginesPathList = new ArrayList<String>();
        for (int i = 0; i < _engineList.size(); i++) {
            enginesPathList.add(_engineList.get(i).getImage());
        }
        return enginesPathList;
    }

    public List<String> getPowerCoresPathList() {
        List<String> powerCoresPathList = new ArrayList<String>();
        for (int i = 0; i < _powerCoreList.size(); i++) {
            powerCoresPathList.add(_powerCoreList.get(i).getImage());
        }
        return powerCoresPathList;
    }

    public List<String> getExtraPartsPathList() {
        List<String> extraPartsPathList = new ArrayList<String>();
        for (int i = 0; i < _extraPartList.size(); i++) {
            extraPartsPathList.add(_extraPartList.get(i).getImage());
        }
        return extraPartsPathList;
    }

    public List<String> getCannonsPathList() {
        List<String> cannosPathList = new ArrayList<String>();
        for (int i = 0; i < _cannonList.size(); i++) {
            cannosPathList.add(_cannonList.get(i).getImage());
        }
        return cannosPathList;
    }

    private void loadAsteroidList() {
        AsteroidDAO _dao = new AsteroidDAO();
        set_asteroidList(_dao.getAsteroids());
    }

    private void loadBackGroundObjectList() {
        BackGroundObjectsDAO _dao = new BackGroundObjectsDAO();
        set_backGroundObjectList(_dao.getBackGroundObjects());
    }

    private void loadEngineList() {
        set_engineList(_shipDao.getEngines());
    }

    private void loadMainBodyShipList() {
        set_mainBodyShipList(_shipDao.getMainBodyShips());
    }

    private void loadPowerCoreList() {
        set_powerCoreList(_shipDao.getPowerCores());
    }

    private void loadExtraPartList() {
        set_extraPartList(_shipDao.getExtraParts());
    }

    private void loadCannonList() {
        set_cannonList(_shipDao.getCannons());
    }

    private void loadLevelAsteroidList() {
        set_levelAsteroidList(_levelDao.getLevelAsteroids());
    }

    private void loadLevelObjectList() {
        set_levelObjectList(_levelDao.getLevelObjects());
    }

    private void loadLevelList()
    {
        set_levelsList(_levelDao.getLevels());
    }

    public Ship get_ship() {
        return _ship;
    }

    public void set_ship(Ship _ship) {
        this._ship = _ship;
    }

    public List<Asteroid> get_asteroidList() {
        return _asteroidList;
    }

    public void set_asteroidList(List<Asteroid> _asteroidList) {
        this._asteroidList = _asteroidList;
    }

    public List<BackGroundObject> get_backGroundObjectList() {
        return _backGroundObjectList;
    }

    public void set_backGroundObjectList(List<BackGroundObject> _backGroundObjectList) {
        this._backGroundObjectList = _backGroundObjectList;
    }

    public List<Engine> get_engineList() {
        return _engineList;
    }

    public void set_engineList(List<Engine> _engineList) {
        this._engineList = _engineList;
    }

    public List<MainBodyShip> get_mainBodyShipList() {
        return _mainBodyShipList;
    }

    public void set_mainBodyShipList(List<MainBodyShip> _mainBodyShipList) {
        this._mainBodyShipList = _mainBodyShipList;
    }

    public List<PowerCore> get_powerCoreList() {
        return _powerCoreList;
    }

    public void set_powerCoreList(List<PowerCore> _powerCoreList) {
        this._powerCoreList = _powerCoreList;
    }

    public List<ExtraParts> get_extraPartList() {
        return _extraPartList;
    }

    public void set_extraPartList(List<ExtraParts> _extraPartList) {
        this._extraPartList = _extraPartList;
    }

    public List<Cannon> get_cannonList() {
        return _cannonList;
    }

    public void set_cannonList(List<Cannon> _cannonList) {
        this._cannonList = _cannonList;
    }

    public List<LevelAsteroids> get_levelAsteroidList() {
        return _levelAsteroidList;
    }

    public void set_levelAsteroidList(List<LevelAsteroids> _levelAsteroidList) {
        this._levelAsteroidList = _levelAsteroidList;
    }

    public List<LevelObjects> get_levelObjectList() {
        return _levelObjectList;
    }

    public void set_levelObjectList(List<LevelObjects> _levelObjectList) {
        this._levelObjectList = _levelObjectList;
    }

    public List<Level> get_levelsList() {
        return _levelsList;
    }

    public void set_levelsList(List<Level> _level) {
        this._levelsList = _level;
    }

    public List<Projectile> get_projectileList() {
        return _projectileList;
    }

}
