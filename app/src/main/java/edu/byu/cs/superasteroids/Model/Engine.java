package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/12/16.
 * Version 1.0
 * Will represent the engine of a ship
 */
public class Engine {

    private long id;

    /** Represents the base speed of the Engine*/
    private int BaseSpeed;

    /** Represents the base turn rate of the Engine */
    private int BaseTurnRate;

    /** Represents the attach point of the Engine */
    private String AttachPoint;

    /** Represents the path of the image of the Engine */
    private String Image;

    /** Represents the image width of the Engine */
    private int ImageWidth;

    /** Represents the image height of the Engine */
    private int ImageHeight;

    /** Constructor to initialize data members */
    public Engine() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBaseSpeed() {
        return BaseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        BaseSpeed = baseSpeed;
    }

    public void setBaseTurnRate(int baseTurnRate) {
        BaseTurnRate = baseTurnRate;
    }

    public String getAttachPoint() {
        return AttachPoint;
    }

    public void setAttachPoint(String attachPoint) {
        AttachPoint = attachPoint;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getImageWidth() {
        return ImageWidth;
    }

    public void setImageWidth(int imageWidth) {
        ImageWidth = imageWidth;
    }

    public int getImageHeight() {
        return ImageHeight;
    }

    public void setImageHeight(int imageHeight) {
        ImageHeight = imageHeight;
    }
}
