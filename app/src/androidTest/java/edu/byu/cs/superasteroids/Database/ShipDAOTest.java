package edu.byu.cs.superasteroids.Database;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import edu.byu.cs.superasteroids.Model.Asteroid;
import edu.byu.cs.superasteroids.Model.Cannon;
import edu.byu.cs.superasteroids.Model.Engine;
import edu.byu.cs.superasteroids.Model.ExtraParts;
import edu.byu.cs.superasteroids.Model.MainBodyShip;
import edu.byu.cs.superasteroids.Model.PowerCore;

/**
 * Created by raulbr on 2/25/16.
 */
public class ShipDAOTest extends AndroidTestCase {
    private DbOpenHelper _dbOpenHelper;
    private SQLiteDatabase _db;
    private ShipDao _dao;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        _dbOpenHelper = new DbOpenHelper(getContext());

        _db = _dbOpenHelper.getWritableDatabase();
        _db.beginTransaction();
        _dao = new ShipDao(_db);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        _db.endTransaction();
        _db = null;

        _dbOpenHelper.close();
        _dbOpenHelper = null;
    }

    public void testAddMainBodyTrue() throws JSONException {
        JSONObject mainBody = new JSONObject();
        mainBody = createMainBodyJsonObj();
        assertTrue(_dao.addMainBodyShip(mainBody));
    }

    public void testAddMainBodyFalse() throws JSONException{
        assertFalse(_dao.addMainBodyShip(null));
    }

    public void testAddCannonTrue() throws JSONException {
        JSONObject obj = new JSONObject();
        obj = createCannonJsonObj();
        assertTrue(_dao.addCannon(obj));
    }

    public void testAddCannonFalse() throws JSONException{
        assertFalse(_dao.addCannon(null));
    }

    public void testAddExtraPartTrue() throws JSONException {
        JSONObject obj = new JSONObject();
        obj = createExtraPartJsonObj();
        assertTrue(_dao.addExtraParts(obj));
    }

    public void testAddExtraPartFalse() throws JSONException{
        assertFalse(_dao.addExtraParts(null));
    }

    public void testAddEngineTrue() throws JSONException {
        JSONObject obj = new JSONObject();
        obj = createEngineJsonObj();
        assertTrue(_dao.addEngine(obj));
    }

    public void testAddEngineFalse() throws JSONException {
        assertFalse(_dao.addEngine(null));
    }

    public void testAddPowerCoreTrue() throws JSONException {
        JSONObject obj = new JSONObject();
        obj = createPowerCoreJsonObj();
        assertTrue(_dao.addPowerCore(obj));
    }

    public void testAddPowerCoreFalse() throws JSONException {
        assertFalse(_dao.addPowerCore(null));
    }

    public void testGetMainBodyList() throws JSONException {
        List<MainBodyShip> mainBodyShipList = _dao.getMainBodyShips();
        assertEquals(2, mainBodyShipList.size());
        _dao.addMainBodyShip(createMainBodyJsonObj());
        mainBodyShipList = _dao.getMainBodyShips();
        assertEquals(3, mainBodyShipList.size());
    }

    public void testGetCannonsList() throws JSONException {
        List<Cannon> cannonList = _dao.getCannons();
        assertEquals(2, cannonList.size());
        _dao.addCannon(createCannonJsonObj());
        cannonList = _dao.getCannons();
        assertEquals(3, cannonList.size());
    }

    public void testGetExtraPartList() throws JSONException {
        List<ExtraParts> extraPartsList = _dao.getExtraParts();
        assertEquals(2, extraPartsList.size());
        _dao.addExtraParts(createExtraPartJsonObj());
        extraPartsList = _dao.getExtraParts();
        assertEquals(3, extraPartsList.size());
    }

    public void testGetEngineList() throws JSONException {
        List<Engine> engineList = _dao.getEngines();
        assertEquals(2, engineList.size());
        _dao.addEngine(createEngineJsonObj());
        engineList = _dao.getEngines();
        assertEquals(3, engineList.size());
    }

    public void testGetPowerCoreList() throws JSONException {
        List<PowerCore> powerCoreList = _dao.getPowerCores();
        assertEquals(2, powerCoreList.size());
        _dao.addPowerCore(createPowerCoreJsonObj());
        powerCoreList = _dao.getPowerCores();
        assertEquals(3, powerCoreList.size());
    }

    private JSONObject createMainBodyJsonObj() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("cannonAttach", "190,227");
        obj.put("engineAttach", "102,392");
        obj.put("extraAttach", "6,253");
        obj.put("image", "images/parts/mainbody1.png");
        obj.put("imageWidth", 200);
        obj.put("imageHeight", 400);

        return obj;
    }

    private JSONObject createCannonJsonObj() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("attachPoint", "14,240");
        obj.put("emitPoint", "104,36");
        obj.put("image", "images/parts/cannon1.png");
        obj.put("imageWidth", 160);
        obj.put("imageHeight", 200);
        obj.put("attackImage", "images/parts/laser.png");
        obj.put("attackImageWidth", 50);
        obj.put("attackImageHeight", 50);
        obj.put("attackSound", "images/parts/laser.png");
        obj.put("damage", 1);

        return obj;
    }

    private JSONObject createExtraPartJsonObj() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("attachPoint", "14,240");
        obj.put("image", "images/parts/extrapart1.png");
        obj.put("imageWidth", 160);
        obj.put("imageHeight", 200);

        return obj;
    }

    private JSONObject createEngineJsonObj() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("baseSpeed", 350);
        obj.put("baseTurnRate", 270);
        obj.put("attachPoint", "106, 6");
        obj.put("image", "images/parts/engine1.png");
        obj.put("imageWidth", 220);
        obj.put("imageHeight", 260);

        return obj;
    }

    private JSONObject createPowerCoreJsonObj() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("cannonBoost", 350);
        obj.put("engineBoost", 270);
        obj.put("image", "images/parts/engine1.png");

        return obj;
    }
}
