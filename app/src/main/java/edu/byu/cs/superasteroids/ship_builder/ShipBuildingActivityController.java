package edu.byu.cs.superasteroids.ship_builder;

import java.util.ArrayList;
import java.util.List;
import edu.byu.cs.superasteroids.Model.MainGameClass;
import edu.byu.cs.superasteroids.Model.Ship;
import edu.byu.cs.superasteroids.base.IView;
import edu.byu.cs.superasteroids.content.ContentManager;
import edu.byu.cs.superasteroids.drawing.DrawingHelper;

/**
 * Created by raulbr on 2/21/16.
 */
public class ShipBuildingActivityController implements IShipBuildingController {
    private IShipBuildingView _shipBuildingView;
    private Ship _ship;
    private ContentManager _content;
    private List<Integer> _allImages;
    private boolean _cannonChosen;
    private boolean _engineChosen;
    private boolean _extraPartChosen;
    private boolean _mainBodyChosen;
    private boolean _powerCoreChosen;
    private PartSelectionFragment _viewStatus;

    private IShipBuildingView.PartSelectionView MAIN_BODY = IShipBuildingView.PartSelectionView.MAIN_BODY;
    private IShipBuildingView.PartSelectionView CANNON = IShipBuildingView.PartSelectionView.CANNON;
    private IShipBuildingView.PartSelectionView ENGINE = IShipBuildingView.PartSelectionView.ENGINE;
    private IShipBuildingView.PartSelectionView EXTRA_PART = IShipBuildingView.PartSelectionView.EXTRA_PART;
    private IShipBuildingView.PartSelectionView POWER_CORE = IShipBuildingView.PartSelectionView.POWER_CORE;

    private IShipBuildingView.ViewDirection RIGHT = IShipBuildingView.ViewDirection.RIGHT;
    private IShipBuildingView.ViewDirection DOWN = IShipBuildingView.ViewDirection.DOWN;
    private IShipBuildingView.ViewDirection LEFT = IShipBuildingView.ViewDirection.LEFT;
    private IShipBuildingView.ViewDirection UP = IShipBuildingView.ViewDirection.UP;

    public ShipBuildingActivityController(IShipBuildingView shipBuildingView) {
        _shipBuildingView = shipBuildingView;
        _ship = new Ship();
        _ship.set_directionInRadians(0.0d);
        //_content = new ContentManager();
        _allImages = new ArrayList<>();
        _cannonChosen = false;
        _engineChosen = false;
        _extraPartChosen = false;
        _mainBodyChosen = false;
        _powerCoreChosen = false;
        _viewStatus = new PartSelectionFragment();
        _viewStatus.setPartView(MAIN_BODY);
    }
    @Override
    public void onViewLoaded(IShipBuildingView.PartSelectionView partView) {
        shouldStartGameBtnBeVisible();
        switch (partView)
        {
            case MAIN_BODY:
                _shipBuildingView.setArrow(MAIN_BODY, RIGHT, true, "Engine");
                _shipBuildingView.setArrow(MAIN_BODY, LEFT, false, null);
                _shipBuildingView.setArrow(MAIN_BODY, UP, false, null);
                _shipBuildingView.setArrow(MAIN_BODY, DOWN, false, null);
                break;

            case ENGINE:
                _shipBuildingView.setArrow(ENGINE, RIGHT, true, "Cannon");
                _shipBuildingView.setArrow(ENGINE, LEFT, true, "Main Body");
                _shipBuildingView.setArrow(ENGINE, UP, false, null);
                _shipBuildingView.setArrow(ENGINE, DOWN, false, null);
                break;

            case CANNON:
                _shipBuildingView.setArrow(CANNON, RIGHT, true, "Extra Part");
                _shipBuildingView.setArrow(CANNON, LEFT, true, "Engine");
                _shipBuildingView.setArrow(CANNON, UP, false, null);
                _shipBuildingView.setArrow(CANNON, DOWN, false, null);
                break;

            case EXTRA_PART:
                _shipBuildingView.setArrow(EXTRA_PART, RIGHT, true, "Power Core");
                _shipBuildingView.setArrow(EXTRA_PART, LEFT, true, "Cannon");
                _shipBuildingView.setArrow(EXTRA_PART, UP, false, null);
                _shipBuildingView.setArrow(EXTRA_PART, DOWN, false, null);
                break;

            case POWER_CORE:
                _shipBuildingView.setArrow(POWER_CORE, RIGHT, true, "Main Body");
                _shipBuildingView.setArrow(POWER_CORE, LEFT, true, "Extra Part");
                _shipBuildingView.setArrow(POWER_CORE, UP, false, null);
                _shipBuildingView.setArrow(POWER_CORE, DOWN, false, null);
                break;

            default:
        }
    }

    @Override
    public void update(double elapsedTime) {

    }

    @Override
    public void loadContent(ContentManager content) {
        loadContentHelper(MAIN_BODY, MainGameClass.get_asteroidGame().getMainBodiesPathList(), content);
        loadContentHelper(ENGINE, MainGameClass.get_asteroidGame().getEnginesPathList(), content);
        loadContentHelper(CANNON, MainGameClass.get_asteroidGame().getCannonsPathList(), content);
        loadContentHelper(EXTRA_PART, MainGameClass.get_asteroidGame().getExtraPartsPathList(), content);
        loadContentHelper(POWER_CORE, MainGameClass.get_asteroidGame().getPowerCoresPathList(), content);
        _content = content;
        MainGameClass.set_content(content);
    }

