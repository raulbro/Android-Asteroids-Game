package edu.byu.cs.superasteroids.Model;

/**
 * Created by raulbr on 2/11/16.
 * Version 1.0
 * Will represent a regular asteroid and will inherit from Asteroid class
 */
public class RegularAsteroid extends Asteroid {

    public RegularAsteroid() {
        super();
        set_name("regular");
        set_image("images/asteroids/asteroid.png");
        set_imageWidth(169);
        set_imageHeight(153);

    }
}
