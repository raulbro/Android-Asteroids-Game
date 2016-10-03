package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/12/16.
 * Version 1.0
 * Will represent a power core of the ship
 */
public class PowerCore {

    private long id;

    /** Represents the cannon boost of the PowerCore */
    private int CannonBoost;

    /** Represents the engine boost of the PowerCore */
    private int EngineBoost;

    /** Represents the path of the image of the PowerCore */
    private String Image;

    /** Constructor will initialize data members*/
    public PowerCore() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCannonBoost() {
        return CannonBoost;
    }

    public void setCannonBoost(int cannonBoost) {
        CannonBoost = cannonBoost;
    }

    public int getEngineBoost() {
        return EngineBoost;
    }

    public void setEngineBoost(int engineBoost) {
        EngineBoost = engineBoost;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
