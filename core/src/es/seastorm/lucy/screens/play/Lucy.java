package es.seastorm.lucy.screens.play;

import dragongames.base.gameobject.SimpleGameObject;
import es.seastorm.lucy.assets.GameAssets;

public class Lucy extends SimpleGameObject {
    float deceleration = 1800;
    public Lucy(){
        super(GameAssets.instance.getTextureRegion(GameAssets.ASSET_PLAY_LUCY));
        this.friction.y = 0;
        this.terminalVelocity.y = 1200;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        velocity.y -= deceleration * deltaTime;
        if (velocity.y < -terminalVelocity.y){
            velocity.y = -terminalVelocity.y;
        }
    }

    public void jump(){
        this.velocity.y = this.terminalVelocity.y;
    }
}
