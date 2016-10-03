package edu.byu.cs.superasteroids.Model;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.List;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.InputManager;
import edu.byu.cs.superasteroids.game.ViewPort;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will represent a ship object and will inherit from MovingObject class
 */
public class Ship extends MovingObject {
    /** Represents the MainBodyShip of the ship*/
    private MainBodyShip mainBodyShip;

    /** Represents the Cannon of the ship*/
    private Cannon cannon;

    /** Represents the ExtraParts of the ship*/
    private ExtraParts extraParts;

    /** Represents the Engine of the ship*/
    private Engine engine;

    /** Represents the PowerCore of the ship*/
    private PowerCore powerCore;
    private Projectile projectile;
    private PointF locationDifference;
    private boolean drawHelper = false;
    private boolean safeMode;
    private int safeCounter;

    /** Constructor will initialize data members */
    public Ship() {
        super();
        mainBodyShip = null;
        cannon = null;
        extraParts = null;
        engine = null;
        powerCore = null;
        safeMode = false;
        safeCounter = 700;
    }

    @Override
    public void update(double elapsedTime) {
        PointF shipPosition = get_location();
        PointF viewPortLocation = MainGameClass.get_viewPort().get_location();
        PointF movePointWorld = GraphicsUtils.add(MainGameClass.get_newPoint(), viewPortLocation);
        PointF rotationHelper = GraphicsUtils.subtract(movePointWorld, shipPosition);
        double newShipRotation = 0.0;
        if(rotationHelper.x != 0 && rotationHelper.y != 0)
        {
            newShipRotation = (Math.atan2(rotationHelper.y, rotationHelper.x) * 180) / GraphicsUtils.PI;
        }
        else if (rotationHelper.x != 0)
        {
            newShipRotation = ((rotationHelper.x > 0) ? 0 : Math.PI);
        }
        else if (rotationHelper.y != 0)
        {
            newShipRotation = ((rotationHelper.y > 0) ? (0.5 * Math.PI) : (1.5 * Math.PI));
        }
        set_directionInRadians(newShipRotation);

        RectF shipBounds = get_rectangle();
        double shipSpeed = get_speed();
        double shipRadians = get_directionInRadians();

        GraphicsUtils.MoveObjectResult moveResult = GraphicsUtils.moveObject(shipPosition, shipBounds, shipSpeed,
                shipRadians, elapsedTime);
        float newX = moveResult.getNewObjPosition().x;
        float newY = moveResult.getNewObjPosition().y;

        //Set location difference to move viewport accordingly
        setLocationDifference(GraphicsUtils.subtract(new PointF(newX, newY), shipPosition));

        //Set new location
        set_location(newX, newY);

        //Set new RectF bounds
        set_rectangle(moveResult.getNewObjBounds());
    }

