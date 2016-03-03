package es.seastorm.lucy.screens.play;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

import dragongames.base.controller.Controller;
import dragongames.base.gameobject.AbstractGameObject;
import dragongames.base.gameobject.SimpleGameObject;
import es.seastorm.lucy.Constants;
import es.seastorm.lucy.LucyGame;
import es.seastorm.lucy.assets.GameAssets;

public class PlayController extends Controller {
    private int MAX_PLATFORM_SEPARATION = 400;
    private int platformSeparation;
    public Lucy lucy;
    public float floor = 0;
    private String theme;
    private long maxHeight;
    private PlayScreen screen;
    private long lastPlatformsLevel;
    private boolean started = false;
    private boolean walls = false;
    private TextureRegion assetPlatform;
    private AbstractGameObject[] bgSpace;
    private AbstractGameObject victory;

    public long score;

    private Sound jumpSound;

    private boolean platformsMobiles = true;
    private boolean platformsCrumble = false;

    private boolean ending = false;
    private float endingTime = 0;
    private int level;
    private boolean winner;
    private boolean moveWin;



    Array<Platform> platforms;

    Random random = new Random();

    public void reset(String theme, boolean platformsMobiles, boolean platformsCrumble, long maxHeight, int level){
        started = false;
        ending = false;
        score = 0;
        winner = false;
        moveWin = false;
        this.platformsMobiles = platformsMobiles;
        this.platformsCrumble = platformsCrumble;
        this.maxHeight = maxHeight;
        this.theme = theme;
        this.level = level;
        if ("forest".equals(theme)) {
            assetPlatform = GameAssets.instance.getTextureRegion(GameAssets.ASSET_PLATFORM_FOREST);
        } else if ("winter".equals(theme)) {
            assetPlatform = GameAssets.instance.getTextureRegion(GameAssets.ASSET_PLATFORM_WINTER);
        } else if ("desert".equals(theme)) {
            assetPlatform = GameAssets.instance.getTextureRegion(GameAssets.ASSET_PLATFORM_DESERT);
        } else {
            assetPlatform = GameAssets.instance.getTextureRegion(GameAssets.ASSET_PLATFORM_ALIEN);
        }
        createGameObjects();
        if (((LucyGame)game).music) {
            playMusic("sound/happy.mp3", 0.2f);
        }
        started = true;
    }

