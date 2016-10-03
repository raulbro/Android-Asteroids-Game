package edu.byu.cs.superasteroids.Model;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.core.GraphicsUtils;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Represents a moving object, this will inherit to other classes and inherit from PositionedObjectClass
 */
public class MovingObject extends PositionedObject {
    /** Represents the speed of the moving object*/
    private double _speed;

    /** Represents the rectangle of te object to check for collisions*/
    private RectF _rectangle;
    private PointF _location;
    private GraphicsUtils.MoveObjectResult _newMoveObjResult;
    private double _directionInRadians;
    private boolean _isAlive;

    /** Will check if an object collides with another object */
    public boolean CollideOtherObject()
    {
        return false;
    }

    /** Constructor will initialize data members*/
    public MovingObject() {
        _newMoveObjResult = new GraphicsUtils.MoveObjectResult();
        _location = new PointF();
        set_isAlive(true);
    }

    public RectF get_rectangle() {
        return _rectangle;
    }

    public void set_rectangle(RectF _rectangle) {
        this._rectangle = _rectangle;
    }

    public double get_speed() {
        return _speed;
    }

    public void set_speed(double _speed) {
        this._speed = _speed;
    }

    public PointF get_location() {
        return _location;
    }

    public void set_location(float x, float y){
        this._location.set(x, y);
    }

    public double get_directionInRadians() {
        return _directionInRadians;
    }

    public void set_directionInRadians(double degrees) {
        double conversion = GraphicsUtils.degreesToRadians(degrees);
        this._directionInRadians = conversion;
    }

    public boolean get_isAlive() {
        return _isAlive;
    }

    public void set_isAlive(boolean _isAlive) {
        this._isAlive = _isAlive;
    }
}