    @Override
    public void draw() {
        if (MainGameClass.get_asteroidsListByLevelId() != null)
        {
            CollideOtherObject();
        }
        PointF viewPortLocation = MainGameClass.get_viewPort().get_location();
        if (safeCounter < 100)
        {
            int left = (int)get_rectangle().left - (int)viewPortLocation.x;
            int top = (int)get_rectangle().top - (int)viewPortLocation.y;
            int right = (int)get_rectangle().right - (int)viewPortLocation.x;
            int bottom = (int)get_rectangle().bottom - (int)viewPortLocation.y;
            Rect rect = new Rect(left, top, right, bottom);
            DrawingHelper.drawRectangle(rect, -65536, 200);
        }
        safeCounter++;
        float bodyCenterX = get_location().x - viewPortLocation.x;
        float bodyCenterY = get_location().y - viewPortLocation.y;
        float degrees = (float)GraphicsUtils.radiansToDegrees(get_directionInRadians());
        if(degrees != 0 )
        {
            if(InputManager.movePoint != null)
            {
                degrees = degrees + 90;
                drawHelper = true;
            }
            else if (drawHelper)
            {
                degrees = degrees + 90;
                drawHelper = false;
            }
            set_directionInRadians(degrees);
        }
        if (mainBodyShip != null)
        {
            String mainBodyImgPath = mainBodyShip.getImage();
            DrawingHelper.drawImage(MainGameClass.get_content().getImageId(mainBodyImgPath), bodyCenterX,
                    bodyCenterY, degrees, 0.3f, 0.3f, 250);
        }
        if(engine != null)
        {
            String engineImgPath = engine.getImage();
            String partAttach = engine.getAttachPoint();
            String bodyAttach = mainBodyShip.getEngineAttach();
            float partCenterX = engine.getImageWidth() / 2.0f;
            float partCenterY = engine.getImageHeight() / 2.0f;
            PointF rotate = computePointXWhereToDraw(partAttach, partCenterX, bodyCenterX, bodyAttach, partCenterY, bodyCenterY);
            DrawingHelper.drawImage(MainGameClass.get_content().getImageId(engineImgPath), rotate.x, rotate.y, degrees, 0.3f,
                    0.3f, 250);
        }
        if(cannon != null)
        {
            String cannonImgPath = cannon.getImage();
            String partAttach = cannon.getAttachPoint();
            String bodyAttach = mainBodyShip.getCannonAttach();
            float partCenterX = cannon.getImageWidth() / 2.0f;
            float partCenterY = cannon.getImageHeight() / 2.0f;
            PointF rotate = computePointXWhereToDraw(partAttach, partCenterX, bodyCenterX, bodyAttach, partCenterY, bodyCenterY);
            cannon.set_location(rotate.x, rotate.y);
            cannon.set_directionInRadians(degrees);
            DrawingHelper.drawImage(MainGameClass.get_content().getImageId(cannonImgPath), rotate.x, rotate.y, degrees, 0.3f,
                    0.3f, 250);
        }
        if(extraParts != null)
        {
            String extraPartImgPath = extraParts.getImage();
            String partAttach = extraParts.getAttachPoint();
            String bodyAttach = mainBodyShip.getExtraAttach();
            float partCenterX = extraParts.getImageWidth() / 2.00f;
            float partCenterY = extraParts.getImageHeight() / 2.00f;
            PointF rotate = computePointXWhereToDraw(partAttach, partCenterX, bodyCenterX, bodyAttach, partCenterY, bodyCenterY);
            DrawingHelper.drawImage(MainGameClass.get_content().getImageId(extraPartImgPath), rotate.x, rotate.y, degrees, 0.3f,
                    0.3f, 250);
        }
//        if(powerCore != null)
//        {
//            String powerCoreImgPath = powerCore.getImage();
//            float pointY = bodyCenterY - ( (mainBodyShip.getImageHeight() * 0.3f) / 2.0f);
//            DrawingHelper.drawImage(MainGameClass.get_content().getImageId(powerCoreImgPath), bodyCenterX, pointY, degrees, 0.3f,
//                    0.3f, 250);
//        }
    }

    private PointF computePointXWhereToDraw(String partAttach, float partCenterX, float bodyCenterX,
                                           String bodyAttach, float partCenterY, float bodyCenterY) {
        //Part X
        float resultXPoint = 0.0f;
        String partAttachXString = "";
        String bodyAttachXString = "";
        float partAttachX = 0.0f;
        float bodyAttachX = 0.0f;
        for (int i = 0; i < partAttach.length(); i++) {
            if(partAttach.charAt(i) == ',')
            {
                break;
            }
            partAttachXString = partAttachXString + partAttach.charAt(i);
        }
        partAttachX = Float.parseFloat(partAttachXString);

        for (int i = 0; i < bodyAttach.length(); i++) {
            if(bodyAttach.charAt(i) == ',')
            {
                break;
            }
            bodyAttachXString = bodyAttachXString + bodyAttach.charAt(i);
        }
        bodyAttachX = Float.parseFloat(bodyAttachXString);
        float bodyCenterXHelper = mainBodyShip.getImageWidth() / 2.00f;
        float partOffSetX = (bodyAttachX - bodyCenterXHelper) + (partCenterX - partAttachX);

        //Part Y
        float resultYPoint = 0.0f;
        String partAttachYString = "";
        String bodyAttachYString = "";
        float partAttachY = 0.0f;
        float bodyAttachY = 0.0f;
        boolean forHelper = false;
        for (int i = 0; i < partAttach.length(); i++) {
            if(forHelper)
            {
                partAttachYString = partAttachYString + partAttach.charAt(i);
            }
            if(partAttach.charAt(i) == ',')
            {
                forHelper = true;
            }
        }
        partAttachY = Float.parseFloat(partAttachYString);
        forHelper = false;
        for (int i = 0; i < bodyAttach.length(); i++) {
            if(forHelper)
            {
                bodyAttachYString = bodyAttachYString + bodyAttach.charAt(i);
            }
            if(bodyAttach.charAt(i) == ',')
            {
                forHelper = true;
            }
        }
        bodyAttachY = Float.parseFloat(bodyAttachYString);
        float bodyCenterYHelper = mainBodyShip.getImageHeight() / 2.00f;
        float partOffSetY = (bodyAttachY - bodyCenterYHelper) + (partCenterY - partAttachY);

        double radians = get_directionInRadians();
        PointF rotateOffSet = GraphicsUtils.rotate(new PointF(partOffSetX, partOffSetY), radians);

        partOffSetX = rotateOffSet.x * 0.3f;
        partOffSetY = rotateOffSet.y * 0.3f;

        resultXPoint = partOffSetX + bodyCenterX;
        resultYPoint = partOffSetY + bodyCenterY;

        return new PointF(resultXPoint, resultYPoint);
    }

