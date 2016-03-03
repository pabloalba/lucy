package es.seastorm.lucy.screens.history;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import dragongames.base.controller.Controller;
import dragongames.base.gameobject.AbstractGameObject;
import dragongames.base.gameobject.SimpleGameObject;
import es.seastorm.lucy.Constants;
import es.seastorm.lucy.LucyGame;
import es.seastorm.lucy.assets.GameAssets;
import es.seastorm.lucy.cache.Cache;
import es.seastorm.lucy.gameobjects.SimpleTextGameObject;

public class MenuHistoryController extends Controller {
    public AbstractGameObject btnOne, btnTwo, btnThree, btnFour, btnLock, dialog;
    public AbstractGameObject dad, mom, lucy, sister1, sister2, alien, ufo, skip;
    int mode = 0;
    int story;
    int storyIndex;
    float lastTouch;
    SimpleTextGameObject text;

    String[][][] storyText = {
            {
                    {"", "Once Upon a time", ""},
                    {"", "a family went on a picnic", ""},
                    {"dad", "This is a good place", ""},
                    {"mom", "C'mon, take out the food", ""},
                    {"lucy", "I'll go to the fountain", ""},
                    {"sister1", "I get the sandwiches", ""},
                    {"sister2", "I don't want to eat! Let's play!", ""},
                    {"", "When suddenly,", ""},
                    {"", "while Lucy was at the fountain...", ""},
                    {"alien", "MWUHAHAHAHAHAHA!", ""},
                    {"alien", "I'll take this humans!", ""},
                    {"", "The aliens abduct Lucy's family!", "ufo"},
                    {"lucy", "Hey, ugly!", "ufo"},
                    {"lucy", "Leave my family alone!", "ufo"},
                    {"lucy", "I'll rescue you!", "ufo"}
            },
            {
                    {"sister1", "Thank you for rescue me!", ""},
                    {"sister1", "I was so afraid!", ""},
                    {"sister1", "You need to save the others!", ""},
                    {"sister1", "Every one is in a different UFO", ""},
                    {"lucy", "Emma, I'll rescue you!", ""},
            },
            {
                    {"sister2", "How fun!", ""},
                    {"sister2", "I want to go up again!", ""},
                    {"lucy", "...", ""},
                    {"sister2", "The aliens are funny!", ""},
                    {"lucy", "Emma, wait here", ""},
                    {"lucy", "I'll go to rescue mum!", ""},
            },
            {
                    {"mom", "Lucy, you are the best!", ""},
                    {"mom", "You rescued us!", ""},
                    {"lucy", "Let's save dad!", ""},
            }

    };

