package es.seastorm.lucy.screens.play;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Iterator;

import dragongames.base.controller.Controller;
import dragongames.base.gameobject.AbstractGameObject;
import dragongames.base.renderer.Renderer;
import es.seastorm.lucy.Constants;
import es.seastorm.lucy.cache.Cache;


public class PlayRenderer extends Renderer{
    private OrthographicCamera cameraGUI;

    public PlayRenderer(Controller controller, float width, float height) {
        super(controller, width, height);

        cameraGUI = new OrthographicCamera(Constants.WIDTH, Constants.HEIGHT);
        cameraGUI.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);
        //cameraGUI.setToOrtho(true); // flip y-axis
        cameraGUI.update();
    }

    public void reset(){
        camera.position.y = 0;
    }

    public void moveCameraUp(float inc){
        camera.position.y += inc;
    }

    @Override
    public void render() {
        PlayController playController = (PlayController) controller;
        this.camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
        Iterator i$ = this.controller.gameObjects.iterator();

        while(i$.hasNext()) {
            AbstractGameObject gameObject = (AbstractGameObject)i$.next();
            gameObject.render(this.batch);
        }

        //Lucy the last
        playController.lucy.render(this.batch);

        this.batch.end();

        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();

        Cache.font.draw(batch, String.format("%07d", playController.score), 25, Constants.HEIGHT - 25);
        batch.end();



    }

  /*  @Override
    public void render() {
        this.camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();

        if (this.controller.gameObjects.size > 0) {

            // First the backgrounds
            this.controller.gameObjects.get(0).render(this.batch);
            this.controller.gameObjects.get(1).render(this.batch);
            this.controller.gameObjects.get(2).render(this.batch);

            // Reverse order
            for (int i = this.controller.gameObjects.size - 1; i > 2; i--) {
                AbstractGameObject gameObject = this.controller.gameObjects.get(i);
                gameObject.render(this.batch);
            }
        }

        this.batch.end();
    }
*/





}
