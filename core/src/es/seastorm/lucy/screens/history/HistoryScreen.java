package es.seastorm.lucy.screens.history;

import com.badlogic.gdx.Game;

import dragongames.base.renderer.Renderer;
import dragongames.base.screen.AbstractGameScreen;
import es.seastorm.lucy.Constants;
import es.seastorm.lucy.assets.GameAssets;

public class HistoryScreen extends AbstractGameScreen {
    private static final String TAG = HistoryScreen.class.getName();

    public HistoryScreen(Game game) {
        super(game, GameAssets.instance);
    }

    @Override
    public void show() {
        controller = new MenuHistoryController(game);
        renderer = new Renderer(controller, Constants.WIDTH, Constants.HEIGHT);
        super.show(controller, renderer);
    }
}
