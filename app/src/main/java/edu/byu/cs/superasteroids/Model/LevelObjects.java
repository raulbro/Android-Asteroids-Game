package edu.byu.cs.superasteroids.Model;

import java.util.List;

import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by raulbr on 2/17/16.
 */
public class LevelObjects extends MovingObject {
    private String _path;

    /** Represents the ID of the Background Object */
    private long _id;
    private int _leveId;
    private String _position;
    private int _objectId;

    /** Represents the scale of the Background Object */
    private float _scale;
    private float _pointX;
    private float _pointY;

    public void draw() {
        int imageId = MainGameClass.get_content().getImageId(get_path());
        float pointX = get_pointX() - MainGameClass.get_viewPort().get_location().x;
        float pointY = get_pointY() - MainGameClass.get_viewPort().get_location().y;
        float scale = get_scale();
        DrawingHelper.drawImage(imageId, pointX, pointY, 0.0f, scale, scale, 100);
    }

    public String get_path() {
        return _path;
    }

    public void set_path(String _path) {
        this._path = _path;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int get_leveId() {
        return _leveId;
    }

    public void set_leveId(int _leveId) {
        this._leveId = _leveId;
    }

    public String get_position() {
        return _position;
    }

    public void set_position(String _position) {
        this._position = _position;
    }

    public int get_objectId() {
        return _objectId;
    }

    public void set_objectId(int _objectId) {
        this._objectId = _objectId;
    }

    public float get_scale() {
        return _scale;
    }

    public void set_scale(float _scale) {
        this._scale = _scale;
    }

    public float get_pointX() {
        String pointXString = "";
        for (int i = 0; i < _position.length(); i++)
        {
            if(_position.charAt(i) == ',')
            {
                break;
            }
            pointXString = pointXString + _position.charAt(i);
        }
        set_pointX(Float.parseFloat(pointXString));
        return _pointX;
    }

    public void set_pointX(float pointX) {
        this._pointX = pointX;
    }

    public float get_pointY() {
        String pointYString = "";
        boolean loopHelper = false;
        for (int i = 0; i < _position.length(); i++)
        {
            if (loopHelper)
            {
                pointYString = pointYString + _position.charAt(i);
            }
            if(_position.charAt(i) == ',')
            {
                loopHelper = true;
            }
        }
        set_pointY(Float.parseFloat(pointYString));
        return _pointY;
    }

    public void set_pointY(float pointY) {
        this._pointY = pointY;
    }
}
