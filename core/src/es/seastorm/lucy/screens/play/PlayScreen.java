package es.seastorm.lucy.screens.play;

import com.badlogic.gdx.Game;

import dragongames.base.screen.AbstractGameScreen;
import es.seastorm.lucy.Constants;
import es.seastorm.lucy.screens.menu.MenuScreen;

public class PlayScreen extends AbstractGameScreen {
    private static final String TAG = MenuScreen.class.getName();
    public static final String[] THEMES = {"forest", "winter", "desert", "alien"};

    private String theme;
    private boolean platformsMobiles;
    private boolean platformsCrumble;
    private long maxHeight;
    private int level;


    public PlayScreen(Game game) {
        super(game);
    }

    public void setLevelSettings(String theme, boolean platformsMobiles, boolean platformsCrumble, long maxHeight, int level){
        this.platformsMobiles = platformsMobiles;
        this.platformsCrumble = platformsCrumble;
        this.maxHeight = maxHeight;
        this.theme = theme;
        this.level = level;
    }



    @Override
    public void show() {
        getController();
        getRenderer();
        reset();
        super.show(controller, renderer);
    }

    public PlayRenderer getRenderer(){
        if (renderer == null) {
            renderer = new PlayRenderer(controller, Constants.WIDTH, Constants.HEIGHT);
        }
        return ((PlayRenderer)renderer);
    }

    public PlayController getController(){
        if (controller == null){
            controller = new PlayController(game, this);
        }
        return ((PlayController)controller);
    }

    public void reset(){
        getRenderer().reset();
        getController().reset(theme, platformsMobiles, platformsCrumble, maxHeight, level);
    }
}
