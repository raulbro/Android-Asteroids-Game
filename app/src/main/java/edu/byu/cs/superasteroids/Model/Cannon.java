package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/12/16.
 * Version 1.0
 * Will represent the cannon of a ship
 */
public class Cannon extends MovingObject {
    private long id;

    /** Represents the attach point of the Cannon */
    private String AttachPoint;

    /** Represents the emit point of the Cannon*/
    private String EmitPoint;

    /** Represents the path of the image of the Cannon */
    private String Image;

    /** Represents the image width of the Cannon */
    private int ImageWidth;

    /** Represents the image height of the Cannon */
    private int ImageHeight;
    private String AttackImage;
    private int AttackImageWidth;
    private int AttackImageHeight;
    private String AttackSounds;
    private int damage;

    /** Constructor to initialize data members */
    public Cannon() {
        super();
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

    public String getEmitPoint() {
        return EmitPoint;
    }

    public void setEmitPoint(String emitPoint) {
        EmitPoint = emitPoint;
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

    public String getAttackImage() {
        return AttackImage;
    }

    public void setAttackImage(String attackImage) {
        AttackImage = attackImage;
    }

    public int getAttackImageWidth() {
        return AttackImageWidth;
    }

    public void setAttackImageWidth(int attackImageWidth) {
        AttackImageWidth = attackImageWidth;
    }

    public int getAttackImageHeight() {
        return AttackImageHeight;
    }

    public void setAttackImageHeight(int attackImageHeight) {
        AttackImageHeight = attackImageHeight;
    }

    public String getAttackSounds() {
        return AttackSounds;
    }

    public void setAttackSounds(String attackSounds) {
        AttackSounds = attackSounds;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
