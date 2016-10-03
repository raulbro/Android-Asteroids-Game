package edu.byu.cs.superasteroids.Model;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;
import edu.byu.cs.superasteroids.game.World;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will represent an Asteroid Object and inherits from MovingObject class
 */
public class Asteroid extends MovingObject {
    /** Represents the Id on the table*/
    private long _id;

    /** Represents the asteroid name */
    private String _name;

    /** Represents the image path of the asteroid */
    private String _image;

    /** Represents the image width of the asteroid */
    private int _imageWidth;

    /** Represents the image height of the asteroid */
    private int _imageHeight;

    /** Represents the type of the asteroid (Same as name) */
    private String _type;
    private int _blood;

    /** Constructor to initialize data members */
    public Asteroid() {
        super();
    }

    public void update(double elapsedTime) {
        if (get_isAlive())
        {
            GraphicsUtils.MoveObjectResult moveResult = GraphicsUtils.moveObject(get_location(), get_rectangle(), get_speed(),
                    get_directionInRadians(), elapsedTime);
            set_location(moveResult.getNewObjPosition().x, moveResult.getNewObjPosition().y);
            set_rectangle(moveResult.getNewObjBounds());
        }
    }

    public void draw() {
        if (!get_isAlive())
        {
            set_rectangle(new RectF(0,0,0,0));
            return;
        }
        PointF viewPortLocation = MainGameClass.get_viewPort().get_location();
        int left = (int)get_rectangle().left - (int)viewPortLocation.x;
        int top = (int)get_rectangle().top - (int)viewPortLocation.y;
        int right = (int)get_rectangle().right - (int)viewPortLocation.x;
        int bottom = (int)get_rectangle().bottom - (int)viewPortLocation.y;
        Rect rect = new Rect(left, top , right , bottom);
        DrawingHelper.drawRectangle(rect, -65536, 255);

        float viewPortLocationX = get_location().x - viewPortLocation.x;
        float viewPortLocationY = get_location().y - viewPortLocation.y;
        DrawingHelper.drawImage(MainGameClass.get_content().getImageId(_image), viewPortLocationX, viewPortLocationY,
        0.2f, 0.3f, 0.3f, 250);
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        _name = name;
    }

    public void set_image(String image) {
        _image = image;
    }

    public void set_imageWidth(int imageWidth) {
        _imageWidth = imageWidth;
    }

    public void set_imageHeight(int image_Height) {
        _imageHeight = image_Height;
    }

    public void set_type(String type) {
        _type = type;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long id) {
        this._id = id;
    }

    public void set_asteroidVariables() {
        _blood = 10;
        set_speed(30.0d);
        set_location(800.0f, 800.0f);

        float scaledProjectileX = _imageWidth * .1f;
        float scaledProjectileY = _imageHeight * .1f;

        RectF bounds = new RectF();
        float left = get_location().x - scaledProjectileX / 2.0f;
        float top = get_location().y - scaledProjectileY / 2.0f;
        float right = get_location().x + scaledProjectileX / 2.0f;
        float bottom = get_location().y + scaledProjectileY / 2.0f;
        bounds.set(left, top, right, bottom);

        set_rectangle(bounds);
    }
}