    public PlayController(Game game, PlayScreen screen) {
        super(game, Constants.WIDTH, Constants.HEIGHT);
        this.screen = screen;
        this.started = false;
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK){
            exitPlay();
        }
        return false;
    }

    private void createGameObjects() {
        //Sound
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("sound/jump.wav"));


        //Background
        AbstractGameObject background = new SimpleGameObject("play/bg_" + theme + ".jpg");
        addGameObject(background);

        bgSpace = new AbstractGameObject[2];

        bgSpace[0] = new SimpleGameObject("play/bg_space.jpg");
        bgSpace[1] = new SimpleGameObject("play/bg_space.jpg");

        bgSpace[0].position.y = background.dimension.y;
        bgSpace[1].position.y = bgSpace[0].position.y + bgSpace[0].dimension.y;

        addGameObject(bgSpace[0]);
        addGameObject(bgSpace[1]);

        platforms = new Array<Platform>();



        lucy = new Lucy();
        lucy.position.x = posMiddleX(lucy);
        lucy.position.y = 0;

        if (maxHeight > -1) {
            // Create win platform and ufo decoration

            SimpleGameObject ufo = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_PLAY_UFO));
            ufo.position.x = posMiddleX(ufo);
            ufo.position.y = maxHeight;
            addGameObject(ufo);


            Platform platform = new Platform(assetPlatform);
            platform.win = true;
            platform.mobile = false;
            platform.position.x = posMiddleX(platform);
            platform.position.y = maxHeight;
            addGameObject(platform);
            platforms.add(platform);

        }





        Platform p = createPlatform(70, 200);
        p.setMobile(false);
        p = createPlatform(520, 300);
        p.setMobile(false);

        lastPlatformsLevel = 450;
        platformSeparation = 100;
        floor = 0;

        createPlatforms();


        if (level > 0){
            if (level == 1) {
                victory = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_VICTORY1));
            } else if (level == 2) {
                victory = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_VICTORY2));
            } else if (level == 3) {
                victory = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_VICTORY3));
            } else if (level == 4) {
                victory = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_VICTORY4));
            }
            victory.position.y = 0;
            victory.position.x = posMiddleX(victory);
            victory.visible = false;
            addGameObject(victory);
        }



    }

    private void createPlatforms(){
        long max = maxHeight;
        if (max < 0){
            max = Long.MAX_VALUE;
        }
        while ((lastPlatformsLevel - floor < Constants.HEIGHT) && (lastPlatformsLevel + MAX_PLATFORM_SEPARATION < max)){
            createPlatform();
        }
    }


    private void createPlatform() {
        int x = random.nextInt((int) (Constants.WIDTH - 150));
        long y = lastPlatformsLevel + platformSeparation;
        if (platformSeparation < MAX_PLATFORM_SEPARATION) {
            platformSeparation += 5;
        }
        createPlatform(x, y);
    }


    private Platform createPlatform(int x, long y) {
        Platform platform = recycleOrCreatePlatform();
        platform.position.x = x;
        platform.position.y = y;
        lastPlatformsLevel = y;
        return platform;

    }

    private Platform recycleOrCreatePlatform(){
        for (int i=0; i<platforms.size;i++){
            if ((platforms.get(i)).position.y == -1000){
                if (platformsMobiles) {
                    platforms.get(i).setMobile(random.nextBoolean());
                }
                return platforms.get(i);
            }
        }
        Platform platform = new Platform(assetPlatform);
        if (platformsMobiles) {
            platform.setMobile(random.nextBoolean());
        }
        addGameObject(platform);
        platforms.add(platform);
        return platform;

    }

    @Override
    protected void init() {

    }

    public void update(float deltaTime) {
        if (started) {
            userControls(deltaTime);
            lucy.update(deltaTime);
            updatePlatforms(deltaTime);
            collisions();
            checkMoveUp();
            checkFail();
        } else if (ending){
            endingTime += deltaTime;
            if (endingTime > 2 && ! winner){
                exitPlay();
            }
            if (moveWin){
                if (maxHeight - floor >=50) {
                    goUp(600*deltaTime);
                } else{
                    win();
                }
            }
        }
    }

    private void updatePlatforms(float deltaTime){
        for (int i=0; i<platforms.size;i++){
            platforms.get(i).update(deltaTime);
        }
    }

    public void touch(float x, float y) {
        if (ending){
            exitPlay();
        }
    }

    private void userControls(float deltaTime) {
        float roll = Gdx.input.getRoll();
        if (roll < -10) {
            lucy.flip = true;
        }
        if (roll > 10){
            lucy.flip = false;
        }

        lucy.position.x += 20 * roll * deltaTime;

        if (walls){
            if (lucy.position.x < 0) {
                lucy.position.x = 0;
            }
            if (lucy.position.x + lucy.dimension.x > Constants.WIDTH) {
                lucy.position.x = Constants.WIDTH - lucy.dimension.x;
            }

        } else {
            if (lucy.position.x < -lucy.dimension.x) {
                lucy.position.x = Constants.WIDTH;
            }
            if (lucy.position.x > Constants.WIDTH) {
                lucy.position.x = -lucy.dimension.x;
            }
        }


    }

    private void jump(){
        lucy.jump();

        if (((LucyGame)game).sound) {
            //Play a preloaded sound
            playSound(jumpSound, 1);
        }
    }



    private void collisions() {
        if (lucy.position.y <= 0) {
            lucy.position.y = 0;
            jump();
        } else {
            if (lucy.velocity.y < -300) {
                for (int i = 0; i < platforms.size; i++) {
                    Platform platform = platforms.get(i);
                    float y = platform.position.y + platform.dimension.y;
                    if ((lucy.position.x + lucy.dimension.x >= platform.position.x) &&
                            (lucy.position.x <= platform.position.x + platform.dimension.x) &&
                            (lucy.position.y <= y) &&
                            (y - lucy.position.y < 40)){
                        lucy.position.y = y;

                        if (platform.win){
                            prepareWin();
                        } else {
                            jump();
                            if (platformsCrumble && !platform.mobile) {
                                removePlatform(platform);
                            }
                        }
                    }
                }
            }
        }
    }

    private void removePlatform(Platform platform){
        platform.position.y = -1000;
        platform.setMobile(false);
    }


    private void checkMoveUp(){
        if (lucy.position.y - floor > Constants.HEIGHT / 2) {
            float inc = lucy.position.y - floor - (Constants.HEIGHT / 2);
            goUp(inc);
            // Remove and reuse platforms
            for (int i=0; i<platforms.size;i++){
                if ((platforms.get(i)).position.y < floor){
                    removePlatform(platforms.get(i));

                }
            }
            createPlatforms();

        }
    }

    private void goUp(float inc){
        //Move camera
        screen.getRenderer().moveCameraUp(inc);
        floor += inc;
        score += Math.round(inc);
        moveSpaceBG();
    }

    private void checkFail(){
        if (((LucyGame)game).easy) {
            if (lucy.position.y < floor) {
                Gdx.input.vibrate(200);
                jump();
            }
        } else {
            if (lucy.position.y + lucy.dimension.y < floor) {
                Gdx.input.vibrate(200);
                finish();
            }
        }
    }

    private void finish(){
        started = false;
        ending = true;
        endingTime = 0;
    }

    private void exitPlay(){
        stopMusic();
        if (level > 0) {
            ((LucyGame) game).loadHistoryScreen();
        } else {
            ((LucyGame) game).loadMenuScreen();
        }
    }

    private void prepareWin(){
        moveWin = true;
        winner = true;
        finish();
    }


    private void win(){
        moveWin = false;
        victory.position.y = floor + Constants.HEIGHT - victory.dimension.y - 100;

        victory.visible = true;
        ((LucyGame)game).setMaxLevel(level + 1);

    }

    private void moveSpaceBG(){
        if (bgSpace[0].position.y + bgSpace[0].dimension.y < floor) {
            bgSpace[0].position.y = bgSpace[1].position.y + bgSpace[1].dimension.y;
        }

        if (bgSpace[1].position.y + bgSpace[1].dimension.y < floor) {
            bgSpace[1].position.y = bgSpace[0].position.y + bgSpace[0].dimension.y;
        }
    }

}
