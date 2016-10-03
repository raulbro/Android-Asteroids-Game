package edu.byu.cs.superasteroids.Model;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.List;

import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will represent the projectile of a ship
 */
public class Projectile extends MovingObject {

    /** Represents the attack image of the Projectile*/
    private String _attackImage;

    /** Represents the image width of the Projectile */
    private int _attackImageWidth;

    /** Represents the image height of the Projectile */
    private int _attackImageHeight;

    /** Represents the attack sound of the Projectile */
    private String _attackSound;

    /** Represents the damage of the Projectile */
    private int _damage;

    /** Constructor will initialize data members*/
    public Projectile(Cannon cannon) {
        super();
        set_attackImage(cannon.getAttackImage());
        set_attackImageWidth(cannon.getAttackImageWidth());
        set_attackImageHeight(cannon.getAttackImageHeight());
        set_attackSound(cannon.getAttackSounds());
        set_damage(cannon.getDamage());

        set_isAlive(true);
        set_speed(400.0d);
        double cannonRadians = cannon.get_directionInRadians();
        double cannonDegrees = GraphicsUtils.radiansToDegrees(cannonRadians);

        set_directionInRadians(cannonDegrees);

        //Compute projectile initial position
        PointF emitPointF = getEmitPoint(cannon);
        emitPointF = GraphicsUtils.rotate(emitPointF, GraphicsUtils.degreesToRadians(cannonDegrees - 95));

        emitPointF.x = emitPointF.x + MainGameClass.get_viewPort().get_location().x;
        emitPointF.y = emitPointF.y + MainGameClass.get_viewPort().get_location().y;

        set_location(cannon.get_location().x + emitPointF.x, cannon.get_location().y + emitPointF.y);

        //Compute projectile bounds
        float scaledProjectileX = _attackImageWidth * .3f;
        float scaledProjectileY = _attackImageHeight * .3f;

        RectF bounds = new RectF();
        float left = get_location().x - scaledProjectileX / 2.0f;
        float top = get_location().y - scaledProjectileY / 2.0f;
        float right = get_location().x + scaledProjectileX / 2.0f;
        float bottom = get_location().y + scaledProjectileY / 2.0f;
        bounds.set(left, top, right, bottom);
        set_rectangle(bounds);
    }

    private PointF getEmitPoint(Cannon cannon) {
        PointF result = new PointF();
        String emitPointXString = "";
        String emitPointYString = "";
        float emitPointXFloat = 0.0f;
        float emitPointYFloat = 0.0f;
        String emitPoint = cannon.getEmitPoint();
        for (int i = 0; i < emitPoint.length(); i++) {
            if(emitPoint.charAt(i) == ',')
            {
                break;
            }
            emitPointXString = emitPointXString + emitPoint.charAt(i);
        }
        emitPointXFloat = Float.parseFloat(emitPointXString);

        boolean forHelper = false;
        for (int i = 0; i < emitPoint.length(); i++) {
            if(forHelper)
            {
                emitPointYString = emitPointYString + emitPoint.charAt(i);
            }
            if(emitPoint.charAt(i) == ',')
            {
                forHelper = true;
            }
        }
        emitPointYFloat = Float.parseFloat(emitPointYString);

        result.set(emitPointXFloat *.3f, emitPointYFloat * .3f);
        return result;
    }

    @Override
    public void update(double elapsedTime) {
        if (get_isAlive()) {
            GraphicsUtils.MoveObjectResult moveResult = GraphicsUtils.moveObject(get_location(), get_rectangle(), get_speed(),
                    get_directionInRadians() - GraphicsUtils.degreesToRadians(90.0), elapsedTime);
            set_location(moveResult.getNewObjPosition().x, moveResult.getNewObjPosition().y);
            set_rectangle(moveResult.getNewObjBounds());
        }
    }

    public void draw() {
        if (MainGameClass.get_asteroidGame().get_ship().getSafeCounter() > 100)
        {
            CollideOtherObject();
        }
        if (!get_isAlive())
        {
            set_rectangle(new RectF(0,0,0,0));
            return;
        }

        int imgInt = MainGameClass.get_content().getImageId(_attackImage);
        float viewPortLocationX = get_location().x - MainGameClass.get_viewPort().get_location().x;
        float viewPortLocationY = get_location().y - MainGameClass.get_viewPort().get_location().y;
        double degrees = GraphicsUtils.radiansToDegrees(get_directionInRadians());
        DrawingHelper.drawImage(imgInt, viewPortLocationX, viewPortLocationY, (float)degrees, 0.3f, 0.3f, 250);
    }

    @Override
    public boolean CollideOtherObject() {
        boolean collision = false;
        //Check collision with Asteroids
        List<Asteroid> asteroidsList = MainGameClass.get_asteroidsListByLevelId();
        for (int i = 0; i < asteroidsList.size(); i++)
        {
            if(RectF.intersects(get_rectangle(), asteroidsList.get(i).get_rectangle()))
            {
                asteroidsList.get(i).set_isAlive(false);
                set_isAlive(false);
                return true;
            }
        }
        return collision;
    }

    public String get_attackImage() {
        return _attackImage;
    }

    public void set_attackImage(String _attackImage) {
        this._attackImage = _attackImage;
    }

    public int get_attackImageWidth() {
        return _attackImageWidth;
    }

    public void set_attackImageWidth(int _attackImageWidth) {
        this._attackImageWidth = _attackImageWidth;
    }

    public int get_attackImageHeight() {
        return _attackImageHeight;
    }

    public void set_attackImageHeight(int _attackImageHeight) {
        this._attackImageHeight = _attackImageHeight;
    }

    public String get_attackSound() {
        return _attackSound;
    }

    public void set_attackSound(String _attackSound) {
        this._attackSound = _attackSound;
    }

    public int get_damage() {
        return _damage;
    }

    public void set_damage(int _damage) {
        this._damage = _damage;
    }
}