    private void loadContentHelper(IShipBuildingView.PartSelectionView partView, List<String> pathList,
                                  ContentManager content) {
        List<Integer> imgToLoadIdList = new ArrayList<>();
        for (int i = 0; i < pathList.size(); i++) {
            int imgId = content.loadImage(pathList.get(i));
            imgToLoadIdList.add(imgId);
            _allImages.add(imgId);
        }
        _shipBuildingView.setPartViewImageList(partView, imgToLoadIdList);
    }

    @Override
    public void unloadContent(ContentManager content) {
        for (int i = 0; i < _allImages.size(); i++) {
            content.unloadImage(_allImages.get(i));
            MainGameClass.get_content().unloadImage(_allImages.get(i));
        }
    }

    @Override
    public void draw() {
        _ship.set_location(DrawingHelper.getGameViewWidth() / 2.0f, DrawingHelper.getGameViewHeight() / 2.0f);
        _ship.draw();
    }

    @Override
    public void onSlideView(IShipBuildingView.ViewDirection direction) {
        shouldStartGameBtnBeVisible();
        switch (_viewStatus.getPartView())
        {
            case MAIN_BODY:
                if (direction == LEFT)
                {
                    _shipBuildingView.animateToView(ENGINE, RIGHT);
                    _viewStatus.setPartView(ENGINE);
                }
                break;

            case ENGINE:
                if (direction == LEFT)
                {
                    _shipBuildingView.animateToView(CANNON, RIGHT);
                    _viewStatus.setPartView(CANNON);
                } else
                {
                    _shipBuildingView.animateToView(MAIN_BODY, LEFT);
                    _viewStatus.setPartView(MAIN_BODY);
                }
                break;

            case CANNON:
                if (direction == LEFT)
                {
                    _shipBuildingView.animateToView(EXTRA_PART, RIGHT);
                    _viewStatus.setPartView(EXTRA_PART);
                } else
                {
                    _shipBuildingView.animateToView(ENGINE, LEFT);
                    _viewStatus.setPartView(ENGINE);
                }
                break;

            case EXTRA_PART:
                if (direction == LEFT)
                {
                    _shipBuildingView.animateToView(POWER_CORE, RIGHT);
                    _viewStatus.setPartView(POWER_CORE);
                } else
                {
                    _shipBuildingView.animateToView(CANNON, LEFT);
                    _viewStatus.setPartView(CANNON);
                }
                break;

            case POWER_CORE:
                if (direction == LEFT)
                {
                    _shipBuildingView.animateToView(MAIN_BODY, RIGHT);
                    _viewStatus.setPartView(MAIN_BODY);
                } else
                {
                    _shipBuildingView.animateToView(EXTRA_PART, LEFT);
                    _viewStatus.setPartView(EXTRA_PART);
                }
                break;
        }
    }

    @Override
    public void onPartSelected(int index) {
        if(_viewStatus.getPartView() == MAIN_BODY)
        {
            _ship.setMainBodyShip(MainGameClass.get_asteroidGame().get_mainBodyShipList().get(index));
            _mainBodyChosen = true;
        }
        else if (_viewStatus.getPartView() == ENGINE)
        {
            _ship.setEngine(MainGameClass.get_asteroidGame().get_engineList().get(index));
            _engineChosen = true;
        }
        else if (_viewStatus.getPartView() == CANNON)
        {
            _ship.setCannon(MainGameClass.get_asteroidGame().get_cannonList().get(index));
            _cannonChosen = true;
            //Set projectile
            //Projectile projectile = new Projectile(_ship.getCannon());
            //_ship.setProjectile(projectile);
        }
        else if (_viewStatus.getPartView() == EXTRA_PART)
        {
            _ship.setExtraParts(MainGameClass.get_asteroidGame().get_extraPartList().get(index));
            _extraPartChosen = true;
        }
        else if (_viewStatus.getPartView() == POWER_CORE)
        {
            _ship.setPowerCore(MainGameClass.get_asteroidGame().get_powerCoreList().get(index));
            _powerCoreChosen = true;
        }
        shouldStartGameBtnBeVisible();
    }

    @Override
    public void onStartGamePressed() {
        MainGameClass.get_asteroidGame().set_ship(_ship);
        _shipBuildingView.startGame();
    }

    @Override
    public void onResume() {

    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }

    private void shouldStartGameBtnBeVisible() {
        if(!_mainBodyChosen)
        {
            _shipBuildingView.setStartGameButton(false);
            return;
        }
        if (!_engineChosen)
        {
            _shipBuildingView.setStartGameButton(false);
            return;
        }
        if (!_cannonChosen)
        {
            _shipBuildingView.setStartGameButton(false);
            return;
        }
        if (!_extraPartChosen)
        {
            _shipBuildingView.setStartGameButton(false);
            return;
        }
        if (!_powerCoreChosen)
        {
            _shipBuildingView.setStartGameButton(false);
            return;
        }
        _shipBuildingView.setStartGameButton(true);
    }
}
