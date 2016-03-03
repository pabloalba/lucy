package es.seastorm.lucy.assets;

import com.badlogic.gdx.assets.AssetManager;

import dragongames.base.asset.AssetsKeeper;
import es.seastorm.lucy.Constants;

public class GameAssets extends AssetsKeeper {
    public static final String FILE_NAME = Constants.TEXTURE_ATLAS_OBJECTS;
    public static final String ASSET_PLAY_LUCY = "hero";
    public static final String ASSET_PLAY_UFO = "ufo2";
    public static final String ASSET_PLATFORM_DESERT = "platform_desert";
    public static final String ASSET_PLATFORM_FOREST = "platform_forest";
    public static final String ASSET_PLATFORM_WINTER = "platform_winter";
    public static final String ASSET_PLATFORM_ALIEN = "platform_alien";
    public static final String ASSET_VICTORY1 = "victory1";
    public static final String ASSET_VICTORY2 = "victory2";
    public static final String ASSET_VICTORY3 = "victory3";
    public static final String ASSET_VICTORY4 = "victory4";
    public static final String ASSET_BTN_HISTORY = "btn_history";
    public static final String ASSET_BTN_INFINITY = "btn_infinity";
    public static final String ASSET_BTN_OPTIONS = "btn_options";
    public static final String ASSET_OPTIONS = "options";
    public static final String ASSET_BTN_MUSIC = "btn_music";
    public static final String ASSET_BTN_SOUND = "btn_sound";
    public static final String ASSET_BTN_EASY = "btn_easy";
    public static final String ASSET_BTN_OK = "btn_ok";
    public static final String ASSET_CHECK = "check";
    public static final String ASSET_BTN_ONE = "btn_one";
    public static final String ASSET_BTN_TWO = "btn_two";
    public static final String ASSET_BTN_THREE = "btn_three";
    public static final String ASSET_BTN_FOUR = "btn_four";
    public static final String ASSET_BTN_LOCK = "btn_lock";
    public static final String ASSET_UFO = "ufo";
    public static final String ASSET_DAD = "dad";
    public static final String ASSET_MOM = "mom";
    public static final String ASSET_SISTER1 = "sister1";
    public static final String ASSET_SISTER2 = "sister2";
    public static final String ASSET_LUCY = "lucy";
    public static final String ASSET_ALIEN = "alien";
    public static final String ASSET_SKIP = "skip";
    public static final String ASSET_DIALOG = "dialog";
    public static final GameAssets instance = new GameAssets();



    // singleton: prevent instantiation from other classes
    private GameAssets() {
    }


    public void init(AssetManager assetManager){
        super.init(assetManager, FILE_NAME);
    }
}