    public MenuHistoryController(Game game) {
        super(game, Constants.WIDTH, Constants.HEIGHT);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.BACK){
            ((LucyGame) game).loadMenuScreen();
        }
        return false;
    }

    private void createGameObjects() {


        //Background
        addGameObject(Cache.backgroundMenu);


        btnOne = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_ONE));
        btnOne.position.x = posMiddleX(btnOne) / 2;
        btnOne.position.y = 3 * posMiddleY(btnOne) / 2;
        addGameObject(btnOne);

        int maxLevel = ((LucyGame)game).maxLevel;

        if (maxLevel > 1) {
            btnTwo = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_TWO));
        } else {
            btnTwo = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_LOCK));
        }
        btnTwo.position.x = 3 * posMiddleX(btnTwo) / 2;
        btnTwo.position.y = 3 * posMiddleY(btnTwo) / 2;
        addGameObject(btnTwo);


        if (maxLevel > 2) {
            btnThree = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_THREE));
        } else {
            btnThree = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_LOCK));
        }

        btnThree.position.x = posMiddleX(btnThree) / 2;
        btnThree.position.y = posMiddleY(btnThree) / 2;
        addGameObject(btnThree);


        if (maxLevel > 3) {
            btnFour = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_FOUR));
        } else {
            btnFour = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_BTN_LOCK));
        }

        btnFour.position.x = 3 * posMiddleX(btnFour) / 2;
        btnFour.position.y = posMiddleY(btnFour) / 2;
        addGameObject(btnFour);

        
        dad = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_DAD));
        dad.position.x = posMiddleX(dad);
        dad.position.y = 170;
        dad.visible = false;
        addGameObject(dad);

        mom = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_MOM));
        mom.position.x = posMiddleX(mom);
        mom.position.y = 170;
        mom.visible = false;
        addGameObject(mom);

        lucy = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_LUCY));
        lucy.position.x = posMiddleX(lucy);
        lucy.position.y = 170;
        lucy.visible = false;
        addGameObject(lucy);

        sister1 = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_SISTER1));
        sister1.position.x = posMiddleX(sister1);
        sister1.position.y = 170;
        sister1.visible = false;
        addGameObject(sister1);

        sister2 = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_SISTER2));
        sister2.position.x = posMiddleX(sister2);
        sister2.position.y = 170;
        sister2.visible = false;
        addGameObject(sister2);

        alien = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_ALIEN));
        alien.position.x = posMiddleX(alien);
        alien.position.y = 170;
        alien.visible = false;
        alien.velocity.x = 400;
        alien.velocity.y = 150;
        addGameObject(alien);


        ufo = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_UFO));
        ufo.position.x = posMiddleX(ufo);
        ufo.position.y = 700;
        ufo.visible = false;
        addGameObject(ufo);



        skip = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_SKIP));
        skip.position.x = Constants.WIDTH - skip.dimension.x - 30;
        skip.position.y = Constants.HEIGHT - skip.dimension.y - 30;
        skip.visible = false;
        addGameObject(skip);



        dialog = new SimpleGameObject(GameAssets.instance.getTextureRegion(GameAssets.ASSET_DIALOG));
        dialog.position.x = 0;
        dialog.position.y = 0;
        dialog.visible = false;
        addGameObject(dialog);




        // Text
        text = new SimpleTextGameObject(Cache.font);
        text.position.x = 0;
        text.position.y = 115;
        addGameObject(text);



    }


    @Override
    protected void init() {
        createGameObjects();
    }

    public void update(float deltaTime){
        lastTouch += deltaTime;
        moveAlien(deltaTime);
    }

    private void moveAlien(float deltaTime){
        alien.position.x += alien.velocity.x * deltaTime;
        alien.position.y += alien.velocity.y * deltaTime;


        if (alien.position.x <=0){
            alien.position.x = 0;
            alien.velocity.x *= -1;
        } else if (alien.position.x + alien.dimension.x>=Constants.WIDTH){
            alien.position.x = Constants.WIDTH - alien.dimension.x;
            alien.velocity.x *= -1;
        }

        if (alien.position.y <=0){
            alien.position.y = 0;
            alien.velocity.y *= -1;
        } else if (alien.position.y + alien.dimension.y>=Constants.HEIGHT){
            alien.position.y = Constants.HEIGHT - alien.dimension.y;
            alien.velocity.y *= -1;
        }

    }

    public void touch(float x, float y){
        Array<AbstractGameObject> list = touchedGameObjects(x, y);
        for (AbstractGameObject object : list) {
            if (mode ==0) {
                //If the player touch the button
                if (object.equals(btnOne)) {
                    loadStory(0);
                    break;
                } else if (object.equals(btnTwo)) {
                    loadStory(1);
                    break;
                } else if (object.equals(btnThree)) {
                    loadStory(2);
                    break;
                } else if (object.equals(btnFour)) {
                    loadStory(3);
                    break;
                }
            } else {
                if (lastTouch > 0.5) {
                    if (object.equals(skip)){
                        skipStory();
                    } else if (object.equals(dialog)){
                        lastTouch = 0;
                        advanceStory();
                    }
                }
            }
        }
    }

    private void loadStory(int story){
        if (((LucyGame)game).maxLevel > story) {
            lastTouch = 0;
            mode = 1;
            storyIndex = 0;
            this.story = story;
            btnOne.visible = false;
            btnTwo.visible = false;
            btnThree.visible = false;
            btnFour.visible = false;
            dialog.visible = true;
            skip.visible = true;


            showText();
        }

    }

    private void showText(){
        dad.visible = false;
        mom.visible = false;
        lucy.visible = false;
        sister1.visible = false;
        sister2.visible = false;
        alien.visible = false;

        if ("dad".equals(storyText[story][storyIndex][0])){
            dad.visible = true;
        }else if ("mom".equals(storyText[story][storyIndex][0])){
            mom.visible = true;
        }else if ("lucy".equals(storyText[story][storyIndex][0])){
            lucy.visible = true;
        }else if ("sister1".equals(storyText[story][storyIndex][0])){
            sister1.visible = true;
        }else if ("sister2".equals(storyText[story][storyIndex][0])){
            sister2.visible = true;
        }else if ("alien".equals(storyText[story][storyIndex][0])){
            alien.visible = true;
        }

        ufo.visible = "ufo".equals(storyText[story][storyIndex][2]);





        String txt = storyText[story][storyIndex][1];
        text.position.x = (Constants.WIDTH - txt.length() * 20) / 2;
        text.setText(txt);
    }

    private void advanceStory(){
        if (storyIndex +1 == storyText[story].length){
            skipStory();
        } else {
            storyIndex++;
            showText();
        }
    }

    private void skipStory(){
        ((LucyGame) game).loadPlayScreen(story+1);
    }




}

