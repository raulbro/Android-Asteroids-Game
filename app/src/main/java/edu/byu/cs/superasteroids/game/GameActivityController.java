package edu.byu.cs.superasteroids.game;

import android.graphics.PointF;
import android.graphics.RectF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.superasteroids.Model.Asteroid;
import edu.byu.cs.superasteroids.Model.Cannon;
import edu.byu.cs.superasteroids.Model.Level;
import edu.byu.cs.superasteroids.Model.LevelAsteroids;
import edu.byu.cs.superasteroids.Model.LevelObjects;
import edu.byu.cs.superasteroids.Model.MainGameClass;
import edu.byu.cs.superasteroids.Model.MovingObject;
import edu.byu.cs.superasteroids.Model.PositionedObject;
import edu.byu.cs.superasteroids.Model.Projectile;
import edu.byu.cs.superasteroids.Model.VisibleObject;
import edu.byu.cs.superasteroids.base.Controller;
import edu.byu.cs.superasteroids.base.IGameDelegate;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.core.GraphicsUtils;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by raulbr on 2/21/16.
 */
public class GameActivityController implements IGameDelegate {
    private final String MAINBODYSHIPIMG;
    private final String ENGINESHIPIMG;
    private final String CANNONSHIPIMG;
    private final String EXTRAPARTSHIPIMG;
    private final String POWERCORESHIPIMG;
    private List<Integer> _allImages;
    private List<Integer> _allSounds;
    private ContentManager _content;
    private Integer _loopSoundInt;
    private boolean _firstTimeShipPosition;
    private int counter = 0;

    public GameActivityController() {
        MAINBODYSHIPIMG  = MainGameClass.get_asteroidGame().get_ship().getMainBodyShip().getImage();
        ENGINESHIPIMG = MainGameClass.get_asteroidGame().get_ship().getEngine().getImage();
        CANNONSHIPIMG = MainGameClass.get_asteroidGame().get_ship().getCannon().getImage();
        EXTRAPARTSHIPIMG = MainGameClass.get_asteroidGame().get_ship().getExtraParts().getImage();
        POWERCORESHIPIMG = MainGameClass.get_asteroidGame().get_ship().getPowerCore().getImage();
        _allImages = new ArrayList<Integer>();
        _allSounds = new ArrayList<Integer>();
        _loopSoundInt = null;
        _firstTimeShipPosition = true;

        //Set current level limits
        Level currentLevel = MainGameClass.get_currentLevel();
        PointF worldLimitsPointF = new PointF();
        worldLimitsPointF.set(currentLevel.get_width(), currentLevel.get_height());
        MainGameClass.get_world().set_worldLimits(worldLimitsPointF);
    }

    @Override
    public void update(double elapsedTime) {
        //TODO: Call all the objects update, after this all objects will call draw and they will be already updated.
        PointF move = InputManager.movePoint;
        if (move != null)
        {
            MainGameClass.set_newPoint(move);
            MainGameClass.get_asteroidGame().get_ship().update(elapsedTime);
            MainGameClass.get_viewPort().update(elapsedTime);
        }
          updateProjectiles(elapsedTime);
          updateAsteroids(elapsedTime);
    }

    private void updateProjectiles(double elapsedTime) {
        int listSize = MainGameClass.get_asteroidGame().get_projectileList().size();
        for (int i = 0; i < listSize; i++) {
            MainGameClass.get_asteroidGame().get_projectileList().get(i).update(elapsedTime);
        }
    }

    private void updateAsteroids(double elapsedTime) {
        int listSize = MainGameClass.get_asteroidsListByLevelId().size();
        for (int i = 0; i < listSize; i++) {
            if (i == 0)
            {
                MainGameClass.get_asteroidsListByLevelId().get(i).set_directionInRadians(0);
            }
            else if (i == 1)
            {
                MainGameClass.get_asteroidsListByLevelId().get(i).set_directionInRadians(45);
            }
            else
            {
                MainGameClass.get_asteroidsListByLevelId().get(i).set_directionInRadians(90);
            }
            MainGameClass.get_asteroidsListByLevelId().get(i).update(elapsedTime);
        }
    }

