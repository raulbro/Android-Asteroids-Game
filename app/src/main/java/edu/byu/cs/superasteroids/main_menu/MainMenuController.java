package edu.byu.cs.superasteroids.main_menu;

import edu.byu.cs.superasteroids.Model.MainBodyShip;
import edu.byu.cs.superasteroids.Model.MainGameClass;
import edu.byu.cs.superasteroids.Model.PowerCore;
import edu.byu.cs.superasteroids.Model.Ship;
import edu.byu.cs.superasteroids.base.IView;

/**
 * Created by raulbr on 2/23/16.
 */
public class MainMenuController implements IMainMenuController {
    MainActivity _mainMenuView;

    public MainMenuController(MainActivity mainActivity)
    {
        _mainMenuView = mainActivity;
    }

    @Override
    public void onQuickPlayPressed() {
        Ship ship = new Ship();
        ship.create();
        MainGameClass.get_asteroidGame().set_ship(ship);
        _mainMenuView.startGame();
    }

    @Override
    public IView getView() {
        return null;
    }

    @Override
    public void setView(IView view) {

    }
}
