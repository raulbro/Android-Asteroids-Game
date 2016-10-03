package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * VisibleObject class will implement common functions among objects that are visible.
 */
public class VisibleObject {
    private boolean _isVisible;

    /** Constructor, pass parameters to initialize data members */
    public VisibleObject() {

    }

    /** Will update the location of an visible object */
    public void update(double elapsedTime) {

    }

    /** Will draw a visible object */
    public void draw() {

    }

    public boolean is_visible() {
        return _isVisible;
    }

    public void set_isVisible(boolean isVisible) {
        this._isVisible = isVisible;
    }
}
