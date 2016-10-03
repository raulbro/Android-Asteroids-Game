package edu.byu.cs.superasteroids.game;

import android.graphics.PointF;

import edu.byu.cs.superasteroids.Model.MainGameClass;

/**
 * Created by raulbr on 3/1/16.
 */
public class World {
    private static int _width;
    private static int _height;
    private static PointF _worldLimits;

    public static void set_World() {
        _width = MainGameClass.get_currentLevel().get_width();
        _width = MainGameClass.get_currentLevel().get_height();
    }

    public static void set_worldLimits(PointF _worldLimits) {
        World._worldLimits = _worldLimits;
    }
}
