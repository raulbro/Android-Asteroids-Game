package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/12/16.
 * Version 1.0
 * Will represent the extra parts of a ship
 */
public class ExtraParts {

    private long id;

    /** Represents the attach point of the ExtraParts */
    private String AttachPoint;

    /** Represents the path of the image of the ExtraParts */
    private String Image;

    /** Represents the image width of the ExtraParts */
    private int ImageWidth;

    /** Represents the image height of the ExtraParts */
    private int ImageHeight;

    /** Constructor to initialize data members */
    public ExtraParts() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
