package edu.byu.cs.superasteroids.game;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import edu.byu.cs.superasteroids.Model.MainGameClass;
import edu.byu.cs.superasteroids.Model.MovingObject;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by raulbr on 2/28/16.
 */
public class ViewPort extends MovingObject {

    public void set_ViewPortVariables() {
        float gameWidth = DrawingHelper.getGameViewWidth();
        float gameHeight = DrawingHelper.getGameViewHeight();
        RectF initialViewPortRect = new RectF();
        initialViewPortRect.set(500, 500, (500 + gameWidth), (500 + gameHeight));
        set_rectangle(initialViewPortRect);
        set_location(500, 500);
    }

    public void update(double elapsedTime) {
        float gameWidth = DrawingHelper.getGameViewWidth();
        float gameHeight = DrawingHelper.getGameViewHeight();

        PointF newLocation = new PointF();
        newLocation = GraphicsUtils.add(get_location(), MainGameClass.get_asteroidGame().get_ship().getLocationDifference());
        set_location(newLocation.x, newLocation.y);

        //update RectF boundaries
        RectF newBounds = get_rectangle();
        newBounds.set(newLocation.x, newLocation.y, (newLocation.x + gameWidth), (newLocation.y + gameHeight));
        set_rectangle(newBounds);
    }
}
