package edu.byu.cs.superasteroids.Model;

import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will represent a Background Object and inherits from PositionObject class
 */
public class BackGroundObject extends PositionedObject {
    /** Represents the path of the Background Object */
    private String _path;

    /** Represents the ID of the Background Object */
    private long _id;

    /** Constructor to initialize data members */
    public BackGroundObject() {
        super();
    }

    public String get_path() {
        return _path;
    }

    public void set_path(String _path) {
        this._path = _path;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}
