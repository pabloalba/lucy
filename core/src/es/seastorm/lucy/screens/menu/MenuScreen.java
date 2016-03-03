package es.seastorm.lucy.screens.menu;

import com.badlogic.gdx.Game;

import dragongames.base.renderer.Renderer;
import dragongames.base.screen.AbstractGameScreen;
import es.seastorm.lucy.Constants;
import es.seastorm.lucy.assets.GameAssets;

public class MenuScreen extends AbstractGameScreen {
    private static final String TAG = MenuScreen.class.getName();

    public MenuScreen(Game game) {
        super(game, GameAssets.instance);
    }

    @Override
    public void show() {
        controller = new MenuScreenController(game);
        renderer = new Renderer(controller, Constants.WIDTH, Constants.HEIGHT);
        super.show(controller, renderer);
    }
}
