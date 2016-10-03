package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Represent a growing type asteroid and inherits from Asteroid Class
 */
public class GrowingASteroid extends Asteroid {

    public GrowingASteroid() {
        super();
        set_name("growing");
        set_image("images/asteroids/blueasteroid.png");
        set_imageWidth(161);
        set_imageHeight(178);
    }

    /** Will grow the asteroid*/
    public void GrowAsteroid() {

    }
}