    @Override
    public void loadContent(ContentManager content) {
        //Load Ship images
        _allImages.add( content.loadImage(MAINBODYSHIPIMG) );
        _allImages.add( content.loadImage(ENGINESHIPIMG) );
        _allImages.add( content.loadImage(CANNONSHIPIMG) );
        _allImages.add( content.loadImage(EXTRAPARTSHIPIMG) );
        _allImages.add( content.loadImage(POWERCORESHIPIMG) );

        //Load Asteroid images
        _allImages.add( content.loadImage("images/asteroids/asteroid.png") );
        _allImages.add( content.loadImage("images/asteroids/blueasteroid.png") );
        _allImages.add( content.loadImage("images/space.bmp") );
        _allImages.add( content.loadImage("images/parts/laser.png") );
        _allImages.add(content.loadImage("images/parts/laser2.png"));

        //Load LevelObjects images
        MainGameClass.set_gameLists();
        loadLevelBackgroundObjects(content);

        //Load sounds
        try {
            _allSounds.add( content.loadSound("sounds/impact.wav") );
            _allSounds.add( content.loadSound("sounds/laser.mp3") );
            _loopSoundInt = content.loadLoopSound("sounds/SpyHunter.ogg");
        }
        catch (IOException ex)
        {

        }
        MainGameClass.set_content(content);
    }

    private void loadLevelBackgroundObjects(ContentManager content) {
        for (int i = 0; i <  MainGameClass.get_levelObjectsListByLevelId().size(); i++)
        {
            String imgPath =  MainGameClass.get_levelObjectsListByLevelId().get(i).get_path();
            _allImages.add(content.loadImage(imgPath));
        }
    }

    @Override
    public void unloadContent(ContentManager content) {
        for (int i = 0; i < _allImages.size(); i++)
        {
            content.unloadImage(_allImages.get(i));
        }

        for (int x = 0; x < _allSounds.size(); x++)
        {
            content.unloadSound(_allSounds.get(x));
        }
        content.unloadLoopSound(_loopSoundInt);
    }

    @Override
    public void draw() {
        //TODO: Remember to draw everything on viewport coordinates and not world. Use viewport conversion function, see website document for formula.
        if (_firstTimeShipPosition)
        {
            MainGameClass.get_asteroidGame().get_ship().createBounds();

            float gameWidth = DrawingHelper.getGameViewWidth() / 2.0f;
            float gameHeight = DrawingHelper.getGameViewHeight() / 2.0f;
            MainGameClass.get_viewPort().set_ViewPortVariables();
            MainGameClass.get_asteroidGame().get_ship().set_location(gameWidth + MainGameClass.get_viewPort().get_location().x, gameHeight + MainGameClass.get_viewPort().get_location().y);

            MainGameClass.get_asteroidGame().get_ship().set_directionInRadians(0.0);
            _firstTimeShipPosition = false;
        }
        DrawingHelper.drawImage(MainGameClass.get_content().getImageId("images/space.bmp"), 1000,
                1000, 0.0f, 1.0f, 1.0f, 150);
        drawLevelObjects();
        MainGameClass.get_asteroidGame().get_ship().draw();
        drawAndCreateProjectile();
        drawLevelAsteroids();
    }

    private void drawAndCreateProjectile() {
        counter++;
        PointF move = InputManager.movePoint;
        if (move == null)
        {
            counter = 15;
        }
        if(MainGameClass.is_projectileFired() && counter > 20 && move != null)
        {
            Projectile projectile = new Projectile(MainGameClass.get_asteroidGame().get_ship().getCannon());
            MainGameClass.addProjectile(projectile);
            MainGameClass.set_projectileFired(false);
            counter = 0;
        }

        for (int i = 0; i < MainGameClass.get_asteroidGame().get_projectileList().size(); i++) {
            MainGameClass.get_asteroidGame().get_projectileList().get(i).draw();
        }
    }

    private void drawLevelObjects() {
        for (int i = 0; i <  MainGameClass.get_levelObjectsListByLevelId().size(); i++) {
            LevelObjects currentLevelObject =  MainGameClass.get_levelObjectsListByLevelId().get(i);
            currentLevelObject.draw();
        }
    }

    private void drawLevelAsteroids() {
        for (int i = 0; i < MainGameClass.get_asteroidsListByLevelId().size(); i++) {
            MainGameClass.get_asteroidsListByLevelId().get(i).draw();
        }
    }
}