    @Override
    public boolean CollideOtherObject() {
        boolean collision = false;
        //Check collision with Asteroids
        List<Asteroid> asteroidsList = MainGameClass.get_asteroidsListByLevelId();
        for (int i = 0; i < asteroidsList.size(); i++) {
            if(RectF.intersects(get_rectangle(), asteroidsList.get(i).get_rectangle()))
            {
                safeCounter = 0;
                return true;
            }
        }
        return collision;
    }

    /** Will create a ship and initialize itself*/
    public void create() {
        PowerCore powerCore = MainGameClass.get_asteroidGame().get_powerCoreList().get(0);
        setMainBodyShip(MainGameClass.get_asteroidGame().get_mainBodyShipList().get(0));
        setEngine(MainGameClass.get_asteroidGame().get_engineList().get(0));
        setCannon(MainGameClass.get_asteroidGame().get_cannonList().get(0));
        setExtraParts(MainGameClass.get_asteroidGame().get_extraPartList().get(0));
        setPowerCore(powerCore);

        //Adding extras values
        int extraCannonBoost = powerCore.getCannonBoost();
        int actualDamage = MainGameClass.get_asteroidGame().get_cannonList().get(0).getDamage();
        getCannon().setDamage((extraCannonBoost + actualDamage));

        int extraEngineBoost = powerCore.getEngineBoost();
        int actualEngineSpeed = getEngine().getBaseSpeed();
        getEngine().setBaseSpeed( (extraEngineBoost + actualEngineSpeed) );
    }

    public void createBounds() {
        float scaledShipleX = (mainBodyShip.getImageWidth() * .2f) + (cannon.getAttackImageWidth() * .2f) + (extraParts.getImageWidth() * .2f);
        float scaledShipY = (mainBodyShip.getImageHeight() * .2f) + (engine.getImageHeight() * .2f);

        float centerScreenX = (DrawingHelper.getGameViewWidth() / 2.0f) + 500;
        float centerScreenY = (DrawingHelper.getGameViewHeight() / 2.0f) + 500;

        RectF bounds = new RectF();
        float left = centerScreenX - scaledShipleX / 2.0f;
        float top = centerScreenY - scaledShipY / 2.0f;
        float right = centerScreenX + scaledShipleX / 2.0f;
        float bottom = centerScreenY + scaledShipY / 2.0f;
        bounds.set(left, top, right, bottom);
        set_rectangle(bounds);
    }

    public MainBodyShip getMainBodyShip() {
        return mainBodyShip;
    }

    public void setMainBodyShip(MainBodyShip mainBodyShip) {
        this.mainBodyShip = mainBodyShip;
    }

    public Cannon getCannon() {
        return cannon;
    }

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
    }

    public ExtraParts getExtraParts() {
        return extraParts;
    }

    public void setExtraParts(ExtraParts extraParts) {
        this.extraParts = extraParts;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public PowerCore getPowerCore() {
        return powerCore;
    }

    public void setPowerCore(PowerCore powerCore) {
        this.powerCore = powerCore;
        //Adding extras that the power core gives
        set_speed( (engine.getBaseSpeed() + powerCore.getEngineBoost()) - 90 );
        cannon.setDamage( (powerCore.getCannonBoost() + cannon.getDamage()) );
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public PointF getLocationDifference() {
        return locationDifference;
    }

    public void setLocationDifference(PointF locationDifference) {
        this.locationDifference = locationDifference;
    }

    public boolean isSafeMode() {
        return safeMode;
    }

    public void setSafeMode(boolean safeMode) {
        this.safeMode = safeMode;
    }

    public int getSafeCounter() {
        return safeCounter;
    }

    public void setSafeCounter(int safeCounter) {
        this.safeCounter = safeCounter;
    }
}
