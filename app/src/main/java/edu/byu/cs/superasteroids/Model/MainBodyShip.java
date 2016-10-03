package edu.byu.cs.superasteroids.Model;

import java.util.List;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Represents a main body ship object
 */
public class MainBodyShip {

    private long id;

    /** Represents the CannonAttach of the MainBodyShip*/
    private String CannonAttach;

    /** Represents the EngineAttach of the MainBodyShip */
    private String EngineAttach;

    /** Represents the ExtraAttach of the MainBodyShip */
    private String ExtraAttach;

    /** Represents the path of the image of the MainBodyShip */
    private String Image;

    /** Represents the image width of the MainBodyShip */
    private int ImageWidth;

    /** Represents the image height of the MainBodyShip */
    private int ImageHeight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /** Constructor to initialize data members */
    public MainBodyShip() {

    }

    public String getCannonAttach() {
        return CannonAttach;
    }

    public void setCannonAttach(String cannonAttach) {
        CannonAttach = cannonAttach;
    }

    public String getEngineAttach() {
        return EngineAttach;
    }

    public void setEngineAttach(String engineAttach) {
        EngineAttach = engineAttach;
    }

    public String getExtraAttach() {
        return ExtraAttach;
    }

    public void setExtraAttach(String extraAttach) {
        ExtraAttach = extraAttach;
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
