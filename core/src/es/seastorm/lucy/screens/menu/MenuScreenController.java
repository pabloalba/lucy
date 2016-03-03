package es.seastorm.lucy.screens.menu;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

import dragongames.base.controller.Controller;
import dragongames.base.gameobject.AbstractGameObject;
import dragongames.base.gameobject.SimpleGameObject;
import es.seastorm.lucy.Constants;
import es.seastorm.lucy.LucyGame;
import es.seastorm.lucy.assets.GameAssets;
import es.seastorm.lucy.cache.Cache;

public class MenuScreenController extends Controller {
    public AbstractGameObject btnHistory, btnInfinity, btnOptions;
    public AbstractGameObject btnMusic, btnSound, btnEasy, btnOk;
    public AbstractGameObject chkMusic, chkSound, chkEasy;
    AbstractGameObject options;
    private Preferences prefs;

    public MenuScreenController(Game game) {
        super(game, Constants.WIDTH, Constants.HEIGHT);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK){
            System.exit(0);
        }
        return false;
    }

    private void createGameObjects() {
        //Background
        addGameObject(Cache.backgroundMenu);

        btnHistory = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_HISTORY));
        btnHistory.position.x = posMiddleX(btnHistory);
        btnHistory.position.y = 3 * posMiddleY(btnHistory) / 2;
        addGameObject(btnHistory);

        btnInfinity = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_INFINITY));
        btnInfinity.position.x = posMiddleX(btnInfinity);
        btnInfinity.position.y = posMiddleY(btnInfinity);
        addGameObject(btnInfinity);

        btnOptions = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_OPTIONS));
        btnOptions.position.x = posMiddleX(btnOptions);
        btnOptions.position.y = posMiddleY(btnOptions) / 2;
        addGameObject(btnOptions);


        options = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_OPTIONS));
        options.position.x = posMiddleX(options);
        options.position.y = posMiddleY(options);
        options.visible = false;

        addGameObject(options);


        btnMusic = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_MUSIC));
        btnMusic.position.x = 200;
        btnMusic.position.y = 550;
        btnMusic.visible = false;
        addGameObject(btnMusic);

        btnSound = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_SOUND));
        btnSound.position.x = 200;
        btnSound.position.y = 450;
        btnSound.visible = false;
        addGameObject(btnSound);

        btnEasy = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_EASY));
        btnEasy.position.x = 200;
        btnEasy.position.y = 350;
        btnEasy.visible = false;
        addGameObject(btnEasy);


        btnOk = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_OK));
        btnOk.position.x = posMiddleX(btnOk);
        btnOk.position.y = 225;
        btnOk.visible = false;
        addGameObject(btnOk);


        chkMusic = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_CHECK));
        chkMusic.position.x = 200;
        chkMusic.position.y = 570;
        chkMusic.visible = false;
        addGameObject(chkMusic);

        chkSound = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_CHECK));
        chkSound.position.x = 200;
        chkSound.position.y = 470;
        chkSound.visible = false;
        addGameObject(chkSound);

        chkEasy = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_CHECK));
        chkEasy.position.x = 200;
        chkEasy.position.y = 370;
        chkEasy.visible = false;
        addGameObject(chkEasy);

    }


    @Override
    protected void init() {
        createGameObjects();
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
        loadPrefs();
    }

    public void update(float deltaTime){
        //Nothing
    }

    public void touch(float x, float y){
        Array<AbstractGameObject> list = touchedGameObjects(x, y);
        for (AbstractGameObject object : list) {
            if (options.visible) {
                if (object.equals(btnMusic)) {
                    toggleMusic();
                }
                if (object.equals(btnSound)) {
                    toggleSound();
                }
                if (object.equals(btnEasy)) {
                    toggleEasy();
                }
                if (object.equals(btnOk)) {
                    saveOptions();
                    break;
                }
            } else {
                //If the player touch the button
                if (object.equals(btnHistory)) {
                    startHistory();
                } else if (object.equals(btnInfinity)) {
                    startInfinity();
                } else if (object.equals(btnOptions)) {
                    toggleOptions(true);
                    break;
                }
            }
        }
    }


    private void startHistory(){
        ((LucyGame)game).loadHistoryScreen();
    }

    private void startInfinity(){


        ((LucyGame)game).loadPlayScreen(0);
    }

    private void toggleOptions(boolean visible){
        System.out.print(" toggle ");
        options.visible = visible;

        btnMusic.visible = visible;
        btnSound.visible = visible;
        btnEasy.visible = visible;
        btnOk.visible = visible;

        showChecks();
    }

    private void saveOptions(){
        toggleOptions(false);
        savePrefs();
    }
    
    private void toggleMusic(){
        ((LucyGame)game).music = ! ((LucyGame)game).music;
        showChecks();
    }

    private void toggleSound(){
        ((LucyGame)game).sound = ! ((LucyGame)game).sound;
        showChecks();
    }

    private void toggleEasy(){
        ((LucyGame)game).easy = ! ((LucyGame)game).easy;
        showChecks();
    }
    
    private void showChecks(){
        if (options.visible){
            LucyGame lucyGame = (LucyGame)game;
            chkMusic.visible = lucyGame.music;
            chkSound.visible = lucyGame.sound;
            chkEasy.visible = lucyGame.easy;
        } else {
            chkMusic.visible = false;
            chkSound.visible = false;
            chkEasy.visible = false;
        }
    }


    public void loadPrefs() {
        LucyGame lucyGame = (LucyGame)game;

        lucyGame.sound = prefs.getBoolean("sound", true);
        lucyGame.music = prefs.getBoolean("music", true);
        lucyGame.easy = prefs.getBoolean("easy", false);
        lucyGame.maxLevel = prefs.getInteger("level", 1);

    }

    public void savePrefs() {
        LucyGame lucyGame = (LucyGame)game;
        prefs.putBoolean("sound", lucyGame.sound);
        prefs.putBoolean("music", lucyGame.music);
        prefs.putBoolean("easy", lucyGame.easy);
        prefs.flush();
    }

}
